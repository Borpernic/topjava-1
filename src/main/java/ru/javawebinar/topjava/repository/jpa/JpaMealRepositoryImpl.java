package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)

public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.getUser() == null) {
            meal.setUser(new User(userId, "", "", "", 0, true, null));
        }
        if (meal.isNew()) {
            em.persist(meal);
        } else if (meal.getUser().getId() == userId) {
            em.merge(meal);
        } else {
            meal = null;
        }
        return meal;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        final Meal meal = em.getReference(Meal.class, id);
        if (meal.getUser().getId() == userId) {
            em.remove(meal);

            return true;
        }
        return false;
    }

    @Override
    public Meal get(int id, int userId) {
        Meal meal = em.find(Meal.class, id);
        if (meal.getUser().getId() == userId) {
            return meal;
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("userId", userId).getResultList();


    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        List<Meal> meals = em.createNamedQuery(Meal.getBetween, Meal.class).setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("userId", userId).getResultList();
        meals.stream().filter(meal -> {
            meal.getTime() <= endDate.toLocalTime();
        });
        return null;
    }
}