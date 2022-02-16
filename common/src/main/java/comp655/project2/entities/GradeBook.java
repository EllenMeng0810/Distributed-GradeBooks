package comp655.project2.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "t_grade_book")
@IdClass(GradeBookKey.class)
public class GradeBook implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.TABLE)
    @GeneratedValue(generator  = "gradeBookIdStrategy")
    @GenericGenerator(name = "gradeBookIdStrategy", strategy = "comp655.project2.id.GradeIdentifierGenerator")
    private Integer id;
    @Id
    @Column(name = "name")
    private String name;
    @Id
    @Column(name = "server")
    private String server;



    public GradeBook(String name, String server) {
        this.name = name;
        this.server = server;
    }

    public GradeBook() {
    }

    public GradeBook(Integer id, String name, String server) {
        this.id = id;
        this.name = name;
        this.server = server;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }


    @Override
    public String toString() {
        return "GradeBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", server='" + server + '\'' +
                '}';
    }
}
