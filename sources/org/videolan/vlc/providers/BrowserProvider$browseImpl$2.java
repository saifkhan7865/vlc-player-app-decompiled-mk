package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider$browseImpl$2", f = "BrowserProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$browseImpl$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $url;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ BrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$browseImpl$2(BrowserProvider browserProvider, String str, Continuation<? super BrowserProvider$browseImpl$2> continuation) {
        super(2, continuation);
        this.this$0 = browserProvider;
        this.$url = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        BrowserProvider$browseImpl$2 browserProvider$browseImpl$2 = new BrowserProvider$browseImpl$2(this.this$0, this.$url, continuation);
        browserProvider$browseImpl$2.L$0 = obj;
        return browserProvider$browseImpl$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BrowserProvider$browseImpl$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.providers.BrowserProvider$browseImpl$2$1", f = "BrowserProvider.kt", i = {}, l = {196, 196}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.providers.BrowserProvider$browseImpl$2$1  reason: invalid class name */
    /* compiled from: BrowserProvider.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(browserProvider2, str, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                BrowserProvider browserProvider = browserProvider2;
                this.label = 1;
                obj = BrowserProvider.filesFlow$default(browserProvider, str, false, this, 2, (Object) null);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else if (i == 2) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            final BrowserProvider browserProvider2 = browserProvider2;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() {
                /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
                /* JADX WARNING: Removed duplicated region for block: B:18:0x004b  */
                /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final java.lang.Object emit(org.videolan.libvlc.interfaces.IMedia r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
                    /*
                        r4 = this;
                        boolean r0 = r6 instanceof org.videolan.vlc.providers.BrowserProvider$browseImpl$2$1$1$emit$1
                        if (r0 == 0) goto L_0x0014
                        r0 = r6
                        org.videolan.vlc.providers.BrowserProvider$browseImpl$2$1$1$emit$1 r0 = (org.videolan.vlc.providers.BrowserProvider$browseImpl$2$1$1$emit$1) r0
                        int r1 = r0.label
                        r2 = -2147483648(0xffffffff80000000, float:-0.0)
                        r1 = r1 & r2
                        if (r1 == 0) goto L_0x0014
                        int r6 = r0.label
                        int r6 = r6 - r2
                        r0.label = r6
                        goto L_0x0019
                    L_0x0014:
                        org.videolan.vlc.providers.BrowserProvider$browseImpl$2$1$1$emit$1 r0 = new org.videolan.vlc.providers.BrowserProvider$browseImpl$2$1$1$emit$1
                        r0.<init>(r4, r6)
                    L_0x0019:
                        java.lang.Object r6 = r0.result
                        java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r2 = r0.label
                        r3 = 1
                        if (r2 == 0) goto L_0x0036
                        if (r2 != r3) goto L_0x002e
                        java.lang.Object r5 = r0.L$0
                        org.videolan.vlc.providers.BrowserProvider$browseImpl$2$1$1 r5 = (org.videolan.vlc.providers.BrowserProvider$browseImpl$2.AnonymousClass1.AnonymousClass1) r5
                        kotlin.ResultKt.throwOnFailure(r6)
                        goto L_0x0047
                    L_0x002e:
                        java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                        java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
                        r5.<init>(r6)
                        throw r5
                    L_0x0036:
                        kotlin.ResultKt.throwOnFailure(r6)
                        org.videolan.vlc.providers.BrowserProvider r6 = r3
                        r0.L$0 = r4
                        r0.label = r3
                        java.lang.Object r6 = r6.findMedia(r5, r0)
                        if (r6 != r1) goto L_0x0046
                        return r1
                    L_0x0046:
                        r5 = r4
                    L_0x0047:
                        org.videolan.medialibrary.media.MediaLibraryItem r6 = (org.videolan.medialibrary.media.MediaLibraryItem) r6
                        if (r6 == 0) goto L_0x0050
                        org.videolan.vlc.providers.BrowserProvider r5 = r3
                        r5.addMedia(r6)
                    L_0x0050:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.BrowserProvider$browseImpl$2.AnonymousClass1.AnonymousClass1.emit(org.videolan.libvlc.interfaces.IMedia, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            BrowserProvider browserProvider = this.this$0;
            final BrowserProvider browserProvider2 = this.this$0;
            final String str = this.$url;
            browserProvider.discoveryJob = BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.L$0, browserProvider.getCoroutineContextProvider().getMain(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
