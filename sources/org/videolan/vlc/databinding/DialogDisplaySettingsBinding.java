package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.constraintlayout.widget.Guideline;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import org.videolan.vlc.R;

public abstract class DialogDisplaySettingsBinding extends ViewDataBinding {
    public final ImageView allArtistsImage;
    public final ImageView allFilesImage;
    public final ImageView displayInListImage;
    public final TextView displayInListText;
    public final View displayModeGroup;
    public final Guideline guideline16;
    public final ImageView hiddenFilesImage;
    public final CheckBox onlyFavsCheckbox;
    public final View onlyFavsGroup;
    public final ImageView onlyFavsImage;
    public final TextView onlyFavsText;
    public final CheckBox showAllArtistCheckbox;
    public final View showAllArtistGroup;
    public final TextView showAllArtistText;
    public final CheckBox showAllFilesCheckbox;
    public final View showAllFilesGroup;
    public final TextView showAllFilesText;
    public final CheckBox showHiddenFilesCheckbox;
    public final View showHiddenFilesGroup;
    public final TextView showHiddenFilesText;
    public final LinearLayout sortsContainer;
    public final TextView sortsTitle;
    public final TextView title;
    public final ImageView videoGroupImage;
    public final Spinner videoGroupSpinner;
    public final TextView videoGroupText;
    public final View videoGroupsGroup;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected DialogDisplaySettingsBinding(Object obj, View view, int i, ImageView imageView, ImageView imageView2, ImageView imageView3, TextView textView, View view2, Guideline guideline, ImageView imageView4, CheckBox checkBox, View view3, ImageView imageView5, TextView textView2, CheckBox checkBox2, View view4, TextView textView3, CheckBox checkBox3, View view5, TextView textView4, CheckBox checkBox4, View view6, TextView textView5, LinearLayout linearLayout, TextView textView6, TextView textView7, ImageView imageView6, Spinner spinner, TextView textView8, View view7) {
        super(obj, view, i);
        this.allArtistsImage = imageView;
        this.allFilesImage = imageView2;
        this.displayInListImage = imageView3;
        this.displayInListText = textView;
        this.displayModeGroup = view2;
        this.guideline16 = guideline;
        this.hiddenFilesImage = imageView4;
        this.onlyFavsCheckbox = checkBox;
        this.onlyFavsGroup = view3;
        this.onlyFavsImage = imageView5;
        this.onlyFavsText = textView2;
        this.showAllArtistCheckbox = checkBox2;
        this.showAllArtistGroup = view4;
        this.showAllArtistText = textView3;
        this.showAllFilesCheckbox = checkBox3;
        this.showAllFilesGroup = view5;
        this.showAllFilesText = textView4;
        this.showHiddenFilesCheckbox = checkBox4;
        this.showHiddenFilesGroup = view6;
        this.showHiddenFilesText = textView5;
        this.sortsContainer = linearLayout;
        this.sortsTitle = textView6;
        this.title = textView7;
        this.videoGroupImage = imageView6;
        this.videoGroupSpinner = spinner;
        this.videoGroupText = textView8;
        this.videoGroupsGroup = view7;
    }

    public static DialogDisplaySettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDisplaySettingsBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (DialogDisplaySettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_display_settings, viewGroup, z, obj);
    }

    public static DialogDisplaySettingsBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDisplaySettingsBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (DialogDisplaySettingsBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.dialog_display_settings, (ViewGroup) null, false, obj);
    }

    public static DialogDisplaySettingsBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static DialogDisplaySettingsBinding bind(View view, Object obj) {
        return (DialogDisplaySettingsBinding) bind(obj, view, R.layout.dialog_display_settings);
    }
}
