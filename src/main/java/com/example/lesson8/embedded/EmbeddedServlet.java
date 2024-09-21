package com.example.lesson8.embedded;

import com.example.lesson8.entity.Address;
import com.example.lesson8.entity.Article;
import com.example.lesson8.entity.Employee2;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(
        name = "com.example.lesson8.embedded.EmbeddedServlet",
        value = "/Embedded"
)
public class EmbeddedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Address address = Address.builder()
                .country_name("Uzbekistan")
                .city_name("Tashkent")
                .zip_code("1234567")
                .build();
        Employee2 employee2 = Employee2.builder()
                .email("john.lgd65@gmail.com")
                .address(address)
                .build();
        entityManager.persist(employee2);
//        long count = (long) entityManager.createQuery("select count(a)from Article a").getSingleResult();
//        System.out.println("count: " + count);
//        List articles = entityManager.createQuery("select upper(a.title) from Article a", Article.class).getResultList();
//        System.out.println("articles upper: " + articles);
//        List<Article> selectAFromArticleA = entityManager.createQuery("select lower(a.title) from Article a", Article.class).getResultList();
//        System.out.println("selectAFromArticleA lower: " + selectAFromArticleA);
//        List<Article> articles1 = entityManager.createQuery("select substring(a.title, 2) from Article a", Article.class).getResultList();
//        System.out.println("articles1 substring: " + articles1);
        TypedQuery<Article> fromArticle = entityManager.createQuery("from Article ", Article.class);
        int page = Integer.parseInt(req.getParameter("page"));
        int size = Integer.parseInt(req.getParameter("size"));
        fromArticle.setFirstResult(page);
        fromArticle.setMaxResults(size);
        System.out.println("fromArticle.getResultList(): " + fromArticle.getResultList());
        entityManager.getTransaction().commit();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
