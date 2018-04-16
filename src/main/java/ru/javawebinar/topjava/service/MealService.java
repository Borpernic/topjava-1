package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;

public interface MealService {

    Meal create(Meal meal, int userId);

    Meal get(int userId, int mealId) throws NotFoundException;

    void delete(int userId, int mealId) throws NotFoundException;

    void deleteAll(int userId) throws NotFoundException;

    void update(int userId, Meal meal);

    List<Meal> getAll(int userId);

}