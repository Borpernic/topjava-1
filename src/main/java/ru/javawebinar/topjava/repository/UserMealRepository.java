package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.UserMeal;

import java.util.Collection;

public interface UserMealRepository {


    UserMeal save(UserMeal userMeal);

    void delete(UserMeal userMeal);

    UserMeal get(long id);

    Collection<UserMeal> getAll ();

}
