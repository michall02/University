package pl.home.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UniversityUtils {

    UNIVERSITY_NAME("Name"),
    UNIVERSITY_COUNTRY("Country"),
    UNIVERSITY_CITY("City"),
    MAIN_MENU("Add new university"),
    SHOW_ALL_UNIVERSITIES("Show all universities"),
    SHOW_STATS("Show statistics");

    private String value;
}
