package ru.kpfu.itis.javalab.idrisov.daniyar;

import ru.kpfu.itis.javalab.idrisov.daniyar.datasource.DatabaseConnection;
import ru.kpfu.itis.javalab.idrisov.daniyar.manager.EntityManager;
import ru.kpfu.itis.javalab.idrisov.daniyar.model.User;

import javax.sql.DataSource;

public class Test {

    public static void main(String[] args) {
        DatabaseConnection connection = new DatabaseConnection();
        DataSource dataSource = connection.getDataSource();
	    EntityManager entityManager = new EntityManager(dataSource);
	    entityManager.createTable("account", User.class);
	    User user1 = new User(1L, "Daniyar", "Idrisov", true);
	    User user2 = new User(2L, "Aivar", "Minsafin", false);
	    entityManager.save("account", user1);
        entityManager.save("account", user2);
        User user3 = entityManager.findById("account", User.class, Long.class, 1L);
        System.out.println(user3.getLastName());
        User user4 = entityManager.findById("account", User.class, Long.class, 2L);
        System.out.println(user4.getLastName());
    }

}
