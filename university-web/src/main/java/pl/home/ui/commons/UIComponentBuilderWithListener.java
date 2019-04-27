package pl.home.ui.commons;

import com.vaadin.ui.Component;
import pl.home.ui.students.SavedListener;

public interface UIComponentBuilderWithListener{
    Component createComponent(SavedListener savedListener);
}
