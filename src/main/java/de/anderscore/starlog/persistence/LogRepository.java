package de.anderscore.starlog.persistence;

import de.anderscore.starlog.model.Log;

import javax.persistence.EntityManager;

import static java.util.UUID.fromString;

/**
 * Created by MBecker on 20.01.2017.
 */
public class LogRepository extends AbstractRepository<Log> {


    public LogRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    Log get(String identifier) {
        return entityManager.find(Log.class, fromString(identifier));
    }


}
