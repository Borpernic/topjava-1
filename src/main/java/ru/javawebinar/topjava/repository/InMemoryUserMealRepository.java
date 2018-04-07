package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryUserMealRepository implements UserMealRepository {
    private AtomicInteger count = new AtomicInteger(0);
    private Map<Long, UserMeal> mealsMap = new HashMap();


    @Override
    public UserMeal save(final UserMeal userMeal) {
        if (userMeal.isNew()) {
            userMeal.setId(count.incrementAndGet());
        }
        mealsMap.put(userMeal.getId(), userMeal);
        return userMeal;
    }

    @Override
    public void delete(final UserMeal userMeal) {
        mealsMap.remove(userMeal.getId());

    }

    @Override
    public UserMeal get(final long id) {
        return mealsMap.get(id);
    }

    @Override
    public Collection<UserMeal> getAll() {
        return mealsMap.values();
    }
}
