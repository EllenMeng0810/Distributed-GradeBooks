package comp655.project2.repository;

import comp655.project2.entities.GradeStudent;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class GradeStudentRepositoryImpl implements GradeStudentRepository {

    @PersistenceContext(unitName = "book-ds")
    EntityManager entityManager;

    public GradeStudentRepositoryImpl() {
        //this.entityManager = ServerEntityManagerFactory.getInstance().getEntityManagerFactory().createEntityManager();
    }

    @Override
    public List<GradeStudent> listAll(String server, Integer bookId) {
        TypedQuery<GradeStudent> query = entityManager.createQuery("select stu from GradeStudent as stu where stu.server=:server and  stu.gradeBookId=:gradeBookId", GradeStudent.class);
        query.setParameter("gradeBookId", bookId);
        query.setParameter("server", server);
        return query.getResultList();
    }

    @Override
    public GradeStudent findOne(String server, Integer bookId, String student) {
        try {
            TypedQuery<GradeStudent> query = entityManager.createQuery("select stu from GradeStudent as stu where stu.server=:server and  stu.gradeBookId=:gradeBookId and stu.student=:student ", GradeStudent.class);
            query.setParameter("gradeBookId", bookId);
            query.setParameter("student", student);
            query.setParameter("server", server);
            return query.getSingleResult();
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public GradeStudent addStudentGrade(GradeStudent gradeStudent) {
        GradeStudent old = findOne(gradeStudent.getServer(), gradeStudent.getGradeBookId(), gradeStudent.getStudent());
        if(null !=old){
            old.setGrade(gradeStudent.getGrade());
            entityManager.merge(old);
            return old;
        }
        entityManager.persist(gradeStudent);
        return gradeStudent;
    }

    public boolean remove(GradeStudent gradeStudent) {
        try {
            entityManager.remove(entityManager.merge(gradeStudent));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
