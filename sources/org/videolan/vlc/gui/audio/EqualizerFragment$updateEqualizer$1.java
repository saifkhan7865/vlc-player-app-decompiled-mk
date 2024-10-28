package org.videolan.vlc.gui.audio;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.EqualizerFragment$updateEqualizer$1", f = "EqualizerFragment.kt", i = {}, l = {464}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: EqualizerFragment.kt */
final class EqualizerFragment$updateEqualizer$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $pos;
    Object L$0;
    int label;
    final /* synthetic */ EqualizerFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EqualizerFragment$updateEqualizer$1(EqualizerFragment equalizerFragment, int i, Continuation<? super EqualizerFragment$updateEqualizer$1> continuation) {
        super(2, continuation);
        this.this$0 = equalizerFragment;
        this.$pos = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EqualizerFragment$updateEqualizer$1(this.this$0, this.$pos, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EqualizerFragment$updateEqualizer$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x00f6  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00f9  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 0
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x001e
            if (r1 != r3) goto L_0x0016
            java.lang.Object r0 = r8.L$0
            org.videolan.vlc.gui.audio.EqualizerFragment r0 = (org.videolan.vlc.gui.audio.EqualizerFragment) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x00e3
        L_0x0016:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x001e:
            kotlin.ResultKt.throwOnFailure(r9)
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            boolean r9 = r9.updateAlreadyHandled
            if (r9 == 0) goto L_0x0030
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            r9.updateAlreadyHandled = r2
            goto L_0x00b9
        L_0x0030:
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            int r1 = r8.$pos
            int r9 = r9.getEqualizerType(r1)
            if (r9 != 0) goto L_0x0056
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            int r1 = r8.$pos
            org.videolan.libvlc.MediaPlayer$Equalizer r1 = org.videolan.libvlc.MediaPlayer.Equalizer.createFromPreset(r1)
            java.lang.String r5 = "createFromPreset(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r5)
            r9.equalizer = r1
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            org.videolan.vlc.gui.audio.EqualizerFragment$EqualizerState r9 = r9.state
            int r1 = r8.$pos
            r9.update(r1, r3)
            goto L_0x00b9
        L_0x0056:
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            int r1 = r8.$pos
            int r9 = r9.getEqualizerType(r1)
            if (r9 != r3) goto L_0x0095
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            org.videolan.resources.VLCOptions r1 = org.videolan.resources.VLCOptions.INSTANCE
            org.videolan.vlc.gui.audio.EqualizerFragment r5 = r8.this$0
            androidx.fragment.app.FragmentActivity r5 = r5.requireActivity()
            java.lang.String r6 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r6)
            android.content.Context r5 = (android.content.Context) r5
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r8.this$0
            java.util.List r6 = r6.allSets
            int r7 = r8.$pos
            java.lang.Object r6 = r6.get(r7)
            java.lang.String r6 = (java.lang.String) r6
            org.videolan.libvlc.MediaPlayer$Equalizer r1 = r1.getCustomSet(r5, r6)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r9.equalizer = r1
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            org.videolan.vlc.gui.audio.EqualizerFragment$EqualizerState r9 = r9.state
            int r1 = r8.$pos
            r9.update(r1, r3)
            goto L_0x00b9
        L_0x0095:
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            int r1 = r8.$pos
            int r9 = r9.getEqualizerType(r1)
            r1 = 2
            if (r9 != r1) goto L_0x00b9
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            org.videolan.libvlc.MediaPlayer$Equalizer r1 = org.videolan.libvlc.MediaPlayer.Equalizer.create()
            java.lang.String r5 = "create(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r5)
            r9.equalizer = r1
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            org.videolan.vlc.gui.audio.EqualizerFragment$EqualizerState r9 = r9.state
            int r1 = r8.$pos
            r9.update(r1, r2)
        L_0x00b9:
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            int r9 = r9.bandCount
            r1 = -1
            if (r9 != r1) goto L_0x00ec
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.vlc.gui.audio.EqualizerFragment$updateEqualizer$1$1 r5 = new org.videolan.vlc.gui.audio.EqualizerFragment$updateEqualizer$1$1
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r8.this$0
            r5.<init>(r6, r4)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = r8
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r8.L$0 = r9
            r8.label = r3
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r5, r6)
            if (r1 != r0) goto L_0x00e1
            return r0
        L_0x00e1:
            r0 = r9
            r9 = r1
        L_0x00e3:
            java.lang.Number r9 = (java.lang.Number) r9
            int r9 = r9.intValue()
            r0.bandCount = r9
        L_0x00ec:
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            androidx.lifecycle.LifecycleOwner r9 = (androidx.lifecycle.LifecycleOwner) r9
            boolean r9 = org.videolan.tools.KotlinExtensionsKt.isStarted(r9)
            if (r9 != 0) goto L_0x00f9
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        L_0x00f9:
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            org.videolan.vlc.databinding.EqualizerBinding r9 = r9.binding
            java.lang.String r0 = "binding"
            if (r9 != 0) goto L_0x0107
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r9 = r4
        L_0x0107:
            com.google.android.material.slider.Slider r9 = r9.equalizerPreamp
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r8.this$0
            org.videolan.libvlc.MediaPlayer$Equalizer r1 = r1.equalizer
            java.lang.String r3 = "equalizer"
            if (r1 != 0) goto L_0x0117
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r1 = r4
        L_0x0117:
            float r1 = r1.getPreAmp()
            int r1 = kotlin.math.MathKt.roundToInt((float) r1)
            float r1 = (float) r1
            r9.setValue(r1)
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            int r9 = r9.bandCount
        L_0x0129:
            if (r2 >= r9) goto L_0x015a
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r8.this$0
            org.videolan.vlc.databinding.EqualizerBinding r1 = r1.binding
            if (r1 != 0) goto L_0x0137
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r1 = r4
        L_0x0137:
            android.widget.LinearLayout r1 = r1.equalizerBands
            android.view.View r1 = r1.getChildAt(r2)
            java.lang.String r5 = "null cannot be cast to non-null type org.videolan.vlc.gui.view.EqualizerBar"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1, r5)
            org.videolan.vlc.gui.view.EqualizerBar r1 = (org.videolan.vlc.gui.view.EqualizerBar) r1
            org.videolan.vlc.gui.audio.EqualizerFragment r5 = r8.this$0
            org.videolan.libvlc.MediaPlayer$Equalizer r5 = r5.equalizer
            if (r5 != 0) goto L_0x0150
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            r5 = r4
        L_0x0150:
            float r5 = r5.getAmp(r2)
            r1.setValue(r5)
            int r2 = r2 + 1
            goto L_0x0129
        L_0x015a:
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r8.this$0
            org.videolan.vlc.databinding.EqualizerBinding r9 = r9.binding
            if (r9 != 0) goto L_0x0166
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r9 = r4
        L_0x0166:
            androidx.appcompat.widget.SwitchCompat r9 = r9.equalizerButton
            boolean r9 = r9.isChecked()
            if (r9 == 0) goto L_0x0184
            org.videolan.vlc.PlaybackService$Companion r9 = org.videolan.vlc.PlaybackService.Companion
            videolan.org.commontools.LiveEvent r9 = r9.getEqualizer()
            org.videolan.vlc.gui.audio.EqualizerFragment r0 = r8.this$0
            org.videolan.libvlc.MediaPlayer$Equalizer r0 = r0.equalizer
            if (r0 != 0) goto L_0x0180
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r3)
            goto L_0x0181
        L_0x0180:
            r4 = r0
        L_0x0181:
            r9.setValue(r4)
        L_0x0184:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.EqualizerFragment$updateEqualizer$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
