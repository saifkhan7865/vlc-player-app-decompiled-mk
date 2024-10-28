package androidx.car.app.model.constraints;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.GridTemplate;
import androidx.car.app.model.ListTemplate;
import androidx.car.app.model.MessageTemplate;
import androidx.car.app.model.PaneTemplate;
import androidx.car.app.model.SearchTemplate;
import androidx.car.app.model.Template;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RequiresCarApi(6)
public class TabContentsConstraints {
    public static final TabContentsConstraints DEFAULT = new TabContentsConstraints(Arrays.asList(new Class[]{ListTemplate.class, PaneTemplate.class, GridTemplate.class, MessageTemplate.class, SearchTemplate.class}));
    private HashSet<Class<? extends Template>> mAllowedTemplateTypes;

    public void validateOrThrow(Template template) {
        if (!this.mAllowedTemplateTypes.contains(template.getClass())) {
            throw new IllegalArgumentException("Type is not allowed in tabs: " + template.getClass().getSimpleName());
        }
    }

    private TabContentsConstraints(List<Class<? extends Template>> list) {
        this.mAllowedTemplateTypes = new HashSet<>(list);
    }
}
