package ru.job4j.grabber;

import ru.job4j.grabber.model.Post;

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
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 16.11.2021
 */

public interface Parse {
    /**
     * Метод list загружает список всех постов.
     * @param link ссылка на форум для парсинга
     * @return список постов после парсинга
     */
    List<Post> list(String link) throws IOException;

    /**
     * Метод detail загружает все детали одного поста
     * (имя, описание, дату обновления, дату создания, ссылки на пост).
     * @param link ссылка на пост для парсинга
     * @return возвращаем детали поста Post
     */
    Post detail(String link) throws IOException;
}
