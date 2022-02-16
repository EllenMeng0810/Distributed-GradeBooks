package comp655.project2.service;

import comp655.project2.entities.GradeBook;
import comp655.project2.entities.Result;
import comp655.project2.repository.GradeBookRepository;
import javax.naming.InitialContext;
import java.util.List;

public class GradeBookService {

    GradeBookRepository bookRepository;

    public GradeBookService(String model) {
        try {
            String lookup = "java:global/"+model+"/GradeBookRepositoryImpl!comp655.project2.repository.GradeBookRepository";
            bookRepository = (GradeBookRepository) new InitialContext().lookup(lookup);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Result<GradeBook> addGradeBook(GradeBook gradeBook) {
        try {
            Result<GradeBook> gradeBookResult = find(gradeBook);
            if (gradeBookResult.getCode().equals("0")) {
                return gradeBookResult;
            }

            gradeBook = bookRepository.addGradeBook(gradeBook);
        } catch (Exception e) {
            return new Result.Builder<GradeBook>().error("add gradebook error :" + e.getMessage(), gradeBook);
        }
        return new Result.Builder<GradeBook>().success("add gradebook success", gradeBook);
    }

    public Result<GradeBook> copyPrimaryBook(GradeBook gradeBook) {
        try {
            GradeBook db = bookRepository.findOne("primary", gradeBook.getId());

            gradeBook.setName(db.getName());
            GradeBook dbBook = bookRepository.find(gradeBook);
            if (null == dbBook) {
                gradeBook = bookRepository.addGradeBook(gradeBook);
                return new Result.Builder<GradeBook>().success("copy primary server gradebook [" + gradeBook.getName() + "] success", gradeBook);
            }
            return new Result.Builder<GradeBook>().error("copy primart server gradebook [" + dbBook.getServer() + "] error,is already exists", dbBook);
        } catch (Exception e) {
            return new Result.Builder<GradeBook>().error("copy primary gradebook error,gradebook is not exists");
        }
    }


    public Result<String> removeGradeBook(GradeBook gradeBook) {
        Result.Builder builder = new Result.Builder<String>();
        try {
            String  errormsg  = "Secondary does not have a copy of the gradebook";
            if("primary".equals(gradeBook.getServer())){
                GradeBook db = bookRepository.findOne("secondary", gradeBook.getId());
                if(null!=db){
                    bookRepository.removeGradeBook(db);
                }
            }
            boolean success = bookRepository.removeGradeBook(gradeBook);
            if(!success && "primary".equals(gradeBook.getServer())){
                errormsg = "No gradebook with the given ID";;
            }
            //The gradebook has been removed from the main server
            return success ? builder.success("The gradebook  has been removed from the " + gradeBook.getServer() + " server ") : builder.error(errormsg);
        } catch (Exception e) {
            return builder.error(e.getMessage());
        }

    }


    public Result<?> listAll(String server) {

        try {
            List<GradeBook> gradeBookList = bookRepository.listAll(server);
            return new Result.Builder().success("get the " + server + " gradebook list success", gradeBookList);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return new Result.Builder().error("get this " + server + " gradebook list error");
    }


    public GradeBook findOne(Integer id, String server) {
        return bookRepository.findOne(server, id);
    }

    public Result<GradeBook> find(GradeBook gradeBook) {
        try {
            GradeBook find = bookRepository.find(gradeBook);
            if (null == find) {
                return new Result.Builder<GradeBook>().error("gradbook [" + gradeBook.getName() + "] is not exists");
            }
            return new Result.Builder<GradeBook>().success(find);
        } catch (Exception e) {
        }
        return new Result.Builder<GradeBook>().error("gradbook [" + gradeBook.getName() + "] is not exists");
    }
}
