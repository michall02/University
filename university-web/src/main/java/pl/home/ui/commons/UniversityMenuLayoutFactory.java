package pl.home.ui.commons;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import pl.home.i18helper.I18Helper;
import pl.home.navigators.UniversityNavigator;

import java.util.Locale;

@org.springframework.stereotype.Component
public class UniversityMenuLayoutFactory implements UIComponentBuilder {


    private class UniversityMenu extends VerticalLayout implements Tree.ItemClickListener {
        private Tree<String> tree;
        private TreeData<String> treeData;
        private I18Helper i18Helper;



        public UniversityMenu init() {
            Locale locale = new Locale(UniversityMainUI.LOCALE);
            i18Helper = new I18Helper(locale);
            tree = new Tree<>();
            treeData = new TreeData<>();
            tree.addItemClickListener(this);
            return this;
        }

        public UniversityMenu layout() {
            setWidth("100%");
            setHeightUndefined();

            treeData.addItem(null, i18Helper.getMessage("menu.university"));
            treeData.addItem(null, i18Helper.getMessage("menu.student"));
            treeData.addItem(i18Helper.getMessage("menu.university"), i18Helper.getMessage("menu.operations"));
            treeData.addItem(i18Helper.getMessage("menu.student"), i18Helper.getMessage("menu.addStudent"));
            treeData.addItem(i18Helper.getMessage("menu.student"), i18Helper.getMessage("menu.removeStudent"));

            TreeDataProvider<String> stringTreeDataProvider = new TreeDataProvider<>(treeData);
            tree.setDataProvider(stringTreeDataProvider);
            tree.expand(i18Helper.getMessage("menu.university"), i18Helper.getMessage("menu.student"));

            addComponent(tree);
            return this;
        }

        @Override
        public void itemClick(Tree.ItemClick itemClick) {
            String selectedItemPath = (String) itemClick.getItem();
            if (selectedItemPath == null) {
                return;
            }

            String path = selectedItemPath.toLowerCase().replaceAll(" ", "");
            UniversityNavigator.navigate(path);
        }
    }

    @Override
    public Component createComponent() {
        return new UniversityMenu().init().layout();
    }
}
