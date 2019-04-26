package pl.home.navigators;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.SingleComponentContainer;
import com.vaadin.ui.UI;

import java.util.Optional;


public class UniversityNavigator extends Navigator {

    public UniversityNavigator(UI ui, SingleComponentContainer container){
        super(ui, container);
    }

    private static UniversityNavigator getNavigator(){
        UI ui = UI.getCurrent();
        Navigator navigator = ui.getNavigator();
        return (UniversityNavigator) navigator;
    }

    public static void navigate(String path){
        try{
            UniversityNavigator.getNavigator().navigateTo(path);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void navigateTo(String viewName) {
        super.navigateTo(Optional.ofNullable(viewName).orElse(""));
    }
}
