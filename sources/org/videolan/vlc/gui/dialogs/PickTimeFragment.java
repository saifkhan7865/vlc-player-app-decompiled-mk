package org.videolan.vlc.gui.dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import io.ktor.util.date.GMTDateParser;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import okhttp3.internal.cache.DiskLruCache;
import org.videolan.resources.Constants;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.DialogTimePickerBinding;
import org.videolan.vlc.gui.helpers.TalkbackUtil;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u0000 F2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001FB\u0005¢\u0006\u0002\u0010\u0004J\b\u0010&\u001a\u00020'H\u0016J\b\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020)H$J\b\u0010+\u001a\u00020\u0013H\u0016J\u0010\u0010,\u001a\u00020\f2\u0006\u0010-\u001a\u00020\fH\u0002J\u0006\u0010.\u001a\u00020/J\b\u00100\u001a\u00020\u0013H&J\b\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u00020'H\u0016J\u0010\u00104\u001a\u00020)2\u0006\u00105\u001a\u000202H\u0016J&\u00106\u001a\u0004\u0018\u0001022\u0006\u00107\u001a\u0002082\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\u0018\u0010=\u001a\u00020)2\u0006\u00105\u001a\u0002022\u0006\u0010>\u001a\u00020'H\u0016J\u001a\u0010?\u001a\u00020)2\u0006\u0010@\u001a\u0002022\b\u0010;\u001a\u0004\u0018\u00010<H\u0016J\u0010\u0010A\u001a\u00020\f2\u0006\u0010-\u001a\u00020\fH\u0002J\b\u0010B\u001a\u00020'H\u0016J\b\u0010C\u001a\u00020'H&J\u000e\u0010D\u001a\u00020)2\u0006\u0010E\u001a\u00020\fR\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0013X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u000f\"\u0004\b\u001b\u0010\u0011R\u000e\u0010\u001c\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u001eX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001a\u0010#\u001a\u00020\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u000f\"\u0004\b%\u0010\u0011¨\u0006G"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/PickTimeFragment;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "Landroid/view/View$OnClickListener;", "Landroid/view/View$OnFocusChangeListener;", "()V", "binding", "Lorg/videolan/vlc/databinding/DialogTimePickerBinding;", "getBinding", "()Lorg/videolan/vlc/databinding/DialogTimePickerBinding;", "setBinding", "(Lorg/videolan/vlc/databinding/DialogTimePickerBinding;)V", "formatTime", "", "hours", "getHours", "()Ljava/lang/String;", "setHours", "(Ljava/lang/String;)V", "mTextColor", "", "maxTimeSize", "getMaxTimeSize", "()I", "setMaxTimeSize", "(I)V", "minutes", "getMinutes", "setMinutes", "pickedRawTime", "playbackService", "Lorg/videolan/vlc/PlaybackService;", "getPlaybackService", "()Lorg/videolan/vlc/PlaybackService;", "setPlaybackService", "(Lorg/videolan/vlc/PlaybackService;)V", "seconds", "getSeconds", "setSeconds", "allowRemote", "", "deleteLastNumber", "", "executeAction", "getDefaultState", "getLastNumbers", "rawTime", "getTimeInMillis", "", "getTitle", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "onClick", "v", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onFocusChange", "hasFocus", "onViewCreated", "view", "removeLastNumbers", "showDeleteCurrent", "showTimeOnly", "updateValue", "value", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PickTimeFragment.kt */
public abstract class PickTimeFragment extends VLCBottomSheetDialogFragment implements View.OnClickListener, View.OnFocusChangeListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final long HOURS_IN_MICROS = 3600000000L;
    public static final long MILLIS_IN_MICROS = 1000;
    public static final long MINUTES_IN_MICROS = 60000000;
    public static final long SECONDS_IN_MICROS = 1000000;
    public static final String TAG = "VLC/PickTimeFragment";
    public DialogTimePickerBinding binding;
    private String formatTime = "";
    private String hours = "";
    private int mTextColor;
    private int maxTimeSize = 6;
    private String minutes = "";
    private String pickedRawTime = "";
    public PlaybackService playbackService;
    private String seconds = "";

    public boolean allowRemote() {
        return true;
    }

    /* access modifiers changed from: protected */
    public abstract void executeAction();

    public int getDefaultState() {
        return 3;
    }

    public abstract int getTitle();

    public boolean needToManageOrientation() {
        return true;
    }

    public boolean showDeleteCurrent() {
        return false;
    }

    public abstract boolean showTimeOnly();

    public final DialogTimePickerBinding getBinding() {
        DialogTimePickerBinding dialogTimePickerBinding = this.binding;
        if (dialogTimePickerBinding != null) {
            return dialogTimePickerBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding(DialogTimePickerBinding dialogTimePickerBinding) {
        Intrinsics.checkNotNullParameter(dialogTimePickerBinding, "<set-?>");
        this.binding = dialogTimePickerBinding;
    }

    public final String getHours() {
        return this.hours;
    }

    public final void setHours(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hours = str;
    }

    public final String getMinutes() {
        return this.minutes;
    }

    public final void setMinutes(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.minutes = str;
    }

    public final String getSeconds() {
        return this.seconds;
    }

    public final void setSeconds(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.seconds = str;
    }

    public final int getMaxTimeSize() {
        return this.maxTimeSize;
    }

    public final void setMaxTimeSize(int i) {
        this.maxTimeSize = i;
    }

    public final PlaybackService getPlaybackService() {
        PlaybackService playbackService2 = this.playbackService;
        if (playbackService2 != null) {
            return playbackService2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("playbackService");
        return null;
    }

    public final void setPlaybackService(PlaybackService playbackService2) {
        Intrinsics.checkNotNullParameter(playbackService2, "<set-?>");
        this.playbackService = playbackService2;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        int i = 0;
        DialogTimePickerBinding inflate = DialogTimePickerBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        setBinding(inflate);
        getBinding().timPicTitle.setText(getTitle());
        TextView textView = getBinding().timPic0;
        Intrinsics.checkNotNullExpressionValue(textView, "timPic0");
        TextView textView2 = getBinding().timPic1;
        Intrinsics.checkNotNullExpressionValue(textView2, "timPic1");
        TextView textView3 = getBinding().timPic2;
        Intrinsics.checkNotNullExpressionValue(textView3, "timPic2");
        TextView textView4 = getBinding().timPic3;
        Intrinsics.checkNotNullExpressionValue(textView4, "timPic3");
        TextView textView5 = getBinding().timPic4;
        Intrinsics.checkNotNullExpressionValue(textView5, "timPic4");
        TextView textView6 = getBinding().timPic5;
        Intrinsics.checkNotNullExpressionValue(textView6, "timPic5");
        TextView textView7 = getBinding().timPic6;
        Intrinsics.checkNotNullExpressionValue(textView7, "timPic6");
        TextView textView8 = getBinding().timPic7;
        Intrinsics.checkNotNullExpressionValue(textView8, "timPic7");
        TextView textView9 = getBinding().timPic8;
        Intrinsics.checkNotNullExpressionValue(textView9, "timPic8");
        TextView textView10 = getBinding().timPic9;
        Intrinsics.checkNotNullExpressionValue(textView10, "timPic9");
        TextView textView11 = getBinding().timPic00;
        Intrinsics.checkNotNullExpressionValue(textView11, "timPic00");
        TextView textView12 = getBinding().timPic30;
        Intrinsics.checkNotNullExpressionValue(textView12, "timPic30");
        ImageButton imageButton = getBinding().timPicDelete;
        Intrinsics.checkNotNullExpressionValue(imageButton, "timPicDelete");
        Button button = getBinding().timPicOk;
        Intrinsics.checkNotNullExpressionValue(button, "timPicOk");
        View[] viewArr = {textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, imageButton, button};
        for (int i2 = 0; i2 < 14; i2++) {
            View view = viewArr[i2];
            view.setOnClickListener(this);
            view.setOnFocusChangeListener(this);
        }
        getBinding().timPicDeleteCurrent.setOnClickListener(this);
        Button button2 = getBinding().timPicDeleteCurrent;
        if (!showDeleteCurrent()) {
            i = 8;
        }
        button2.setVisibility(i);
        getBinding().timPicDeleteCurrent.setOnFocusChangeListener(this);
        this.mTextColor = getBinding().timPicTimetojump.getCurrentTextColor();
        if (showTimeOnly()) {
            KotlinExtensionsKt.setGone(getBinding().timPicWaitCheckbox);
            KotlinExtensionsKt.setGone(getBinding().timPicResetCheckbox);
        }
        return getBinding().getRoot();
    }

    public View initialFocusedView() {
        TextView textView = getBinding().timPic1;
        Intrinsics.checkNotNullExpressionValue(textView, "timPic1");
        return textView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        KextensionsKt.launchWhenStarted(FlowKt.onEach(FlowKt.filterNotNull(PlaybackService.Companion.getServiceFlow()), new PickTimeFragment$onViewCreated$1(this, (Continuation<? super PickTimeFragment$onViewCreated$1>) null)), LifecycleOwnerKt.getLifecycleScope(this));
        super.onViewCreated(view, bundle);
    }

    public void onFocusChange(View view, boolean z) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (view instanceof TextView) {
            ((TextView) view).setTextColor(z ? ContextCompat.getColor(requireActivity(), R.color.orange500) : this.mTextColor);
        }
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        int id = view.getId();
        if (id == R.id.tim_pic_1) {
            updateValue(DiskLruCache.VERSION_1);
        } else if (id == R.id.tim_pic_2) {
            updateValue("2");
        } else if (id == R.id.tim_pic_3) {
            updateValue("3");
        } else if (id == R.id.tim_pic_4) {
            updateValue("4");
        } else if (id == R.id.tim_pic_5) {
            updateValue("5");
        } else if (id == R.id.tim_pic_6) {
            updateValue(Constants.GROUP_VIDEOS_NAME);
        } else if (id == R.id.tim_pic_7) {
            updateValue("7");
        } else if (id == R.id.tim_pic_8) {
            updateValue("8");
        } else if (id == R.id.tim_pic_9) {
            updateValue("9");
        } else if (id == R.id.tim_pic_0) {
            updateValue(Constants.GROUP_VIDEOS_FOLDER);
        } else if (id == R.id.tim_pic_00) {
            updateValue("00");
        } else if (id == R.id.tim_pic_30) {
            updateValue("30");
        } else if (id == R.id.tim_pic_delete) {
            deleteLastNumber();
        } else if (id == R.id.tim_pic_ok) {
            executeAction();
        }
    }

    private final String getLastNumbers(String str) {
        if (str.length() == 0) {
            return "";
        }
        if (str.length() == 1) {
            return str;
        }
        String substring = str.substring(str.length() - 2);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }

    private final String removeLastNumbers(String str) {
        if (str.length() <= 1) {
            return "";
        }
        String substring = str.substring(0, str.length() - 2);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        return substring;
    }

    private final void deleteLastNumber() {
        String str = this.pickedRawTime;
        if (str != "") {
            String substring = str.substring(0, str.length() - 1);
            Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
            this.pickedRawTime = substring;
            updateValue("");
        }
    }

    public final void updateValue(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        if (this.pickedRawTime.length() < this.maxTimeSize) {
            String str2 = this.pickedRawTime + str;
            this.pickedRawTime = str2;
            this.formatTime = "";
            if (this.maxTimeSize > 4) {
                String lastNumbers = getLastNumbers(str2);
                this.seconds = lastNumbers;
                if (lastNumbers != "") {
                    this.formatTime = this.seconds + GMTDateParser.SECONDS;
                }
                str2 = removeLastNumbers(str2);
            } else {
                this.seconds = "";
            }
            String lastNumbers2 = getLastNumbers(str2);
            this.minutes = lastNumbers2;
            if (lastNumbers2 != "") {
                this.formatTime = this.minutes + "m " + this.formatTime;
            }
            String lastNumbers3 = getLastNumbers(removeLastNumbers(str2));
            this.hours = lastNumbers3;
            if (lastNumbers3 != "") {
                this.formatTime = this.hours + "h " + this.formatTime;
            }
            getBinding().timPicTimetojump.setText(this.formatTime);
            TextView textView = getBinding().timPicTimetojump;
            TalkbackUtil talkbackUtil = TalkbackUtil.INSTANCE;
            FragmentActivity requireActivity = requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            textView.announceForAccessibility(talkbackUtil.millisToString(requireActivity, getTimeInMillis()));
        }
    }

    public final long getTimeInMillis() {
        long j = 0;
        long parseLong = !Intrinsics.areEqual((Object) this.hours, (Object) "") ? Long.parseLong(this.hours) * HOURS_IN_MICROS : 0;
        long parseLong2 = !Intrinsics.areEqual((Object) this.minutes, (Object) "") ? Long.parseLong(this.minutes) * MINUTES_IN_MICROS : 0;
        if (!Intrinsics.areEqual((Object) this.seconds, (Object) "")) {
            j = SECONDS_IN_MICROS * Long.parseLong(this.seconds);
        }
        return ((parseLong + parseLong2) + j) / 1000;
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tXT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/PickTimeFragment$Companion;", "", "()V", "HOURS_IN_MICROS", "", "MILLIS_IN_MICROS", "MINUTES_IN_MICROS", "SECONDS_IN_MICROS", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PickTimeFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
