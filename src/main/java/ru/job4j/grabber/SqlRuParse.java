package ru.job4j.grabber;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.model.Post;
import ru.job4j.grabber.utils.DateTimeParser;
import ru.job4j.grabber.utils.PostLoader;
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
        //SqlRuParse sqlRuParse = new SqlRuParse(dateTimeParser);
        List<Post> postList = new ArrayList<>();
        Document document = Jsoup.connect(link).get();
        Elements row = document.select(".postlisttopic");

        for (Element td : row) {
            Element href = td.child(0);
            Element date = td.parent().child(5);

            SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
            LocalDateTime localDateTime = sqlRuDateTimeParser.parse(date.text());
            postList.add(new Post());
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
        Document document = Jsoup.connect(link).get();
        Post post = new Post();
        String text = document.select(".msgBody").get(1).text();
        String footer = document.select(".msgFooter").get(0).text();
        String header = document.select(".messageHeader").get(0).text();
        int indexOfDate = footer.indexOf(":");
//        SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
//        LocalDateTime created = sqlRuDateTimeParser
//                .parse(footer.substring(0, indexOfDate + 2));

        LocalDateTime created1 = dateTimeParser.parse(footer.substring(0, indexOfDate + 2));

        post.setLink(link);
        post.setTitle(header);
        post.setDescription(text);
        post.setCreated(created1);



//        SqlRuParse sqlRuParse = new SqlRuParse(dateTimeParser);
//        PostLoader postLoader = new PostLoader();

        return post;
    }

    /**
     * {@inheritDoc}
     *
     * @param parse
     * @return
     */
//    @Override
//    public LocalDateTime parse(String parse) {
//        return null;
//    }
}
