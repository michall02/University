package pl.home.ui.commons;

import com.vaadin.data.TreeData;
import com.vaadin.data.provider.TreeDataProvider;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class UniversityMenuLayoutFactory implements UIComponentBuilder {


    private class UniversityMenu extends VerticalLayout {
        Tree<String> tree;
        TreeData<String> treeData;

        public UniversityMenu init(){
            tree = new Tree<>();
            treeData = new TreeData<>();
            return this;
        }

        public UniversityMenu layout(){
            setWidth("100%");
            setHeightUndefined();

            treeData.addItem(null,"UNIVERSITY");
            treeData.addItem(null,"STUDENT");
            treeData.addItem("UNIVERSITY","Operations");
            treeData.addItem("STUDENT","Add student");
            treeData.addItem("STUDENT","Remove student");

            TreeDataProvider<String> stringTreeDataProvider = new TreeDataProvider<>(treeData);
            tree.setDataProvider(stringTreeDataProvider);
            tree.expand("UNIVERSITY","STUDENT");


//            mainMenu.setItems("STUDENT","UNIVERSITY","Add student","Remove student", "Operations");
//            mainMenu.expand("STUDENT","UNIVERSITY");
//
//            mainMenu.

//            mainMenu.addRootItems("STUDENT","UNIVERSITY","Add student","Remove student", "Operations");

//            mainMenu.setParent("Add student", "STUDENT");
//            mainMenu.setParent("Remove student", "STUDENT");
//            mainMenu.setParent("Operations", "UNIVERSITY");

            addComponent(tree);
            return this;
        }
    }

    @Override
    public Component createComponent() {
        return new UniversityMenu().init().layout();
    }
}
