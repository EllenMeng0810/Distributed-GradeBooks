package comp655.project2.repository;

import comp655.project2.entities.GradeBook;

import java.util.List;

public interface GradeBookRepository {

    GradeBook addGradeBook(GradeBook gradeBook);

    boolean removeGradeBook(GradeBook gradeBook);

    List<GradeBook> listAll(String server);

    GradeBook findOne(String server,Integer id);

    GradeBook find(GradeBook gradeBook);
}
