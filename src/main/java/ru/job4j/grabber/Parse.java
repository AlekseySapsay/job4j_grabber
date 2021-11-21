package ru.job4j.grabber;

import java.io.IOException;
import java.util.List;

/**
 * https://job4j.ru/profile/exercise/56/task-view/360
 * https://job4j.ru/profile/exercise/56/task-view/357
 * https://job4j.ru/profile/exercise/56/task-view/355
 * <p>
 * Парсинг html страницы средствами jsoup.
 * Добавить парсинг первых пяти страниц.
 * В этом задании нужно собрать все
 * элементы парсинга в классе SqlRuParse.
 *
 * Метод list загружает список всех постов.
 *
 * Метод detail загружает все детали одного поста
 * (имя, описание, дату обновления, дату создания, ссылки на пост).
 *
 * Описание компонента через интерфейс позволяет расширить наш проект.
 *
 * Например, осуществить сбор данных с других площадок:
 * SqlRuParse, HhRuParse, SuperJobParse.
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 21.11.2021
 */

public interface Parse {
    /**
     * Метод list загружает список всех постов.
     * list(link) - этот метод загружает список
     *  объявлений по ссылке типа
     * - https://www.sql.ru/forum/job-offers/1
     *
     * @param link ссылка на форум для парсинга
     * @return список постов после парсинга
     */
    List<Post> list(String link) throws IOException;

    /**
     * Метод detail загружает все детали одного поста
     * (имя, описание, дату обновления, дату создания,
     * ссылки на пост).
     * detail(link) - этот метод загружает детали объявления
     * по ссылке типа
     * - https://www.sql.ru/forum/1323839/razrabotchik-java-g-kazan
     * @param link ссылка на пост для парсинга
     * @return возвращаем детали поста Post
     */
    Post detail(String link) throws IOException;
}
