package mx.edu.utez.assessment.controller;

import mx.edu.utez.assessment.model.students.BeanStudent;
import mx.edu.utez.assessment.model.students.DaoStudent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/api")
public class QualificationService {
    Map<String, Object> response = new HashMap<>();
    @GET
    @Path("/qualification")
    @Produces(value = {"application/json"})
    public List<BeanStudent> getAll(){
        return new DaoStudent().findSC();
    }



}
