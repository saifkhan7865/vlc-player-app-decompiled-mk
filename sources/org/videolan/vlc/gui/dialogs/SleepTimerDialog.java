package org.videolan.vlc.gui.dialogs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import androidx.fragment.app.FragmentActivity;
import java.util.Calendar;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.R;
import org.videolan.vlc.viewmodels.PlaylistModel;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J&\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u00132\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\b\u0010\u001d\u001a\u00020\u0004H\u0016J\b\u0010\u001e\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SleepTimerDialog;", "Lorg/videolan/vlc/gui/dialogs/PickTimeFragment;", "()V", "defaultSleepTimer", "", "playlistModel", "Lorg/videolan/vlc/viewmodels/PlaylistModel;", "getPlaylistModel", "()Lorg/videolan/vlc/viewmodels/PlaylistModel;", "playlistModel$delegate", "Lkotlin/Lazy;", "settings", "Landroid/content/SharedPreferences;", "executeAction", "", "getTitle", "", "onClick", "v", "Landroid/view/View;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "showDeleteCurrent", "showTimeOnly", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SleepTimerDialog.kt */
public final class SleepTimerDialog extends PickTimeFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private boolean defaultSleepTimer;
    private final Lazy playlistModel$delegate = LazyKt.lazy(new SleepTimerDialog$playlistModel$2(this));
    private SharedPreferences settings;

    public boolean showDeleteCurrent() {
        return true;
    }

    public boolean showTimeOnly() {
        return false;
    }

    private final PlaylistModel getPlaylistModel() {
        return (PlaylistModel) this.playlistModel$delegate.getValue();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        setMaxTimeSize(4);
        Settings settings2 = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        this.settings = (SharedPreferences) settings2.getInstance(requireActivity);
        return onCreateView;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        Bundle arguments = getArguments();
        boolean z = false;
        this.defaultSleepTimer = arguments != null ? arguments.getBoolean("for_default", false) : false;
        CheckBox checkBox = getBinding().timPicWaitCheckbox;
        SharedPreferences sharedPreferences = this.settings;
        SharedPreferences sharedPreferences2 = null;
        if (sharedPreferences == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences = null;
        }
        checkBox.setChecked(sharedPreferences.getBoolean(this.defaultSleepTimer ? SettingsKt.SLEEP_TIMER_DEFAULT_WAIT : SettingsKt.SLEEP_TIMER_WAIT, false));
        CheckBox checkBox2 = getBinding().timPicResetCheckbox;
        SharedPreferences sharedPreferences3 = this.settings;
        if (sharedPreferences3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("settings");
            sharedPreferences3 = null;
        }
        checkBox2.setChecked(sharedPreferences3.getBoolean(this.defaultSleepTimer ? SettingsKt.SLEEP_TIMER_DEFAULT_RESET_INTERACTION : SettingsKt.SLEEP_TIMER_RESET_INTERACTION, false));
        if (this.defaultSleepTimer) {
            SharedPreferences sharedPreferences4 = this.settings;
            if (sharedPreferences4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settings");
            } else {
                sharedPreferences2 = sharedPreferences4;
            }
            long j = sharedPreferences2.getLong(SettingsKt.SLEEP_TIMER_DEFAULT_INTERVAL, -1);
            if (j > 0) {
                updateValue(String.valueOf(j / 3600000) + String.valueOf((j % 3600000) / 60000));
            }
        }
        CheckBox checkBox3 = getBinding().timPicWaitCheckbox;
        PlaybackService service = getPlaylistModel().getService();
        checkBox3.setChecked(service != null ? service.getWaitForMediaEnd() : false);
        CheckBox checkBox4 = getBinding().timPicResetCheckbox;
        PlaybackService service2 = getPlaylistModel().getService();
        if (service2 != null) {
            z = service2.getResetOnInteraction();
        }
        checkBox4.setChecked(z);
    }

    /* access modifiers changed from: protected */
    public void executeAction() {
        long j = 0;
        long parseLong = !Intrinsics.areEqual((Object) getHours(), (Object) "") ? Long.parseLong(getHours()) * PickTimeFragment.HOURS_IN_MICROS : 0;
        if (!Intrinsics.areEqual((Object) getMinutes(), (Object) "")) {
            j = PickTimeFragment.MINUTES_IN_MICROS * Long.parseLong(getMinutes());
        }
        long j2 = (parseLong + j) / 1000;
        Settings settings2 = Settings.INSTANCE;
        FragmentActivity requireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
        SharedPreferences sharedPreferences = (SharedPreferences) settings2.getInstance(requireActivity);
        if (this.defaultSleepTimer) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putLong(SettingsKt.SLEEP_TIMER_DEFAULT_INTERVAL, j2);
            edit.putBoolean(SettingsKt.SLEEP_TIMER_DEFAULT_WAIT, getBinding().timPicWaitCheckbox.isChecked());
            edit.putBoolean(SettingsKt.SLEEP_TIMER_DEFAULT_RESET_INTERACTION, getBinding().timPicResetCheckbox.isChecked());
            edit.apply();
            dismiss();
            return;
        }
        PlaybackService service = getPlaylistModel().getService();
        if (service != null) {
            service.setWaitForMediaEnd(getBinding().timPicWaitCheckbox.isChecked());
        }
        PlaybackService service2 = getPlaylistModel().getService();
        if (service2 != null) {
            service2.setResetOnInteraction(getBinding().timPicResetCheckbox.isChecked());
        }
        SettingsKt.putSingle(sharedPreferences, SettingsKt.SLEEP_TIMER_RESET_INTERACTION, Boolean.valueOf(getBinding().timPicResetCheckbox.isChecked()));
        SettingsKt.putSingle(sharedPreferences, SettingsKt.SLEEP_TIMER_WAIT, Boolean.valueOf(getBinding().timPicWaitCheckbox.isChecked()));
        PlaybackService service3 = getPlaylistModel().getService();
        if (service3 != null) {
            service3.setSleepTimerInterval(j2);
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(instance.getTimeInMillis() + j2);
        instance.set(13, 0);
        PlaybackService service4 = getPlaylistModel().getService();
        if (service4 != null) {
            service4.setSleepTimer(instance);
        }
        dismiss();
    }

    public void onClick(View view) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (view.getId() == R.id.tim_pic_delete_current) {
            SharedPreferences sharedPreferences = null;
            if (this.defaultSleepTimer) {
                SharedPreferences sharedPreferences2 = this.settings;
                if (sharedPreferences2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("settings");
                } else {
                    sharedPreferences = sharedPreferences2;
                }
                SettingsKt.putSingle(sharedPreferences, SettingsKt.SLEEP_TIMER_DEFAULT_INTERVAL, -1L);
                dismiss();
                return;
            }
            PlaybackService service = getPlaylistModel().getService();
            if (service != null) {
                service.setWaitForMediaEnd(false);
            }
            PlaybackService service2 = getPlaylistModel().getService();
            if (service2 != null) {
                service2.setSleepTimer((Calendar) null);
            }
            SharedPreferences sharedPreferences3 = this.settings;
            if (sharedPreferences3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("settings");
            } else {
                sharedPreferences = sharedPreferences3;
            }
            SettingsKt.putSingle(sharedPreferences, SettingsKt.SLEEP_TIMER_WAIT, false);
            dismiss();
            return;
        }
        super.onClick(view);
    }

    public int getTitle() {
        return R.string.sleep_in;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SleepTimerDialog$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/SleepTimerDialog;", "forDefault", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SleepTimerDialog.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public static /* synthetic */ SleepTimerDialog newInstance$default(Companion companion, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            return companion.newInstance(z);
        }

        public final SleepTimerDialog newInstance(boolean z) {
            SleepTimerDialog sleepTimerDialog = new SleepTimerDialog();
            Bundle bundle = new Bundle(1);
            bundle.putBoolean("for_default", z);
            sleepTimerDialog.setArguments(bundle);
            return sleepTimerDialog;
        }
    }
}
