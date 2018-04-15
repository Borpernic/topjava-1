package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> this.save(meal, meal.getUserId()));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet(), userId);
        }
        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (checkMealByUser(id, userId)) {
            repository.remove(id);
            return true;
        }

        return false;
    }

    private boolean checkMealByUser(int id, int userId) {
        return repository.get(id).getUserId() == userId;
    }

    @Override
    public Meal get(int id, int userId) {
        if (checkMealByUser(id, userId)) {
            return repository.get(id);
        }

        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Collection<Meal> collect = repository.values().stream().filter(meal -> meal.getUserId() == userId).collect(Collectors.toCollection(ArrayList::new));
        return collect;
    }
}

