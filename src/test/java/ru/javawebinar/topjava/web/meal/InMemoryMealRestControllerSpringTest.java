package ru.javawebinar.topjava.web.meal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Collection;

import static ru.javawebinar.topjava.web.UserTestData.ADMIN;
import static ru.javawebinar.topjava.web.meal.MealTestData.assertMatchMeal;
import static ru.javawebinar.topjava.web.meal.MealTestData.getNewTestMeal;


@ContextConfiguration("classpath:spring/spring_mock.xml")
@RunWith(SpringRunner.class)

public class InMemoryMealRestControllerSpringTest {


    @Autowired
    private MealRestController controller;

    @Autowired
    private InMemoryMealRepositoryImpl repository;

    @Before
    public void setUp() {
        repository.init();
    }


    @Test
    public void get() {

        final Meal meal = getNewTestMeal();
        Meal meal1added = controller.create(meal);
        assertMatchMeal(meal, meal1added);
        Meal mealFromRepository = controller.get(meal1added.getId());
        assertMatchMeal(mealFromRepository, meal1added);


    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() {
        controller.get(10000);
    }

    @Test
    public void delete() {

        final Meal meal = getNewTestMeal();
        Meal meal1added = controller.create(meal);
        Collection<MealWithExceed> meals = controller.getAll();
        Assert.assertEquals(4, meals.size());
        controller.delete(meal1added.getId());
        meals = controller.getAll();
        Assert.assertEquals(3, meals.size());


    }

    @Test
    public void getAll() {

        Collection<MealWithExceed> meals = controller.getAll();
        Assert.assertEquals(3, meals.size());

    }

    @Test
    public void create() {
        final Meal meal = getNewTestMeal();
        Meal meal1added = controller.create(meal);
        assertMatchMeal(meal, meal1added);
        Collection<MealWithExceed> meals = controller.getAll();
        Assert.assertEquals(4, meals.size());
    }

    @Test
    public void update() {

        final Meal meal = getNewTestMeal();
        Meal meal1added = controller.create(meal);
        meal1added.setDateTime(LocalDateTime.of(1111, 11, 11, 11, 11));
        meal1added.setDescription(meal1added + "Updated");
        meal1added.setCalories(1111);
        controller.update(meal1added, meal1added.getId());
        Collection<MealWithExceed> meals = controller.getAll();
        Assert.assertEquals(4, meals.size());
        assertMatchMeal(meal, controller.get(meal1added.getId()));

    }

    @Test(expected = NotFoundException.class)
    public void testUpdateNotFound() {
        controller.update(getNewTestMeal(), 10000);
    }


    @Test
    public void getBetween() {
    }

    @Test
    public void getBetween1() {
    }
}