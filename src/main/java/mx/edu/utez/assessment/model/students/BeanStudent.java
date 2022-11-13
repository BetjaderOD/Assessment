package mx.edu.utez.assessment.model.students;

import mx.edu.utez.assessment.model.BeanQualification;

public class BeanStudent {
    int id;
    String name;
    String surnames;
    String date_of_birth;
    String curp;
    String matricula;
    BeanQualification qualification;

    public BeanStudent() {
    }

    public BeanStudent(int id, String name, String surnames, String date_of_birth, String curp, String matricula, BeanQualification qualification) {
        this.id = id;
        this.name = name;
        this.surnames = surnames;
        this.date_of_birth = date_of_birth;
        this.curp = curp;
        this.matricula = matricula;
        this.qualification = qualification;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public BeanQualification getQualification() {
        return qualification;
    }

    public void setQualification(BeanQualification qualification) {
        this.qualification = qualification;
    }
}
