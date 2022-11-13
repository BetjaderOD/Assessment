package mx.edu.utez.assessment.model.students;

import mx.edu.utez.assessment.model.BeanQualification;
import mx.edu.utez.assessment.model.Repository;
import mx.edu.utez.assessment.model.teachers.BeanTeacher;
import mx.edu.utez.assessment.model.teachers.DaoTeacher;
import mx.edu.utez.assessment.utils.MySQLConnection;
import mx.edu.utez.assessment.utils.Response;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoStudent implements Repository<BeanStudent> {
    Connection conn;
    PreparedStatement ps;
    ResultSet rs;
    MySQLConnection client = new MySQLConnection();
    @Override
    public List<BeanStudent> findAll() {
        List<BeanStudent> students = new ArrayList<>();
        BeanStudent student = null;
        try {
            conn = client.getConnection();
            String query = "SELECT * FROM students;";
            ps = conn.prepareStatement(query);
            rs= ps.executeQuery();
            while (rs.next()){
                student = new BeanStudent();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSurnames(rs.getString("surnames"));
                student.setDate_of_birth(rs.getString("date_of_birth"));
                student.setCurp(rs.getString("curp"));
                student.setMatricula(rs.getString("matricula"));
                students.add(student);
            }
        } catch (Exception e){
            System.out.println("Error -> findAll"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return students;
    }


    @Override
    public BeanStudent findById(Long id) {
        return null;
    }

    @Override
    public Response<BeanStudent> save(BeanStudent object) {
        try{
            if (verifyCurp(object.getCurp())){
                return new Response<BeanStudent>(200, "El CURP ya existe", true);
            }
            if (verifyMatricula(object.getMatricula())){
                return new Response<BeanStudent>(200, "La matricula ya existe", true);
            }
            conn = client.getConnection();
            String query = "INSERT INTO students (name, surnames, date_of_birth, curp, matricula) VALUES (?,?,?,?,?);";
            ps = conn.prepareStatement(query);
            ps.setString(1, object.getName());
            ps.setString(2, object.getSurnames());
            ps.setString(3, object.getDate_of_birth());
            ps.setString(4, object.getCurp());
            ps.setString(5, object.getMatricula());
            if(ps.executeUpdate() == 1){
                return new Response<BeanStudent>(200, "Registrado correctamente", object, false);
            } else {
                return new Response<>(200, "Error al registrar", object, true);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoStudent.class.getName())
                    .log(Level.SEVERE, "Error -> save: "+ e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        } finally {
            client.close(conn,ps,rs);
        }
    }

    @Override
    public Response<BeanStudent> update(BeanStudent object) {
        try{
            conn=client.getConnection();
            String query = "UPDATE students SET name=?, surnames=?, date_of_birth=?, curp=?, matricula=? WHERE id=?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, object.getName());
            ps.setString(2, object.getSurnames());
            ps.setString(3, object.getDate_of_birth());
            ps.setString(4, object.getCurp());
            ps.setString(5, object.getMatricula());
            ps.setInt(6, object.getId());
            if(ps.executeUpdate() == 1){
                return new Response<BeanStudent>(200, "Actualizado correctamente", object, false);
            } else {
                return new Response<>(200, "Error al actualizar", object, true);
            }

        }catch (SQLException e){
            Logger.getLogger(DaoStudent.class.getName())
                    .log(Level.SEVERE, "Error -> save: "+ e.getMessage());
            return new Response<>(400, "Error con el servidor", null, true);
        } finally {
            client.close(conn,ps,rs);
        }

    }

    @Override
    public Response<BeanStudent> remove(Long id) {
        return null;
    }

    @Override
    public List<BeanStudent> findSC(String name) {
        return null;
    }


    @Override
    public List<BeanStudent> findSC() {
        List<BeanStudent> students = new ArrayList<>();
        BeanStudent student = null;
        BeanQualification qualification = null;
        try{
            conn = client.getConnection();
            String query = "SELECT students.name, students.surnames, evaluations.subject, evaluations.qualification FROM students INNER JOIN evaluations ON students.id = evaluations.id_student;";
            ps = conn.prepareStatement(query);
            rs= ps.executeQuery();
            while (rs.next()){
                student = new BeanStudent();
                qualification = new BeanQualification();
                student.setName(rs.getString("name"));
                student.setSurnames(rs.getString("surnames"));
                qualification.setSubject(rs.getString("subject"));
                qualification.setQualification(rs.getInt("qualification"));
                student.setQualification(qualification);
                students.add(student);
            }
        }catch (Exception e){
            System.out.println("Error -> findAll"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return students;

    }

    public List<BeanStudent> findAverage(){
        List<BeanStudent> students = new ArrayList<>();
        BeanStudent student = null;
        BeanQualification qualification = null;
        try{
            qualification = new BeanQualification();
            conn = client.getConnection();
            String query = "SELECT students.name, students.surnames, AVG(evaluations.qualification) AS promedio FROM students INNER JOIN evaluations ON students.id = evaluations.id_student GROUP BY students.name;";
            ps = conn.prepareStatement(query);
            rs= ps.executeQuery();
            while (rs.next()){
                student = new BeanStudent();
                student.setName(rs.getString("name"));
                student.setSurnames(rs.getString("surnames"));
                qualification.setQualification(rs.getInt("promedio"));
                student.setQualification(qualification);
                students.add(student);
            }
        }catch (Exception e){
            System.out.println("Error -> findAll"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return students;
    }

    public boolean verifyCurp(String curp){
        try{
            conn = client.getConnection();
            String query = "SELECT * FROM students WHERE curp = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, curp);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println("Error -> verifyCurp"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return false;
    }

    public boolean verifyMatricula(String matricula){
        try{
            conn = client.getConnection();
            String query = "SELECT * FROM students WHERE matricula = ?;";
            ps = conn.prepareStatement(query);
            ps.setString(1, matricula);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        }catch (Exception e){
            System.out.println("Error -> verifyMatricula"+ e.getMessage());
        } finally {
            client.close(conn,ps,rs);
        }
        return false;
    }

}
