package pl.home.ui.commons;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import pl.home.navigators.UniversityNavigator;

import static pl.home.utils.MenuUtils.MENU_ADD_STUDENT;
import static pl.home.utils.MenuUtils.MENU_OPERATIONS;
import static pl.home.utils.MenuUtils.MENU_REMOVE_STUDENT;
import static pl.home.utils.MenuUtils.MENU_STUDENT;
import static pl.home.utils.MenuUtils.MENU_UNIVERSITY;

@org.springframework.stereotype.Component
public class UniversityMenuLayoutFactory implements UIComponentBuilder {


    private class UniversityMenu extends VerticalLayout implements Tree.ItemClickListener {
        Tree<String> tree;
        TreeData<String> treeData;

        public UniversityMenu init() {
            tree = new Tree<>();
            treeData = new TreeData<>();
            tree.addItemClickListener(this);
            return this;
        }

        public UniversityMenu layout() {
            setWidth("100%");
            setHeightUndefined();

            treeData.addItem(null, MENU_UNIVERSITY.getValue());
            treeData.addItem(null, MENU_STUDENT.getValue());
            treeData.addItem(MENU_UNIVERSITY.getValue(), MENU_OPERATIONS.getValue());
            treeData.addItem(MENU_STUDENT.getValue(), MENU_ADD_STUDENT.getValue());
            treeData.addItem(MENU_STUDENT.getValue(), MENU_REMOVE_STUDENT.getValue());

            TreeDataProvider<String> stringTreeDataProvider = new TreeDataProvider<>(treeData);
            tree.setDataProvider(stringTreeDataProvider);
            tree.expand(MENU_UNIVERSITY.getValue(), MENU_STUDENT.getValue());

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
