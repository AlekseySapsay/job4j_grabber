package ru.job4j.grabber;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;

/**
 * https://job4j.ru/profile/exercise/56/task-view/363
 * <p>
 * Парсинг html страницы средствами jsoup.
 * Преобразование даты из формата с сайта sql.ru
 * в дату понятную java.
 * <p>
 * Наш проект будет хранить данные в базе Postgresql.
 * Связь с базой будет осуществляться через интерфейс.
 * Интерфейсы позволяют избавиться от прямой зависимости.
 * На первом этапе можно использовать
 * MemStore - хранение данных в памяти.
 *
 * @author AlexSapsay (sapsayalexey@gmail.com)
 * @version 1.0
 * @since 21.11.2021
 */
public class PsqlStore implements Store, AutoCloseable {
    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        /* cnn = DriverManager.getConnection(...); */
    }

    /**
     * {@inheritDoc}
     * @param post объявление для сохранения
     */
    @Override
    public void save(Post post) {

    }

    /**
     * {@inheritDoc}
     * @return лист постов для парсинга
     */
    @Override
    public List<Post> getAll() {
        return null;
    }

    /**
     * {@inheritDoc}
     * @param id для извлечения
     * @return найденный пост по id
     */
    @Override
    public Post findById(int id) {
        return null;
    }

    /**
     * {@inheritDoc}
     * @throws Exception не удалось закрыть ресурс
     */
    @Override
    public void close() throws Exception {
        if (cnn != null) {
            cnn.close();
        }
    }
}
