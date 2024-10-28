package org.videolan.vlc.gui.audio;

import android.content.Context;
import android.view.View;
import android.widget.CompoundButton;
import androidx.fragment.app.FragmentActivity;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.resources.VLCOptions;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.databinding.EqualizerBinding;
import videolan.org.commontools.LiveEvent;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1", f = "EqualizerFragment.kt", i = {0}, l = {129}, m = "invokeSuspend", n = {"presets"}, s = {"L$0"})
/* compiled from: EqualizerFragment.kt */
final class EqualizerFragment$fillViews$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ EqualizerFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    EqualizerFragment$fillViews$1(EqualizerFragment equalizerFragment, Continuation<? super EqualizerFragment$fillViews$1> continuation) {
        super(2, continuation);
        this.this$0 = equalizerFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new EqualizerFragment$fillViews$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((EqualizerFragment$fillViews$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r21) {
        /*
            r20 = this;
            r0 = r20
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = -1
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0025
            if (r2 != r4) goto L_0x001d
            java.lang.Object r1 = r0.L$1
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = (org.videolan.vlc.gui.audio.EqualizerFragment) r1
            java.lang.Object r2 = r0.L$0
            java.lang.String[] r2 = (java.lang.String[]) r2
            kotlin.ResultKt.throwOnFailure(r21)
            r7 = r21
            goto L_0x0064
        L_0x001d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x0025:
            kotlin.ResultKt.throwOnFailure(r21)
            org.videolan.vlc.gui.audio.EqualizerFragment$Companion r2 = org.videolan.vlc.gui.audio.EqualizerFragment.Companion
            java.lang.String[] r2 = r2.getEqualizerPresets()
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r0.this$0
            android.content.Context r6 = r6.getContext()
            if (r6 == 0) goto L_0x0310
            if (r2 != 0) goto L_0x003a
            goto L_0x0310
        L_0x003a:
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r0.this$0
            int r6 = r6.bandCount
            if (r6 != r3) goto L_0x006d
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r0.this$0
            kotlinx.coroutines.CoroutineDispatcher r7 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r7 = (kotlin.coroutines.CoroutineContext) r7
            org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$1 r8 = new org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$1
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r0.this$0
            r8.<init>(r9, r5)
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r0.L$0 = r2
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = kotlinx.coroutines.BuildersKt.withContext(r7, r8, r9)
            if (r7 != r1) goto L_0x0063
            return r1
        L_0x0063:
            r1 = r6
        L_0x0064:
            java.lang.Number r7 = (java.lang.Number) r7
            int r6 = r7.intValue()
            r1.bandCount = r6
        L_0x006d:
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            androidx.lifecycle.LifecycleOwner r1 = (androidx.lifecycle.LifecycleOwner) r1
            boolean r1 = org.videolan.tools.KotlinExtensionsKt.isStarted(r1)
            if (r1 != 0) goto L_0x007a
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x007a:
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            java.util.List r1 = r1.allSets
            r1.clear()
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.List r6 = (java.util.List) r6
            r1.allSets = r6
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            java.util.List r1 = r1.allSets
            int r6 = r2.length
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r6)
            java.util.List r2 = kotlin.collections.CollectionsKt.listOf(r2)
            java.util.Collection r2 = (java.util.Collection) r2
            r1.addAll(r2)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            java.util.List r2 = r1.allSets
            int r2 = r2.size()
            r1.presetCount = r2
            org.videolan.tools.Settings r1 = org.videolan.tools.Settings.INSTANCE
            org.videolan.vlc.gui.audio.EqualizerFragment r2 = r0.this$0
            androidx.fragment.app.FragmentActivity r2 = r2.requireActivity()
            java.lang.String r6 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r6)
            java.lang.Object r1 = r1.getInstance(r2)
            android.content.SharedPreferences r1 = (android.content.SharedPreferences) r1
            java.util.Map r1 = r1.getAll()
            java.lang.String r2 = "getAll(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            java.util.Set r1 = r1.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x00d4:
            boolean r2 = r1.hasNext()
            r7 = 0
            if (r2 == 0) goto L_0x0121
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r2 = r2.getKey()
            r8 = r2
            java.lang.String r8 = (java.lang.String) r8
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            java.lang.String r2 = "custom_equalizer_"
            r9 = 2
            boolean r2 = kotlin.text.StringsKt.startsWith$default(r8, r2, r7, r9, r5)
            if (r2 == 0) goto L_0x00d4
            org.videolan.vlc.gui.audio.EqualizerFragment r2 = r0.this$0
            java.util.List r2 = r2.allSets
            r12 = 4
            r13 = 0
            java.lang.String r9 = "custom_equalizer_"
            java.lang.String r10 = ""
            r11 = 0
            java.lang.String r14 = kotlin.text.StringsKt.replace$default((java.lang.String) r8, (java.lang.String) r9, (java.lang.String) r10, (boolean) r11, (int) r12, (java.lang.Object) r13)
            r18 = 4
            r19 = 0
            java.lang.String r15 = "_"
            java.lang.String r16 = " "
            r17 = 0
            java.lang.String r7 = kotlin.text.StringsKt.replace$default((java.lang.String) r14, (java.lang.String) r15, (java.lang.String) r16, (boolean) r17, (int) r18, (java.lang.Object) r19)
            r2.add(r7)
            org.videolan.vlc.gui.audio.EqualizerFragment r2 = r0.this$0
            int r7 = r2.customCount
            int r7 = r7 + r4
            r2.customCount = r7
            goto L_0x00d4
        L_0x0121:
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            java.util.List r1 = r1.allSets
            org.videolan.vlc.gui.audio.EqualizerFragment r2 = r0.this$0
            java.lang.String r2 = r2.newPresetName
            r1.add(r2)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            org.videolan.resources.VLCOptions r2 = org.videolan.resources.VLCOptions.INSTANCE
            org.videolan.vlc.gui.audio.EqualizerFragment r8 = r0.this$0
            androidx.fragment.app.FragmentActivity r8 = r8.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r6)
            android.content.Context r8 = (android.content.Context) r8
            org.videolan.libvlc.MediaPlayer$Equalizer r2 = r2.getEqualizerSetFromSettings(r8, r4)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            r1.equalizer = r2
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            org.videolan.vlc.databinding.EqualizerBinding r1 = r1.binding
            java.lang.String r2 = "binding"
            if (r1 != 0) goto L_0x0157
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r5
        L_0x0157:
            androidx.appcompat.widget.SwitchCompat r1 = r1.equalizerButton
            org.videolan.resources.VLCOptions r8 = org.videolan.resources.VLCOptions.INSTANCE
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r0.this$0
            androidx.fragment.app.FragmentActivity r9 = r9.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r6)
            android.content.Context r9 = (android.content.Context) r9
            boolean r6 = r8.getEqualizerEnabledState(r9)
            r1.setChecked(r6)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            org.videolan.vlc.databinding.EqualizerBinding r1 = r1.binding
            if (r1 != 0) goto L_0x0179
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r5
        L_0x0179:
            androidx.appcompat.widget.SwitchCompat r1 = r1.equalizerButton
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r0.this$0
            org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$$ExternalSyntheticLambda0 r8 = new org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$$ExternalSyntheticLambda0
            r8.<init>(r6)
            r1.setOnCheckedChangeListener(r8)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            org.videolan.vlc.databinding.EqualizerBinding r1 = r1.binding
            if (r1 != 0) goto L_0x0191
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r5
        L_0x0191:
            android.widget.Button r1 = r1.equalizerSave
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r0.this$0
            org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$$ExternalSyntheticLambda1 r8 = new org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$$ExternalSyntheticLambda1
            r8.<init>(r6)
            r1.setOnClickListener(r8)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            org.videolan.vlc.databinding.EqualizerBinding r1 = r1.binding
            if (r1 != 0) goto L_0x01a9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r5
        L_0x01a9:
            android.widget.Button r1 = r1.equalizerDelete
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r0.this$0
            org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$$ExternalSyntheticLambda2 r8 = new org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$$ExternalSyntheticLambda2
            r8.<init>(r6)
            r1.setOnClickListener(r8)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            org.videolan.vlc.databinding.EqualizerBinding r1 = r1.binding
            if (r1 != 0) goto L_0x01c1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r5
        L_0x01c1:
            android.widget.Button r1 = r1.equalizerRevert
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r0.this$0
            org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$$ExternalSyntheticLambda3 r8 = new org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$$ExternalSyntheticLambda3
            r8.<init>(r6)
            r1.setOnClickListener(r8)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            android.widget.ArrayAdapter r6 = new android.widget.ArrayAdapter
            org.videolan.vlc.gui.audio.EqualizerFragment r8 = r0.this$0
            android.content.Context r8 = r8.requireContext()
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r0.this$0
            java.util.List r9 = r9.allSets
            r10 = 17367049(0x1090009, float:2.516295E-38)
            r6.<init>(r8, r10, r9)
            r1.adapter = r6
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            org.videolan.vlc.databinding.EqualizerBinding r1 = r1.binding
            if (r1 != 0) goto L_0x01f2
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r5
        L_0x01f2:
            androidx.appcompat.widget.AppCompatSpinner r1 = r1.equalizerPresets
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r0.this$0
            android.widget.ArrayAdapter r6 = r6.adapter
            if (r6 != 0) goto L_0x0202
            java.lang.String r6 = "adapter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r6 = r5
        L_0x0202:
            android.widget.SpinnerAdapter r6 = (android.widget.SpinnerAdapter) r6
            r1.setAdapter((android.widget.SpinnerAdapter) r6)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            org.videolan.vlc.databinding.EqualizerBinding r1 = r1.binding
            if (r1 != 0) goto L_0x0213
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r5
        L_0x0213:
            com.google.android.material.slider.Slider r1 = r1.equalizerPreamp
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r0.this$0
            org.videolan.libvlc.MediaPlayer$Equalizer r6 = r6.equalizer
            java.lang.String r8 = "equalizer"
            if (r6 != 0) goto L_0x0223
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r6 = r5
        L_0x0223:
            float r6 = r6.getPreAmp()
            int r6 = kotlin.math.MathKt.roundToInt((float) r6)
            float r6 = (float) r6
            r1.setValue(r6)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            org.videolan.vlc.databinding.EqualizerBinding r1 = r1.binding
            if (r1 != 0) goto L_0x023b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r5
        L_0x023b:
            com.google.android.material.slider.Slider r1 = r1.equalizerPreamp
            org.videolan.vlc.gui.audio.EqualizerFragment r6 = r0.this$0
            com.google.android.material.slider.BaseOnChangeListener r6 = (com.google.android.material.slider.BaseOnChangeListener) r6
            r1.addOnChangeListener(r6)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            java.util.ArrayList r1 = r1.eqBandsViews
            r1.clear()
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            org.videolan.vlc.databinding.EqualizerBinding r1 = r1.binding
            if (r1 != 0) goto L_0x0259
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r5
        L_0x0259:
            android.widget.LinearLayout r1 = r1.equalizerBands
            r1.removeAllViews()
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            int r1 = r1.bandCount
            r6 = 0
        L_0x0265:
            if (r6 >= r1) goto L_0x02c7
            float r9 = org.videolan.libvlc.MediaPlayer.Equalizer.getBandFrequency(r6)
            org.videolan.vlc.gui.view.EqualizerBar r10 = new org.videolan.vlc.gui.view.EqualizerBar
            org.videolan.vlc.gui.audio.EqualizerFragment r11 = r0.this$0
            android.content.Context r11 = r11.requireContext()
            java.lang.String r12 = "requireContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r12)
            r10.<init>((android.content.Context) r11, (float) r9)
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r0.this$0
            org.videolan.libvlc.MediaPlayer$Equalizer r9 = r9.equalizer
            if (r9 != 0) goto L_0x0287
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r9 = r5
        L_0x0287:
            float r9 = r9.getAmp(r6)
            r10.setValue(r9)
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r0.this$0
            org.videolan.vlc.databinding.EqualizerBinding r9 = r9.binding
            if (r9 != 0) goto L_0x029a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r9 = r5
        L_0x029a:
            android.widget.LinearLayout r9 = r9.equalizerBands
            r11 = r10
            android.view.View r11 = (android.view.View) r11
            r9.addView(r11)
            android.widget.LinearLayout$LayoutParams r9 = new android.widget.LinearLayout$LayoutParams
            r11 = -2
            r12 = 1065353216(0x3f800000, float:1.0)
            r9.<init>(r11, r3, r12)
            android.view.ViewGroup$LayoutParams r9 = (android.view.ViewGroup.LayoutParams) r9
            r10.setLayoutParams(r9)
            org.videolan.vlc.gui.audio.EqualizerFragment r9 = r0.this$0
            java.util.ArrayList r9 = r9.eqBandsViews
            r9.add(r10)
            org.videolan.vlc.gui.audio.EqualizerFragment$BandListener r9 = new org.videolan.vlc.gui.audio.EqualizerFragment$BandListener
            org.videolan.vlc.gui.audio.EqualizerFragment r11 = r0.this$0
            r9.<init>(r6)
            org.videolan.vlc.interfaces.OnEqualizerBarChangeListener r9 = (org.videolan.vlc.interfaces.OnEqualizerBarChangeListener) r9
            r10.setListener(r9)
            int r6 = r6 + 1
            goto L_0x0265
        L_0x02c7:
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            java.util.ArrayList r1 = r1.eqBandsViews
            java.lang.Object r1 = r1.get(r7)
            org.videolan.vlc.gui.view.EqualizerBar r1 = (org.videolan.vlc.gui.view.EqualizerBar) r1
            int r3 = org.videolan.vlc.R.id.equalizer_preamp
            r1.setNextFocusLeftId(r3)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            java.util.ArrayList r1 = r1.eqBandsViews
            org.videolan.vlc.gui.audio.EqualizerFragment r3 = r0.this$0
            java.util.ArrayList r3 = r3.eqBandsViews
            int r3 = r3.size()
            int r3 = r3 - r4
            java.lang.Object r1 = r1.get(r3)
            org.videolan.vlc.gui.view.EqualizerBar r1 = (org.videolan.vlc.gui.view.EqualizerBar) r1
            int r3 = org.videolan.vlc.R.id.snapBands
            r1.setNextFocusRightId(r3)
            org.videolan.vlc.gui.audio.EqualizerFragment r1 = r0.this$0
            org.videolan.vlc.databinding.EqualizerBinding r1 = r1.binding
            if (r1 != 0) goto L_0x0300
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L_0x0301
        L_0x0300:
            r5 = r1
        L_0x0301:
            androidx.appcompat.widget.AppCompatSpinner r1 = r5.equalizerPresets
            org.videolan.vlc.gui.audio.EqualizerFragment r2 = r0.this$0
            org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$$ExternalSyntheticLambda4 r3 = new org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1$$ExternalSyntheticLambda4
            r3.<init>(r2)
            r1.post(r3)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x0310:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.EqualizerFragment$fillViews$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$0(EqualizerFragment equalizerFragment, CompoundButton compoundButton, boolean z) {
        LiveEvent<MediaPlayer.Equalizer> equalizer = PlaybackService.Companion.getEqualizer();
        MediaPlayer.Equalizer equalizer2 = null;
        if (z) {
            MediaPlayer.Equalizer access$getEqualizer$p = equalizerFragment.equalizer;
            if (access$getEqualizer$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("equalizer");
            } else {
                equalizer2 = access$getEqualizer$p;
            }
        }
        equalizer.setValue(equalizer2);
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$1(EqualizerFragment equalizerFragment, View view) {
        EqualizerBinding access$getBinding$p = equalizerFragment.binding;
        if (access$getBinding$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            access$getBinding$p = null;
        }
        equalizerFragment.createSaveCustomSetDialog(access$getBinding$p.equalizerPresets.getSelectedItemPosition(), true, false);
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$2(EqualizerFragment equalizerFragment, View view) {
        equalizerFragment.createDeleteCustomSetSnacker();
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$3(EqualizerFragment equalizerFragment, View view) {
        equalizerFragment.revertCustomSetChanges();
    }

    /* access modifiers changed from: private */
    public static final void invokeSuspend$lambda$4(EqualizerFragment equalizerFragment) {
        FragmentActivity activity = equalizerFragment.getActivity();
        if (activity != null) {
            EqualizerBinding access$getBinding$p = equalizerFragment.binding;
            EqualizerBinding equalizerBinding = null;
            if (access$getBinding$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p = null;
            }
            access$getBinding$p.equalizerPresets.setOnItemSelectedListener(equalizerFragment.setListener);
            Context context = activity;
            int indexOf = CollectionsKt.indexOf(equalizerFragment.allSets, VLCOptions.INSTANCE.getEqualizerNameFromSettings(context));
            equalizerFragment.state.update(indexOf, VLCOptions.INSTANCE.getEqualizerSavedState(context));
            equalizerFragment.updateAlreadyHandled = true;
            EqualizerBinding access$getBinding$p2 = equalizerFragment.binding;
            if (access$getBinding$p2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                access$getBinding$p2 = null;
            }
            int i = 0;
            if (access$getBinding$p2.equalizerButton.isChecked() || !equalizerFragment.state.getSaved$vlc_android_release()) {
                equalizerFragment.savePos = indexOf;
                if (equalizerFragment.getEqualizerType(indexOf) == 1) {
                    i = indexOf;
                }
                equalizerFragment.revertPos = i;
                EqualizerBinding access$getBinding$p3 = equalizerFragment.binding;
                if (access$getBinding$p3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    equalizerBinding = access$getBinding$p3;
                }
                equalizerBinding.equalizerPresets.setSelection(indexOf);
                return;
            }
            Job unused = equalizerFragment.updateEqualizer(0);
        }
    }
}
