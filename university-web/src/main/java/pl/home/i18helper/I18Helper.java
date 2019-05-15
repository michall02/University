package pl.home.i18helper;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class I18Helper {

    private final ResourceBundle resourceBundle;

    public I18Helper(Locale locale){
        resourceBundle = ResourceBundle.getBundle("messages",locale);
    }

    public String getMessage(String key) {
        try{
            return  resourceBundle.getString(key);
        }catch(MissingResourceException e){
            return key;
        }
    }
}
