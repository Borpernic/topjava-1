package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.InMemoryUserMealRepository;
import ru.javawebinar.topjava.repository.UserMealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;
import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExceeded;
import static ru.javawebinar.topjava.util.MealsUtil.sUserMeals;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);
    private UserMealRepository userMealRepository;

    @Override
    public void init() throws ServletException {
        super.init();
        userMealRepository = new InMemoryUserMealRepository();
        for (UserMeal meal : MealsUtil.sUserMeals) {
            userMealRepository.save(meal);
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("doPost redirect to sUserMeals");
        String sarttime = request.getParameter("sarttime");
        String endtime = request.getParameter("endtime");
        System.out.println("sarttime" + sarttime);
        System.out.println("endtime" + endtime);
        if (null == sarttime) {
            sarttime = "00:00";
        }
        if (null == endtime) {
            endtime = "23:59";
        }

        try {

            String action = request.getParameter("action").toString();

            String idStr = request.getParameter("id");
            long id = null == idStr ? 0 : Long.parseLong(request.getParameter("id"));

            log.debug("doPost: " + action + ", id " + id);
            switch (action) {
                case "delete":
                    userMealRepository.delete(new UserMeal(Integer.parseInt(request.getParameter("id")), null, null, 2000));
                    break;
                case "save":
                    userMealRepository.save(new UserMeal(now(), "Вечер", 2000));
                    break;
            }


        } catch (NullPointerException e) {
            log.debug("doPost " + e);
        }


        List<MealWithExceed> mealsWithExceeded = getFilteredWithExceeded(userMealRepository.getAll().stream().collect(Collectors.toList()), LocalTime.parse(sarttime), LocalTime.parse(endtime), 2000);
        request.setAttribute("mealList", mealsWithExceeded);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to sUserMeals");
        String sarttime = request.getParameter("sarttime");
        String endtime = request.getParameter("endtime");
        System.out.println("sarttime" + sarttime);
        System.out.println("endtime" + endtime);
        if (null == sarttime) {
            sarttime = "00:00";
        }
        if (null == endtime) {
            endtime = "23:59";
        }

        List<MealWithExceed> mealsWithExceeded = getFilteredWithExceeded(userMealRepository.getAll().stream().collect(Collectors.toList()), LocalTime.parse(sarttime), LocalTime.parse(endtime), 2000);

        request.setAttribute("mealList", mealsWithExceeded);
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
