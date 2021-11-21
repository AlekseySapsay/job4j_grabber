package ru.job4j.grabber;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 * https://job4j.ru/profile/exercise/56/task-view/360
 * https://job4j.ru/profile/exercise/56/task-view/357
 * https://job4j.ru/profile/exercise/56/task-view/355
 * <p>
 * Парсинг html страницы средствами jsoup.
 * Добавить парсинг первых пяти страниц.
 * В этом задании нужно собрать все
 * элементы парсинга в классе SqlRuParse.
 * <p>
 * В этом проекты мы будем использовать quartz для запуска
 * парсера. Но напрямую мы не будем его использовать.
 * <p>
 * Абстрагируемся через интерфейс.
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 21.11.2021
 */
public interface Grab {
    void init(Parse parse, Store store, Scheduler scheduler) throws SchedulerException;
}
