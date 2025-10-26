package ma.jee.spring.tp3.service;

import ma.jee.spring.tp3.entity.Product;

import java.util.Collection;

public interface IService<E> {
    Collection<E> getAll();
    E getById(Long id);
    void insert(E e);
    void update(E e, Long id);
    void delete(Long id);
}
