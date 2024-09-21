package com.example.lesson8.embedded;

import com.example.lesson8.entity.Rating;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(
        name = "com.example.lesson8.embedded.RateServlet",
        value = "/RateServlet"
)
public class RateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        int rate = Integer.parseInt(req.getParameter("rate"));
        Rating rating = Rating.builder().rate(rate).build();
        entityManager.persist(rating);
        System.out.println(entityManager.createQuery("from Rating", Rating.class).getResultList());
        entityManager.getTransaction().commit();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
