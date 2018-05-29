package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.List;
import java.util.Optional;

public interface CrudMealRepository extends JpaRepository<Meal, Integer> {
    @Override
    @Transactional
    <S extends Meal> S save(S entity);

    @Override
    Optional<Meal> findById(Integer integer);

    @Override
    @Transactional
    void deleteById(Integer integer);

    @Override
    @Transactional
    void deleteAll();

    @Override
    List<Meal> findAll(Sort sort);

    boolean deleteByIdAndUserId(Integer id, Integer userId);
}
