package pl.home.ui.universities;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.home.ui.commons.UIComponentBuilder;
import pl.home.ui.commons.UIComponentBuilderWithListener;
import pl.home.ui.commons.UniversityMainUI;
import pl.home.ui.students.SavedListener;
import pl.home.ui.students.UITableRefresher;

import static pl.home.utils.UniversityUtils.MAIN_MENU;
import static pl.home.utils.UniversityUtils.SHOW_ALL_UNIVERSITIES;
import static pl.home.utils.UniversityUtils.SHOW_STATS;

@SpringView(name = UniversityLayoutFactory.NAME, ui = UniversityMainUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View, SavedListener {
    public static final String NAME = "operations";

    private TabSheet tabSheet;

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
    public void saved() {
        showAllUniversitiesRefreshTable.refreshTable();
        statsUniversityRefreshTable.refreshTable();
    }
}
