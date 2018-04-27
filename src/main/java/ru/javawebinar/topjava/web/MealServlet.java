package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);
    private ConfigurableApplicationContext appCtx;
    private MealRestController mealController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "classpath:spring/spring-db.xml");
        mealController = appCtx.getBean(MealRestController.class);

    }

    @Override
    public void destroy() {
        super.destroy();
        appCtx.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        Meal meal;
        if (id.isEmpty()) {

            meal = new Meal(null, null,
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")));
            log.info("Create {}", meal);
            mealController.create(meal);
        } else {
            meal = new Meal(Integer.parseInt(id), null,
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.parseInt(request.getParameter("calories")));
            log.info("Create {}", meal);
            mealController.update(meal, meal.getId());
        }
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        request.setCharacterEncoding("UTF-8");

        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                mealController.delete(id);
                response.sendRedirect("meals");
                break;
            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(null, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealController.get(getId(request));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "filter":

                LocalDateTime startDateTime = dateTime(request, "startFilter");
                LocalDateTime endDateTime = dateTime(request, "startFilter");
                log.info("filter startDateTime{}, endDateTime {}", startDateTime, endDateTime);


                request.setAttribute("meals",
                        mealController.getAll());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        mealController.getAll());
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    private LocalDateTime dateTime(HttpServletRequest request, String typeFilterDateTime) {
        switch (typeFilterDateTime) {
            case "startFilter":
                LocalDate startDate = ("startDate").isEmpty() ? DateTimeUtil.MIN_DATE
                        : LocalDate.parse(request.getParameter("startDate"));
                LocalTime startTime = "startTime".isEmpty() ? LocalTime.of(0, 0)
                        : LocalTime.parse(request.getParameter("startTime"));
                return LocalDateTime.of(startDate, startTime);


            case "endFilter":

                LocalDate endDate = ("endDate").isEmpty() ? DateTimeUtil.MAX_DATE
                        : LocalDate.parse(request.getParameter("endDate"));
                LocalTime endTime = "endTime".isEmpty() ? LocalTime.of(23, 59)
                        : LocalTime.parse(request.getParameter("endTime"));

                return LocalDateTime.of(endDate, endTime);


        }

        return LocalDateTime.of(0, 0, 0, 0, 0);
    }
}
