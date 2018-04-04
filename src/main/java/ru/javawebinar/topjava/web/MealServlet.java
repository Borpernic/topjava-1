package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExceeded;
import static ru.javawebinar.topjava.util.MealsUtil.sUserMeals;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to sUserMeals");
         String sarttime = request.getParameter("sarttime");
         String endtime = request.getParameter("endtime");
        System.out.println("sarttime" + sarttime);
        System.out.println("endtime" + endtime);
        if (null==sarttime) {sarttime="00:00";
        }
        if (null==endtime) {endtime="23:59";
        }

        List<MealWithExceed> mealsWithExceeded = getFilteredWithExceeded(sUserMeals, LocalTime.parse(sarttime), LocalTime.parse(endtime), 2000);

        request.setAttribute("mealList", mealsWithExceeded);
        request.getRequestDispatcher("/sUserMeals.jsp").forward(request, response);
    }
}
