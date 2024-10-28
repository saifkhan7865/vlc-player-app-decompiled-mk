package org.videolan.vlc.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.dialogs.SubDownloadDialogState;
import org.videolan.vlc.gui.view.LanguageSelector;
import org.videolan.vlc.viewmodels.SubtitlesModel;

public abstract class SubtitleDownloaderDialogBinding extends ViewDataBinding {
    public final Button cancelButton;
    public final ConstraintLayout constraintLayout;
    public final TextInputEditText episode;
    public final TextInputLayout episodeContainer;
    public final LanguageSelector languageListSpinner;
    @Bindable
    protected Boolean mInError;
    @Bindable
    protected SubDownloadDialogState mState;
    @Bindable
    protected SubtitlesModel mViewmodel;
    public final TextView message;
    public final TextView movieName;
    public final TextInputEditText name;
    public final TextInputLayout nameContainer;
    public final TextView resultDescription;
    public final Button retryButton;
    public final NestedScrollView scrollView;
    public final Button searchButton;
    public final TextInputEditText season;
    public final TextInputLayout seasonContainer;
    public final ImageView subDownloadHistory;
    public final ProgressBar subDownloadLoading;
    public final ImageView subDownloadNext;
    public final ImageView subDownloadSearch;
    public final RecyclerView subsDownloadList;
    public final RecyclerView subsHistoryList;

    public abstract void setInError(Boolean bool);

    public abstract void setState(SubDownloadDialogState subDownloadDialogState);

    public abstract void setViewmodel(SubtitlesModel subtitlesModel);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected SubtitleDownloaderDialogBinding(Object obj, View view, int i, Button button, ConstraintLayout constraintLayout2, TextInputEditText textInputEditText, TextInputLayout textInputLayout, LanguageSelector languageSelector, TextView textView, TextView textView2, TextInputEditText textInputEditText2, TextInputLayout textInputLayout2, TextView textView3, Button button2, NestedScrollView nestedScrollView, Button button3, TextInputEditText textInputEditText3, TextInputLayout textInputLayout3, ImageView imageView, ProgressBar progressBar, ImageView imageView2, ImageView imageView3, RecyclerView recyclerView, RecyclerView recyclerView2) {
        super(obj, view, i);
        this.cancelButton = button;
        this.constraintLayout = constraintLayout2;
        this.episode = textInputEditText;
        this.episodeContainer = textInputLayout;
        this.languageListSpinner = languageSelector;
        this.message = textView;
        this.movieName = textView2;
        this.name = textInputEditText2;
        this.nameContainer = textInputLayout2;
        this.resultDescription = textView3;
        this.retryButton = button2;
        this.scrollView = nestedScrollView;
        this.searchButton = button3;
        this.season = textInputEditText3;
        this.seasonContainer = textInputLayout3;
        this.subDownloadHistory = imageView;
        this.subDownloadLoading = progressBar;
        this.subDownloadNext = imageView2;
        this.subDownloadSearch = imageView3;
        this.subsDownloadList = recyclerView;
        this.subsHistoryList = recyclerView2;
    }

    public SubtitlesModel getViewmodel() {
        return this.mViewmodel;
    }

    public SubDownloadDialogState getState() {
        return this.mState;
    }

    public Boolean getInError() {
        return this.mInError;
    }

    public static SubtitleDownloaderDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        return inflate(layoutInflater, viewGroup, z, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SubtitleDownloaderDialogBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z, Object obj) {
        return (SubtitleDownloaderDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.subtitle_downloader_dialog, viewGroup, z, obj);
    }

    public static SubtitleDownloaderDialogBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SubtitleDownloaderDialogBinding inflate(LayoutInflater layoutInflater, Object obj) {
        return (SubtitleDownloaderDialogBinding) ViewDataBinding.inflateInternal(layoutInflater, R.layout.subtitle_downloader_dialog, (ViewGroup) null, false, obj);
    }

    public static SubtitleDownloaderDialogBinding bind(View view) {
        return bind(view, DataBindingUtil.getDefaultComponent());
    }

    @Deprecated
    public static SubtitleDownloaderDialogBinding bind(View view, Object obj) {
        return (SubtitleDownloaderDialogBinding) bind(obj, view, R.layout.subtitle_downloader_dialog);
    }
}
