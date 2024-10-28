package org.videolan.vlc.gui.browser;

import android.os.Bundle;
import androidx.fragment.app.FragmentActivity;
import java.util.LinkedList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.LifecycleAwareScheduler;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.browser.BaseBrowserFragment$playAll$1", f = "BaseBrowserFragment.kt", i = {0, 0}, l = {558}, m = "invokeSuspend", n = {"positionInPlaylist", "mediaLocations"}, s = {"L$0", "L$1"})
/* compiled from: BaseBrowserFragment.kt */
final class BaseBrowserFragment$playAll$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $mw;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ BaseBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseBrowserFragment$playAll$1(BaseBrowserFragment baseBrowserFragment, MediaWrapper mediaWrapper, Continuation<? super BaseBrowserFragment$playAll$1> continuation) {
        super(2, continuation);
        this.this$0 = baseBrowserFragment;
        this.$mw = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BaseBrowserFragment$playAll$1(this.this$0, this.$mw, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseBrowserFragment$playAll$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Ref.IntRef intRef;
        LinkedList linkedList;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            intRef = new Ref.IntRef();
            LinkedList linkedList2 = new LinkedList();
            LifecycleAwareScheduler.scheduleAction$default(this.this$0.getScheduler(), "msg_show_enqueuing", 1000, (Bundle) null, 4, (Object) null);
            final BaseBrowserFragment baseBrowserFragment = this.this$0;
            final MediaWrapper mediaWrapper = this.$mw;
            final LinkedList linkedList3 = linkedList2;
            final Ref.IntRef intRef2 = intRef;
            this.L$0 = intRef;
            this.L$1 = linkedList2;
            this.label = 1;
            if (BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            linkedList = linkedList2;
        } else if (i == 1) {
            linkedList = (LinkedList) this.L$1;
            intRef = (Ref.IntRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        LifecycleAwareScheduler.startAction$default(this.this$0.getScheduler(), "msg_hide_enqueuing", (Bundle) null, 2, (Object) null);
        FragmentActivity activity = this.this$0.getActivity();
        if (activity != null) {
            MediaUtils.openList$default(MediaUtils.INSTANCE, activity, linkedList, intRef.element, false, 8, (Object) null);
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.gui.browser.BaseBrowserFragment$playAll$1$1", f = "BaseBrowserFragment.kt", i = {1}, l = {559, 562}, m = "invokeSuspend", n = {"file"}, s = {"L$1"})
    /* renamed from: org.videolan.vlc.gui.browser.BaseBrowserFragment$playAll$1$1  reason: invalid class name */
    /* compiled from: BaseBrowserFragment.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(baseBrowserFragment, linkedList3, mediaWrapper, intRef2, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
            jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
            	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
            	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
            */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x0093  */
        /* JADX WARNING: Removed duplicated region for block: B:26:0x00bb A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x00d5 A[SYNTHETIC] */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 2
                r3 = 1
                if (r1 == 0) goto L_0x002b
                if (r1 == r3) goto L_0x0027
                if (r1 != r2) goto L_0x001f
                java.lang.Object r1 = r7.L$2
                java.util.LinkedList r1 = (java.util.LinkedList) r1
                java.lang.Object r4 = r7.L$1
                org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r4
                java.lang.Object r5 = r7.L$0
                java.util.Iterator r5 = (java.util.Iterator) r5
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x00bc
            L_0x001f:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L_0x0027:
                kotlin.ResultKt.throwOnFailure(r8)
                goto L_0x006d
            L_0x002b:
                kotlin.ResultKt.throwOnFailure(r8)
                org.videolan.vlc.gui.browser.BaseBrowserFragment r8 = r4
                org.videolan.vlc.viewmodels.SortableModel r8 = r8.getViewModel()
                org.videolan.vlc.viewmodels.browser.BrowserModel r8 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r8
                java.lang.String r8 = r8.getUrl()
                if (r8 == 0) goto L_0x0070
                r1 = 0
                r4 = 0
                java.lang.String r5 = "file"
                boolean r8 = kotlin.text.StringsKt.startsWith$default(r8, r5, r1, r2, r4)
                if (r8 != r3) goto L_0x0070
                org.videolan.vlc.gui.browser.BaseBrowserFragment r8 = r4
                org.videolan.vlc.viewmodels.SortableModel r8 = r8.getViewModel()
                org.videolan.vlc.viewmodels.browser.BrowserModel r8 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r8
                org.videolan.vlc.providers.BrowserProvider r8 = r8.getProvider()
                org.videolan.vlc.gui.browser.BaseBrowserFragment r1 = r4
                org.videolan.vlc.viewmodels.SortableModel r1 = r1.getViewModel()
                org.videolan.vlc.viewmodels.browser.BrowserModel r1 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r1
                java.lang.String r1 = r1.getUrl()
                kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                r4 = r7
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                r7.label = r3
                java.lang.Object r8 = r8.browseUrl(r1, r4)
                if (r8 != r0) goto L_0x006d
                return r0
            L_0x006d:
                java.util.List r8 = (java.util.List) r8
                goto L_0x0080
            L_0x0070:
                org.videolan.vlc.gui.browser.BaseBrowserFragment r8 = r4
                org.videolan.vlc.viewmodels.SortableModel r8 = r8.getViewModel()
                org.videolan.vlc.viewmodels.browser.BrowserModel r8 = (org.videolan.vlc.viewmodels.browser.BrowserModel) r8
                org.videolan.tools.livedata.LiveDataset r8 = r8.getDataset()
                java.util.List r8 = r8.getList()
            L_0x0080:
                java.lang.Iterable r8 = (java.lang.Iterable) r8
                java.lang.Class<org.videolan.medialibrary.interfaces.media.MediaWrapper> r1 = org.videolan.medialibrary.interfaces.media.MediaWrapper.class
                java.util.List r8 = kotlin.collections.CollectionsKt.filterIsInstance(r8, r1)
                java.util.Iterator r8 = r8.iterator()
                r5 = r8
            L_0x008d:
                boolean r8 = r5.hasNext()
                if (r8 == 0) goto L_0x00d5
                java.lang.Object r8 = r5.next()
                r4 = r8
                org.videolan.medialibrary.interfaces.media.MediaWrapper r4 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r4
                int r8 = r4.getType()
                if (r8 == 0) goto L_0x00a6
                int r8 = r4.getType()
                if (r8 != r3) goto L_0x008d
            L_0x00a6:
                java.util.LinkedList<org.videolan.medialibrary.interfaces.media.MediaWrapper> r1 = r5
                org.videolan.vlc.gui.browser.BaseBrowserFragment r8 = r4
                r6 = r7
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r7.L$0 = r5
                r7.L$1 = r4
                r7.L$2 = r1
                r7.label = r2
                java.lang.Object r8 = r8.getMediaWithMeta(r4, r6)
                if (r8 != r0) goto L_0x00bc
                return r0
            L_0x00bc:
                r1.add(r8)
                org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = r6
                if (r8 == 0) goto L_0x008d
                boolean r8 = r4.equals((org.videolan.medialibrary.interfaces.media.MediaWrapper) r8)
                if (r8 == 0) goto L_0x008d
                kotlin.jvm.internal.Ref$IntRef r8 = r7
                java.util.LinkedList<org.videolan.medialibrary.interfaces.media.MediaWrapper> r1 = r5
                int r1 = r1.size()
                int r1 = r1 - r3
                r8.element = r1
                goto L_0x008d
            L_0x00d5:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.BaseBrowserFragment$playAll$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }
}
