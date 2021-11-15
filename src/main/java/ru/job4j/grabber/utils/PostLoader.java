package ru.job4j.grabber.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import ru.job4j.Post;

import java.time.LocalDateTime;

/**
 * https://job4j.ru/profile/exercise/56/task-view/359
 * <p>
 * Парсинг html страницы средствами jsoup.
 * Преобразование даты из формата с сайта sql.ru
 * в дату понятную java. Загрузка описания и даты
 * создания поста.
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 15.11.2021
 */

public class PostLoader {
    public static Post postLoad(String url) {
        Post post = new Post();
        try {
            Document document = Jsoup.connect(url).get();
            String text = document.select(".msgBody").get(1).text();
            String footer = document.select(".msgFooter").get(0).text();
            String header = document.select(".messageHeader").get(0).text();
            int indexOfDate = footer.indexOf(":");
            SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
            LocalDateTime localDateTimeCreated = sqlRuDateTimeParser
                    .parse(footer.substring(0, indexOfDate + 2));

            post.setLink(url);
            post.setTitle(header);
            post.setDescription(text);
            post.setCreated(localDateTimeCreated);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return post;
    }

    public static void main(String[] args) {
        Post post = postLoad("https://www.sql.ru/forum/1325330/lidy-be-fe-senior"
                + "-cistemnye-analitiki-qa-i-devops-moskva-do-200t");
        System.out.println("post.getDescription() : " + post.getDescription());
        System.out.println("post.getCreated() : " + post.getCreated());
        System.out.println("post.getLink() : " + post.getLink());
        System.out.println("post.getTitle() : " + post.getTitle());
    }
}
