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
    STUDENT_REMOVE_SUCCESS_DESCRIPTION("Student(s) successfully removed");

    private String value;

}
