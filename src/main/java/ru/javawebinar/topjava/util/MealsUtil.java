package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class MealsUtil {
public static List<UserMeal> sUserMeals = Arrays.asList(
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
        new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
);

    public static void main(String[] args) {
        List<UserMeal> userMeals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List<MealWithExceed> mealsWithExceeded = getFilteredWithExceeded(userMeals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsWithExceeded.forEach(System.out::println);

        System.out.println(getFilteredWithExceededByCycle(userMeals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<MealWithExceed> getFilteredWithExceeded(List<UserMeal> userMeals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesSumByDate = userMeals.stream()
                .collect(
                        Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories))
//                      Collectors.toMap(UserMeal::getDate, UserMeal::getCalories, Integer::sum)
                );

        return userMeals.stream()
                .filter(userMeal -> TimeUtil.isBetween(userMeal.getTime(), startTime, endTime))
                .map(userMeal -> createWithExceed(userMeal, caloriesSumByDate.get(userMeal.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static List<MealWithExceed> getFilteredWithExceededByCycle(List<UserMeal> userMeals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        final Map<LocalDate, Integer> caloriesSumByDate = new HashMap<>();
        userMeals.forEach(userMeal -> caloriesSumByDate.merge(userMeal.getDate(), userMeal.getCalories(), Integer::sum));

        final List<MealWithExceed> mealsWithExceeded = new ArrayList<>();
        userMeals.forEach(userMeal -> {
            if (TimeUtil.isBetween(userMeal.getTime(), startTime, endTime)) {
                mealsWithExceeded.add(createWithExceed(userMeal, caloriesSumByDate.get(userMeal.getDate()) > caloriesPerDay));
            }
        });
        return mealsWithExceeded;
    }

    public static MealWithExceed createWithExceed(UserMeal userMeal, boolean exceeded) {
        return new MealWithExceed(userMeal.getId(),userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), exceeded);
    }
}