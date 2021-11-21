package ru.job4j.gc;

/**
 * https://job4j.ru/profile/exercise/58/task-view/370
 * <p>
 * Изучение работы GC
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 20.11.2021
 */

public class User {
    private String name;
    private int age;
    private char sex;
    private int salary;

    public User(String name, int age, char sex, int salary) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.salary = salary;
    }

    public User() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\''
                + ", age=" + age
                + ", sex=" + sex
                + ", salary=" + salary
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (age != user.age) {
            return false;
        }
        if (sex != user.sex) {
            return false;
        }
        if (salary != user.salary) {
            return false;
        }
        return name != null ? name.equals(user.name) : user.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        result = 31 * result + (int) sex;
        result = 31 * result + salary;
        return result;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(String.format("destory object user", this.name));
    }
}
