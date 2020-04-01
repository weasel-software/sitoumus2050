package com.soikea.commitment2050.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = "Sitoumus",
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)

@Meta.OCD(
        id = "com.soikea.commitment2050.configuration.SitoumusConfiguration",
        name = "Sähköpostikonfiguraatio notifikaatiosähköposteille"
)
public interface SitoumusConfiguration {
    @Meta.AD(
            name = "email notification configuration",
            description = "Provide json configuration. ( for example { \"key\": \"value\", \"key1\": \"value2\"} )",
            deflt = "default",
            required = false
    )
    String jsonConfiguration();
}
