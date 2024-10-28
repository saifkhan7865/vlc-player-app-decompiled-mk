package org.videolan.vlc.gui.browser;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.BaseBrowserFragment$onActionItemClicked$2", f = "BaseBrowserFragment.kt", i = {0}, l = {604}, m = "invokeSuspend", n = {"destination$iv$iv"}, s = {"L$1"})
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$onActionItemClicked$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<MediaWrapper> $list;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$onActionItemClicked$2(BaseBrowserFragment baseBrowserFragment, List<? extends MediaWrapper> list, Continuation<? super BaseBrowserFragment$onActionItemClicked$2> continuation) {
        super(2, continuation);
        this.this$0 = baseBrowserFragment;
        this.$list = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseBrowserFragment$onActionItemClicked$2(this.this$0, this.$list, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseBrowserFragment$onActionItemClicked$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:9:0x005f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 1
            if (r1 == 0) goto L_0x002f
            if (r1 != r2) goto L_0x0027
            java.lang.Object r1 = r9.L$5
            java.util.Collection r1 = (java.util.Collection) r1
            java.lang.Object r3 = r9.L$4
            android.content.Context r3 = (android.content.Context) r3
            java.lang.Object r4 = r9.L$3
            org.videolan.vlc.media.MediaUtils r4 = (org.videolan.vlc.media.MediaUtils) r4
            java.lang.Object r5 = r9.L$2
            java.util.Iterator r5 = (java.util.Iterator) r5
            java.lang.Object r6 = r9.L$1
            java.util.Collection r6 = (java.util.Collection) r6
            java.lang.Object r7 = r9.L$0
            org.videolan.vlc.gui.browser.BaseBrowserFragment r7 = (org.videolan.vlc.gui.browser.BaseBrowserFragment) r7
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x007b
        L_0x0027:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x002f:
            kotlin.ResultKt.throwOnFailure(r10)
            org.videolan.vlc.media.MediaUtils r10 = org.videolan.vlc.media.MediaUtils.INSTANCE
            org.videolan.vlc.gui.browser.BaseBrowserFragment r1 = r9.this$0
            androidx.fragment.app.FragmentActivity r1 = r1.getActivity()
            android.content.Context r1 = (android.content.Context) r1
            java.util.List<org.videolan.medialibrary.interfaces.media.MediaWrapper> r3 = r9.$list
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            org.videolan.vlc.gui.browser.BaseBrowserFragment r4 = r9.this$0
            java.util.ArrayList r5 = new java.util.ArrayList
            r6 = 10
            int r6 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r3, r6)
            r5.<init>(r6)
            java.util.Collection r5 = (java.util.Collection) r5
            java.util.Iterator r3 = r3.iterator()
            r7 = r4
            r4 = r10
            r8 = r3
            r3 = r1
            r1 = r5
            r5 = r8
        L_0x0059:
            boolean r10 = r5.hasNext()
            if (r10 == 0) goto L_0x0082
            java.lang.Object r10 = r5.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r10 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r10
            r9.L$0 = r7
            r9.L$1 = r1
            r9.L$2 = r5
            r9.L$3 = r4
            r9.L$4 = r3
            r9.L$5 = r1
            r9.label = r2
            java.lang.Object r10 = r7.getMediaWithMeta(r10, r9)
            if (r10 != r0) goto L_0x007a
            return r0
        L_0x007a:
            r6 = r1
        L_0x007b:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r10 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r10
            r1.add(r10)
            r1 = r6
            goto L_0x0059
        L_0x0082:
            java.util.List r1 = (java.util.List) r1
            r4.appendMedia((android.content.Context) r3, (java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>) r1)
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.BaseBrowserFragment$onActionItemClicked$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
