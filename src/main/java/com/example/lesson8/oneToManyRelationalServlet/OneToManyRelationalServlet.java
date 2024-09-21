package com.example.lesson8.oneToManyRelationalServlet;

import com.example.lesson8.entity.Article;
import com.example.lesson8.entity.Author;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Objects;

@WebServlet(
        name = "com.example.lesson8.oneToManyRelationalServlet.OneToManyRelationalServlet",
        value = "/O2MRServlet"
)
public class OneToManyRelationalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Author author = Author.builder()
                .full_name("Elmurodov Javohir")
                .build();
        Article article1 = Article.builder()
                .title("Global warning")
                .author(author)
                .build();
        Article article2 = Article.builder()
                .title("ChatGPT")
                .author(author)
                .build();
        Article article3 = Article.builder()
                .title("Spring Native()")
                .author(author)
                .build();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(article1);
        entityManager.persist(article2);
        entityManager.persist(article3);
        entityManager.getTransaction().commit();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Author author = entityManager.find(Author.class, id);
        if (Objects.isNull(author)) {
            resp.sendError(404, "Author with id '%S' not found".formatted(id));
        } else {
            entityManager.remove(author);
        }
        entityManager.getTransaction().commit();
    }
}
