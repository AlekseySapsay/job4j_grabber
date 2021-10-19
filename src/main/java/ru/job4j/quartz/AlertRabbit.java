package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.InputStream;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

public class AlertRabbit {
    public static void main(String[] args) {
        // readProperties
        int a = readProperties();

        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(1)
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

    public static int readProperties() {
        InputStream stream = AlertRabbit.class.getResourceAsStream(
                "C:\\projects\\job4j_grabber\\src\\main\\resources");
        System.out.println(stream != null);
        stream = AlertRabbit.class.getClassLoader().getResourceAsStream(
                "C:\\projects\\job4j_grabber\\src\\main\\resources");
        System.out.println(stream != null);
        System.out.println(stream);

        return 0;
    }
}