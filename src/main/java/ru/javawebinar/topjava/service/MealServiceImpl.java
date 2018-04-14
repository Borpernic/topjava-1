package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal create(final int userId) {
        repository.save(new Meal(userId, LocalDateTime.now(), "", 0),userId);
        return null;
    }

    @Override
    public Meal get(final int userId, final int mealId) throws NotFoundException {
        return null;
    }

    @Override
    public void delete(final int userId, final int mealId) throws NotFoundException {

    }

    @Override
    public void deleteAll(final int userId) throws NotFoundException {

    }

    @Override
    public void update(final int userId, final Meal meal) {

    }

    @Override
    public List<Meal> getAll(final int userId) {
        return null;
    }
}