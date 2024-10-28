package org.videolan.vlc.gui.dialogs;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.MutableStateFlow;
import org.videolan.vlc.gui.video.VideoPlayerActivity;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.dialogs.VLCBottomSheetDialogFragment$onStart$1", f = "VLCBottomSheetDialogFragment.kt", i = {}, l = {49}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VLCBottomSheetDialogFragment.kt */
final class VLCBottomSheetDialogFragment$onStart$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VLCBottomSheetDialogFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VLCBottomSheetDialogFragment$onStart$1(VLCBottomSheetDialogFragment vLCBottomSheetDialogFragment, Continuation<? super VLCBottomSheetDialogFragment$onStart$1> continuation) {
        super(2, continuation);
        this.this$0 = vLCBottomSheetDialogFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VLCBottomSheetDialogFragment$onStart$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VLCBottomSheetDialogFragment$onStart$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow<String> videoRemoteFlow = VideoPlayerActivity.Companion.getVideoRemoteFlow();
            final VLCBottomSheetDialogFragment vLCBottomSheetDialogFragment = this.this$0;
            this.label = 1;
            if (videoRemoteFlow.collect(new FlowCollector() {
                /* JADX WARNING: Can't fix incorrect switch cases order */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final java.lang.Object emit(java.lang.String r3, kotlin.coroutines.Continuation<? super kotlin.Unit> r4) {
                    /*
                        r2 = this;
                        r0 = 0
                        if (r3 == 0) goto L_0x006b
                        int r1 = r3.hashCode()
                        switch(r1) {
                            case -1364013995: goto L_0x005b;
                            case 3739: goto L_0x004b;
                            case 3015911: goto L_0x003c;
                            case 3089570: goto L_0x002c;
                            case 3317767: goto L_0x001c;
                            case 108511772: goto L_0x000c;
                            default: goto L_0x000a;
                        }
                    L_0x000a:
                        goto L_0x006b
                    L_0x000c:
                        java.lang.String r1 = "right"
                        boolean r3 = r3.equals(r1)
                        if (r3 != 0) goto L_0x0015
                        goto L_0x006b
                    L_0x0015:
                        r3 = 22
                        java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
                        goto L_0x006c
                    L_0x001c:
                        java.lang.String r1 = "left"
                        boolean r3 = r3.equals(r1)
                        if (r3 != 0) goto L_0x0025
                        goto L_0x006b
                    L_0x0025:
                        r3 = 21
                        java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
                        goto L_0x006c
                    L_0x002c:
                        java.lang.String r1 = "down"
                        boolean r3 = r3.equals(r1)
                        if (r3 != 0) goto L_0x0035
                        goto L_0x006b
                    L_0x0035:
                        r3 = 20
                        java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
                        goto L_0x006c
                    L_0x003c:
                        java.lang.String r1 = "back"
                        boolean r3 = r3.equals(r1)
                        if (r3 != 0) goto L_0x0045
                        goto L_0x006b
                    L_0x0045:
                        r3 = 4
                        java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
                        goto L_0x006c
                    L_0x004b:
                        java.lang.String r1 = "up"
                        boolean r3 = r3.equals(r1)
                        if (r3 != 0) goto L_0x0054
                        goto L_0x006b
                    L_0x0054:
                        r3 = 19
                        java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
                        goto L_0x006c
                    L_0x005b:
                        java.lang.String r1 = "center"
                        boolean r3 = r3.equals(r1)
                        if (r3 != 0) goto L_0x0064
                        goto L_0x006b
                    L_0x0064:
                        r3 = 23
                        java.lang.Integer r3 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r3)
                        goto L_0x006c
                    L_0x006b:
                        r3 = r0
                    L_0x006c:
                        if (r3 == 0) goto L_0x008a
                        org.videolan.vlc.gui.dialogs.VLCBottomSheetDialogFragment r1 = r3
                        java.lang.Number r3 = (java.lang.Number) r3
                        int r3 = r3.intValue()
                        r1.simulateKeyPress(r3)
                        org.videolan.vlc.gui.video.VideoPlayerActivity$Companion r3 = org.videolan.vlc.gui.video.VideoPlayerActivity.Companion
                        kotlinx.coroutines.flow.MutableStateFlow r3 = r3.getVideoRemoteFlow()
                        java.lang.Object r3 = r3.emit(r0, r4)
                        java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        if (r3 != r4) goto L_0x008a
                        return r3
                    L_0x008a:
                        kotlin.Unit r3 = kotlin.Unit.INSTANCE
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.VLCBottomSheetDialogFragment$onStart$1.AnonymousClass1.emit(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        } else {
            ResultKt.throwOnFailure(obj);
        }
        throw new KotlinNothingValueException();
    }
}
