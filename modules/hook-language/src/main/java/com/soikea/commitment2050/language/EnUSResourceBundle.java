package com.soikea.commitment2050.language;

import com.liferay.portal.kernel.language.UTF8Control;
import org.osgi.service.component.annotations.Component;

import java.util.Enumeration;
import java.util.ResourceBundle;

@Component(
        property = { "language.id=en_US" },
        service = ResourceBundle.class
)
public class EnUSResourceBundle extends ResourceBundle {

    ResourceBundle bundle = ResourceBundle.getBundle("content.Language_en_US", UTF8Control.INSTANCE);

    @Override
    protected Object handleGetObject(String key) {
        return bundle.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return bundle.getKeys();
    }

}
