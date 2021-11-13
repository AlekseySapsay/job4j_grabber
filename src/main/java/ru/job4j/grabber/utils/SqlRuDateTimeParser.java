package ru.job4j.grabber.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Map;

import static java.util.Map.entry;

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

        String cleanParse1 = parse.replace(",", "");
        cleanParse1 = cleanParse1.replace(":", " ");
        String[] partsOfDate = cleanParse1.split(" ");

        if (parse.contains("сегодня") || parse.contains("вчера")) {

            int hours = Integer.parseInt(partsOfDate[1]);
            int minutes = Integer.parseInt(partsOfDate[2]);

            LocalDateTime localDateTime = LocalDateTime.of(
                    localDateTimeNow.getYear(),
                    localDateTimeNow.getMonth(),
                    localDateTimeNow.getDayOfMonth(),
                    hours, minutes, 0, 0);

            if (parse.contains("сегодня")) {
                return localDateTimeNow;
            } else if (parse.contains("вчера")) {
                return localDateTime.minusDays(1);
            }
        }

//        String clearedParse1 = parse.replace(",", "");
//        clearedParse1 = clearedParse1.replace(":", " ");
//        String[] clearedParse2 = clearedParse1.split(" ");

//        int days = Integer.parseInt(clearedParse2[0]);
//        //int month = Integer.parseInt(MONTHS.get(clearedParse2[1]));
//        int month = MONTHS.get(clearedParse2[1]);
//        int year = Integer.parseInt(clearedParse2[2]) + 2000;
//        int hours = Integer.parseInt(clearedParse2[3]);
//        int minutes = Integer.parseInt(clearedParse2[4]);

        int days = Integer.parseInt(partsOfDate[0]);
        //int month = Integer.parseInt(MONTHS.get(clearedParse2[1]));
        int month = MONTHS.get(partsOfDate[1]);
        int year = Integer.parseInt(partsOfDate[2]) + 2000;
        int hours = Integer.parseInt(partsOfDate[3]);
        int minutes = Integer.parseInt(partsOfDate[4]);


        LocalDateTime localDateTime2 = LocalDateTime.of(year, month, days,
                hours, minutes, 0, 0);

//        StringBuilder strDateBuilder = new StringBuilder();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm");
//        strDateBuilder.append(localDateTime.format(formatter));

//        LocalDateTime localDateTimeNow = LocalDateTime.now();

//        if (parse.contains("вчера")) {
//            localDateTimeNow = localDateTimeNow.minusDays(1);
//            return localDateTimeNow;
//        } else if (parse.contains("сегодня")) {
//            return localDateTimeNow;
//        }


        return localDateTime2;
    }

//    public LocalDateTime localDateTime2(String parse) {
//        String clearedParse1 = parse.replace(",", "");
//        clearedParse1 = clearedParse1.replace(":", " ");
//        String[] clearedParse2 = clearedParse1.split(" ");
//
//        int days = Integer.parseInt(clearedParse2[0]);
//        int month = Integer.parseInt(MONTHS.get(clearedParse2[1]));
//        int year = Integer.parseInt(clearedParse2[2]) + 2000;
//        int hours = Integer.parseInt(clearedParse2[3]);
//        int minutes = Integer.parseInt(clearedParse2[4]);
//
//        LocalDateTime localDateTime2 = LocalDateTime.of(year, month, days,
//                hours, minutes, 0, 0);
//        return localDateTime2;
//    }
}
