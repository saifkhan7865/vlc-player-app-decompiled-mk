package org.videolan.vlc.gui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.SearchAggregate;
import org.videolan.resources.Constants;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.SearchActivityBinding;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.helpers.UiToolsKt;
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0016\u0018\u0000 )2\u00020\u00012\u00020\u00022\u00020\u0003:\u0002()B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J(\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u0019\u001a\u00020\fH\u0002J\u0012\u0010\u001a\u001a\u00020\f2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0014J$\u0010\u001d\u001a\u00020\u00182\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020\u00122\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J(\u0010#\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016J\u0012\u0010%\u001a\u00020\f2\b\u0010&\u001a\u0004\u0018\u00010'H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lorg/videolan/vlc/gui/SearchActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "Landroid/text/TextWatcher;", "Landroid/widget/TextView$OnEditorActionListener;", "()V", "binding", "Lorg/videolan/vlc/databinding/SearchActivityBinding;", "clickHandler", "Lorg/videolan/vlc/gui/SearchActivity$ClickHandler;", "medialibrary", "Lorg/videolan/medialibrary/interfaces/Medialibrary;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "", "initializeLists", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onEditorAction", "v", "Landroid/widget/TextView;", "actionId", "event", "Landroid/view/KeyEvent;", "onTextChanged", "before", "performSearh", "query", "", "ClickHandler", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SearchActivity.kt */
public class SearchActivity extends BaseActivity implements TextWatcher, TextView.OnEditorActionListener {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String TAG = "VLC/SearchActivity";
    /* access modifiers changed from: private */
    public SearchActivityBinding binding;
    private final ClickHandler clickHandler = new ClickHandler();
    private Medialibrary medialibrary;

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
        super.onCreate(bundle);
        UiToolsKt.applyTheme(this);
        Intent intent = getIntent();
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.search_activity);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        SearchActivityBinding searchActivityBinding = (SearchActivityBinding) contentView;
        this.binding = searchActivityBinding;
        SearchActivityBinding searchActivityBinding2 = null;
        if (searchActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            searchActivityBinding = null;
        }
        searchActivityBinding.setHandler(this.clickHandler);
        SearchActivityBinding searchActivityBinding3 = this.binding;
        if (searchActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            searchActivityBinding3 = null;
        }
        searchActivityBinding3.setSearchAggregate(new SearchAggregate());
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        this.medialibrary = instance;
        if (Intrinsics.areEqual((Object) "android.intent.action.SEARCH", (Object) intent.getAction()) || Intrinsics.areEqual((Object) Constants.ACTION_SEARCH_GMS, (Object) intent.getAction())) {
            String stringExtra = intent.getStringExtra("query");
            initializeLists();
            CharSequence charSequence = stringExtra;
            if (!(charSequence == null || charSequence.length() == 0)) {
                getWindow().setSoftInputMode(2);
                SearchActivityBinding searchActivityBinding4 = this.binding;
                if (searchActivityBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    searchActivityBinding4 = null;
                }
                searchActivityBinding4.searchEditText.setText(charSequence);
                SearchActivityBinding searchActivityBinding5 = this.binding;
                if (searchActivityBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    searchActivityBinding5 = null;
                }
                searchActivityBinding5.searchEditText.setSelection(stringExtra.length());
                performSearh(stringExtra);
            }
        }
        SearchActivityBinding searchActivityBinding6 = this.binding;
        if (searchActivityBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            searchActivityBinding6 = null;
        }
        searchActivityBinding6.searchEditText.addTextChangedListener(this);
        SearchActivityBinding searchActivityBinding7 = this.binding;
        if (searchActivityBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            searchActivityBinding2 = searchActivityBinding7;
        }
        searchActivityBinding2.searchEditText.setOnEditorActionListener(this);
    }

    private final void performSearh(String str) {
        if (str != null && str.length() > 0) {
            LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new SearchActivity$performSearh$1(this, str, (Continuation<? super SearchActivity$performSearh$1>) null));
        }
    }

    private final void initializeLists() {
        SearchActivityBinding searchActivityBinding = this.binding;
        if (searchActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            searchActivityBinding = null;
        }
        int childCount = searchActivityBinding.resultsContainer.getChildCount();
        for (int i = 0; i < childCount; i++) {
            SearchActivityBinding searchActivityBinding2 = this.binding;
            if (searchActivityBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                searchActivityBinding2 = null;
            }
            View childAt = searchActivityBinding2.resultsContainer.getChildAt(i);
            if (childAt instanceof RecyclerView) {
                RecyclerView recyclerView = (RecyclerView) childAt;
                LayoutInflater layoutInflater = getLayoutInflater();
                Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
                recyclerView.setAdapter(new SearchResultAdapter(layoutInflater));
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type org.videolan.vlc.gui.SearchResultAdapter");
                ((SearchResultAdapter) adapter).setClickHandler$vlc_android_release(this.clickHandler);
            }
        }
    }

    public void afterTextChanged(Editable editable) {
        if (editable == null || editable.length() == 0) {
            SearchActivityBinding searchActivityBinding = this.binding;
            if (searchActivityBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                searchActivityBinding = null;
            }
            searchActivityBinding.setSearchAggregate(new SearchAggregate());
            return;
        }
        performSearh(editable.toString());
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i != 3) {
            return false;
        }
        UiTools uiTools = UiTools.INSTANCE;
        SearchActivityBinding searchActivityBinding = this.binding;
        if (searchActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            searchActivityBinding = null;
        }
        uiTools.setKeyboardVisibility(searchActivityBinding.getRoot(), false);
        return true;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/gui/SearchActivity$ClickHandler;", "", "(Lorg/videolan/vlc/gui/SearchActivity;)V", "onBack", "", "v", "Landroid/view/View;", "onItemClick", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SearchActivity.kt */
    public final class ClickHandler {
        public ClickHandler() {
        }

        public final void onBack(View view) {
            Intrinsics.checkNotNullParameter(view, "v");
            SearchActivity.this.finish();
        }

        public final void onItemClick(MediaLibraryItem mediaLibraryItem) {
            Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
            MediaUtils.playTracks$default(MediaUtils.INSTANCE, (Context) SearchActivity.this, mediaLibraryItem, 0, false, 8, (Object) null);
            SearchActivity.this.finish();
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/gui/SearchActivity$Companion;", "", "()V", "TAG", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SearchActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
