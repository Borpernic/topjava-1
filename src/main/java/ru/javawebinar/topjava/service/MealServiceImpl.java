package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal create(final int userId) {
        return repository.save(new Meal(userId, LocalDateTime.now(), "", 0), userId);

    }

    @Override
    public Meal get(final int userId, final int mealId) throws NotFoundException {
        return repository.get(mealId, userId);

    }

    @Override
    public void delete(final int userId, final int mealId) throws NotFoundException {
        repository.delete(mealId, userId);
    }

    @Override
    public void deleteAll(final int userId) throws NotFoundException {
        repository.deleteAll(userId);

    }

    @Override
    public void update(final int userId, final Meal meal) {
        repository.save(meal, userId);

    }

    @Override
    public List<Meal> getAll(final int userId) {

        return repository.getAll(userId);
    }
}