package comp655.project2.repository;

import comp655.project2.entities.GradeStudent;

import java.util.List;

public interface GradeStudentRepository {

    List<GradeStudent> listAll(String server,Integer bookId);

    GradeStudent findOne(String server,Integer bookId,String student);

    GradeStudent addStudentGrade(GradeStudent gradeStudent);

    boolean remove(GradeStudent gradeStudent);
}
