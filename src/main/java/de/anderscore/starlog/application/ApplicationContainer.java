package de.anderscore.starlog.application;

import de.anderscore.starlog.persistence.LogRepository;
import de.anderscore.starlog.persistence.PersistenceContext;

import javax.persistence.EntityManager;
import java.util.logging.Logger;

import static de.anderscore.starlog.persistence.PersistenceContext.entityManager;

/**
 * Created by MBecker on 20.01.2017.
 */
public class ApplicationContainer {

    private static final Logger logger = Logger.getLogger(ApplicationContainer.class.getName());

    private static boolean initialized;
    private static LogRepository logRepository;

    public synchronized static void init() {
        if (initialized) {
            return;
        }

        initialized = true;

        logger.info("Initializing Application Container ...");

        logRepository = new LogRepository(entityManager());

        logger.info("Initializing Application Container ... DONE");
    }

    public synchronized static void tearDown() {
        PersistenceContext.tearDown();
    }

    public static LogRepository logRepository() {return logRepository;}

}
