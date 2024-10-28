package org.videolan.vlc;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.libvlc.RendererDiscoverer;
import org.videolan.libvlc.RendererItem;
import org.videolan.resources.AppContextProvider;
import org.videolan.tools.AppScope;
import org.videolan.tools.Connection;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.tools.NetworkMonitor;
import org.videolan.tools.livedata.LiveDataset;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u000e\u0010\u0014\u001a\u00020\u0010H@¢\u0006\u0002\u0010\u0015J\u0006\u0010\u0016\u001a\u00020\u0010R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/RendererDelegate;", "Lorg/videolan/libvlc/RendererDiscoverer$EventListener;", "()V", "TAG", "", "discoverers", "Ljava/util/ArrayList;", "Lorg/videolan/libvlc/RendererDiscoverer;", "renderers", "Lorg/videolan/tools/livedata/LiveDataset;", "Lorg/videolan/libvlc/RendererItem;", "getRenderers", "()Lorg/videolan/tools/livedata/LiveDataset;", "started", "", "clear", "", "onEvent", "event", "Lorg/videolan/libvlc/RendererDiscoverer$Event;", "start", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stop", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RendererDelegate.kt */
public final class RendererDelegate implements RendererDiscoverer.EventListener {
    public static final RendererDelegate INSTANCE = new RendererDelegate();
    private static final String TAG = "VLC/RendererDelegate";
    private static final ArrayList<RendererDiscoverer> discoverers = new ArrayList<>();
    private static final LiveDataset<RendererItem> renderers = new LiveDataset<>();
    private static volatile boolean started;

    private RendererDelegate() {
    }

    static {
        FlowKt.launchIn(FlowKt.onEach(((NetworkMonitor) NetworkMonitor.Companion.getInstance(AppContextProvider.INSTANCE.getAppContext())).getConnectionFlow(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null)), AppScope.INSTANCE);
    }

    public final LiveDataset<RendererItem> getRenderers() {
        return renderers;
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/tools/Connection;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.RendererDelegate$1", f = "RendererDelegate.kt", i = {}, l = {45}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.RendererDelegate$1  reason: invalid class name */
    /* compiled from: RendererDelegate.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Connection, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(Connection connection, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(connection, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                if (((Connection) this.L$0).getConnected()) {
                    this.label = 1;
                    if (RendererDelegate.INSTANCE.start(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    RendererDelegate.INSTANCE.stop();
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object start(kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r13 = this;
            boolean r0 = r14 instanceof org.videolan.vlc.RendererDelegate$start$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            org.videolan.vlc.RendererDelegate$start$1 r0 = (org.videolan.vlc.RendererDelegate$start$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.RendererDelegate$start$1 r0 = new org.videolan.vlc.RendererDelegate$start$1
            r0.<init>(r13, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 1
            if (r2 == 0) goto L_0x004f
            if (r2 == r5) goto L_0x0047
            if (r2 != r4) goto L_0x003f
            int r2 = r0.I$1
            int r6 = r0.I$0
            java.lang.Object r7 = r0.L$2
            org.videolan.libvlc.RendererDiscoverer$Description[] r7 = (org.videolan.libvlc.RendererDiscoverer.Description[]) r7
            java.lang.Object r8 = r0.L$1
            org.videolan.libvlc.interfaces.ILibVLC r8 = (org.videolan.libvlc.interfaces.ILibVLC) r8
            java.lang.Object r9 = r0.L$0
            org.videolan.vlc.RendererDelegate r9 = (org.videolan.vlc.RendererDelegate) r9
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00b9
        L_0x003f:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0047:
            java.lang.Object r2 = r0.L$0
            org.videolan.vlc.RendererDelegate r2 = (org.videolan.vlc.RendererDelegate) r2
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0072
        L_0x004f:
            kotlin.ResultKt.throwOnFailure(r14)
            boolean r14 = started
            if (r14 == 0) goto L_0x0059
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x0059:
            kotlinx.coroutines.CoroutineDispatcher r14 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r14 = (kotlin.coroutines.CoroutineContext) r14
            org.videolan.vlc.RendererDelegate$start$libVlc$1 r2 = new org.videolan.vlc.RendererDelegate$start$libVlc$1
            r2.<init>(r3)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r13
            r0.label = r5
            java.lang.Object r14 = kotlinx.coroutines.BuildersKt.withContext(r14, r2, r0)
            if (r14 != r1) goto L_0x0071
            return r1
        L_0x0071:
            r2 = r13
        L_0x0072:
            org.videolan.libvlc.interfaces.ILibVLC r14 = (org.videolan.libvlc.interfaces.ILibVLC) r14
            started = r5
            org.videolan.libvlc.RendererDiscoverer$Description[] r6 = org.videolan.libvlc.RendererDiscoverer.list(r14)
            java.lang.String r7 = "list(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r7)
            int r7 = r6.length
            r8 = 0
            r8 = r14
            r9 = r2
            r2 = r7
            r7 = r6
            r6 = 0
        L_0x0086:
            if (r6 >= r2) goto L_0x00bb
            r14 = r7[r6]
            org.videolan.libvlc.RendererDiscoverer r10 = new org.videolan.libvlc.RendererDiscoverer
            java.lang.String r14 = r14.name
            r10.<init>(r8, r14)
            java.util.ArrayList<org.videolan.libvlc.RendererDiscoverer> r14 = discoverers
            r14.add(r10)
            r14 = r9
            org.videolan.libvlc.RendererDiscoverer$EventListener r14 = (org.videolan.libvlc.RendererDiscoverer.EventListener) r14
            r10.setEventListener(r14)
            org.videolan.vlc.RendererDelegate$start$2 r14 = new org.videolan.vlc.RendererDelegate$start$2
            r14.<init>(r10, r3)
            kotlin.jvm.functions.Function1 r14 = (kotlin.jvm.functions.Function1) r14
            r0.L$0 = r9
            r0.L$1 = r8
            r0.L$2 = r7
            r0.I$0 = r6
            r0.I$1 = r2
            r0.label = r4
            r10 = 5
            r11 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r14 = org.videolan.tools.KotlinExtensionsKt.retry(r10, r11, r14, r0)
            if (r14 != r1) goto L_0x00b9
            return r1
        L_0x00b9:
            int r6 = r6 + r5
            goto L_0x0086
        L_0x00bb:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.RendererDelegate.start(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void stop() {
        PlaybackService instance;
        if (started) {
            started = false;
            Iterator<RendererDiscoverer> it = discoverers.iterator();
            while (it.hasNext()) {
                it.next().stop();
            }
            if (KotlinExtensionsKt.isAppStarted() || (instance = PlaybackService.Companion.getInstance()) == null || (!instance.isPlaying())) {
                PlaybackService.Companion.getRenderer().setValue((RendererItem) null);
            }
            clear();
        }
    }

    private final void clear() {
        discoverers.clear();
        renderers.clear();
    }

    public void onEvent(RendererDiscoverer.Event event) {
        Integer valueOf = event != null ? Integer.valueOf(event.type) : null;
        if (valueOf != null && valueOf.intValue() == 1282) {
            LiveDataset<RendererItem> liveDataset = renderers;
            RendererItem item = event.getItem();
            Intrinsics.checkNotNullExpressionValue(item, "getItem(...)");
            liveDataset.add(item);
        } else if (valueOf != null && valueOf.intValue() == 1283) {
            LiveDataset<RendererItem> liveDataset2 = renderers;
            RendererItem item2 = event.getItem();
            Intrinsics.checkNotNullExpressionValue(item2, "getItem(...)");
            liveDataset2.remove(item2);
        }
    }
}
