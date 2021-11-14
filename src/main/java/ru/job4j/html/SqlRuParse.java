package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ru.job4j.grabber.utils.SqlRuDateTimeParser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * https://job4j.ru/profile/exercise/56/task-view/355
 * https://job4j.ru/profile/exercise/56/task-view/357
 * <p>
 * Парсинг html страницы средствами jsoup.
 * Добавить парсинг первых пяти страниц.
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 13.11.2021
 */

public class SqlRuParse {
    public static void main(String[] args) throws IOException {
        for (int i = 1; i < 6; i++) {
            Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers" + "/" + i).get();
            Elements row = doc.select(".postslisttopic");

            for (Element td : row) {
                Element href = td.child(0);
                System.out.println(href.attr("href"));
                System.out.println(href.text());
                Element date = td.parent().child(5);

                SqlRuDateTimeParser sqlRuDateTimeParser = new SqlRuDateTimeParser();
                LocalDateTime localDateTime;
                localDateTime = sqlRuDateTimeParser.parse(date.text());

                System.out.println(localDateTime.
                        format(DateTimeFormatter
                                .ofPattern("dd MMM yyyy HH:mm")));
            }
        }
    }
}
