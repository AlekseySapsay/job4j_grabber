package ru.job4j.grabber;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.model.Post;
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
     * @return
     */
    @Override
    public List<Post> list(String link) throws IOException {
        List<Post> postList = new ArrayList<>();
        Document document = Jsoup.connect(link).get();
        Elements row = document.select(".postslisttopic");

        for (Element td : row) {
            Element href = td.child(0);
            postList.add(detail(href.attr("href")));
        }
        return postList;
    }

    /**
     * {@inheritDoc}
     *
     * @param link ссылка на пост для парсинга
     * @return
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
        List<Post> postList = sqlRuParse.list("https://www.sql.ru/forum/job-offers/2");

        for (Post post : postList) {
            System.out.println(post.getTitle());
            System.out.println(post.getDescription());
            System.out.println(post.getLink());
            System.out.println(post.getCreated());
        }
    }
}
