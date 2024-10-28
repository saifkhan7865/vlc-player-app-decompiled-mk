package org.videolan.moviepedia.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.moviepedia.R;
import org.videolan.moviepedia.databinding.MoviepediaActivityBinding;
import org.videolan.moviepedia.models.resolver.ResolverMedia;
import org.videolan.moviepedia.viewmodel.MediaScrapingModel;
import org.videolan.resources.Constants;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.vlc.gui.BaseActivity;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.helpers.UiToolsKt;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001+B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J(\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016H\u0016J\u0012\u0010\u0019\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0012\u0010\u001d\u001a\u00020\u00102\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J \u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00162\u0006\u0010$\u001a\u00020%H\u0016J(\u0010&\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010'\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0016J\u0010\u0010(\u001a\u00020\u00102\u0006\u0010)\u001a\u00020*H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX.¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lorg/videolan/moviepedia/ui/MediaScrapingActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "Landroid/text/TextWatcher;", "Landroid/widget/TextView$OnEditorActionListener;", "()V", "binding", "Lorg/videolan/moviepedia/databinding/MoviepediaActivityBinding;", "clickHandler", "Lorg/videolan/moviepedia/ui/MediaScrapingActivity$ClickHandler;", "media", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "mediaScrapingResultAdapter", "Lorg/videolan/moviepedia/ui/MediaScrapingResultAdapter;", "viewModel", "Lorg/videolan/moviepedia/viewmodel/MediaScrapingModel;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onEditorAction", "v", "Landroid/widget/TextView;", "actionId", "event", "Landroid/view/KeyEvent;", "onTextChanged", "before", "performSearh", "query", "", "ClickHandler", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaScrapingActivity.kt */
public class MediaScrapingActivity extends BaseActivity implements TextWatcher, TextView.OnEditorActionListener {
    private MoviepediaActivityBinding binding;
    private final ClickHandler clickHandler = new ClickHandler();
    private MediaWrapper media;
    /* access modifiers changed from: private */
    public MediaScrapingResultAdapter mediaScrapingResultAdapter;
    private MediaScrapingModel viewModel;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(charSequence, "s");
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(charSequence, "s");
    }

    public View getSnackAnchorView(boolean z) {
        return findViewById(16908290);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        Parcelable parcelable;
        super.onCreate(bundle);
        UiToolsKt.applyTheme(this);
        Intent intent = getIntent();
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.moviepedia_activity);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        MoviepediaActivityBinding moviepediaActivityBinding = (MoviepediaActivityBinding) contentView;
        this.binding = moviepediaActivityBinding;
        MediaWrapper mediaWrapper = null;
        if (moviepediaActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            moviepediaActivityBinding = null;
        }
        moviepediaActivityBinding.setHandler(this.clickHandler);
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
        MediaScrapingResultAdapter mediaScrapingResultAdapter2 = new MediaScrapingResultAdapter(layoutInflater);
        this.mediaScrapingResultAdapter = mediaScrapingResultAdapter2;
        mediaScrapingResultAdapter2.setClickHandler$moviepedia_release(this.clickHandler);
        MoviepediaActivityBinding moviepediaActivityBinding2 = this.binding;
        if (moviepediaActivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            moviepediaActivityBinding2 = null;
        }
        RecyclerView recyclerView = moviepediaActivityBinding2.nextResults;
        MediaScrapingResultAdapter mediaScrapingResultAdapter3 = this.mediaScrapingResultAdapter;
        if (mediaScrapingResultAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mediaScrapingResultAdapter");
            mediaScrapingResultAdapter3 = null;
        }
        recyclerView.setAdapter(mediaScrapingResultAdapter3);
        MoviepediaActivityBinding moviepediaActivityBinding3 = this.binding;
        if (moviepediaActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            moviepediaActivityBinding3 = null;
        }
        moviepediaActivityBinding3.nextResults.setLayoutManager(new GridLayoutManager(this, 2));
        Intrinsics.checkNotNull(intent);
        if (Build.VERSION.SDK_INT >= 33) {
            parcelable = (Parcelable) AppUtils$$ExternalSyntheticApiModelOutline0.m(intent, Constants.MOVIEPEDIA_MEDIA, MediaWrapper.class);
        } else {
            Parcelable parcelableExtra = intent.getParcelableExtra(Constants.MOVIEPEDIA_MEDIA);
            if (!(parcelableExtra instanceof MediaWrapper)) {
                parcelableExtra = null;
            }
            parcelable = (MediaWrapper) parcelableExtra;
        }
        MediaWrapper mediaWrapper2 = (MediaWrapper) parcelable;
        if (mediaWrapper2 != null) {
            this.media = mediaWrapper2;
        }
        if (this.media == null) {
            finish();
            return;
        }
        MoviepediaActivityBinding moviepediaActivityBinding4 = this.binding;
        if (moviepediaActivityBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            moviepediaActivityBinding4 = null;
        }
        moviepediaActivityBinding4.searchEditText.addTextChangedListener(this);
        MoviepediaActivityBinding moviepediaActivityBinding5 = this.binding;
        if (moviepediaActivityBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            moviepediaActivityBinding5 = null;
        }
        moviepediaActivityBinding5.searchEditText.setOnEditorActionListener(this);
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        MediaWrapper mediaWrapper3 = this.media;
        if (mediaWrapper3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("media");
            mediaWrapper3 = null;
        }
        String path = mediaWrapper3.getUri().getPath();
        if (path == null) {
            path = "";
        }
        MediaScrapingModel mediaScrapingModel = (MediaScrapingModel) viewModelProvider.get(path, MediaScrapingModel.class);
        this.viewModel = mediaScrapingModel;
        if (mediaScrapingModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            mediaScrapingModel = null;
        }
        mediaScrapingModel.getApiResult().observe(this, new MediaScrapingActivity$sam$androidx_lifecycle_Observer$0(new MediaScrapingActivity$onCreate$2(this)));
        MediaScrapingModel mediaScrapingModel2 = this.viewModel;
        if (mediaScrapingModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            mediaScrapingModel2 = null;
        }
        MediaWrapper mediaWrapper4 = this.media;
        if (mediaWrapper4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("media");
            mediaWrapper4 = null;
        }
        Uri uri = mediaWrapper4.getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
        mediaScrapingModel2.search(uri);
        MoviepediaActivityBinding moviepediaActivityBinding6 = this.binding;
        if (moviepediaActivityBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            moviepediaActivityBinding6 = null;
        }
        EditText editText = moviepediaActivityBinding6.searchEditText;
        MediaWrapper mediaWrapper5 = this.media;
        if (mediaWrapper5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("media");
        } else {
            mediaWrapper = mediaWrapper5;
        }
        editText.setText(mediaWrapper.getTitle());
    }

    private final void performSearh(String str) {
        MediaScrapingModel mediaScrapingModel = this.viewModel;
        if (mediaScrapingModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            mediaScrapingModel = null;
        }
        mediaScrapingModel.search(str);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(textView, "v");
        Intrinsics.checkNotNullParameter(keyEvent, NotificationCompat.CATEGORY_EVENT);
        if (i != 3) {
            return false;
        }
        UiTools uiTools = UiTools.INSTANCE;
        MoviepediaActivityBinding moviepediaActivityBinding = this.binding;
        if (moviepediaActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            moviepediaActivityBinding = null;
        }
        uiTools.setKeyboardVisibility(moviepediaActivityBinding.getRoot(), false);
        performSearh(textView.getText().toString());
        return true;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lorg/videolan/moviepedia/ui/MediaScrapingActivity$ClickHandler;", "", "(Lorg/videolan/moviepedia/ui/MediaScrapingActivity;)V", "onBack", "", "v", "Landroid/view/View;", "onItemClick", "item", "Lorg/videolan/moviepedia/models/resolver/ResolverMedia;", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaScrapingActivity.kt */
    public final class ClickHandler {
        public ClickHandler() {
        }

        public final void onBack(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            MediaScrapingActivity.this.finish();
        }

        public final void onItemClick(ResolverMedia resolverMedia) {
            Intrinsics.checkNotNullParameter(resolverMedia, "item");
            MediaScrapingActivity.this.finish();
        }
    }
}
