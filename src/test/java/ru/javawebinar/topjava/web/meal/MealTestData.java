package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static Meal getNewTestMeal() {
        final Meal meal = new Meal();
        meal.setCalories(2000);
        meal.setDateTime(LocalDateTime.of(2018, 12, 12, 12, 12));
        meal.setDescription("test");
        return meal;
    }


    public static void assertMatchMeal(Meal actual, Meal expected) {
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }

    public static void assertMatchMeal(Iterable<Meal> actual, Meal... expected) {
        assertMatchMeal(actual, Arrays.asList(expected));
    }

    public static void assertMatchMeal(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
