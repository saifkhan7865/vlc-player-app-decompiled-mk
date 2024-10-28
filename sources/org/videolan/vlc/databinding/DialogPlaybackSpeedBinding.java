package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class DialogPlaybackSpeedBinding extends ViewDataBinding {
    public final TextView buttonSpeed08;
    public final TextView buttonSpeed1;
    public final TextView buttonSpeed125;
    public final TextView buttonSpeed15;
    public final TextView buttonSpeed2;
    public final AppCompatImageButton buttonSpeedMinus;
    public final AppCompatImageButton buttonSpeedPlus;
    public final SeekBar playbackSpeedSeek;
    public final TextView playbackSpeedValue;
    public final TextView streamWarning;
    public final TextView textView12;
    public final TextView textView13;
    public final TextView textView14;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DialogPlaybackSpeedBinding(Object obj, View view, int i, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, AppCompatImageButton appCompatImageButton, AppCompatImageButton appCompatImageButton2, SeekBar seekBar, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10) {
        super(obj, view, i);
        this.buttonSpeed08 = textView;
        this.buttonSpeed1 = textView2;
        this.buttonSpeed125 = textView3;
        this.buttonSpeed15 = textView4;
        this.buttonSpeed2 = textView5;
        this.buttonSpeedMinus = appCompatImageButton;
        this.buttonSpeedPlus = appCompatImageButton2;
        this.playbackSpeedSeek = seekBar;
        this.playbackSpeedValue = textView6;
        this.streamWarning = textView7;
        this.textView12 = textView8;
        this.textView13 = textView9;
        this.textView14 = textView10;
    }

    public static DialogPlaybackSpeedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPlaybackSpeedBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogPlaybackSpeedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_playback_speed, viewGroup, z, obj);
    }

    public static DialogPlaybackSpeedBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPlaybackSpeedBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogPlaybackSpeedBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_playback_speed, (ViewGroup) null, false, obj);
    }

    public static DialogPlaybackSpeedBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogPlaybackSpeedBinding bind(View view, Object obj) {
        return (DialogPlaybackSpeedBinding) bind(obj, view, R.layout.dialog_playback_speed);
    }
}
