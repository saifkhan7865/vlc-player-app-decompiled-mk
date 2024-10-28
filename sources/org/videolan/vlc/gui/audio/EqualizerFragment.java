package org.videolan.vlc.gui.audio;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.google.android.material.slider.Slider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.VLCOptions;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.EqualizerBinding;
import org.videolan.vlc.gui.dialogs.VLCBottomSheetDialogFragment;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.EqualizerBar;
import org.videolan.vlc.interfaces.OnEqualizerBarChangeListener;
import videolan.org.commontools.LiveEvent;

@Metadata(d1 = {"\u0000±\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001\u0019\u0018\u0000 O2\u00020\u00012\u00020\u0002:\u0003NOPB\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u001f\u001a\u00020\u001eH\u0016J\b\u0010 \u001a\u00020!H\u0002J \u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020\n2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u001eH\u0002J\b\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020\nH\u0016J\u0010\u0010)\u001a\u00020\n2\u0006\u0010*\u001a\u00020\nH\u0002J\b\u0010+\u001a\u00020,H\u0016J\b\u0010-\u001a\u00020\u001eH\u0016J$\u0010.\u001a\u00020,2\u0006\u0010/\u001a\u0002002\b\u00101\u001a\u0004\u0018\u0001022\b\u00103\u001a\u0004\u0018\u000104H\u0016J\b\u00105\u001a\u00020!H\u0016J\u0010\u00106\u001a\u00020!2\u0006\u00107\u001a\u000208H\u0016J\b\u0010%\u001a\u00020!H\u0016J\b\u00109\u001a\u00020!H\u0016J \u0010:\u001a\u00020!2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020\u001eH\u0016J\u001a\u0010@\u001a\u00020!2\u0006\u0010A\u001a\u00020,2\b\u00103\u001a\u0004\u0018\u000104H\u0017J\b\u0010B\u001a\u00020!H\u0002JH\u0010C\u001a\u00020!2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020\u00062\u0006\u0010I\u001a\u00020\u00132\u0006\u0010%\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\u001e2\u0006\u0010#\u001a\u00020\n2\u0006\u0010J\u001a\u00020KH\u0002J\u0010\u0010L\u001a\u00020'2\u0006\u0010M\u001a\u00020\nH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u000fj\b\u0012\u0004\u0012\u00020\u0010`\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u0012\u0010\u001b\u001a\u00060\u001cR\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u000e¢\u0006\u0002\n\u0000¨\u0006Q"}, d2 = {"Lorg/videolan/vlc/gui/audio/EqualizerFragment;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "Lcom/google/android/material/slider/Slider$OnChangeListener;", "()V", "adapter", "Landroid/widget/ArrayAdapter;", "", "allSets", "", "bandCount", "", "binding", "Lorg/videolan/vlc/databinding/EqualizerBinding;", "customCount", "eqBandsViews", "Ljava/util/ArrayList;", "Lorg/videolan/vlc/gui/view/EqualizerBar;", "Lkotlin/collections/ArrayList;", "equalizer", "Lorg/videolan/libvlc/MediaPlayer$Equalizer;", "newPresetName", "presetCount", "revertPos", "savePos", "setListener", "org/videolan/vlc/gui/audio/EqualizerFragment$setListener$1", "Lorg/videolan/vlc/gui/audio/EqualizerFragment$setListener$1;", "state", "Lorg/videolan/vlc/gui/audio/EqualizerFragment$EqualizerState;", "updateAlreadyHandled", "", "allowRemote", "createDeleteCustomSetSnacker", "", "createSaveCustomSetDialog", "positionToSave", "displayedByUser", "onPause", "fillViews", "Lkotlinx/coroutines/Job;", "getDefaultState", "getEqualizerType", "position", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onDismiss", "dialog", "Landroid/content/DialogInterface;", "onResume", "onValueChange", "slider", "Lcom/google/android/material/slider/Slider;", "value", "", "fromUser", "onViewCreated", "view", "revertCustomSetChanges", "save", "ctx", "Landroid/content/Context;", "input", "Landroid/widget/EditText;", "oldName", "temporarySet", "saveEqualizer", "Landroidx/appcompat/app/AlertDialog;", "updateEqualizer", "pos", "BandListener", "Companion", "EqualizerState", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: EqualizerFragment.kt */
public final class EqualizerFragment extends VLCBottomSheetDialogFragment implements Slider.OnChangeListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/EqualizerFragment";
    private static final int TYPE_CUSTOM = 1;
    private static final int TYPE_NEW = 2;
    private static final int TYPE_PRESET = 0;
    /* access modifiers changed from: private */
    public ArrayAdapter<String> adapter;
    /* access modifiers changed from: private */
    public List<String> allSets = new ArrayList();
    /* access modifiers changed from: private */
    public int bandCount;
    /* access modifiers changed from: private */
    public EqualizerBinding binding;
    /* access modifiers changed from: private */
    public int customCount;
    /* access modifiers changed from: private */
    public final ArrayList<EqualizerBar> eqBandsViews;
    /* access modifiers changed from: private */
    public MediaPlayer.Equalizer equalizer;
    /* access modifiers changed from: private */
    public final String newPresetName;
    /* access modifiers changed from: private */
    public int presetCount;
    /* access modifiers changed from: private */
    public int revertPos;
    /* access modifiers changed from: private */
    public int savePos;
    /* access modifiers changed from: private */
    public final EqualizerFragment$setListener$1 setListener;
    /* access modifiers changed from: private */
    public final EqualizerState state = new EqualizerState();
    /* access modifiers changed from: private */
    public boolean updateAlreadyHandled;

    public boolean allowRemote() {
        return true;
    }

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    public EqualizerFragment() {
        String string = AppContextProvider.INSTANCE.getAppResources().getString(R.string.equalizer_new_preset_name);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        this.newPresetName = string;
        this.bandCount = -1;
        this.eqBandsViews = new ArrayList<>();
        this.setListener = new EqualizerFragment$setListener$1(this);
    }

    public View initialFocusedView() {
        EqualizerBinding equalizerBinding = this.binding;
        if (equalizerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            equalizerBinding = null;
        }
        ConstraintLayout constraintLayout = equalizerBinding.equalizerContainer;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "equalizerContainer");
        return constraintLayout;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        super.onCreateView(layoutInflater, viewGroup, bundle);
        ViewDataBinding inflate = DataBindingUtil.inflate(layoutInflater, R.layout.equalizer, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        EqualizerBinding equalizerBinding = (EqualizerBinding) inflate;
        this.binding = equalizerBinding;
        EqualizerBinding equalizerBinding2 = null;
        if (equalizerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            equalizerBinding = null;
        }
        equalizerBinding.setState(this.state);
        this.customCount = 0;
        EqualizerBinding equalizerBinding3 = this.binding;
        if (equalizerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            equalizerBinding2 = equalizerBinding3;
        }
        View root = equalizerBinding2.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        EqualizerBinding equalizerBinding = this.binding;
        if (equalizerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            equalizerBinding = null;
        }
        equalizerBinding.equalizerBands.setOnTouchListener(new EqualizerFragment$$ExternalSyntheticLambda5());
    }

    /* access modifiers changed from: private */
    public static final boolean onViewCreated$lambda$0(View view, MotionEvent motionEvent) {
        view.getParent().requestDisallowInterceptTouchEvent(true);
        return true;
    }

    private final Job fillViews() {
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new EqualizerFragment$fillViews$1(this, (Continuation<? super EqualizerFragment$fillViews$1>) null), 1, (Object) null);
    }

    public void onResume() {
        fillViews();
        super.onResume();
    }

    public void onPause() {
        MediaPlayer.Equalizer equalizer2;
        super.onPause();
        EqualizerBinding equalizerBinding = this.binding;
        if (equalizerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            equalizerBinding = null;
        }
        equalizerBinding.equalizerButton.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        EqualizerBinding equalizerBinding2 = this.binding;
        if (equalizerBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            equalizerBinding2 = null;
        }
        equalizerBinding2.equalizerPresets.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) null);
        EqualizerBinding equalizerBinding3 = this.binding;
        if (equalizerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            equalizerBinding3 = null;
        }
        equalizerBinding3.equalizerPreamp.removeOnChangeListener(this);
        EqualizerBinding equalizerBinding4 = this.binding;
        if (equalizerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            equalizerBinding4 = null;
        }
        if (equalizerBinding4.equalizerButton.isChecked()) {
            EqualizerBinding equalizerBinding5 = this.binding;
            if (equalizerBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                equalizerBinding5 = null;
            }
            int selectedItemPosition = equalizerBinding5.equalizerPresets.getSelectedItemPosition();
            if (this.equalizer != null) {
                VLCOptions vLCOptions = VLCOptions.INSTANCE;
                FragmentActivity requireActivity = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
                Context context = requireActivity;
                MediaPlayer.Equalizer equalizer3 = this.equalizer;
                if (equalizer3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("equalizer");
                    equalizer2 = null;
                } else {
                    equalizer2 = equalizer3;
                }
                vLCOptions.saveEqualizerInSettings(context, equalizer2, this.allSets.get(selectedItemPosition), true, this.state.getSaved$vlc_android_release());
                return;
            }
            return;
        }
        VLCOptions vLCOptions2 = VLCOptions.INSTANCE;
        FragmentActivity requireActivity2 = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
        vLCOptions2.saveEqualizerInSettings(requireActivity2, MediaPlayer.Equalizer.createFromPreset(0), this.allSets.get(0), false, true);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        EqualizerBinding equalizerBinding;
        Intrinsics.checkNotNullParameter(dialogInterface, "dialog");
        Iterator it = this.eqBandsViews.iterator();
        while (true) {
            equalizerBinding = null;
            if (!it.hasNext()) {
                break;
            }
            ((EqualizerBar) it.next()).setListener((OnEqualizerBarChangeListener) null);
        }
        super.onDismiss(dialogInterface);
        if (!this.state.getSaved$vlc_android_release()) {
            EqualizerBinding equalizerBinding2 = this.binding;
            if (equalizerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                equalizerBinding = equalizerBinding2;
            }
            createSaveCustomSetDialog(equalizerBinding.equalizerPresets.getSelectedItemPosition(), false, true);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        PlaybackService.Companion.getEqualizer().clear();
    }

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\bH\u0016J\b\u0010\u000e\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/gui/audio/EqualizerFragment$BandListener;", "Lorg/videolan/vlc/interfaces/OnEqualizerBarChangeListener;", "index", "", "(Lorg/videolan/vlc/gui/audio/EqualizerFragment;I)V", "oldBands", "", "onProgressChanged", "", "value", "", "fromUser", "", "onStartTrackingTouch", "onStopTrackingTouch", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: EqualizerFragment.kt */
    private final class BandListener implements OnEqualizerBarChangeListener {
        private final int index;
        private List<Integer> oldBands = new ArrayList();

        public BandListener(int i) {
            this.index = i;
        }

        public void onProgressChanged(float f, boolean z) {
            if (z) {
                MediaPlayer.Equalizer access$getEqualizer$p = EqualizerFragment.this.equalizer;
                MediaPlayer.Equalizer equalizer = null;
                if (access$getEqualizer$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("equalizer");
                    access$getEqualizer$p = null;
                }
                access$getEqualizer$p.setAmp(this.index, f);
                EqualizerBinding access$getBinding$p = EqualizerFragment.this.binding;
                if (access$getBinding$p == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    access$getBinding$p = null;
                }
                if (!access$getBinding$p.equalizerButton.isChecked()) {
                    EqualizerBinding access$getBinding$p2 = EqualizerFragment.this.binding;
                    if (access$getBinding$p2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        access$getBinding$p2 = null;
                    }
                    access$getBinding$p2.equalizerButton.setChecked(true);
                }
                EqualizerBinding access$getBinding$p3 = EqualizerFragment.this.binding;
                if (access$getBinding$p3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    access$getBinding$p3 = null;
                }
                int selectedItemPosition = access$getBinding$p3.equalizerPresets.getSelectedItemPosition();
                if (EqualizerFragment.this.getEqualizerType(selectedItemPosition) == 0) {
                    EqualizerFragment.this.revertPos = selectedItemPosition;
                    EqualizerFragment equalizerFragment = EqualizerFragment.this;
                    equalizerFragment.savePos = equalizerFragment.presetCount + EqualizerFragment.this.customCount;
                    EqualizerFragment.this.state.update(EqualizerFragment.this.presetCount + EqualizerFragment.this.customCount, false);
                    EqualizerFragment.this.updateAlreadyHandled = true;
                    EqualizerBinding access$getBinding$p4 = EqualizerFragment.this.binding;
                    if (access$getBinding$p4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        access$getBinding$p4 = null;
                    }
                    access$getBinding$p4.equalizerPresets.setSelection(EqualizerFragment.this.presetCount + EqualizerFragment.this.customCount);
                } else if (EqualizerFragment.this.getEqualizerType(selectedItemPosition) == 1) {
                    EqualizerFragment.this.revertPos = selectedItemPosition;
                    EqualizerFragment.this.savePos = selectedItemPosition;
                    EqualizerFragment.this.state.update(selectedItemPosition, false);
                }
                EqualizerBinding access$getBinding$p5 = EqualizerFragment.this.binding;
                if (access$getBinding$p5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    access$getBinding$p5 = null;
                }
                if (access$getBinding$p5.snapBands.isChecked() && (!this.oldBands.isEmpty())) {
                    int progress = ((EqualizerBar) EqualizerFragment.this.eqBandsViews.get(this.index)).getProgress() - this.oldBands.get(this.index).intValue();
                    int size = EqualizerFragment.this.eqBandsViews.size();
                    for (int i = 0; i < size; i++) {
                        if (i != this.index) {
                            ((EqualizerBar) EqualizerFragment.this.eqBandsViews.get(i)).setProgress(RangesKt.coerceIn(this.oldBands.get(i).intValue() + (progress / (((Math.abs(i - this.index) * Math.abs(i - this.index)) * Math.abs(i - this.index)) + 1)), 0, 400));
                            EqualizerBinding access$getBinding$p6 = EqualizerFragment.this.binding;
                            if (access$getBinding$p6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                access$getBinding$p6 = null;
                            }
                            if (access$getBinding$p6.equalizerButton.isChecked()) {
                                MediaPlayer.Equalizer access$getEqualizer$p2 = EqualizerFragment.this.equalizer;
                                if (access$getEqualizer$p2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("equalizer");
                                    access$getEqualizer$p2 = null;
                                }
                                access$getEqualizer$p2.setAmp(i, ((EqualizerBar) EqualizerFragment.this.eqBandsViews.get(i)).getValue());
                            }
                        }
                    }
                }
                EqualizerBinding access$getBinding$p7 = EqualizerFragment.this.binding;
                if (access$getBinding$p7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    access$getBinding$p7 = null;
                }
                if (access$getBinding$p7.equalizerButton.isChecked()) {
                    LiveEvent<MediaPlayer.Equalizer> equalizer2 = PlaybackService.Companion.getEqualizer();
                    MediaPlayer.Equalizer access$getEqualizer$p3 = EqualizerFragment.this.equalizer;
                    if (access$getEqualizer$p3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("equalizer");
                    } else {
                        equalizer = access$getEqualizer$p3;
                    }
                    equalizer2.setValue(equalizer);
                }
            }
        }

        public void onStartTrackingTouch() {
            this.oldBands.clear();
            Iterator it = EqualizerFragment.this.eqBandsViews.iterator();
            while (it.hasNext()) {
                this.oldBands.add(Integer.valueOf(((EqualizerBar) it.next()).getProgress()));
            }
        }

        public void onStopTrackingTouch() {
            this.oldBands.clear();
        }
    }

    /* access modifiers changed from: private */
    public final void createSaveCustomSetDialog(int i, boolean z, boolean z2) {
        int i2;
        int i3;
        int i4 = i;
        boolean z3 = z2;
        String str = this.allSets.get(i4);
        MediaPlayer.Equalizer create = MediaPlayer.Equalizer.create();
        MediaPlayer.Equalizer equalizer2 = this.equalizer;
        if (equalizer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("equalizer");
            equalizer2 = null;
        }
        create.setPreAmp(equalizer2.getPreAmp());
        int bandCount2 = MediaPlayer.Equalizer.getBandCount();
        for (int i5 = 0; i5 < bandCount2; i5++) {
            MediaPlayer.Equalizer equalizer3 = this.equalizer;
            if (equalizer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("equalizer");
                equalizer3 = null;
            }
            create.setAmp(i5, equalizer3.getAmp(i5));
        }
        EditText editText = new EditText(getContext());
        editText.setText(str);
        editText.setSelectAllOnFocus(true);
        editText.setSingleLine();
        editText.addTextChangedListener(new EqualizerFragment$createSaveCustomSetDialog$1(this, editText, str));
        FrameLayout frameLayout = new FrameLayout(requireContext());
        int dimension = (int) getResources().getDimension(R.dimen.kl_normal);
        frameLayout.setPadding(dimension, 0, dimension, 0);
        frameLayout.addView(editText);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        Resources resources = getResources();
        if (z) {
            i2 = R.string.custom_set_save_title;
        } else {
            i2 = R.string.custom_set_save_warning;
        }
        AlertDialog.Builder title = builder.setTitle((CharSequence) resources.getString(i2));
        Resources resources2 = getResources();
        if (getEqualizerType(i) == 1) {
            i3 = R.string.existing_custom_set_save_message;
        } else {
            i3 = R.string.new_custom_set_save_message;
        }
        AlertDialog create2 = title.setMessage((CharSequence) resources2.getString(i3)).setView((View) frameLayout).setPositiveButton(R.string.save, (DialogInterface.OnClickListener) null).setNegativeButton(R.string.do_not_save, (DialogInterface.OnClickListener) new EqualizerFragment$$ExternalSyntheticLambda0(z3, this, i4)).setOnCancelListener(new EqualizerFragment$$ExternalSyntheticLambda1(z3, this, i4)).create();
        Intrinsics.checkNotNullExpressionValue(create2, "create(...)");
        EqualizerFragment$$ExternalSyntheticLambda2 equalizerFragment$$ExternalSyntheticLambda2 = r0;
        EqualizerFragment$$ExternalSyntheticLambda2 equalizerFragment$$ExternalSyntheticLambda22 = new EqualizerFragment$$ExternalSyntheticLambda2(this, create2, editText, str, create, z2, z, i);
        editText.setOnKeyListener(equalizerFragment$$ExternalSyntheticLambda2);
        editText.requestFocus();
        Window window = create2.getWindow();
        if (window != null) {
            window.setSoftInputMode(5);
        }
        create2.setOnShowListener(new EqualizerFragment$$ExternalSyntheticLambda3(this, create2, editText, str, create, z2, z, i));
        create2.show();
    }

    /* access modifiers changed from: private */
    public static final void createSaveCustomSetDialog$lambda$2(boolean z, EqualizerFragment equalizerFragment, int i, DialogInterface dialogInterface, int i2) {
        MediaPlayer.Equalizer equalizer2;
        Intrinsics.checkNotNullParameter(equalizerFragment, "this$0");
        if (z) {
            VLCOptions vLCOptions = VLCOptions.INSTANCE;
            Context appContext = AppContextProvider.INSTANCE.getAppContext();
            MediaPlayer.Equalizer equalizer3 = equalizerFragment.equalizer;
            EqualizerBinding equalizerBinding = null;
            if (equalizer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("equalizer");
                equalizer2 = null;
            } else {
                equalizer2 = equalizer3;
            }
            String str = equalizerFragment.allSets.get(i);
            EqualizerBinding equalizerBinding2 = equalizerFragment.binding;
            if (equalizerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                equalizerBinding = equalizerBinding2;
            }
            vLCOptions.saveEqualizerInSettings(appContext, equalizer2, str, equalizerBinding.equalizerButton.isChecked(), false);
        }
    }

    /* access modifiers changed from: private */
    public static final void createSaveCustomSetDialog$lambda$3(boolean z, EqualizerFragment equalizerFragment, int i, DialogInterface dialogInterface) {
        MediaPlayer.Equalizer equalizer2;
        Intrinsics.checkNotNullParameter(equalizerFragment, "this$0");
        if (z) {
            VLCOptions vLCOptions = VLCOptions.INSTANCE;
            Context appContext = AppContextProvider.INSTANCE.getAppContext();
            MediaPlayer.Equalizer equalizer3 = equalizerFragment.equalizer;
            EqualizerBinding equalizerBinding = null;
            if (equalizer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("equalizer");
                equalizer2 = null;
            } else {
                equalizer2 = equalizer3;
            }
            String str = equalizerFragment.allSets.get(i);
            EqualizerBinding equalizerBinding2 = equalizerFragment.binding;
            if (equalizerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                equalizerBinding = equalizerBinding2;
            }
            vLCOptions.saveEqualizerInSettings(appContext, equalizer2, str, equalizerBinding.equalizerButton.isChecked(), false);
        }
    }

    /* access modifiers changed from: private */
    public static final boolean createSaveCustomSetDialog$lambda$4(EqualizerFragment equalizerFragment, AlertDialog alertDialog, EditText editText, String str, MediaPlayer.Equalizer equalizer2, boolean z, boolean z2, int i, View view, int i2, KeyEvent keyEvent) {
        EqualizerFragment equalizerFragment2 = equalizerFragment;
        Intrinsics.checkNotNullParameter(equalizerFragment, "this$0");
        AlertDialog alertDialog2 = alertDialog;
        Intrinsics.checkNotNullParameter(alertDialog, "$saveEqualizer");
        EditText editText2 = editText;
        Intrinsics.checkNotNullParameter(editText, "$input");
        String str2 = str;
        Intrinsics.checkNotNullParameter(str, "$oldName");
        if (i2 != 66 || keyEvent.getAction() != 1) {
            return false;
        }
        Context context = alertDialog.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Intrinsics.checkNotNull(equalizer2);
        equalizerFragment.save(context, editText, str, equalizer2, z, z2, i, alertDialog);
        return true;
    }

    /* access modifiers changed from: private */
    public static final void createSaveCustomSetDialog$lambda$6(EqualizerFragment equalizerFragment, AlertDialog alertDialog, EditText editText, String str, MediaPlayer.Equalizer equalizer2, boolean z, boolean z2, int i, DialogInterface dialogInterface) {
        DialogInterface dialogInterface2 = dialogInterface;
        Intrinsics.checkNotNullParameter(equalizerFragment, "this$0");
        Intrinsics.checkNotNullParameter(alertDialog, "$saveEqualizer");
        Intrinsics.checkNotNullParameter(editText, "$input");
        Intrinsics.checkNotNullParameter(str, "$oldName");
        Intrinsics.checkNotNull(dialogInterface2, "null cannot be cast to non-null type androidx.appcompat.app.AlertDialog");
        ((AlertDialog) dialogInterface2).getButton(-1).setOnClickListener(new EqualizerFragment$$ExternalSyntheticLambda4(equalizerFragment, alertDialog, editText, str, equalizer2, z, z2, i));
    }

    /* access modifiers changed from: private */
    public static final void createSaveCustomSetDialog$lambda$6$lambda$5(EqualizerFragment equalizerFragment, AlertDialog alertDialog, EditText editText, String str, MediaPlayer.Equalizer equalizer2, boolean z, boolean z2, int i, View view) {
        Intrinsics.checkNotNullParameter(equalizerFragment, "this$0");
        Intrinsics.checkNotNullParameter(alertDialog, "$saveEqualizer");
        Intrinsics.checkNotNullParameter(editText, "$input");
        Intrinsics.checkNotNullParameter(str, "$oldName");
        Context context = alertDialog.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
        Intrinsics.checkNotNull(equalizer2);
        equalizerFragment.save(context, editText, str, equalizer2, z, z2, i, alertDialog);
    }

    /* JADX WARNING: type inference failed for: r8v3 */
    /* JADX WARNING: type inference failed for: r8v4, types: [android.widget.ArrayAdapter] */
    /* JADX WARNING: type inference failed for: r8v7 */
    /* JADX WARNING: type inference failed for: r8v10, types: [org.videolan.vlc.databinding.EqualizerBinding] */
    /* JADX WARNING: type inference failed for: r8v13 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void save(android.content.Context r7, android.widget.EditText r8, java.lang.String r9, org.videolan.libvlc.MediaPlayer.Equalizer r10, boolean r11, boolean r12, int r13, androidx.appcompat.app.AlertDialog r14) {
        /*
            r6 = this;
            java.lang.CharSequence r0 = r8.getError()
            if (r0 != 0) goto L_0x0072
            android.text.Editable r8 = r8.getText()
            java.lang.String r3 = r8.toString()
            org.videolan.resources.VLCOptions r8 = org.videolan.resources.VLCOptions.INSTANCE
            r8.saveCustomSet(r7, r10, r3)
            r8 = 0
            if (r11 == 0) goto L_0x0033
            org.videolan.vlc.databinding.EqualizerBinding r9 = r6.binding
            if (r9 != 0) goto L_0x0020
            java.lang.String r9 = "binding"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            goto L_0x0021
        L_0x0020:
            r8 = r9
        L_0x0021:
            androidx.appcompat.widget.SwitchCompat r8 = r8.equalizerButton
            boolean r8 = r8.isChecked()
            if (r8 == 0) goto L_0x006f
            org.videolan.resources.VLCOptions r0 = org.videolan.resources.VLCOptions.INSTANCE
            r4 = 1
            r5 = 1
            r1 = r7
            r2 = r10
            r0.saveEqualizerInSettings(r1, r2, r3, r4, r5)
            goto L_0x006f
        L_0x0033:
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r9)
            r9 = 1
            if (r7 == 0) goto L_0x0048
            if (r12 == 0) goto L_0x006f
            org.videolan.vlc.gui.audio.EqualizerFragment$EqualizerState r7 = r6.state
            java.util.List<java.lang.String> r8 = r6.allSets
            int r8 = r8.indexOf(r3)
            r7.update(r8, r9)
            goto L_0x006f
        L_0x0048:
            java.util.List<java.lang.String> r7 = r6.allSets
            r7.add(r13, r3)
            int r7 = r6.customCount
            int r7 = r7 + r9
            r6.customCount = r7
            if (r12 == 0) goto L_0x006f
            android.widget.ArrayAdapter<java.lang.String> r7 = r6.adapter
            if (r7 != 0) goto L_0x005e
            java.lang.String r7 = "adapter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            goto L_0x005f
        L_0x005e:
            r8 = r7
        L_0x005f:
            r8.notifyDataSetChanged()
            org.videolan.vlc.gui.audio.EqualizerFragment$EqualizerState r7 = r6.state
            java.util.List<java.lang.String> r8 = r6.allSets
            int r8 = r8.indexOf(r3)
            r7.update(r8, r9)
            r6.updateAlreadyHandled = r9
        L_0x006f:
            r14.dismiss()
        L_0x0072:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.EqualizerFragment.save(android.content.Context, android.widget.EditText, java.lang.String, org.videolan.libvlc.MediaPlayer$Equalizer, boolean, boolean, int, androidx.appcompat.app.AlertDialog):void");
    }

    /* access modifiers changed from: private */
    public final void createDeleteCustomSetSnacker() {
        EqualizerBinding equalizerBinding = this.binding;
        EqualizerBinding equalizerBinding2 = null;
        if (equalizerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            equalizerBinding = null;
        }
        int selectedItemPosition = equalizerBinding.equalizerPresets.getSelectedItemPosition();
        String str = this.allSets.get(selectedItemPosition);
        if (getEqualizerType(selectedItemPosition) == 1) {
            VLCOptions vLCOptions = VLCOptions.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            MediaPlayer.Equalizer customSet = vLCOptions.getCustomSet(requireActivity, str);
            if (customSet != null) {
                VLCOptions vLCOptions2 = VLCOptions.INSTANCE;
                FragmentActivity requireActivity2 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity2, "requireActivity(...)");
                vLCOptions2.deleteCustomSet(requireActivity2, str);
                this.allSets.remove(str);
                this.customCount--;
                this.state.update(0, true);
                EqualizerBinding equalizerBinding3 = this.binding;
                if (equalizerBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    equalizerBinding2 = equalizerBinding3;
                }
                equalizerBinding2.equalizerPresets.setSelection(0);
                String string = getString(R.string.custom_set_deleted_message, str);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                UiTools uiTools = UiTools.INSTANCE;
                FragmentActivity requireActivity3 = requireActivity();
                Intrinsics.checkNotNullExpressionValue(requireActivity3, "requireActivity(...)");
                UiTools.snackerWithCancel$default(uiTools, requireActivity3, string, false, EqualizerFragment$createDeleteCustomSetSnacker$1.INSTANCE, new EqualizerFragment$createDeleteCustomSetSnacker$2(this, customSet, str, selectedItemPosition), 4, (Object) null);
            }
        }
    }

    /* access modifiers changed from: private */
    public final void revertCustomSetChanges() {
        String str;
        EqualizerBinding equalizerBinding = this.binding;
        EqualizerBinding equalizerBinding2 = null;
        if (equalizerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            equalizerBinding = null;
        }
        int selectedItemPosition = equalizerBinding.equalizerPresets.getSelectedItemPosition();
        MediaPlayer.Equalizer create = MediaPlayer.Equalizer.create();
        MediaPlayer.Equalizer equalizer2 = this.equalizer;
        if (equalizer2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("equalizer");
            equalizer2 = null;
        }
        create.setPreAmp(equalizer2.getPreAmp());
        int bandCount2 = MediaPlayer.Equalizer.getBandCount();
        for (int i = 0; i < bandCount2; i++) {
            MediaPlayer.Equalizer equalizer3 = this.equalizer;
            if (equalizer3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("equalizer");
                equalizer3 = null;
            }
            create.setAmp(i, equalizer3.getAmp(i));
        }
        this.state.update(this.revertPos, true);
        int i2 = this.revertPos;
        if (selectedItemPosition == i2) {
            updateEqualizer(i2);
        } else {
            EqualizerBinding equalizerBinding3 = this.binding;
            if (equalizerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                equalizerBinding2 = equalizerBinding3;
            }
            equalizerBinding2.equalizerPresets.setSelection(this.revertPos);
        }
        if (getEqualizerType(selectedItemPosition) == 1) {
            str = getString(R.string.custom_set_restored);
        } else {
            str = getString(R.string.unsaved_set_deleted_message);
        }
        String str2 = str;
        Intrinsics.checkNotNull(str2);
        UiTools uiTools = UiTools.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        UiTools.snackerWithCancel$default(uiTools, requireActivity, str2, false, EqualizerFragment$revertCustomSetChanges$1.INSTANCE, new EqualizerFragment$revertCustomSetChanges$2(this, selectedItemPosition, create), 4, (Object) null);
    }

    /* access modifiers changed from: private */
    public final Job updateEqualizer(int i) {
        return BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, CoroutineStart.UNDISPATCHED, new EqualizerFragment$updateEqualizer$1(this, i, (Continuation<? super EqualizerFragment$updateEqualizer$1>) null), 1, (Object) null);
    }

    /* access modifiers changed from: private */
    public final int getEqualizerType(int i) {
        if (i < 0) {
            return -1;
        }
        int i2 = this.presetCount;
        if (i < i2) {
            return 0;
        }
        return i < i2 + this.customCount ? 1 : 2;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0010R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR\u001a\u0010\f\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR\u001a\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001a"}, d2 = {"Lorg/videolan/vlc/gui/audio/EqualizerFragment$EqualizerState;", "", "(Lorg/videolan/vlc/gui/audio/EqualizerFragment;)V", "deleteButtonVisibility", "Landroidx/databinding/ObservableBoolean;", "getDeleteButtonVisibility", "()Landroidx/databinding/ObservableBoolean;", "setDeleteButtonVisibility", "(Landroidx/databinding/ObservableBoolean;)V", "revertButtonVisibility", "getRevertButtonVisibility", "setRevertButtonVisibility", "saveButtonVisibility", "getSaveButtonVisibility", "setSaveButtonVisibility", "saved", "", "getSaved$vlc_android_release", "()Z", "setSaved$vlc_android_release", "(Z)V", "update", "", "newPos", "", "newSaved", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: EqualizerFragment.kt */
    public final class EqualizerState {
        private ObservableBoolean deleteButtonVisibility = new ObservableBoolean(false);
        private ObservableBoolean revertButtonVisibility = new ObservableBoolean(false);
        private ObservableBoolean saveButtonVisibility = new ObservableBoolean(false);
        private boolean saved = true;

        public EqualizerState() {
        }

        public final boolean getSaved$vlc_android_release() {
            return this.saved;
        }

        public final void setSaved$vlc_android_release(boolean z) {
            this.saved = z;
        }

        public final ObservableBoolean getSaveButtonVisibility() {
            return this.saveButtonVisibility;
        }

        public final void setSaveButtonVisibility(ObservableBoolean observableBoolean) {
            Intrinsics.checkNotNullParameter(observableBoolean, "<set-?>");
            this.saveButtonVisibility = observableBoolean;
        }

        public final ObservableBoolean getRevertButtonVisibility() {
            return this.revertButtonVisibility;
        }

        public final void setRevertButtonVisibility(ObservableBoolean observableBoolean) {
            Intrinsics.checkNotNullParameter(observableBoolean, "<set-?>");
            this.revertButtonVisibility = observableBoolean;
        }

        public final ObservableBoolean getDeleteButtonVisibility() {
            return this.deleteButtonVisibility;
        }

        public final void setDeleteButtonVisibility(ObservableBoolean observableBoolean) {
            Intrinsics.checkNotNullParameter(observableBoolean, "<set-?>");
            this.deleteButtonVisibility = observableBoolean;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:3:0x001b, code lost:
            if (org.videolan.vlc.gui.audio.EqualizerFragment.access$getEqualizerType(r2.this$0, r3) == 1) goto L_0x001f;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void update(int r3, boolean r4) {
            /*
                r2 = this;
                r2.saved = r4
                androidx.databinding.ObservableBoolean r0 = r2.saveButtonVisibility
                r1 = r4 ^ 1
                r0.set(r1)
                androidx.databinding.ObservableBoolean r0 = r2.revertButtonVisibility
                r1 = r4 ^ 1
                r0.set(r1)
                androidx.databinding.ObservableBoolean r0 = r2.deleteButtonVisibility
                if (r4 == 0) goto L_0x001e
                org.videolan.vlc.gui.audio.EqualizerFragment r4 = org.videolan.vlc.gui.audio.EqualizerFragment.this
                int r3 = r4.getEqualizerType(r3)
                r4 = 1
                if (r3 != r4) goto L_0x001e
                goto L_0x001f
            L_0x001e:
                r4 = 0
            L_0x001f:
                r0.set(r4)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.audio.EqualizerFragment.EqualizerState.update(int, boolean):void");
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\n8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lorg/videolan/vlc/gui/audio/EqualizerFragment$Companion;", "", "()V", "TAG", "", "TYPE_CUSTOM", "", "TYPE_NEW", "TYPE_PRESET", "equalizerPresets", "", "getEqualizerPresets", "()[Ljava/lang/String;", "newInstance", "Lorg/videolan/vlc/gui/audio/EqualizerFragment;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: EqualizerFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final String[] getEqualizerPresets() {
            int presetCount = MediaPlayer.Equalizer.getPresetCount();
            if (presetCount <= 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList(presetCount);
            for (int i = 0; i < presetCount; i++) {
                arrayList.add(MediaPlayer.Equalizer.getPresetName(i));
            }
            return (String[]) arrayList.toArray(new String[0]);
        }

        public final EqualizerFragment newInstance() {
            return new EqualizerFragment();
        }
    }

    public void onValueChange(Slider slider, float f, boolean z) {
        Intrinsics.checkNotNullParameter(slider, "slider");
        if (z) {
            MediaPlayer.Equalizer equalizer2 = this.equalizer;
            MediaPlayer.Equalizer equalizer3 = null;
            if (equalizer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("equalizer");
                equalizer2 = null;
            }
            EqualizerBinding equalizerBinding = this.binding;
            if (equalizerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                equalizerBinding = null;
            }
            equalizer2.setPreAmp(equalizerBinding.equalizerPreamp.getValue());
            EqualizerBinding equalizerBinding2 = this.binding;
            if (equalizerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                equalizerBinding2 = null;
            }
            if (!equalizerBinding2.equalizerButton.isChecked()) {
                EqualizerBinding equalizerBinding3 = this.binding;
                if (equalizerBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    equalizerBinding3 = null;
                }
                equalizerBinding3.equalizerButton.setChecked(true);
            }
            EqualizerBinding equalizerBinding4 = this.binding;
            if (equalizerBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                equalizerBinding4 = null;
            }
            int selectedItemPosition = equalizerBinding4.equalizerPresets.getSelectedItemPosition();
            if (getEqualizerType(selectedItemPosition) == 0) {
                this.revertPos = selectedItemPosition;
                int i = this.presetCount;
                int i2 = this.customCount;
                this.savePos = i + i2;
                this.state.update(i + i2, false);
                this.updateAlreadyHandled = true;
                EqualizerBinding equalizerBinding5 = this.binding;
                if (equalizerBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    equalizerBinding5 = null;
                }
                equalizerBinding5.equalizerPresets.setSelection(this.presetCount + this.customCount);
            } else if (getEqualizerType(selectedItemPosition) == 1) {
                this.revertPos = selectedItemPosition;
                this.savePos = selectedItemPosition;
                this.state.update(selectedItemPosition, false);
            }
            EqualizerBinding equalizerBinding6 = this.binding;
            if (equalizerBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                equalizerBinding6 = null;
            }
            if (equalizerBinding6.equalizerButton.isChecked()) {
                LiveEvent<MediaPlayer.Equalizer> equalizer4 = PlaybackService.Companion.getEqualizer();
                MediaPlayer.Equalizer equalizer5 = this.equalizer;
                if (equalizer5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("equalizer");
                } else {
                    equalizer3 = equalizer5;
                }
                equalizer4.setValue(equalizer3);
            }
        }
    }
}
