package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
   /*         System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            System.out.println(adminUserController.getAll());
            adminUserController.create(new User(null, "userName1", "email", "password", Role.ROLE_ADMIN));
            adminUserController.create(new User(null, "userName2", "email", "password", Role.ROLE_USER));
            adminUserController.create(new User(null, "userName3", "email", "password", Role.ROLE_USER));
            adminUserController.create(new User(null, "userName4", "email", "password", Role.ROLE_ADMIN));
            System.out.println(adminUserController.getAll());

            int[] userId = adminUserController.getAll().stream().mapToInt(value -> value.getId()).toArray();
            for (int id : userId) {
                User user = adminUserController.get(id);
                user.setEmail("Email User" + id);
                user.setPassword("password" + id);
                user.setName("User" + id + user.getRoles());
                adminUserController.update(user, id);
                System.out.println(adminUserController.getAll());
            }
            try {
                adminUserController.delete(100);


            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
            }*/


            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            System.out.println(mealRestController.getAll());
            mealRestController.create(new Meal(1, LocalDateTime.now(), "еда", 500));
            mealRestController.create(new Meal(2, LocalDateTime.now(), "обед", 500));
            System.out.println(mealRestController.getAll());
        }
    }
}
