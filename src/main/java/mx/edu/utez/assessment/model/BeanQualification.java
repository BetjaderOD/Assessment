package mx.edu.utez.assessment.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeanQualification {
    int id;
    String subject;
   int  qualification;

        public BeanQualification() {
        }



        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public int getQualification() {
            return qualification;
        }

        public void setQualification(int qualification) {
            this.qualification = qualification;
        }
}
