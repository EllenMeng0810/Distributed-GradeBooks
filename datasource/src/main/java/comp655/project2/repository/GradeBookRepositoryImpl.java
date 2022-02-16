package comp655.project2.repository;

import comp655.project2.entities.GradeBook;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class GradeBookRepositoryImpl implements GradeBookRepository {

    @PersistenceContext(unitName = "book-ds")
    private EntityManager entityManager;

    public GradeBookRepositoryImpl() {
        //entityManager = ServerEntityManagerFactory.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public GradeBook addGradeBook(GradeBook gradeBook) {
        try {
            entityManager.persist(gradeBook);
            return gradeBook;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gradeBook;
    }

    @Override
    public boolean removeGradeBook(GradeBook gradeBook) {
        try {
            TypedQuery<GradeBook> query = entityManager.createQuery("select b from GradeBook as b where b.server=:server and b.id=:id", GradeBook.class);

            query.setParameter("server", gradeBook.getServer());
            query.setParameter("id", gradeBook.getId());
            gradeBook = query.getSingleResult();
            entityManager.remove(entityManager.merge(gradeBook));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<GradeBook> listAll(String server) {
        TypedQuery<GradeBook> query = entityManager.createQuery("select b from GradeBook as b where b.server=:server", GradeBook.class);

        query.setParameter("server", server);
        List<GradeBook> gradeBookList = query.getResultList();
        return gradeBookList;
    }

    @Override
    public GradeBook findOne(String server, Integer id) {
        try {
            TypedQuery<GradeBook> query = entityManager.createQuery("select b from GradeBook as b where b.id=:id and b.server=:server", GradeBook.class);
            query.setParameter("id", id);
            query.setParameter("server", server);
            return query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public GradeBook find(GradeBook gradeBook) {
        try {
            TypedQuery<GradeBook> query = entityManager.createQuery("select b from GradeBook as b where b.name=:name and b.server=:server", GradeBook.class);
            query.setParameter("name", gradeBook.getName());
            query.setParameter("server", gradeBook.getServer());
            return query.getSingleResult();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
