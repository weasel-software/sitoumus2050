package com.soikea.commitment2050.editorconfig;

import com.liferay.portal.kernel.editor.configuration.BaseEditorConfigContributor;
import com.liferay.portal.kernel.editor.configuration.EditorConfigContributor;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.portlet.RequestBackedPortletURLFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import org.osgi.service.component.annotations.Component;

import java.util.Map;

/**
 * Wysiwyg editor configuration so that empty tags does not get stripped off.
 * TODO: what is wrong with this configuration?
 *
 *
 * As a workaround one can just add &nbsp; in code editor mode...
 *
 */
@Component(
        immediate = true,
        service = EditorConfigContributor.class
)
public class CommitmentEditorConfiguration extends BaseEditorConfigContributor {
        @Override
        public void populateConfigJSONObject(
                JSONObject jsonObject, Map<String, Object> inputEditorTaglibAttributes,
                ThemeDisplay themeDisplay,
                RequestBackedPortletURLFactory requestBackedPortletURLFactory) {
                jsonObject.put("extraAllowedContent", "span(*)");

                System.out.println(jsonObject);
        }
}
