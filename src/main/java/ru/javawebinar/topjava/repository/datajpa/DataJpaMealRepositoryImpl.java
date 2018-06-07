package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    private static final Sort SORT_DATETIME_DESC = new Sort(Sort.Direction.DESC, "dateTime");

    @Autowired
    private CrudMealRepository crudRepository;
    @Autowired
    private UserService userService;

    @Override
    public Meal save(Meal meal, int userId) {
        //if (meal.getUser().getId() == userId) return crudRepository.save(meal);
        if (userService.get(userId) != null) {
            meal.setUser(userService.get(userId));
        } else {
            return null;
        }
        if (meal.getUser().getId() != userId || AuthorizedUser.id() != userId) return null;
        // if (meal.isNew()) {
        return crudRepository.save(meal);
        // }

    }

    @Override
    public boolean delete(int id, int userId) {


        return crudRepository.deleteByIdAndUserId(id, userId) > 0;

    }

    @Override
    public Meal get(int id, int userId) {

        return crudRepository.findByIdAndUserId(id, userId).orElse(null);

    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> meals = crudRepository.findByUserId(userId, SORT_DATETIME_DESC);
        return meals;

    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<Meal> meals = crudRepository.findByUserIdAndDateTimeBetween(userId, startDate, endDate, SORT_DATETIME_DESC);
        return meals;


    }
}
