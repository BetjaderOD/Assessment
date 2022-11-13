package mx.edu.utez.assessment.controller;

import jdk.jfr.Period;
import mx.edu.utez.assessment.model.teachers.BeanTeacher;
import mx.edu.utez.assessment.model.teachers.DaoTeacher;
import mx.edu.utez.assessment.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/teacher")
public class TeacherServices {
    Map<String, Object> response = new HashMap<>();

    @GET
    @Path("/")
    @Produces(value = {"application/json"})
    public List<BeanTeacher> getAll(){
        return new DaoTeacher().findAll();
    }
    @POST
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> save (BeanTeacher teacher){
        Response<BeanTeacher> result = new  DaoTeacher().save(teacher);
        response.put("result", result);
        return response;
    }
    @PUT
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> update (BeanTeacher teacher){
        System.out.println(teacher.getName());
        Response<BeanTeacher> result = new  DaoTeacher().update(teacher);
        response.put("result", result);
        return response;
    }



}
