package comp655.project2.primary.rest;

import com.sun.jersey.spi.resource.Singleton;
import comp655.project2.entities.GradeBook;
import comp655.project2.entities.GradeStudent;
import comp655.project2.entities.Result;
import comp655.project2.service.GradeBookService;
import comp655.project2.service.GradeStudentService;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Singleton
public class GradeBookREST {

    private static final String SERVER_NAME = "primary";

    private GradeBookService bookService;
    private GradeStudentService studentService;

    @PostConstruct
    public void initService() {
        bookService = new GradeBookService(SERVER_NAME);
        studentService = new GradeStudentService(SERVER_NAME);
    }

    @Path("/gradebook")
    @Produces(MediaType.APPLICATION_JSON)
    @GET
    public Response listAll() {
        Result result = bookService.listAll(SERVER_NAME);
        return Response.status(200).entity(result).build();
    }

    @PUT
    @Path("/gradebook/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putCreate(@PathParam("name") String name) {

        GradeBook gradeBook = new GradeBook(name, SERVER_NAME);
        Result result =  bookService.addGradeBook(gradeBook);
        return Response.status(200).entity(result).build();
    }

    @POST
    @Path("/gradebook/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postCreate(@PathParam("name") String name) {
        GradeBook gradeBook = new GradeBook(name, SERVER_NAME);
        Result result = bookService.addGradeBook(gradeBook);
        return Response.status(200).entity(result).build();
    }

    @DELETE
    @Path("/gradebook/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id) {
        GradeBook gradeBook = new GradeBook();
        gradeBook.setServer(SERVER_NAME);
        gradeBook.setId(id);
        Result result = bookService.removeGradeBook(gradeBook);
        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/gradebook/{id}/student")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllStudent(@PathParam("id") Integer id) {
        Result result = studentService.listAll(SERVER_NAME, id);

        return Response.status(200).entity(result).build();
    }

    @GET
    @Path("/gradebook/{id}/student/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOne(@PathParam("id") Integer id, @PathParam("name") String name) {
        Result result= studentService.findOne(SERVER_NAME, id, name);
        return Response.status(200).entity(result).build();
    }

    @DELETE
    @Path("/gradebook/{id}/student/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteGradeBookStudent(@PathParam("id") Integer id, @PathParam("name") String name) {
        Result<GradeStudent> result  = studentService.findOne(SERVER_NAME, id, name);
        if("0".equals(result.getCode())){
            result = studentService.remove(result.getData());
        }
        return Response.status(200).entity(result).build();
    }

    @PUT
    @Path("/gradebook/{id}/student/{name}/grade/{grade}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response putStudentGrade(@PathParam("id") Integer id, @PathParam("name") String name, @PathParam("grade") String grade) {
        GradeStudent gradeStudent = new GradeStudent(id, name, grade, SERVER_NAME);
        Result result = studentService.addStudentGrade(gradeStudent);
        return Response.status(200).entity(result).build();
    }

    @POST
    @Path("/gradebook/{id}/student/{name}/grade/{grade}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response postStudentGrade(@PathParam("id") Integer id, @PathParam("name") String name, @PathParam("grade") String grade) {
        return putStudentGrade(id, name, grade);
    }

}
