package com.soikea.commitment2050.language;


import java.util.Enumeration;
import java.util.ResourceBundle;
import org.osgi.service.component.annotations.Component;
import com.liferay.portal.kernel.language.UTF8Control;

@Component(
        property = { "language.id=sv_SE" },
        service = ResourceBundle.class
)
public class SvResourceBundle extends ResourceBundle {

    ResourceBundle bundle = ResourceBundle.getBundle("content.Language_sv_SE", UTF8Control.INSTANCE);

    @Override
    protected Object handleGetObject(String key) {
        return bundle.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return bundle.getKeys();
    }

}