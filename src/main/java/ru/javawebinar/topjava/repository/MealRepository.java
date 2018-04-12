package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.Collection;

public interface MealRepository {
    Meal save(Meal meal, User user);

    // false if not found
    boolean delete(int id, User user);

    // null if not found
    Meal get(int id, User user);

    // null if not found
    Collection<Meal> getAll(User user);
}
