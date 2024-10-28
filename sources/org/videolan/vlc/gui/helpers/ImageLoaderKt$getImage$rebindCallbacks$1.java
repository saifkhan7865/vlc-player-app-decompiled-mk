package org.videolan.vlc.gui.helpers;

import androidx.databinding.OnRebindCallback;
import androidx.databinding.ViewDataBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/vlc/gui/helpers/ImageLoaderKt$getImage$rebindCallbacks$1", "Landroidx/databinding/OnRebindCallback;", "Landroidx/databinding/ViewDataBinding;", "onPreBind", "", "binding", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ImageLoader.kt */
public final class ImageLoaderKt$getImage$rebindCallbacks$1 extends OnRebindCallback<ViewDataBinding> {
    final /* synthetic */ Ref.BooleanRef $bindChanged;

    ImageLoaderKt$getImage$rebindCallbacks$1(Ref.BooleanRef booleanRef) {
        this.$bindChanged = booleanRef;
    }

    public boolean onPreBind(ViewDataBinding viewDataBinding) {
        Intrinsics.checkNotNullParameter(viewDataBinding, "binding");
        this.$bindChanged.element = true;
        return super.onPreBind(viewDataBinding);
    }
}
