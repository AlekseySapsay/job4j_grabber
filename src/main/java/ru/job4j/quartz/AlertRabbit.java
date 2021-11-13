package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static java.lang.System.currentTimeMillis;
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
    public static void main(String[] args) {
        try {
            Properties properties = new Properties();
            try (InputStream in = AlertRabbit.class
                    .getClassLoader()
                    .getResourceAsStream("rabbit.properties")) {
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Class.forName(properties.getProperty("db.driver"));
            try (Connection connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.username"),
                    properties.getProperty("db.password"))) {
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                scheduler.start();
                JobDataMap data = new JobDataMap();
                data.put("connection", connection);

                JobDetail job = JobBuilder.newJob(Rabbit.class).build();

                SimpleScheduleBuilder times = simpleSchedule()
                        .withIntervalInSeconds(Integer
                                .parseInt(properties
                                        .getProperty("rabbit.interval")))
                        .repeatForever();
                Trigger trigger = newTrigger()
                        .startNow()
                        .withSchedule(times)
                        .build();
                scheduler.scheduleJob(job, trigger);
                Thread.sleep(10000);
                scheduler.shutdown();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context)
                throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
            Connection connection = (Connection) context
                    .getJobDetail()
                    .getJobDataMap()
                    .get("connection");
            try (PreparedStatement preparedStatement =
                         connection
                                 .prepareStatement(
                                         "insert into job(created_date) values(?);")) {
                preparedStatement.setLong(1, currentTimeMillis());
                preparedStatement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}