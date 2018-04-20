package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;
import ru.javawebinar.topjava.web.user.ProfileRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExceeded;

public class MealServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(MealServlet.class);

    //private MealRepository repository;
    private MealRestController mealRestController;
    private ProfileRestController profileRestController;
    private AdminRestController adminRestController;
    private ConfigurableApplicationContext appCtx;


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        //repository = new InMemoryMealRepositoryImpl();
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        mealRestController = appCtx.getBean(MealRestController.class);
        adminRestController = appCtx.getBean(AdminRestController.class);
        profileRestController = appCtx.getBean(ProfileRestController.class);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        String login = request.getParameter("email").toString();
        String password = request.getParameter("password").toString();

/*        if (!(login.isEmpty() || password.isEmpty())) {


            Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                    LocalDateTime.parse(request.getParameter("dateTime")),
                    request.getParameter("description"),
                    Integer.valueOf(request.getParameter("calories")));
        }
        log.info(meal.isNew() ? "Create {}" : "Update {}", meal);
        repository.save(meal);*/
        response.sendRedirect("meals");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action == null ? "all" : action) {
            case "login":
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                HttpSession session = request.getSession(true);
                session.setAttribute("email", email);
                session.setAttribute("logged", email);

                request.setAttribute("meals",
                        MealsUtil.getWithExceeded(mealRestController.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
            case "delete":
                int id = getId(request);
                log.info("Delete {}", id);
                mealRestController.delete(id);
                response.sendRedirect("meals");
                break;


            case "create":
            case "update":
                final Meal meal = "create".equals(action) ?
                        new Meal(AuthorizedUser.id(), LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000) :
                        mealRestController.get(getId(request));


                request.setAttribute("meal", meal);
                request.getRequestDispatcher("/mealForm.jsp").forward(request, response);
                break;
            case "filter":
                log.debug("redirect to meals");
                String sarttime = request.getParameter("sarttime");
                String endtime = request.getParameter("endtime");
                System.out.println("sarttime " + sarttime);
                System.out.println("endtime " + endtime);
                if (null == sarttime) {
                    sarttime = "00:00";
                }
                if (null == endtime) {
                    endtime = "23:59";
                }
                List<MealWithExceed> mealsWithExceeded = MealsUtil.getFilteredWithExceeded(mealRestController.getAll().stream().collect(Collectors.toList()), LocalTime.parse(sarttime), LocalTime.parse(endtime), 2000);
                request.setAttribute("meals", mealsWithExceeded);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);

                break;
            case "save":
                String dateTime = request.getParameter("dateTime").toString();
                LocalDateTime mealDateTime = LocalDateTime.parse(dateTime);
                String description = request.getParameter("description").toString();
                int calories = Integer.parseInt(request.getParameter("calories").toString());
                String id1 = request.getParameter("id").toString();
                id = id1.isEmpty() ? 0 : Integer.parseInt(id1);
                final Meal mealSave = new Meal(id, AuthorizedUser.id(), mealDateTime, description, calories);
                mealRestController.update(mealSave);

            case "all":
            default:
                log.info("getAll");
                request.setAttribute("meals",
                        MealsUtil.getWithExceeded(mealRestController.getAll(), MealsUtil.DEFAULT_CALORIES_PER_DAY));
                request.setAttribute("user", null);
                request.getRequestDispatcher("/meals.jsp").forward(request, response);
                break;
        }


    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }

    private int getLoggedUser(HttpServletRequest request) {

        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
