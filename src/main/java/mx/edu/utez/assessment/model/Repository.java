package mx.edu.utez.assessment.model;

import mx.edu.utez.assessment.model.students.BeanStudent;
import mx.edu.utez.assessment.utils.Response;

import java.util.List;

public interface Repository <T> {
    List<T> findAll();
    T findById(Long id);
    Response<T> save(T object);
    Response<T> update(T object);
    Response<T> remove(Long id);

    List <T> findSC(String name);

    List<BeanStudent> findSC();

}
