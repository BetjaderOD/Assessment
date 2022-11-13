package mx.edu.utez.assessment.controller;

import mx.edu.utez.assessment.model.students.BeanStudent;
import mx.edu.utez.assessment.model.students.DaoStudent;
import mx.edu.utez.assessment.model.teachers.BeanTeacher;
import mx.edu.utez.assessment.model.teachers.DaoTeacher;
import mx.edu.utez.assessment.utils.Response;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api/student")
public class StudentServicies {
        Map<String, Object> response = new HashMap<>();
        @GET
        @Path("/")
        @Produces(value = {"application/json"})
        public List<BeanStudent> getAll(){
            return new DaoStudent().findAll();
        }
        @POST
        @Path("/")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Map<String, Object> save (BeanStudent student){
                Response<BeanStudent> result = new  DaoStudent().save(student);
                response.put("result", result);
                return response;
        }
        @PUT
        @Path("/")
        @Consumes(MediaType.APPLICATION_JSON)
        @Produces(MediaType.APPLICATION_JSON)
        public Map<String, Object> update (BeanStudent student){

                Response<BeanStudent> result = new  DaoStudent().update(student);
                response.put("result", result);
                return response;
        }



}
