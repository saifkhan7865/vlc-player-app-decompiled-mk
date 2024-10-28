package org.videolan.vlc.interfaces;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\n\u0010\t\u001a\u0004\u0018\u00010\bH&J\b\u0010\n\u001a\u00020\u0006H&J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0003H&¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/interfaces/Filterable;", "", "allowedToExpand", "", "enableSearchOption", "filter", "", "query", "", "getFilterQuery", "restoreList", "setSearchVisibility", "visible", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Filterable.kt */
public interface Filterable {
    boolean allowedToExpand();

    boolean enableSearchOption();

    void filter(String str);

    String getFilterQuery();

    void restoreList();

    void setSearchVisibility(boolean z);
}
