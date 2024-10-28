package org.videolan.tools;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B!\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0006R\u0019\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\n\u0010\b¨\u0006\u000b"}, d2 = {"Lorg/videolan/tools/LocalePair;", "", "localeEntries", "", "", "localeEntryValues", "([Ljava/lang/String;[Ljava/lang/String;)V", "getLocaleEntries", "()[Ljava/lang/String;", "[Ljava/lang/String;", "getLocaleEntryValues", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LocaleUtils.kt */
public final class LocalePair {
    private final String[] localeEntries;
    private final String[] localeEntryValues;

    public LocalePair(String[] strArr, String[] strArr2) {
        Intrinsics.checkNotNullParameter(strArr, "localeEntries");
        Intrinsics.checkNotNullParameter(strArr2, "localeEntryValues");
        this.localeEntries = strArr;
        this.localeEntryValues = strArr2;
    }

    public final String[] getLocaleEntries() {
        return this.localeEntries;
    }

    public final String[] getLocaleEntryValues() {
        return this.localeEntryValues;
    }
}
