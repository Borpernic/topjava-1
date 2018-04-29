package ru.javawebinar.topjava.web.meal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.repository.mock.InMemoryMealRepositoryImpl;
import ru.javawebinar.topjava.repository.mock.InMemoryUserRepositoryImpl;
import ru.javawebinar.topjava.web.user.AdminRestController;

import static org.junit.Assert.*;


@ContextConfiguration("classpath:spring/spring_mock.xml")
@RunWith(SpringRunner.class)

public class MealRestControllerTest {


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
    }

    @Test
    public void delete() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void create() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getBetween() {
    }

    @Test
    public void getBetween1() {
    }
}