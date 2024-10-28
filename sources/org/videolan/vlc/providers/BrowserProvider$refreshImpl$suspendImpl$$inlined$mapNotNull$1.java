package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001f\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007¸\u0006\t"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "collect", "", "collector", "Lkotlinx/coroutines/flow/FlowCollector;", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$$inlined$unsafeFlow$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$mapNotNull$$inlined$unsafeTransform$1"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SafeCollector.common.kt */
public final class BrowserProvider$refreshImpl$suspendImpl$$inlined$mapNotNull$1 implements Flow<MediaLibraryItem> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;
    final /* synthetic */ BrowserProvider this$0;

    public BrowserProvider$refreshImpl$suspendImpl$$inlined$mapNotNull$1(Flow flow, BrowserProvider browserProvider) {
        this.$this_unsafeTransform$inlined = flow;
        this.this$0 = browserProvider;
    }

    public Object collect(final FlowCollector flowCollector, Continuation continuation) {
        Flow flow = this.$this_unsafeTransform$inlined;
        final BrowserProvider browserProvider = this.this$0;
        Object collect = flow.collect(new FlowCollector() {
            /* JADX WARNING: Removed duplicated region for block: B:14:0x003d  */
            /* JADX WARNING: Removed duplicated region for block: B:19:0x0059  */
            /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public final java.lang.Object emit(java.lang.Object r7, kotlin.coroutines.Continuation r8) {
                /*
                    r6 = this;
                    boolean r0 = r8 instanceof org.videolan.vlc.providers.BrowserProvider$refreshImpl$suspendImpl$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1
                    if (r0 == 0) goto L_0x0014
                    r0 = r8
                    org.videolan.vlc.providers.BrowserProvider$refreshImpl$suspendImpl$$inlined$mapNotNull$1$2$1 r0 = (org.videolan.vlc.providers.BrowserProvider$refreshImpl$suspendImpl$$inlined$mapNotNull$1.AnonymousClass2.AnonymousClass1) r0
                    int r1 = r0.label
                    r2 = -2147483648(0xffffffff80000000, float:-0.0)
                    r1 = r1 & r2
                    if (r1 == 0) goto L_0x0014
                    int r8 = r0.label
                    int r8 = r8 - r2
                    r0.label = r8
                    goto L_0x0019
                L_0x0014:
                    org.videolan.vlc.providers.BrowserProvider$refreshImpl$suspendImpl$$inlined$mapNotNull$1$2$1 r0 = new org.videolan.vlc.providers.BrowserProvider$refreshImpl$suspendImpl$$inlined$mapNotNull$1$2$1
                    r0.<init>(r6, r8)
                L_0x0019:
                    java.lang.Object r8 = r0.result
                    java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r2 = r0.label
                    r3 = 2
                    r4 = 1
                    if (r2 == 0) goto L_0x003d
                    if (r2 == r4) goto L_0x0035
                    if (r2 != r3) goto L_0x002d
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L_0x0065
                L_0x002d:
                    java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
                    java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
                    r7.<init>(r8)
                    throw r7
                L_0x0035:
                    java.lang.Object r7 = r0.L$0
                    kotlinx.coroutines.flow.FlowCollector r7 = (kotlinx.coroutines.flow.FlowCollector) r7
                    kotlin.ResultKt.throwOnFailure(r8)
                    goto L_0x0057
                L_0x003d:
                    kotlin.ResultKt.throwOnFailure(r8)
                    kotlinx.coroutines.flow.FlowCollector r8 = r4
                    r2 = r0
                    kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                    org.videolan.libvlc.interfaces.IMedia r7 = (org.videolan.libvlc.interfaces.IMedia) r7
                    org.videolan.vlc.providers.BrowserProvider r2 = r2
                    r0.L$0 = r8
                    r0.label = r4
                    java.lang.Object r7 = r2.findMedia(r7, r0)
                    if (r7 != r1) goto L_0x0054
                    return r1
                L_0x0054:
                    r5 = r8
                    r8 = r7
                    r7 = r5
                L_0x0057:
                    if (r8 == 0) goto L_0x0065
                    r2 = 0
                    r0.L$0 = r2
                    r0.label = r3
                    java.lang.Object r7 = r7.emit(r8, r0)
                    if (r7 != r1) goto L_0x0065
                    return r1
                L_0x0065:
                    kotlin.Unit r7 = kotlin.Unit.INSTANCE
                    return r7
                */
                throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.providers.BrowserProvider$refreshImpl$suspendImpl$$inlined$mapNotNull$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
            }
        }, continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}