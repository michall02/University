package pl.home.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum NotificationMessages {
    STUDENT_SAVE_VALIDATION_ERROR_TITLE("Error"),
    STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION("All fields must be filled"),
    STUDENT_SAVE_VALIDATION_ACCEPTED_TITLE("Saved"),
    STUDENT_SAVE_VALIDATION_ACCEPTED_DESCRIPTION("Student has been added"),
    STUDENT_REMOVE_SUCCESS_TITLE("Removed"),
    STUDENT_REMOVE_SUCCESS_DESCRIPTION("Student(s) successfully removed"),
    STUDENT_SAVE_INVALID_TITLE("Error"),
    STUDENT_SAVE_INVALID_DESCRIPTION("Must have at least one university"),
    UNIVERSITY_SAVE_VALIDATION_ERROR_TITLE("Error"),
    UNIVERSITY_SAVE_VALIDATION_ERROR_DESCRIPTION("All fields must be filled"),
    UNIVERSITY_SAVE_VALIDATION_ACCEPTED_TITLE("Saved"),
    UNIVERSITY_SAVE_VALIDATION_ACCEPTED_DESCRIPTION("University has been added"),
    UNIVERSITY_REMOVE_SUCCESS_TITLE("Removed"),
    UNIVERSITY_REMOVE_SUCCESS_DESCRIPTION("University(ies) successfully removed");

    private String value;

}
