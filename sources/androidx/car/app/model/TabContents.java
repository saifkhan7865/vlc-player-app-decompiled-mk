package androidx.car.app.model;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.constraints.TabContentsConstraints;
import j$.util.Objects;

@RequiresCarApi(6)
public class TabContents implements Content {
    public static final String CONTENT_ID = "TAB_CONTENTS_CONTENT_ID";
    private Template mTemplate;

    public String getContentId() {
        return CONTENT_ID;
    }

    public Template getTemplate() {
        return (Template) Objects.requireNonNull(this.mTemplate);
    }

    public String toString() {
        return "[template: " + this.mTemplate + "]";
    }

    public int hashCode() {
        return Objects.hash(this.mTemplate);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TabContents)) {
            return false;
        }
        return Objects.equals(this.mTemplate, ((TabContents) obj).mTemplate);
    }

    TabContents(Builder builder) {
        this.mTemplate = builder.mTemplate;
    }

    private TabContents() {
        this.mTemplate = null;
    }

    public static final class Builder {
        Template mTemplate;

        public TabContents build() {
            return new TabContents(this);
        }

        public Builder(Template template) {
            TabContentsConstraints.DEFAULT.validateOrThrow((Template) Objects.requireNonNull(template));
            this.mTemplate = template;
        }
    }
}
