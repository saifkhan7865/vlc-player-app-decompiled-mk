package org.videolan.vlc.gui.browser;

import android.view.View;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.BaseBrowserFragment$onClick$2", f = "BaseBrowserFragment.kt", i = {0}, l = {718}, m = "invokeSuspend", n = {"destination$iv$iv"}, s = {"L$1"})
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$onClick$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $position;
    final /* synthetic */ View $v;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$onClick$2(BaseBrowserFragment baseBrowserFragment, View view, int i, Continuation<? super BaseBrowserFragment$onClick$2> continuation) {
        super(2, continuation);
        this.this$0 = baseBrowserFragment;
        this.$v = view;
        this.$position = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseBrowserFragment$onClick$2(this.this$0, this.$v, this.$position, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseBrowserFragment$onClick$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0081  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 1
            if (r1 == 0) goto L_0x0028
            if (r1 != r2) goto L_0x0020
            java.lang.Object r1 = r14.L$3
            java.util.Collection r1 = (java.util.Collection) r1
            java.lang.Object r3 = r14.L$2
            java.util.Iterator r3 = (java.util.Iterator) r3
            java.lang.Object r4 = r14.L$1
            java.util.Collection r4 = (java.util.Collection) r4
            java.lang.Object r5 = r14.L$0
            org.videolan.vlc.gui.browser.BaseBrowserFragment r5 = (org.videolan.vlc.gui.browser.BaseBrowserFragment) r5
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00a0
        L_0x0020:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x0028:
            kotlin.ResultKt.throwOnFailure(r15)
            org.videolan.vlc.gui.browser.BaseBrowserFragment r15 = r14.this$0
            org.videolan.vlc.viewmodels.SortableModel r15 = r15.getViewModel()
            org.videolan.vlc.viewmodels.browser.BrowserModel r15 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r15
            org.videolan.tools.livedata.LiveDataset r15 = r15.getDataset()
            java.util.List r15 = r15.getList()
            java.lang.Iterable r15 = (java.lang.Iterable) r15
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r15 = r15.iterator()
        L_0x0048:
            boolean r3 = r15.hasNext()
            if (r3 == 0) goto L_0x0060
            java.lang.Object r3 = r15.next()
            r4 = r3
            org.videolan.medialibrary.media.MediaLibraryItem r4 = (org.videolan.medialibrary.media.MediaLibraryItem) r4
            int r4 = r4.getItemType()
            r5 = 3
            if (r4 == r5) goto L_0x0048
            r1.add(r3)
            goto L_0x0048
        L_0x0060:
            java.util.List r1 = (java.util.List) r1
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            org.videolan.vlc.gui.browser.BaseBrowserFragment r15 = r14.this$0
            java.util.ArrayList r3 = new java.util.ArrayList
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r1, r4)
            r3.<init>(r4)
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r1 = r1.iterator()
            r5 = r15
            r13 = r3
            r3 = r1
            r1 = r13
        L_0x007b:
            boolean r15 = r3.hasNext()
            if (r15 == 0) goto L_0x00a7
            java.lang.Object r15 = r3.next()
            org.videolan.medialibrary.media.MediaLibraryItem r15 = (org.videolan.medialibrary.media.MediaLibraryItem) r15
            java.lang.String r4 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r15, r4)
            org.videolan.medialibrary.interfaces.media.MediaWrapper r15 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r15
            r14.L$0 = r5
            r14.L$1 = r1
            r14.L$2 = r3
            r14.L$3 = r1
            r14.label = r2
            java.lang.Object r15 = r5.getMediaWithMeta(r15, r14)
            if (r15 != r0) goto L_0x009f
            return r0
        L_0x009f:
            r4 = r1
        L_0x00a0:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r15 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r15
            r1.add(r15)
            r1 = r4
            goto L_0x007b
        L_0x00a7:
            r8 = r1
            java.util.List r8 = (java.util.List) r8
            org.videolan.vlc.media.MediaUtils r6 = org.videolan.vlc.media.MediaUtils.INSTANCE
            android.view.View r15 = r14.$v
            android.content.Context r7 = r15.getContext()
            int r9 = r14.$position
            r11 = 8
            r12 = 0
            r10 = 0
            org.videolan.vlc.media.MediaUtils.openList$default(r6, r7, r8, r9, r10, r11, r12)
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.BaseBrowserFragment$onClick$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
