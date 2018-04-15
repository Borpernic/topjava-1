package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;

import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private MealService service;


    public List<Meal> getAll() {
        log.info("getAll");
        return service.getAll(AuthorizedUser.id());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(AuthorizedUser.id(), id);
    }

    public Meal create(Meal meal) {
        log.info("create {}", AuthorizedUser.id());
        checkNew(meal);
        return service.create(AuthorizedUser.id());

    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(AuthorizedUser.id(), id);
    }

    public void update(Meal meal) {
        log.info("update {} with id={}", meal, AuthorizedUser.id());
        assureIdConsistent(meal, meal.getUserId(), AuthorizedUser.id());
        service.update(AuthorizedUser.id(), meal);
    }


}