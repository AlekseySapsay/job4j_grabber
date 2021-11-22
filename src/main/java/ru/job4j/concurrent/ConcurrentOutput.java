package ru.job4j.concurrent;

/**
 * https://job4j.ru/profile/exercise/71/task-view/398
 * <p>
 * Изучение Thread.start()
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 22.11.2021
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread another =
                new Thread(() -> System.out.println(Thread.currentThread()
                        .getName()));
        another.start();
        System.out.println(Thread.currentThread().getName());
    }
}
