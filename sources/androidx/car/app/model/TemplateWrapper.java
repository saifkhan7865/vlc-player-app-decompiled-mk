package androidx.car.app.model;

import androidx.car.app.utils.CollectionUtils;
import j$.util.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class TemplateWrapper {
    private int mCurrentTaskStep;
    private String mId;
    private boolean mIsRefresh;
    private Template mTemplate;
    private List<TemplateInfo> mTemplateInfoForScreenStack;

    public static TemplateWrapper wrap(Template template) {
        return wrap(template, createRandomId());
    }

    public static TemplateWrapper wrap(Template template, String str) {
        return new TemplateWrapper((Template) Objects.requireNonNull(template), (String) Objects.requireNonNull(str));
    }

    public Template getTemplate() {
        return (Template) Objects.requireNonNull(this.mTemplate);
    }

    public String getId() {
        return (String) Objects.requireNonNull(this.mId);
    }

    public void setTemplateInfosForScreenStack(List<TemplateInfo> list) {
        this.mTemplateInfoForScreenStack = list;
    }

    public List<TemplateInfo> getTemplateInfosForScreenStack() {
        return CollectionUtils.emptyIfNull(this.mTemplateInfoForScreenStack);
    }

    public int getCurrentTaskStep() {
        return this.mCurrentTaskStep;
    }

    public void setCurrentTaskStep(int i) {
        this.mCurrentTaskStep = i;
    }

    public void setRefresh(boolean z) {
        this.mIsRefresh = z;
    }

    public boolean isRefresh() {
        return this.mIsRefresh;
    }

    public void setTemplate(Template template) {
        this.mTemplate = template;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public static TemplateWrapper copyOf(TemplateWrapper templateWrapper) {
        TemplateWrapper wrap = wrap(templateWrapper.getTemplate(), templateWrapper.getId());
        wrap.setRefresh(templateWrapper.isRefresh());
        wrap.setCurrentTaskStep(templateWrapper.getCurrentTaskStep());
        List<TemplateInfo> templateInfosForScreenStack = templateWrapper.getTemplateInfosForScreenStack();
        if (templateInfosForScreenStack != null) {
            wrap.setTemplateInfosForScreenStack(templateInfosForScreenStack);
        }
        return wrap;
    }

    public String toString() {
        return "[template: " + this.mTemplate + ", ID: " + this.mId + "]";
    }

    private TemplateWrapper(Template template, String str) {
        this.mTemplateInfoForScreenStack = new ArrayList();
        this.mTemplate = template;
        this.mId = str;
    }

    private TemplateWrapper() {
        this.mTemplateInfoForScreenStack = new ArrayList();
        this.mTemplate = null;
        this.mId = "";
    }

    private static String createRandomId() {
        return UUID.randomUUID().toString();
    }
}
