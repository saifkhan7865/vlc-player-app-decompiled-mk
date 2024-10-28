package org.videolan.vlc.gui.video;

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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.video.VideoPlayerActivity$onStart$2", f = "VideoPlayerActivity.kt", i = {}, l = {910}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideoPlayerActivity.kt */
final class VideoPlayerActivity$onStart$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ VideoPlayerActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideoPlayerActivity$onStart$2(VideoPlayerActivity videoPlayerActivity, Continuation<? super VideoPlayerActivity$onStart$2> continuation) {
        super(2, continuation);
        this.this$0 = videoPlayerActivity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideoPlayerActivity$onStart$2(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((VideoPlayerActivity$onStart$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MutableStateFlow<String> videoRemoteFlow = VideoPlayerActivity.Companion.getVideoRemoteFlow();
            final VideoPlayerActivity videoPlayerActivity = this.this$0;
            this.label = 1;
            if (videoRemoteFlow.collect(new FlowCollector() {
                /* JADX WARNING: Can't fix incorrect switch cases order */
                /* JADX WARNING: Code restructure failed: missing block: B:14:0x0048, code lost:
                    if (r5.equals("pause") == false) goto L_0x00bb;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:16:0x0052, code lost:
                    if (r5.equals("play") == false) goto L_0x00bb;
                 */
                /* JADX WARNING: Code restructure failed: missing block: B:17:0x0056, code lost:
                    r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(85);
                 */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public final java.lang.Object emit(java.lang.String r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
                    /*
                        r4 = this;
                        r0 = 0
                        if (r5 == 0) goto L_0x00bb
                        int r1 = r5.hashCode()
                        r2 = 88
                        switch(r1) {
                            case -1364013995: goto L_0x00ab;
                            case 3739: goto L_0x009b;
                            case 110999: goto L_0x008d;
                            case 3015911: goto L_0x007e;
                            case 3089570: goto L_0x006e;
                            case 3317767: goto L_0x005e;
                            case 3443508: goto L_0x004c;
                            case 106440182: goto L_0x0042;
                            case 108511772: goto L_0x0030;
                            case 304486277: goto L_0x0020;
                            case 2048286337: goto L_0x000e;
                            default: goto L_0x000c;
                        }
                    L_0x000c:
                        goto L_0x00bb
                    L_0x000e:
                        java.lang.String r1 = "skip-next"
                        boolean r5 = r5.equals(r1)
                        if (r5 != 0) goto L_0x0018
                        goto L_0x00bb
                    L_0x0018:
                        r5 = 87
                        java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
                        goto L_0x00bc
                    L_0x0020:
                        java.lang.String r1 = "skip-previous"
                        boolean r5 = r5.equals(r1)
                        if (r5 != 0) goto L_0x002a
                        goto L_0x00bb
                    L_0x002a:
                        java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
                        goto L_0x00bc
                    L_0x0030:
                        java.lang.String r1 = "right"
                        boolean r5 = r5.equals(r1)
                        if (r5 != 0) goto L_0x003a
                        goto L_0x00bb
                    L_0x003a:
                        r5 = 22
                        java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
                        goto L_0x00bc
                    L_0x0042:
                        java.lang.String r1 = "pause"
                        boolean r5 = r5.equals(r1)
                        if (r5 != 0) goto L_0x0056
                        goto L_0x00bb
                    L_0x004c:
                        java.lang.String r1 = "play"
                        boolean r5 = r5.equals(r1)
                        if (r5 != 0) goto L_0x0056
                        goto L_0x00bb
                    L_0x0056:
                        r5 = 85
                        java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
                        goto L_0x00bc
                    L_0x005e:
                        java.lang.String r1 = "left"
                        boolean r5 = r5.equals(r1)
                        if (r5 != 0) goto L_0x0067
                        goto L_0x00bb
                    L_0x0067:
                        r5 = 21
                        java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
                        goto L_0x00bc
                    L_0x006e:
                        java.lang.String r1 = "down"
                        boolean r5 = r5.equals(r1)
                        if (r5 != 0) goto L_0x0077
                        goto L_0x00bb
                    L_0x0077:
                        r5 = 20
                        java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
                        goto L_0x00bc
                    L_0x007e:
                        java.lang.String r1 = "back"
                        boolean r5 = r5.equals(r1)
                        if (r5 != 0) goto L_0x0087
                        goto L_0x00bb
                    L_0x0087:
                        r5 = 4
                        java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
                        goto L_0x00bc
                    L_0x008d:
                        java.lang.String r1 = "pip"
                        boolean r5 = r5.equals(r1)
                        if (r5 != 0) goto L_0x0096
                        goto L_0x00bb
                    L_0x0096:
                        java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r2)
                        goto L_0x00bc
                    L_0x009b:
                        java.lang.String r1 = "up"
                        boolean r5 = r5.equals(r1)
                        if (r5 != 0) goto L_0x00a4
                        goto L_0x00bb
                    L_0x00a4:
                        r5 = 19
                        java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
                        goto L_0x00bc
                    L_0x00ab:
                        java.lang.String r1 = "center"
                        boolean r5 = r5.equals(r1)
                        if (r5 != 0) goto L_0x00b4
                        goto L_0x00bb
                    L_0x00b4:
                        r5 = 23
                        java.lang.Integer r5 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r5)
                        goto L_0x00bc
                    L_0x00bb:
                        r5 = r0
                    L_0x00bc:
                        if (r5 == 0) goto L_0x00f2
                        org.videolan.vlc.gui.video.VideoPlayerActivity r1 = r3
                        java.lang.Number r5 = (java.lang.Number) r5
                        int r5 = r5.intValue()
                        org.videolan.vlc.gui.dialogs.VLCBottomSheetDialogFragment$Companion r2 = org.videolan.vlc.gui.dialogs.VLCBottomSheetDialogFragment.Companion
                        androidx.lifecycle.MutableLiveData r2 = r2.getShouldInterceptRemote()
                        java.lang.Object r2 = r2.getValue()
                        r3 = 1
                        java.lang.Boolean r3 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
                        boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
                        if (r2 != 0) goto L_0x00f2
                        r2 = r1
                        android.content.Context r2 = (android.content.Context) r2
                        r1.simulateKeyPress(r2, r5)
                        org.videolan.vlc.gui.video.VideoPlayerActivity$Companion r5 = org.videolan.vlc.gui.video.VideoPlayerActivity.Companion
                        kotlinx.coroutines.flow.MutableStateFlow r5 = r5.getVideoRemoteFlow()
                        java.lang.Object r5 = r5.emit(r0, r6)
                        java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        if (r5 != r6) goto L_0x00f2
                        return r5
                    L_0x00f2:
                        kotlin.Unit r5 = kotlin.Unit.INSTANCE
                        return r5
                    */
                    throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.video.VideoPlayerActivity$onStart$2.AnonymousClass1.emit(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
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
