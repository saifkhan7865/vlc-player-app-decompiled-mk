package io.netty.handler.codec.spdy;

import io.netty.util.internal.StringUtil;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public class DefaultSpdySettingsFrame implements SpdySettingsFrame {
    private boolean clear;
    private final Map<Integer, Setting> settingsMap = new TreeMap();

    public Set<Integer> ids() {
        return this.settingsMap.keySet();
    }

    public boolean isSet(int i) {
        return this.settingsMap.containsKey(Integer.valueOf(i));
    }

    public int getValue(int i) {
        Setting setting = this.settingsMap.get(Integer.valueOf(i));
        if (setting != null) {
            return setting.getValue();
        }
        return -1;
    }

    public SpdySettingsFrame setValue(int i, int i2) {
        return setValue(i, i2, false, false);
    }

    public SpdySettingsFrame setValue(int i, int i2, boolean z, boolean z2) {
        if (i < 0 || i > 16777215) {
            throw new IllegalArgumentException("Setting ID is not valid: " + i);
        }
        Integer valueOf = Integer.valueOf(i);
        Setting setting = this.settingsMap.get(valueOf);
        if (setting != null) {
            setting.setValue(i2);
            setting.setPersist(z);
            setting.setPersisted(z2);
        } else {
            this.settingsMap.put(valueOf, new Setting(i2, z, z2));
        }
        return this;
    }

    public SpdySettingsFrame removeValue(int i) {
        this.settingsMap.remove(Integer.valueOf(i));
        return this;
    }

    public boolean isPersistValue(int i) {
        Setting setting = this.settingsMap.get(Integer.valueOf(i));
        return setting != null && setting.isPersist();
    }

    public SpdySettingsFrame setPersistValue(int i, boolean z) {
        Setting setting = this.settingsMap.get(Integer.valueOf(i));
        if (setting != null) {
            setting.setPersist(z);
        }
        return this;
    }

    public boolean isPersisted(int i) {
        Setting setting = this.settingsMap.get(Integer.valueOf(i));
        return setting != null && setting.isPersisted();
    }

    public SpdySettingsFrame setPersisted(int i, boolean z) {
        Setting setting = this.settingsMap.get(Integer.valueOf(i));
        if (setting != null) {
            setting.setPersisted(z);
        }
        return this;
    }

    public boolean clearPreviouslyPersistedSettings() {
        return this.clear;
    }

    public SpdySettingsFrame setClearPreviouslyPersistedSettings(boolean z) {
        this.clear = z;
        return this;
    }

    private Set<Map.Entry<Integer, Setting>> getSettings() {
        return this.settingsMap.entrySet();
    }

    private void appendSettings(StringBuilder sb) {
        for (Map.Entry next : getSettings()) {
            Setting setting = (Setting) next.getValue();
            sb.append("--> ");
            sb.append(next.getKey());
            sb.append(AbstractJsonLexerKt.COLON);
            sb.append(setting.getValue());
            sb.append(" (persist value: ");
            sb.append(setting.isPersist());
            sb.append("; persisted: ");
            sb.append(setting.isPersisted());
            sb.append(')');
            sb.append(StringUtil.NEWLINE);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringUtil.simpleClassName((Object) this));
        sb.append(StringUtil.NEWLINE);
        appendSettings(sb);
        sb.setLength(sb.length() - StringUtil.NEWLINE.length());
        return sb.toString();
    }

    private static final class Setting {
        private boolean persist;
        private boolean persisted;
        private int value;

        Setting(int i, boolean z, boolean z2) {
            this.value = i;
            this.persist = z;
            this.persisted = z2;
        }

        /* access modifiers changed from: package-private */
        public int getValue() {
            return this.value;
        }

        /* access modifiers changed from: package-private */
        public void setValue(int i) {
            this.value = i;
        }

        /* access modifiers changed from: package-private */
        public boolean isPersist() {
            return this.persist;
        }

        /* access modifiers changed from: package-private */
        public void setPersist(boolean z) {
            this.persist = z;
        }

        /* access modifiers changed from: package-private */
        public boolean isPersisted() {
            return this.persisted;
        }

        /* access modifiers changed from: package-private */
        public void setPersisted(boolean z) {
            this.persisted = z;
        }
    }
}
