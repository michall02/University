package pl.home.bootstrap.dataloaders;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.home.models.Student;
import pl.home.models.University;
import pl.home.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final StudentRepository studentRepository;


    public DataLoader(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            Optional<Student> s1 = studentRepository.findById(1L);
            Optional<Student> s2 = studentRepository.findById(2L);
            Optional<Student> s3 = studentRepository.findById(3L);
            Optional<Student> s4 = studentRepository.findById(4L);
            Optional<Student> s5 = studentRepository.findById(5L);
            Optional<Student> s6 = studentRepository.findById(6L);
            Optional<Student> s7 = studentRepository.findById(7L);
            Optional<Student> s8 = studentRepository.findById(8L);
            Optional<Student> s9 = studentRepository.findById(9L);
            Optional<Student> s10 = studentRepository.findById(10L);
            if(isStudentsPresent(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10)){
                studentRepository.saveAll(getStudents());
            }


    }

    private boolean isStudentsPresent(Optional<Student> s1, Optional<Student> s2, Optional<Student> s3,
                                      Optional<Student> s4, Optional<Student> s5, Optional<Student> s6,
                                      Optional<Student> s7, Optional<Student> s8, Optional<Student> s9,
                                      Optional<Student> s10) {
        return !s1.isPresent()&&!s2.isPresent()&&!s3.isPresent()&&!s4.isPresent()&&!s5.isPresent()
                &&!s6.isPresent()&&!s7.isPresent()&&!s8.isPresent()&&!s9.isPresent()&&!s10.isPresent();
    }

    private List<Student> getStudents() {
        List<Student> students = new ArrayList<>();

        University u1 = new University();
        u1.setUniversityName("MIT");
        u1.setUniversityCountry("USA");
        u1.setUniversityCity("Boston");
        University u2 = new University();
        u2.setUniversityName("PW");
        u2.setUniversityCountry("Poland");
        u2.setUniversityCity("Warsaw");
        University u3 = new University();
        u3.setUniversityName("University of Moscow");
        u3.setUniversityCountry("Russia");
        u3.setUniversityCity("Moscow");
        University u4 = new University();
        u4.setUniversityName("KUL");
        u4.setUniversityCountry("Poland");
        u4.setUniversityCity("Lublin");

        Student st1 = new Student();
        setStudent(u1, st1, "Janusz", "Zachara", "70", "male");
        students.add(st1);

        Student st2 = new Student();
        setStudent(u1,st2,"Aldona","Zalewska","45","female");
        students.add(st2);

        Student st3 = new Student();
        setStudent(u1,st3,"Jakub","Dębski","30","male");
        students.add(st3);

        Student st4 = new Student();
        setStudent(u2,st4,"Adam","Malysz","45","male");
        students.add(st4);

        Student st5 = new Student();
        setStudent(u2,st5,"Agnieszka","Radwanska","33","female");
        students.add(st5);

        Student st6 = new Student();
        setStudent(u2,st6,"Krzysztof","Gonciarz","33","male");
        students.add(st6);

        Student st7 = new Student();
        setStudent(u2,st7,"Tomasz","Kot","40","male");
        students.add(st7);

        Student st8 = new Student();
        setStudent(u3,st8,"Vladimir","Putin","55","male");
        students.add(st8);

        Student st9 = new Student();
        setStudent(u3,st9,"Iwan","the Terrible","99","other");
        students.add(st9);

        Student st10 = new Student();
        setStudent(u4,st10,"Krzysztof","Krawczyk","75","male");
        students.add(st10);

        return students;
    }

    private void setStudent(University u1, Student st1, String firstName, String lastName, String age, String gender) {
        st1.setFirstName(firstName);
        st1.setLastName(lastName);
        st1.setAge(age);
        st1.setGender(gender);
        st1.setUniversity(u1);
    }
}
