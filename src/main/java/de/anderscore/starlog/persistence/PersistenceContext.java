package de.anderscore.starlog.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by MBecker on 20.01.2017.
 */
public class PersistenceContext {

    private static final Logger logger = Logger.getLogger(PersistenceContext.class.getName());

    private static EntityManager entityManager;
    private static EntityManagerFactory emFactory;

    public static synchronized EntityManager entityManager() {
        if (entityManager != null) {
            return entityManager;
        }

        Map<String, String> props = new HashMap<>();
        props.put("hibernate.connection.driver_class", "org.h2.Driver");
        props.put("hibernate.connection.url", "jdbc:h2:~/.h2/starlog.h2");
        props.put("hibernate.connection.username", "rtuStarlog");
        props.put("hibernate.connection.password", "starlogger");
        props.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.put("hibernate.show-sql", "true");
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("provider", "org.hibernate.ejb.HibernatePersistence");

        logger.info("Loading Persistence Context with settings ...");
        props.entrySet().forEach(entry -> logger.info(entry.getKey() + ": " + entry.getValue()));

        emFactory = Persistence.createEntityManagerFactory("starlogPU", props);

        return entityManager = emFactory.createEntityManager();
    }

    public static synchronized void tearDown() {
        emFactory.close();
        Connection connection = entityManager.unwrap(Connection.class);
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        entityManager.close();
    }

}
