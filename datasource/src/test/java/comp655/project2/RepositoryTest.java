package comp655.project2;

import comp655.project2.entities.GradeBook;
import comp655.project2.repository.GradeBookRepository;
import comp655.project2.repository.ServerEntityManagerFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.List;
public class RepositoryTest {
    EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction transaction;
    GradeBookRepository gradeBookRepository;

    private static final String PRIMARY_SERVER="primary";
    private static final String SECONDARY_SERVER="secondary";
    @Before
    public void init(){
        entityManagerFactory = ServerEntityManagerFactory.getInstance().getEntityManagerFactory();
        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
        //gradeBookRepository = new GradeBookRepositoryImpl(entityManager);
    }

    @After
    public void destroy(){
        transaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void query(){

        List<GradeBook> gradeBookList = gradeBookRepository.listAll(PRIMARY_SERVER);
        System.out.println(gradeBookList.size());
    }

    @Test
    public void createBook(){
        GradeBook gradeBook = new GradeBook("Math Grad",PRIMARY_SERVER);
        gradeBookRepository.addGradeBook(gradeBook);
        gradeBook = new GradeBook("Language Grad",PRIMARY_SERVER);
        gradeBookRepository.addGradeBook(gradeBook);
        System.out.println(gradeBook.getId());
    }
    @Test
    public void createSecondaryBook(){
        Integer id = 1;
        GradeBook gradeBook =  gradeBookRepository.findOne(PRIMARY_SERVER,id);
        GradeBook copyBook = new GradeBook(gradeBook.getId(),gradeBook.getName(),SECONDARY_SERVER);
        gradeBookRepository.addGradeBook(copyBook);
        System.out.println(copyBook);
    }
    @Test
    public void removeGradeBook(){
        GradeBook gradeBook = new GradeBook();
        gradeBook.setId(1);
        boolean flag = gradeBookRepository.removeGradeBook(gradeBook);
        System.out.println(flag);
    }
}
