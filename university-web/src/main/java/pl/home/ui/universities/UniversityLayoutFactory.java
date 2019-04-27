package pl.home.ui.universities;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.home.ui.commons.UIComponentBuilderWithListener;
import pl.home.ui.commons.UniversityMainUI;
import pl.home.ui.students.SavedListener;

import static pl.home.utils.UniversityUtils.MAIN_MENU;
import static pl.home.utils.UniversityUtils.SHOW_ALL_UNIVERSITIES;
import static pl.home.utils.UniversityUtils.SHOW_STATS;

@SpringView(name = UniversityLayoutFactory.NAME, ui = UniversityMainUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View, SavedListener {
    public static final String NAME = "operations";

    private TabSheet tabSheet;

    private final UIComponentBuilderWithListener addUniversityComponentBuilder;

    public UniversityLayoutFactory(@Qualifier("addUniversityLayoutFactory") UIComponentBuilderWithListener addUniversityComponentBuilder) {
        this.addUniversityComponentBuilder = addUniversityComponentBuilder;
    }

    private void addLayout() {
        setMargin(true);

        tabSheet = new TabSheet();
        tabSheet.setWidth("100%");

        Component addUniversityTab = addUniversityComponentBuilder.createComponent(this);
        Component showAllUniversitiesTab = new Label("All universities");
        Component showStatsTab = new Label("Show stats");

        tabSheet.addTab(addUniversityTab, MAIN_MENU.getValue());
        tabSheet.addTab(showAllUniversitiesTab, SHOW_ALL_UNIVERSITIES.getValue());
        tabSheet.addTab(showStatsTab, SHOW_STATS.getValue());

        addComponent(tabSheet);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();
        addLayout();

    }


    @Override
    public void studentSaved() {

    }
}
