package zheye.server.controller;


import io.swagger.models.auth.In;
import org.springframework.data.annotation.Transient;
import zheye.server.entity.Book;

import zheye.server.entity.BookCollect;
import zheye.server.entity.BookComment;
import zheye.server.entity.User;
import zheye.server.service.BookCollectService;
import zheye.server.service.BookCommentService;
import zheye.server.service.BookService;

import org.springframework.web.bind.annotation.*;
import zheye.server.service.UserService;
import zheye.server.utils.Result;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * (Book)表控制层
 *
 * @author makejava
 * @since 2023-02-22 23:41:43
 */

@RestController
@RequestMapping("frontApi/book")
public class BookController {
    /**
     * 服务对象
     */
    @Resource
    private BookService bookService;
    @Resource
    private UserService userService;
    @Resource
    BookCollectService bookCollectService;
    @Resource
    BookCommentService bookCommentService;

    @PostMapping("/bookItem")
    public Result bookItem() {
        List<Book> books = bookService.getBooks();
        return Result.ok(books).message("获取书籍成功");
    }

    /*  @PostMapping("/bookByType")
      public Result bookByType() {
          List<Book> books=bookService.getBooksByType();
          return Result.ok(books).message("获取书籍成功");
      }*/
    @PostMapping("/getBookById")
    public Result getBookById(@RequestBody Long bookId) {
        Book book = bookService.getBookById(bookId);
        if (book != null) {

            return Result.ok(book).message("获取书籍成功");
        } else {
            return Result.error().message("获取书籍失败");
        }
    }

    @PostMapping("/getBookName")
    public Result getBookName(@RequestBody int bookId) {
        String bookName = bookService.getBookName(bookId);
        if (bookName != null) {

            return Result.ok(bookName).message("获取书籍名称成功");
        } else {
            return Result.error().message("获取书籍名称失败");
        }
    }

    @PostMapping("/getRecommendList")
    public Result getRecommendList(@RequestBody Long id) {
        List<User> users = userService.getBaseMapper().selectList(null);
        Map<Long, User> userMap = users.stream().collect(Collectors.toMap(User::getId, user -> user));
        //找到最近邻
        Map<Long, Double> distances = computeNearestNeighbor(id, users);
        List<Book> recommendationBooks = new ArrayList<>();
        Iterator<Double> distanceIterator = distances.values().iterator();
        Iterator<Long> distanceKeyIterator = distances.keySet().iterator();
        while (distanceIterator.hasNext() && distanceIterator.next() > 0.4) {
            Long nearest = distanceKeyIterator.next();
            System.out.println("最近邻的id -> " + nearest);
            //找到最近邻看过，但是我们没看过的书，计算推荐
            User neighborRatings = userMap.get(nearest);
            System.out.println("最近邻看过的书-> " + getBookList(neighborRatings));
            User userRatings = userMap.get(id);
            System.out.println("用户看过的书-> " + getBookList(userRatings));
            //根据自己和邻居的书计算推荐的书
            for (Book book : getBookList(neighborRatings)) {
                Map<Long, Book> bookMap = recommendationBooks.stream().collect(Collectors.toMap(Book::getId, book1 -> book1));
                if (find(userRatings, book.getId()) == null && bookMap.get(book.getId()) == null) {
                    recommendationBooks.add(bookService.getBookById(book.getId()));
                }
            }
        }
        recommendationBooks.sort(Comparator.comparing(Book::getScore).reversed());
        return Result.ok(recommendationBooks).message("获取推荐排行榜成功");
    }

    /**
     * 在给定用户id的情况下，计算其他用户和它的距离并排序
     */
    private Map<Long, Double> computeNearestNeighbor(Long id, List<User> users) {
        Map<Long, Double> distances = new TreeMap<>();
        List<Book> bookListU1 = setScore(userService.getUserInfo(id));
        System.out.println(setScore(userService.getUserInfo(id)));
        for (int i = 0; i < users.size(); i++) {
            User u2 = users.get(i);
            List<Book> bookListU2 = setScore(u2);
            System.out.println(distances);
            if (!u2.getId().equals(id)) {
                double distance = pearson_dis(bookListU1, bookListU2);
                distances.put(u2.getId(), distance);
            }
        }
        Map<Long, Double> sortedMap = new LinkedHashMap<>();
        distances.entrySet().stream().sorted(Map.Entry.<Long, Double>comparingByValue().reversed()).forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
        System.out.println("该用户与其他用户的皮尔森相关系数 -> " + sortedMap);
        return sortedMap;
    }

    private List<Book> setScore(User user) {
        List<Book> bookList = bookService.getBooks();
        for (Book book : bookList) {
            Book book1 = find(user, book.getId());
            if (book1 != null) {
                book.setScore(book1.getScore());
            } else {
                book.setScore((float) 0);
            }

        }
        return bookList;
    }

    /**
     * 计算2个打分序列间的pearson距离
     * 选择公式四进行计算
     */
    private double pearson_dis(List<Book> rating1, List<Book> rating2) {
        int n = rating1.size();
        List<Float> rating1ScoreCollect = rating1.stream().map(A -> A.getScore()).collect(Collectors.toList());
        List<Float> rating2ScoreCollect = rating2.stream().map(A -> A.getScore()).collect(Collectors.toList());

        double Ex = rating1ScoreCollect.stream().mapToDouble(x -> x).sum();
        double Ey = rating2ScoreCollect.stream().mapToDouble(y -> y).sum();
        double Ex2 = rating1ScoreCollect.stream().mapToDouble(x -> Math.pow(x, 2)).sum();
        double Ey2 = rating2ScoreCollect.stream().mapToDouble(y -> Math.pow(y, 2)).sum();
        double Exy = IntStream.range(0, n - 1).mapToDouble(i -> rating1ScoreCollect.get(i) * rating2ScoreCollect.get(i)).sum();
        double numerator = Exy - Ex * Ey / n;
        double denominator = Math.sqrt((Ex2 - Math.pow(Ex, 2) / n) * (Ey2 - Math.pow(Ey, 2) / n));
        if (denominator == 0) return 0.0;
        return numerator / denominator;
    }

    public List<Book> getBookList(User user) {
        List<Book> bookList = new ArrayList<>();
        List<BookComment> bookCommentList = bookCommentService.getCommentByUserId(Math.toIntExact(user.getId()));
        bookCommentList.sort(Comparator.comparing(BookComment::getBookId));
        float average = 0;//平均评分
        int flag = 0;
        for (int left = 0, right = 0; right < bookCommentList.size(); right++) {
            if (Objects.equals(bookCommentList.get(left).getBookId(), bookCommentList.get(right).getBookId())) {
                average += bookCommentList.get(right).getRate();
                if (bookCommentList.get(right).getRate() == null || bookCommentList.get(right).getRate() == 0) {
                    flag++;
                }
            } else {
                if (right - left - flag != 0) {
                    average = average / (right - left - flag);
                } else {
                    average = 0;
                }
                flag = 0;
                Book book = bookService.getBookById(bookCommentList.get(left).getBookId());
                book.setScore(average);
                bookList.add(book);
                left = right;
            }
        }
        return bookList;
    }

    public Book find(User user, Long bookId) {
        List<Book> bookList = getBookList(user);
        for (Book book : bookList) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    @PostMapping("/getBookByType")
    public Result getBookByType(@RequestBody int id) {
        List<Object> resList = new ArrayList<>();
        List<Book> bookList = bookService.getBookByType(id);
//        将书籍按照评分、收藏人数和评论条数综合排序，评分占比0.4，收藏人数和评论条数各占0.3
        Map<Double, Book> overallScoreMap = new TreeMap<>();
        for (Book book : bookList) {
            /*
             * 评论数在10条内，评分1，评论数在10-50条内，评分2,评论数在50-100条内，评分3,评论数在100-200条内，评分4,评论数在200条以上，评分5
             * 评分1-5，
             * 收藏人数在10人内，评分1，收藏人数在10-50内，评分2,收藏人数在50-100内，评分3,收藏人数在100-200人，评分4,收藏人数在200以上，评分5
             * */
            int comments = bookCommentService.selectCommentByBookId(book.getId()).size();
            int collects = bookCollectService.selectCollectByBookId(book.getId()).size();
            double commentScore = getCommentScore(comments);
            double collectScore = getCollectScore(collects);
            double overallScore = book.getScore() * 0.4 + commentScore * 0.3 + collectScore * 0.3;
            overallScoreMap.put(overallScore, book);
        }
        TreeMap<Double, Book> reversedOverallScoreMap = new TreeMap<>(Comparator.reverseOrder());
        reversedOverallScoreMap.putAll(overallScoreMap); // 复制并反转键值对
        bookList = new ArrayList<>(reversedOverallScoreMap.values());
        System.out.println("综合排序：" + reversedOverallScoreMap.values());
        if (bookList.size() == 0) {
            return Result.error().message("该类书籍为空");
        } else {
            return Result.ok(bookList).message("获取该类别书籍成功");
        }
    }

    private double getCollectScore(int collects) {
//        收藏人数在10人内，评分1，收藏人数在10-50内，评分2,收藏人数在50-100内，评分3,收藏人数在100-200人，评分4,收藏人数在200以上，评分5
        return getScore(collects);
    }

    private double getCommentScore(int comments) {
//评论数在10条内，评分1，评论数在10-50条内，评分2,评论数在50-100条内，评分3,评论数在100-200条内，评分4,评论数在200条以上，评分5
        return getScore(comments);
    }

    private double getScore(int comments) {
        if (comments < 10) {
            return 1.;
        } else if (comments < 50) {
            return 2.;
        } else if (comments < 100) {
            return 3.;
        } else if (comments < 200) {
            return 4.;
        } else {
            return 5.;
        }
    }
}

