package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserMealRepository implements UserMealRepository{
    private AtomicInteger count = new AtomicInteger(0);
    private Map mealsMap = new HashMap();

    @Override
    public UserMeal save(final UserMeal userMeal) {
        return null;
    }

    @Override
    public void delete(final UserMeal userMeal) {

    }

    @Override
    public UserMeal get(final int id) {
        return null;
    }

    @Override
    public Collection<UserMeal> getAll() {
        return null;
    }
}
