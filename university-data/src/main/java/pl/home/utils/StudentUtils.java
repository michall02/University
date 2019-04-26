package pl.home.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StudentUtils {

    MAIN_MENU("Registration"),
    SHOW_ALL_STUDENTS("Show all students"),
    FIRST_NAME("First name"),
    LAST_NAME("Last name"),
    AGE("Age"),
    GENDER("Gender"),
    SAVE("Save"),
    CLEAR("Clear");

    private String value;
}
