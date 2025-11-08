package ma.jee.spring.jdbccrud.dao;

import java.util.List;

public interface IDao<E> {
    List<E> getAll();
    E getById(Long id);
    boolean create(E entity);
    boolean update(E entity);
    void delete(Long id);
}
