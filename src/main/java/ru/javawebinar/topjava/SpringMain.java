package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.time.LocalDateTime;

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
            System.out.println("перед добавлением " + mealRestController.getAll());
            mealRestController.create(
                    new Meal(AuthorizedUser.id(), LocalDateTime.now(), "еда1 user1", 500));
            mealRestController.create(
                    new Meal(AuthorizedUser.id(), LocalDateTime.now(), "еда2 user1", 500));
            System.out.println("после добавления " + mealRestController.getAll());
            mealRestController.getAll().stream().forEach(meal -> {
                Meal meal1Updated = new Meal(meal.getId(), meal.getUserId(), meal.getDateTime(),
                        meal.getDescription() + meal.getUserId(), meal.getCalories());
                mealRestController.update(meal1Updated);
            });

            System.out.println("после обновления " + mealRestController.getAll());
}
    }
}
