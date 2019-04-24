package pl.home.ui.commons;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("/main")
public class UniversityMainUI extends VerticalLayout {

    public UniversityMainUI() {
        VerticalLayout root = new VerticalLayout();

        root.addComponentAsFirst(new Label("..."));

        add(root);
    }
}