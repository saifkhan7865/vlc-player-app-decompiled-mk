package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/viewmodels/Filter;", "Lorg/videolan/vlc/viewmodels/Update;", "query", "", "(Ljava/lang/String;)V", "getQuery", "()Ljava/lang/String;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BaseModel.kt */
public final class Filter extends Update {
    private final String query;

    public Filter(String str) {
        super((DefaultConstructorMarker) null);
        this.query = str;
    }

    public final String getQuery() {
        return this.query;
    }
}
