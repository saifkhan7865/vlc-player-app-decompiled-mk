package org.videolan.vlc.viewmodels.tv;

import kotlin.Metadata;
import org.videolan.resources.util.HeaderProvider;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\b\u0010\u0012\u001a\u00020\u0013H&R\u001a\u0010\u0003\u001a\u0004\u0018\u00018\u0000X¦\u000e¢\u0006\f\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\u00020\tX¦\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lorg/videolan/vlc/viewmodels/tv/TvBrowserModel;", "T", "", "currentItem", "getCurrentItem", "()Ljava/lang/Object;", "setCurrentItem", "(Ljava/lang/Object;)V", "nbColumns", "", "getNbColumns", "()I", "setNbColumns", "(I)V", "provider", "Lorg/videolan/resources/util/HeaderProvider;", "getProvider", "()Lorg/videolan/resources/util/HeaderProvider;", "isEmpty", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TvBrowserModel.kt */
public interface TvBrowserModel<T> {
    T getCurrentItem();

    int getNbColumns();

    HeaderProvider getProvider();

    boolean isEmpty();

    void setCurrentItem(T t);

    void setNbColumns(int i);
}
