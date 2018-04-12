package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Meal {
    private Integer id;

    private User user;

    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    public Meal(User user, LocalDateTime dateTime, String description, int calories) {

        this(null, user, dateTime, description, calories);
    }

    public Meal(Integer id, User user, LocalDateTime dateTime, String description, int calories) {
        this.id = id;
        this.user = user;
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id, User user) {
        this.id = id;
        this.user=user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public boolean isNew() {
        return id == null;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", user=" + user +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
