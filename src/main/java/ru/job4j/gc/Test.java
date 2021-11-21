package ru.job4j.gc;

import static com.carrotsearch.sizeof.RamUsageEstimator.sizeOf;

public class Test {
    public static void main(String[] args) throws Throwable {
        User user1 = new User("Ivan", 35, 'm', 250000);
        User user2 = new User("Petr", 40, 'm', 350000);
        User user3 = new User();

        System.out.println(sizeOf(user1));
        System.out.println(sizeOf(user2));
        System.out.println("sizeOf(user3) " + sizeOf(user3));

        System.out.println("sizeOf(user1.getAge()) " + sizeOf(user1.getAge()));
        System.out.println("sizeOf(user1.getName())" + sizeOf(user1.getName()));
        System.out.println("sizeOf(user1.getSalary())" + sizeOf(user1.getSalary()));
        System.out.println("sizeOf(user1.getSex())" + sizeOf(user1.getSex()));

        System.out.println("Finalazy statr");
        user1.finalize();
        System.out.println("Finalazy STOP");

        for (int i = 0; i < 100; i++) {
            System.out.println(new User() + "counter : " + i);
        }
    }
}
