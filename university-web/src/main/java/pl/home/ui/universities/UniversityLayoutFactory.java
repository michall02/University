package pl.home.ui.universities;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.home.i18helper.I18Helper;
import pl.home.ui.commons.UIComponentBuilder;
import pl.home.ui.commons.UIComponentBuilderWithListener;
import pl.home.ui.commons.UniversityMainUI;
import pl.home.ui.students.SavedListener;
import pl.home.ui.students.UITableRefresher;

import java.util.Locale;

@SpringView(name = UniversityLayoutFactory.NAME, ui = UniversityMainUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View, SavedListener {
    public static final String NAME = "operations";

    private TabSheet tabSheet;

    private Locale locale = new Locale(UniversityMainUI.LOCALE);
    private I18Helper i18Helper = new I18Helper(locale);

    private final UIComponentBuilderWithListener addUniversityComponentBuilder;
    private final UIComponentBuilder showAllUniversitiesComponentBuilder;
    private final UIComponentBuilder statsUniversityComponentBuilder;
    private final UITableRefresher showAllUniversitiesRefreshTable;
    private final UITableRefresher statsUniversityRefreshTable;

    public UniversityLayoutFactory(@Qualifier("addUniversityLayoutFactory") UIComponentBuilderWithListener addUniversityComponentBuilder,
                                   @Qualifier("showAllUniversitiesLayoutFactory") UIComponentBuilder showAllUniversitiesComponentBuilder,
                                   @Qualifier("statsUniversityLayoutFactory") UIComponentBuilder statsUniversityComponentBuilder,
                                   @Qualifier("showAllUniversitiesLayoutFactory") UITableRefresher showAllUniversitiesRefreshTable,
                                   @Qualifier("statsUniversityLayoutFactory") UITableRefresher statsUniversityRefreshTable) {
        this.addUniversityComponentBuilder = addUniversityComponentBuilder;
        this.showAllUniversitiesComponentBuilder = showAllUniversitiesComponentBuilder;
        this.statsUniversityComponentBuilder = statsUniversityComponentBuilder;
        this.showAllUniversitiesRefreshTable = showAllUniversitiesRefreshTable;
        this.statsUniversityRefreshTable = statsUniversityRefreshTable;
    }

    private void addLayout() {
        setMargin(true);

        Component addUniversityTab = addUniversityComponentBuilder.createComponent(this);
        Component showAllUniversitiesTab = showAllUniversitiesComponentBuilder.createComponent();
        Component showStatsTab = statsUniversityComponentBuilder.createComponent();

        tabSheet = new TabSheet();
        tabSheet.setWidth("100%");

        tabSheet.addTab(addUniversityTab, i18Helper.getMessage("menu.newUniversity"));
        tabSheet.addTab(showAllUniversitiesTab, i18Helper.getMessage("menu.allUniversities"));
        tabSheet.addTab(showStatsTab, i18Helper.getMessage("menu.stats"));

        addComponent(tabSheet);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        removeAllComponents();
        addLayout();

    }

    @Override
    public void saved() {
        showAllUniversitiesRefreshTable.refreshTable();
        statsUniversityRefreshTable.refreshTable();
    }
}
