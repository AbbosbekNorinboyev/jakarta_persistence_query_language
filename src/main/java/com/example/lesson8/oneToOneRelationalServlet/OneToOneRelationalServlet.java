package com.example.lesson8.oneToOneRelationalServlet;

import com.example.lesson8.entity.AuthUser;
import com.example.lesson8.entity.Employee;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(
        name = "com.example.lesson8.oneToOneRelationalServlet.OneToOneRelationalServlet",
        value = "/O2ORServlet"
)
public class OneToOneRelationalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        List<AuthUser> authUserList = entityManager.createQuery("select a from AuthUser a", AuthUser.class).getResultList();
        System.out.println("authUserList: " + authUserList);
//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
//                .setPrettyPrinting()
//                .create();
//        resp.getWriter().println(gson.toJson(groupsList));
        entityManager.getTransaction().commit();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String phoneNumber = req.getParameter("phone_number");
        String firstName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String address = req.getParameter("address");
        Employee employee = Employee.builder()
                .first_name(firstName)
                .last_name(lastName)
                .address(address)
                .build();
        AuthUser authUser = AuthUser.builder()
                .email(email)
                .password(password)
                .phone_number(phoneNumber)
                .employee(employee)
                .build();
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
//        entityManager.persist(employee);
        entityManager.persist(authUser);
        entityManager.getTransaction().commit();
    }
}

class LocalDateTimeTypeAdapter extends TypeAdapter<LocalDateTime> {
    @Override
    public void write(JsonWriter jsonWriter, LocalDateTime localDateTime) throws IOException {
        jsonWriter.value(localDateTime.toString());
    }

    @Override
    public LocalDateTime read(JsonReader jsonReader) throws IOException {
        return LocalDateTime.parse(jsonReader.nextString());
    }
}
