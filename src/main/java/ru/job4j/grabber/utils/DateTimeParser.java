package ru.job4j.grabber.utils;

import java.time.LocalDateTime;

/**
 * https://job4j.ru/profile/exercise/56/task-view/356
 * <p>
 * Парсинг html страницы средствами jsoup.
 * Преобразование даты из формата с сайта sql.ru
 * в дату понятную java.
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 13.11.2021
 */

public interface DateTimeParser {
    /**
     * Парсинг html страницы средствами jsoup.
     * Преобразование даты из формата с сайта sql.ru
     * в дату понятную java.
     *
     * @param parse строка содержащая дату
     * @return дата в формате LocalDateTime
     */
    LocalDateTime parse(String parse);
}
