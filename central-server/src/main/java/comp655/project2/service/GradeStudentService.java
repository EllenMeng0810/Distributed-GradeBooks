package comp655.project2.service;

import comp655.project2.entities.GradeBook;
import comp655.project2.entities.GradeStudent;
import comp655.project2.entities.Result;
import comp655.project2.repository.GradeStudentRepository;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

public class GradeStudentService {

    GradeStudentRepository studentRepository;

    GradeBookService gradeBookService;

    public GradeStudentService(String model) {
        try {
            String lookup= "java:global/"+model+"/GradeStudentRepositoryImpl!comp655.project2.repository.GradeStudentRepository";
            studentRepository = (GradeStudentRepository) new InitialContext().lookup(lookup);
        } catch (NamingException e) {
            e.printStackTrace();
        }
        gradeBookService = new GradeBookService(model);
    }

    public Result<?> listAll(String server, Integer bookId) {
        List<GradeStudent> all = studentRepository.listAll(server, bookId);
        return new Result.Builder().success("gradebook id " + bookId + ",list all student grade size:" + all.size(), all);
    }

    public Result<GradeStudent> findOne(String server, Integer bookId, String student) {
        GradeStudent gradeStudent = studentRepository.findOne(server, bookId, student);
        if (gradeStudent != null)
            return new Result.Builder().success(gradeStudent);

        return new Result.Builder().error("student ["+student+"] grade not in "+server+"server grades ["+bookId+"]");
    }

    public Result<?> addStudentGrade(GradeStudent gradeStudent) {

        // 如果当前成绩册有副本需要向副本添加数据
        if ("primary".equals(gradeStudent.getServer())) {
            //判断是否有副本
            GradeBook gradeBook = gradeBookService.findOne(gradeStudent.getGradeBookId(), "secondary");
            if (null != gradeBook) {
                //添加副本
                GradeStudent copy = new GradeStudent();
                copy.setServer("secondary");
                copy.setId(gradeStudent.getId());
                copy.setStudent(gradeStudent.getStudent());
                copy.setGrade(gradeStudent.getGrade());
                copy.setGradeBookId(gradeStudent.getGradeBookId());
                studentRepository.addStudentGrade(copy);
            }
        }

        gradeStudent = studentRepository.addStudentGrade(gradeStudent);

        return new Result.Builder().success("add or update student grade success", gradeStudent);
    }

    public Result remove(GradeStudent gradeStudent) {
        Result.Builder builder = new Result.Builder<String>();
        // 如果当前成绩册有副本需要删除副本数据
        if ("primary".equals(gradeStudent.getServer())) {
            //判断是否有副本
            GradeBook gradeBook = gradeBookService.findOne(gradeStudent.getGradeBookId(), "secondary");
            if (null != gradeBook) {
                //添加副本
                Result<GradeStudent> result  =  findOne("secondary", gradeStudent.getGradeBookId(),
                        gradeStudent.getStudent());
                if("0".equals(result.getCode())){
                    studentRepository.remove(result.getData());
                }
            }
        }
        boolean success = studentRepository.remove(gradeStudent);
        return success ? builder.success("remove student grade [" + gradeStudent.getStudent() + "] from the " + gradeStudent.getServer() + " success!") : builder.error();
    }
}
