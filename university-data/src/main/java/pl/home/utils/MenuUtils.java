package pl.home.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MenuUtils {
    MENU_STUDENT("STUDENT"),
    MENU_UNIVERSITY("UNIVERSITY"),
    MENU_ADD_STUDENT("Add student"),
    MENU_REMOVE_STUDENT("Remove student"),
    MENU_OPERATIONS("Operations");


    private final String value;

}
