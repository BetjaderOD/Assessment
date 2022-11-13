package mx.edu.utez.assessment.controller;

import mx.edu.utez.assessment.model.students.BeanStudent;
import mx.edu.utez.assessment.model.students.DaoStudent;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.List;
@Path("/api")
public class AvergaeService {
    @GET
    @Path("/average")
    @Produces(value = {"application/json"})
    public List<BeanStudent> getAll(){
        return new DaoStudent().findAverage();
    }
}
