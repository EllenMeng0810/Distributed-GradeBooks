package comp655.project2.repository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServerEntityManagerFactory {

    private static ServerEntityManagerFactory serverEntityManagerFactory;
    private static EntityManagerFactory entityManagerFactory;

    private ServerEntityManagerFactory(){

    }
    public static ServerEntityManagerFactory getInstance() {
        if (null == serverEntityManagerFactory) {
            serverEntityManagerFactory = new ServerEntityManagerFactory();
            entityManagerFactory = Persistence.createEntityManagerFactory("book-ds");
        }
        return serverEntityManagerFactory;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
