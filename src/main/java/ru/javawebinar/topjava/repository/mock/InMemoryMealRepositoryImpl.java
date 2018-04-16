package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    private static final Logger log = getLogger(InMemoryMealRepositoryImpl.class);
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(meal -> {
            this.save(meal, meal.getUserId());
        });
    }

    public InMemoryMealRepositoryImpl() {

        counter.set(repository.size());
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet(), userId);
        }
        log.info("save userID {} meal {}", userId, meal);

        repository.put(meal.getId(), meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        if (checkMealByUser(id, userId)) {
            log.info("delete {}", id);
            repository.remove(id);
            return true;
        }

        return false;
    }

    @Override
    public boolean deleteAll(int userId) {

        log.info("delete all by userId {}", userId);
        ArrayList<Meal> mealsByUser = repository.values()
                .stream().filter(meal -> meal.getUserId() == userId)
                .collect(Collectors.toCollection(ArrayList::new));
        mealsByUser.forEach(meal -> this.delete(meal.getId(), userId));


        return true;
    }

    private boolean checkMealByUser(int id, int userId) {
        log.info("checkMealByUser {}", id);
        return repository.get(id).getUserId() == userId;
    }

    @Override
    public Meal get(int id, int userId) {
        log.info("get id{} userId{}", id, userId);
        if (checkMealByUser(id, userId)) {
            return repository.get(id);
        }

        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.info("getAll userId {}", userId);
        List<Meal> collect = repository.values().stream().filter(meal -> meal.getUserId() == userId).collect(Collectors.toCollection(ArrayList::new));
        return collect;
    }
}

