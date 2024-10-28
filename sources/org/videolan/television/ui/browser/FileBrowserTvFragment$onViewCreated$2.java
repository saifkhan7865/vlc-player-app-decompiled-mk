package org.videolan.television.ui.browser;

import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.collection.SparseArrayCompat;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.television.ui.MediaBrowserAnimatorDelegate;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012$\u0010\u0002\u001a \u0012\u0004\u0012\u00020\u0004 \u0006*\u0010\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\u0004\u0018\u0001`\u00050\u0003j\u0002`\u0005H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Landroidx/collection/SparseArrayCompat;", "", "Lorg/videolan/resources/util/HeadersIndex;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileBrowserTvFragment.kt */
final class FileBrowserTvFragment$onViewCreated$2 extends Lambda implements Function1<SparseArrayCompat<String>, Unit> {
    final /* synthetic */ FileBrowserTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserTvFragment$onViewCreated$2(FileBrowserTvFragment fileBrowserTvFragment) {
        super(1);
        this.this$0 = fileBrowserTvFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((SparseArrayCompat<String>) (SparseArrayCompat) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(SparseArrayCompat<String> sparseArrayCompat) {
        FileBrowserTvFragment fileBrowserTvFragment = this.this$0;
        Intrinsics.checkNotNull(sparseArrayCompat);
        fileBrowserTvFragment.updateHeaders(sparseArrayCompat);
        this.this$0.getBinding().list.invalidateItemDecorations();
        MediaBrowserAnimatorDelegate animationDelegate$television_release = this.this$0.getAnimationDelegate$television_release();
        AppCompatImageButton appCompatImageButton = this.this$0.getBinding().imageButtonHeader;
        Intrinsics.checkNotNullExpressionValue(appCompatImageButton, "imageButtonHeader");
        int i = 8;
        animationDelegate$television_release.setVisibility(appCompatImageButton, this.this$0.getViewModel().getProvider().getHeaders().isEmpty() ? 8 : 0);
        MediaBrowserAnimatorDelegate animationDelegate$television_release2 = this.this$0.getAnimationDelegate$television_release();
        AppCompatImageButton appCompatImageButton2 = this.this$0.getBinding().headerButton;
        Intrinsics.checkNotNullExpressionValue(appCompatImageButton2, "headerButton");
        animationDelegate$television_release2.setVisibility(appCompatImageButton2, this.this$0.getViewModel().getProvider().getHeaders().isEmpty() ? 8 : 0);
        MediaBrowserAnimatorDelegate animationDelegate$television_release3 = this.this$0.getAnimationDelegate$television_release();
        TextView textView = this.this$0.getBinding().headerDescription;
        Intrinsics.checkNotNullExpressionValue(textView, "headerDescription");
        View view = textView;
        if (!this.this$0.getViewModel().getProvider().getHeaders().isEmpty()) {
            i = 0;
        }
        animationDelegate$television_release3.setVisibility(view, i);
    }
}
