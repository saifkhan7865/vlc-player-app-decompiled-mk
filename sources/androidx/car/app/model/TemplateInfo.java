package androidx.car.app.model;

import j$.util.Objects;

public final class TemplateInfo {
    private final Class<? extends Template> mTemplateClass;
    private final String mTemplateId;

    public TemplateInfo(Class<? extends Template> cls, String str) {
        this.mTemplateClass = cls;
        this.mTemplateId = str;
    }

    public Class<? extends Template> getTemplateClass() {
        return (Class) Objects.requireNonNull(this.mTemplateClass);
    }

    public String getTemplateId() {
        return (String) Objects.requireNonNull(this.mTemplateId);
    }

    public int hashCode() {
        return Objects.hash(this.mTemplateClass, this.mTemplateId);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TemplateInfo)) {
            return false;
        }
        TemplateInfo templateInfo = (TemplateInfo) obj;
        if (!Objects.equals(this.mTemplateClass, templateInfo.mTemplateClass) || !Objects.equals(this.mTemplateId, templateInfo.mTemplateId)) {
            return false;
        }
        return true;
    }

    private TemplateInfo() {
        this.mTemplateClass = null;
        this.mTemplateId = null;
    }
}
