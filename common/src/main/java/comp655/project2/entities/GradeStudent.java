package comp655.project2.entities;

import javax.persistence.*;

@Entity
@Table(name = "t_grade_student")
public class GradeStudent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "grade_book_id")
    private Integer gradeBookId;
    @Column(name = "student")
    private String student;
    @Column(name = "grade")
    private String grade;
    @Column(name = "server")
    private String server;

    public GradeStudent(Integer gradeBookId, String student, String grade, String server) {
        this.gradeBookId = gradeBookId;
        this.student = student;
        this.grade = grade;
        this.server = server;
    }

    public GradeStudent() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Integer getGradeBookId() {
        return gradeBookId;
    }

    public void setGradeBookId(Integer gradeBookId) {
        this.gradeBookId = gradeBookId;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
