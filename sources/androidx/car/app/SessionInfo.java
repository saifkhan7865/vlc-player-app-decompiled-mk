package androidx.car.app;

import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.Template;
import androidx.car.app.navigation.model.NavigationTemplate;
import com.google.common.collect.ImmutableSet;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Set;

@RequiresCarApi(6)
public class SessionInfo {
    private static final ImmutableSet<Class<? extends Template>> CLUSTER_SUPPORTED_TEMPLATES_API_6 = ImmutableSet.of(NavigationTemplate.class);
    private static final ImmutableSet<Class<? extends Template>> CLUSTER_SUPPORTED_TEMPLATES_LESS_THAN_API_6 = ImmutableSet.of();
    public static final SessionInfo DEFAULT_SESSION_INFO = new SessionInfo(0, "main");
    public static final int DISPLAY_TYPE_CLUSTER = 1;
    public static final int DISPLAY_TYPE_MAIN = 0;
    private static final char DIVIDER = '/';
    private final int mDisplayType;
    private final String mSessionId;

    @Retention(RetentionPolicy.SOURCE)
    public @interface DisplayType {
    }

    public SessionInfo(int i, String str) {
        this.mDisplayType = i;
        this.mSessionId = str;
    }

    private SessionInfo() {
        this.mSessionId = "main";
        this.mDisplayType = 0;
    }

    public String getSessionId() {
        return this.mSessionId;
    }

    public int getDisplayType() {
        return this.mDisplayType;
    }

    public Set<Class<? extends Template>> getSupportedTemplates(int i) {
        if (this.mDisplayType != 1) {
            return null;
        }
        if (i >= 6) {
            return CLUSTER_SUPPORTED_TEMPLATES_API_6;
        }
        return CLUSTER_SUPPORTED_TEMPLATES_LESS_THAN_API_6;
    }

    public String toString() {
        return String.valueOf(this.mDisplayType) + DIVIDER + this.mSessionId;
    }

    public int hashCode() {
        return Objects.hash(this.mSessionId, Integer.valueOf(this.mDisplayType));
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SessionInfo)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        SessionInfo sessionInfo = (SessionInfo) obj;
        if (!getSessionId().equals(sessionInfo.getSessionId()) || getDisplayType() != sessionInfo.getDisplayType()) {
            return false;
        }
        return true;
    }
}
