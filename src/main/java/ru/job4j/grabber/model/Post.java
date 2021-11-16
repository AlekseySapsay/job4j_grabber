package ru.job4j.grabber.model;

import java.time.LocalDateTime;

/**
 * https://job4j.ru/profile/exercise/56/task-view/358
 * <p>
 * Парсинг html страницы средствами jsoup.
 * Преобразование даты из формата с сайта sql.ru
 * в дату понятную java.
 * <p>
 * 1. Создайте модель данных Post. Модель должна иметь поля:
 * <p>
 * - id типа int - идентификатор вакансии (берется из нашей базы данных);
 * - title типа String - название вакансии;
 * - link типа String - ссылка на описание вакансии;
 * - description типа String - описание вакансии;
 * - created типа LocalDateTime - дата создания вакансии.
 * <p>
 * 2. Переопределить equals(), hashCode(), toString().
 * Какое поле можно исключить из equals() & hashCode()?
 * Исключите его и напишите в комментарии к заданию почему нужно это сделать.
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 14.11.2021
 */

public class Post {
    private int id;
    private String title;
    private String link;
    private String description;
    private LocalDateTime created;

    public Post(int id, String title, String link,
                String description, LocalDateTime created) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.description = description;
        this.created = created;
    }

    public Post() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Post post = (Post) o;

        if (id != post.id) {
            return false;
        }
        if (title != null ? !title.equals(post.title) : post.title != null) {
            return false;
        }
        if (link != null ? !link.equals(post.link) : post.link != null) {
            return false;
        }
        return created != null ? created.equals(post.created) : post.created == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", title='" + title + '\''
                + ", link='" + link + '\''
                + ", description='" + description + '\''
                + ", created=" + created
                + '}';
    }
}
