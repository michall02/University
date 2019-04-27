package pl.home.ui.commons;

import com.vaadin.ui.Component;
import pl.home.ui.students.StudentSavedListener;

public interface UIComponentBuilderWithListener{
    Component createComponent(StudentSavedListener studentSavedListener);
}
