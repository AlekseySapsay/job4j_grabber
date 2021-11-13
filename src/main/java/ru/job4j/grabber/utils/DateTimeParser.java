package ru.job4j.grabber.utils;

import java.time.LocalDateTime;

/**
 * https://job4j.ru/profile/exercise/56/task-view/356
 * <p>
 * Парсинг html страницы средствами jsoup.
 * Преобразование даты из формата  sql.ru
 * в дату понятную java.
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 13.11.2021
 */
public interface DateTimeParser {
    LocalDateTime parse(String parse);
}
