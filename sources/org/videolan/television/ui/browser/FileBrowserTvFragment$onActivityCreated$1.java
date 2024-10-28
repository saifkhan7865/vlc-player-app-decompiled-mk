package org.videolan.television.ui.browser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.browser.FileBrowserTvFragment$onActivityCreated$1", f = "FileBrowserTvFragment.kt", i = {}, l = {238}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FileBrowserTvFragment.kt */
final class FileBrowserTvFragment$onActivityCreated$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ FileBrowserTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserTvFragment$onActivityCreated$1(FileBrowserTvFragment fileBrowserTvFragment, Continuation<? super FileBrowserTvFragment$onActivityCreated$1> continuation) {
        super(2, continuation);
        this.this$0 = fileBrowserTvFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileBrowserTvFragment$onActivityCreated$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FileBrowserTvFragment$onActivityCreated$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c0  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00d8  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00f3  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x010e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 1
            r3 = 0
            if (r1 == 0) goto L_0x001d
            if (r1 != r2) goto L_0x0015
            java.lang.Object r0 = r6.L$0
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = (org.videolan.television.ui.browser.FileBrowserTvFragment) r0
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x009e
        L_0x0015:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x001d:
            kotlin.ResultKt.throwOnFailure(r7)
            org.videolan.television.ui.browser.FileBrowserTvFragment r7 = r6.this$0
            org.videolan.television.ui.MediaBrowserAnimatorDelegate r7 = r7.getAnimationDelegate$television_release()
            org.videolan.television.ui.browser.FileBrowserTvFragment r1 = r6.this$0
            org.videolan.television.databinding.SongBrowserBinding r1 = r1.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r1 = r1.favoriteButton
            java.lang.String r4 = "favoriteButton"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            android.view.View r1 = (android.view.View) r1
            r7.setVisibility(r1, r3)
            org.videolan.television.ui.browser.FileBrowserTvFragment r7 = r6.this$0
            org.videolan.television.ui.MediaBrowserAnimatorDelegate r7 = r7.getAnimationDelegate$television_release()
            org.videolan.television.ui.browser.FileBrowserTvFragment r1 = r6.this$0
            org.videolan.television.databinding.SongBrowserBinding r1 = r1.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r1 = r1.imageButtonFavorite
            java.lang.String r4 = "imageButtonFavorite"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            android.view.View r1 = (android.view.View) r1
            r7.setVisibility(r1, r3)
            org.videolan.television.ui.browser.FileBrowserTvFragment r7 = r6.this$0
            org.videolan.television.ui.MediaBrowserAnimatorDelegate r7 = r7.getAnimationDelegate$television_release()
            org.videolan.television.ui.browser.FileBrowserTvFragment r1 = r6.this$0
            org.videolan.television.databinding.SongBrowserBinding r1 = r1.getBinding()
            android.widget.TextView r1 = r1.favoriteDescription
            java.lang.String r4 = "favoriteDescription"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            android.view.View r1 = (android.view.View) r1
            r7.setVisibility(r1, r3)
            org.videolan.television.ui.browser.FileBrowserTvFragment r7 = r6.this$0
            org.videolan.medialibrary.media.MediaLibraryItem r1 = r7.currentItem
            boolean r4 = r1 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            r5 = 0
            if (r4 == 0) goto L_0x0076
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            goto L_0x0077
        L_0x0076:
            r1 = r5
        L_0x0077:
            if (r1 == 0) goto L_0x00a9
            org.videolan.television.ui.browser.FileBrowserTvFragment r4 = r6.this$0
            org.videolan.vlc.repository.BrowserFavRepository r4 = r4.browserFavRepository
            if (r4 != 0) goto L_0x0087
            java.lang.String r4 = "browserFavRepository"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L_0x0088
        L_0x0087:
            r5 = r4
        L_0x0088:
            android.net.Uri r1 = r1.getUri()
            java.lang.String r4 = "getUri(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            r6.L$0 = r7
            r6.label = r2
            java.lang.Object r1 = r5.browserFavExists(r1, r6)
            if (r1 != r0) goto L_0x009c
            return r0
        L_0x009c:
            r0 = r7
            r7 = r1
        L_0x009e:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x00a8
            r7 = r0
            goto L_0x00aa
        L_0x00a8:
            r7 = r0
        L_0x00a9:
            r2 = 0
        L_0x00aa:
            r7.favExists = r2
            org.videolan.television.ui.browser.FileBrowserTvFragment r7 = r6.this$0
            org.videolan.television.databinding.SongBrowserBinding r7 = r7.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r7 = r7.favoriteButton
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = r6.this$0
            boolean r0 = r0.favExists
            if (r0 == 0) goto L_0x00c0
            int r0 = org.videolan.television.R.drawable.ic_tv_browser_favorite
            goto L_0x00c2
        L_0x00c0:
            int r0 = org.videolan.television.R.drawable.ic_tv_browser_favorite_outline
        L_0x00c2:
            r7.setImageResource(r0)
            org.videolan.television.ui.browser.FileBrowserTvFragment r7 = r6.this$0
            org.videolan.television.databinding.SongBrowserBinding r7 = r7.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r7 = r7.favoriteButton
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = r6.this$0
            boolean r1 = r0.favExists
            if (r1 == 0) goto L_0x00d8
            int r1 = org.videolan.television.R.string.favorites_remove
            goto L_0x00da
        L_0x00d8:
            int r1 = org.videolan.television.R.string.favorites_add
        L_0x00da:
            java.lang.String r0 = r0.getString(r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r7.setContentDescription(r0)
            org.videolan.television.ui.browser.FileBrowserTvFragment r7 = r6.this$0
            org.videolan.television.databinding.SongBrowserBinding r7 = r7.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r7 = r7.imageButtonFavorite
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = r6.this$0
            boolean r0 = r0.favExists
            if (r0 == 0) goto L_0x00f6
            int r0 = org.videolan.television.R.drawable.ic_fabtvmini_favorite
            goto L_0x00f8
        L_0x00f6:
            int r0 = org.videolan.television.R.drawable.ic_fabtvmini_favorite_outline
        L_0x00f8:
            r7.setImageResource(r0)
            org.videolan.television.ui.browser.FileBrowserTvFragment r7 = r6.this$0
            org.videolan.television.databinding.SongBrowserBinding r7 = r7.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r7 = r7.imageButtonFavorite
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = r6.this$0
            boolean r1 = r0.favExists
            if (r1 == 0) goto L_0x010e
            int r1 = org.videolan.television.R.string.favorites_remove
            goto L_0x0110
        L_0x010e:
            int r1 = org.videolan.television.R.string.favorites_add
        L_0x0110:
            java.lang.String r0 = r0.getString(r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r7.setContentDescription(r0)
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.browser.FileBrowserTvFragment$onActivityCreated$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
