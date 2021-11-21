package ru.job4j.grabber;

import java.util.List;

/**
 * https://job4j.ru/profile/exercise/56/task-view/361
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
public interface Store {
    /**
     * Метод save() - сохраняет объявление в базе.
     * @param post объявление для сохранения
     */
    void save(Post post);

    /**
     * Метод getAll() - позволяет извлечь объявления из базы.
     * @return извлекаемое объявление
     */
    List<Post> getAll();

    /**
     * Метод findById(int id) -
     * позволяет извлечь объявление из базы по id.
     * @param id для извлечения
     * @return найденное объявление в БД
     */
    Post findById(int id);
}
