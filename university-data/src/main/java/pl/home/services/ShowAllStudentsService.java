package pl.home.services;

import pl.home.models.Student;

import java.util.List;

public interface ShowAllStudentsService {
    List<Student> getAllStudent();

    List<Student> findByLastNameStartsWithIgnoreCase(String lastName);
}
