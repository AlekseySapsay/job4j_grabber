package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * https://job4j.ru/profile/exercise/56/task-view/353
 * https://job4j.ru/profile/exercise/56/task-view/354
 * <p>
 * Программа выполняет переодические действия. Выводит сообщения на консоль
 * используя планировщик Quartz
 * http://www.quartz-scheduler.org/.
 * <p>
 * Вывод в консоль каждые 10 секунд сообщения
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 05.11.2021
 */

public class AlertRabbit {
    private Properties configuration;

    public AlertRabbit(Properties configuration) {
        this.configuration = configuration;
    }

    public static void main(String[] args) {
        AlertRabbit alertRabbit = new AlertRabbit(getProperties("rabbit.properties"));
        try (Connection connection = alertRabbit.init(alertRabbit.configuration)) {
            alertRabbit.executeScheduler(connection, alertRabbit.configuration);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Метод для получения интервала из .properties
     *
     * @param configuration объект Properties
     * @return значение интервала преобразованный в Integer
     */
    public static Integer getInterval(Properties configuration) {
        return Integer.parseInt(configuration.getProperty("rabbit.interval"));
    }

    public void executeScheduler(Connection connection,
                                 Properties properties)
            throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        scheduler.scheduleJob(getJob(connection), getTrigger(properties));
        Thread.sleep(1000);
        scheduler.shutdown();
    }

    /**
     * Формирование подключения к BD
     *
     * @param configuration принимает Properties
     * @return Connection
     * @throws SQLException           выбрасовывание исключения
     * @throws ClassNotFoundException еще исключение
     */
    private Connection init(Properties configuration)
            throws SQLException, ClassNotFoundException {
        Class.forName(configuration.getProperty("driver-class-name"));
        return DriverManager.getConnection(
                configuration.getProperty("urs"),
                configuration.getProperty("username"),
                configuration.getProperty("password")
        );
    }

    /**
     * Возвращаем Properties настройки из файла
     *
     * @param path путь к файлу в виде строки
     * @return Properties
     */
    private static Properties getProperties(String path) {
        Properties config = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader()
                .getResourceAsStream(path)) {
            config.load(in);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return config;
    }

    /**
     * Метод создает объект и настраивает Job
     * для Scheduler
     *
     * @param connection подключение к базе данных
     * @return задача, выполняемая классом
     */
    private JobDetail getJob(Connection connection) {
        JobDataMap data = new JobDataMap();
        data.put("store", connection);

        JobDetail job = newJob(Rabbit.class)
                .usingJobData(data)
                .build();
        return job;
    }

    private Trigger getTrigger(Properties configuration) {
        /**
         * Формирование расписания, настройка переодичности запуска
         */
        SimpleScheduleBuilder times = simpleSchedule()
                .withIntervalInSeconds(
                        getInterval(configuration))
                .repeatForever();

        /**
         * Trigger - объект, обозначающий время для начала запуска.
         * В нашем случае выполнение происходит сразу.
         */
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
        return trigger;
    }

    /**
     * Задача для использования основного класса
     */
    public static class Rabbit implements Job {
        public Rabbit() {
            System.out.println(hashCode());
        }

        /**
         * Метод исполнения Job
         * Выводит в консоль текст
         * Получает Connection из контекста
         * Добавляет новую запись с текущим временем в базу данных в таблицу rabbit
         *
         * @param context контекст
         * @throws JobExecutionException исключение
         */
        @Override
        public void execute(JobExecutionContext context) throws
                JobExecutionException {
            System.out.println("Rabbit runs here ...");
            Connection connection = (Connection) context.
                    getJobDetail()
                    .getJobDataMap()
                    .get("store");

            try (PreparedStatement statement =
                         connection.prepareStatement("insert into rabbit(created_date) values (?)")) {
                statement.setLong(1, System.currentTimeMillis());
                statement.execute();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
