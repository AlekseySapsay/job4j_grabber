package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * https://job4j.ru/profile/exercise/56/task-view/360
 * https://job4j.ru/profile/exercise/56/task-view/355
 * https://job4j.ru/profile/exercise/56/task-view/357
 * <p>
 * Парсинг html страницы средствами jsoup.
 * Добавить парсинг первых пяти страниц.
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 16.11.2021
 */

public class SqlRuParse implements Parse {
    private final DateTimeParser dateTimeParser;

    public SqlRuParse(DateTimeParser dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    /**
     * {@inheritDoc}
     *
     * @param link ссылка на форум для парсинга
     *             в виде https://www.sql.ru/forum/job-offers/
     *             парсим только первые 5 страниц форума
     * @return список постов после парсинга,
     * содержащих в названии " Java " или  " java ".
     */
    @Override
    public List<Post> list(String link) throws IOException {
        List<String> listKeyWords = new ArrayList<>();
        listKeyWords = List.of(" java ", " Java ");
        //System.out.println("String link " + link);


        List<String> listLinks = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            listLinks.add(link + i);
        }

        List<Post> postListRow = new ArrayList<>();

        for (String linkList : listLinks) {
            Document document = Jsoup.connect(linkList).get();
            Elements row = document.select(".postslisttopic");
            //System.out.println("row : " + row);

            for (Element td : row) {
                Element href = td.child(0);
                postListRow.add(detail(href.attr("href")));
            }
        }

        //remove element from postList if not contains " java " or " Java ".
        // first solution1
//        List<Post> postListClear = new ArrayList<>();
//        for (Post post : postListRow) {
//            if (post.getTitle().contains(" java ")
//                    || post.getTitle().contains(" Java ")
//                    || post.getTitle().contains("Java ")
//                    || post.getTitle().contains("java ")) {
//                postListClear.add(post);
//            }
//        }

        //remove element from postList if not contains " java " or " Java ".
        // second solution2
        //List<Post> postListClear2 = new ArrayList<>();
        postListRow.removeIf(
                x -> !x.getTitle().contains(" java ")
                        && !x.getTitle().contains("java ")
                        && !x.getTitle().contains(" Java ")
                        && !x.getTitle().contains("Java "));
//        for (Post post : postListRow) {
//            if (post.getTitle().contains(" java ")
//                    || post.getTitle().contains(" Java ")
//                    || post.getTitle().contains("Java ")
//                    || post.getTitle().contains("java ")) {
//                postListClear.add(post);
//            }
//        }


        // получаем html отдельной странички по link
        //System.out.println("document : " + document);


//        DateTimeParser dateTimeParser = new SqlRuDateTimeParser();
//        SqlRuParse sqlRuParse = new SqlRuParse(dateTimeParser);
        //List<Post> postList = new ArrayList<>();

        //условие для фильтрации по ключевым словам.
//        if (document.select(".postslisttopic").contains(" Java ")
//                || document.select(".postslisttopic").contains(" java ")) {
//            Elements row = document.select(".postslisttopic");
//
//            for (Element td : row) {
//                Element href = td.child(0);
//                postList.add(detail(href.attr("href")));
//            }
//        }


//        for (Post post : postList) {
//            System.out.println("post from postList" + post);
//        }


        //Elements row = document.select(".postslisttopic").contains(" Java ", " java ");

        return postListRow;
    }

    /**
     * {@inheritDoc}
     *
     * @param link ссылка на пост для парсинга
     * @return возвращаем детали поста Post
     */
    @Override
    public Post detail(String link) throws IOException {
        Post post = new Post();
        Document document = Jsoup.connect(link).get();
        String text = document.select(".msgBody").get(1).text();
        String footer = document.select(".msgFooter").get(0).text();
        String header = document.select(".messageHeader").get(0).text();
        LocalDateTime created = dateTimeParser.parse(footer
                .substring(0, footer.indexOf(":") + 2));

        post.setLink(link);
        post.setTitle(header);
        post.setDescription(text);
        post.setCreated(created);
        return post;
    }

    public static void main(String[] args) throws IOException {
        DateTimeParser dateTimeParser = new SqlRuDateTimeParser();
        SqlRuParse sqlRuParse = new SqlRuParse(dateTimeParser);
        List<Post> postList = new ArrayList<>();
        postList = sqlRuParse.list("https://www.sql.ru/forum/job-offers/");


//        for (int i = 1; i < 6; i++) {
//            List<Post> postListBuffer =
//                    sqlRuParse.list("https://www.sql.ru/forum/job-offers/");
        //postList.addAll(postListBuffer);
        //       }

        for (Post post : postList) {
            System.out.println(post.getTitle());
            //System.out.println(post.getDescription());
            //System.out.println(post.getLink());
            //System.out.println(post.getCreated());
        }
        System.out.println("postList.size() :" + postList.size());
    }
}
