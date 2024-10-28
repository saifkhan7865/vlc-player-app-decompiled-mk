package org.videolan.vlc.util;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B'\b\u0002\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0006J\b\u0010\u0014\u001a\u00020\u0015H\u0007R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0000X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001e\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u0016"}, d2 = {"Lorg/videolan/vlc/util/FeatureFlag;", "", "dependsOn", "title", "", "warning", "(Ljava/lang/String;ILorg/videolan/vlc/util/FeatureFlag;ILjava/lang/Integer;)V", "getDependsOn", "()Lorg/videolan/vlc/util/FeatureFlag;", "setDependsOn", "(Lorg/videolan/vlc/util/FeatureFlag;)V", "getTitle", "()I", "setTitle", "(I)V", "getWarning", "()Ljava/lang/Integer;", "setWarning", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getKey", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FeatureFlagManager.kt */
public enum FeatureFlag {
    ;
    
    private FeatureFlag dependsOn;
    private int title;
    private Integer warning;

    static {
        FeatureFlag[] $values;
        $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
    }

    public static EnumEntries<FeatureFlag> getEntries() {
        return $ENTRIES;
    }

    private FeatureFlag(FeatureFlag featureFlag, int i, Integer num) {
        this.dependsOn = featureFlag;
        this.title = i;
        this.warning = num;
    }

    public final FeatureFlag getDependsOn() {
        return this.dependsOn;
    }

    public final int getTitle() {
        return this.title;
    }

    public final Integer getWarning() {
        return this.warning;
    }

    public final void setDependsOn(FeatureFlag featureFlag) {
        this.dependsOn = featureFlag;
    }

    public final void setTitle(int i) {
        this.title = i;
    }

    public final void setWarning(Integer num) {
        this.warning = num;
    }

    public final String getKey() {
        StringBuilder sb = new StringBuilder("ff_");
        String name = name();
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
        String lowerCase = name.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        sb.append(lowerCase);
        return sb.toString();
    }
}
