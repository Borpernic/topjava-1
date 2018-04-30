package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.UserTestData;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.javawebinar.topjava.web.UserTestData.*;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    // Map  userId -> (mealId-> meal)
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public void init() {
        repository.clear();

        //MealsUtil.MEALS.forEach(meal -> save(meal, USER_ID));
        save(new Meal(ADMIN_ID, LocalDateTime.of(2015, Month.JUNE, 1, 9, 0), "Админ завтрак", 500), ADMIN_ID);
        save(new Meal(ADMIN_ID, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "Админ ланч", 510), ADMIN_ID);
        save(new Meal(ADMIN_ID, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "Админ ужин", 1500), ADMIN_ID);

        save(new Meal(USER_ID, LocalDateTime.of(2015, Month.JUNE, 1, 9, 0), "User завтрак", 500), USER_ID);
        save(new Meal(USER_ID, LocalDateTime.of(2015, Month.JUNE, 1, 14, 0), "User ланч", 510), USER_ID);
        save(new Meal(USER_ID, LocalDateTime.of(2015, Month.JUNE, 1, 21, 0), "User ужин", 1500), USER_ID);

    }


    @Override
    public Meal save(Meal meal, int userId) {
        Map<Integer, Meal> userMeals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            userMeals.put(meal.getId(), meal);
            return meal;
        }
        return userMeals.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals != null && userMeals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals == null ? null : userMeals.get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return getAllAsStream(userId).collect(Collectors.toList());
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return getAllAsStream(userId)
                .filter(meal -> DateTimeUtil.isBetween(meal.getDateTime(), startDateTime, endDateTime))
                .collect(Collectors.toList());
    }

    private Stream<Meal> getAllAsStream(int userId) {
        Map<Integer, Meal> userMeals = repository.get(userId);
        return userMeals == null ?
                Stream.empty() :
                userMeals.values().stream()
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed());
    }


}