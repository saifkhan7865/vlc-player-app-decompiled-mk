package org.videolan.tools;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\u0017\u0010\u0003\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0004\u001a\u00020\u0005H&¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0002H&J\"\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0002H&¨\u0006\r"}, d2 = {"Lorg/videolan/tools/MultiSelectAdapter;", "T", "", "getItem", "position", "", "(I)Ljava/lang/Object;", "notifyItemChanged", "", "start", "payload", "notifyItemRangeChanged", "count", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MultiSelectHelper.kt */
public interface MultiSelectAdapter<T> {
    T getItem(int i);

    void notifyItemChanged(int i, Object obj);

    void notifyItemRangeChanged(int i, int i2, Object obj);
}
