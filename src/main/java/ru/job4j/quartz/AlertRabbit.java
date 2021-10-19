package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * https://job4j.ru/profile/exercise/56/task-view/353
 *
 * Программа выполняет переодические действия. Выводит сообщения на консоль
 * используя планировщик Quartz
 * http://www.quartz-scheduler.org/.
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 19.10.2021
 */

public class AlertRabbit {
    public static void main(String[] args) {
        AlertRabbit alertRabbit = new AlertRabbit();
        int periodInSeconds = alertRabbit.readProperties();

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(periodInSeconds)
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }

    public int readProperties() {
        List<String> stringList = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(
                "C:\\projects\\job4j_grabber\\src\\main\\resources\\rabbit.properties"))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                stringList = Arrays.asList(line.split("="));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.parseInt(stringList.get(1));
    }
}