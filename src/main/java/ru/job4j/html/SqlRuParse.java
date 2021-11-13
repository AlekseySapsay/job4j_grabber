package ru.job4j.html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * https://job4j.ru/profile/exercise/56/task-view/355
 * <p>
 * Парсинг html страницы средствами jsoup
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 13.11.2021
 */

public class SqlRuParse {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://www.sql.ru/forum/job-offers").get();
        //Elements row = doc.select(".postslisttopic");
        Elements row = doc.select(".altCol");

//        for (Element td : row) {
//            Element href = td.child(0);
//            System.out.println(href.attr("href"));
//            System.out.println("href.text()");
//        }

        for (Element td : row) {
            Element parent = td.parent();
            //System.out.println(parent.tag());
            //System.out.println(parent.children().size());
            //System.out.println(parent.children().get(5).text());
            System.out.println(parent.child(5).text());
        }
    }
}
