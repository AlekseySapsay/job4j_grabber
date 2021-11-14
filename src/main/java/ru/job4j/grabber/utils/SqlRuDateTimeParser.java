package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.util.Map;

import static java.util.Map.entry;

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

public class SqlRuDateTimeParser implements DateTimeParser {
    private static final Map<String, Integer> MONTHS =
            Map.ofEntries(
                    entry("янв", 1),
                    entry("фев", 2),
                    entry("мар", 3),
                    entry("апр", 4),
                    entry("май", 5),
                    entry("июн", 6),
                    entry("июл", 7),
                    entry("авг", 8),
                    entry("сен", 9),
                    entry("окт", 10),
                    entry("ноя", 11),
                    entry("дек", 12)
            );

    @Override
    public LocalDateTime parse(String parse) {
        LocalDateTime localDateTimeNow = LocalDateTime.now();

        String cleanParse1 = parse
                .replace(",", "")
                .replace(":", " ");

        String[] partsOfDate = cleanParse1.split(" ");

        if (parse.contains("сегодня") || parse.contains("вчера")) {
            int hours = Integer.parseInt(partsOfDate[1]);
            int minutes = Integer.parseInt(partsOfDate[2]);

            if (parse.contains("сегодня")) {
                return localDateTimeNow.
                        withHour(hours)
                        .withMinute(minutes);
            } else if (parse.contains("вчера")) {
                return localDateTimeNow
                        .withHour(hours)
                        .withMinute(minutes)
                        .minusDays(1);
            }
        }

        int days = Integer.parseInt(partsOfDate[0]);
        int month = MONTHS.get(partsOfDate[1]);
        int year = Integer.parseInt(partsOfDate[2]) + 2000;
        int hours = Integer.parseInt(partsOfDate[3]);
        int minutes = Integer.parseInt(partsOfDate[4]);

        return LocalDateTime.of(year, month, days,
                hours, minutes, 0, 0);
    }
}
