package org.videolan.vlc.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.appbar.MaterialToolbar;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.util.HelpersKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.AboutAuthorsActivityBinding;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0002J\u0012\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\nXD¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/gui/AuthorsActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "()V", "binding", "Lorg/videolan/vlc/databinding/AboutAuthorsActivityBinding;", "getBinding$vlc_android_release", "()Lorg/videolan/vlc/databinding/AboutAuthorsActivityBinding;", "setBinding$vlc_android_release", "(Lorg/videolan/vlc/databinding/AboutAuthorsActivityBinding;)V", "displayTitle", "", "getDisplayTitle", "()Z", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "loadAuthors", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: AuthorsActivity.kt */
public final class AuthorsActivity extends BaseActivity {
    public AboutAuthorsActivityBinding binding;
    private final boolean displayTitle = true;

    public final AboutAuthorsActivityBinding getBinding$vlc_android_release() {
        AboutAuthorsActivityBinding aboutAuthorsActivityBinding = this.binding;
        if (aboutAuthorsActivityBinding != null) {
            return aboutAuthorsActivityBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding$vlc_android_release(AboutAuthorsActivityBinding aboutAuthorsActivityBinding) {
        Intrinsics.checkNotNullParameter(aboutAuthorsActivityBinding, "<set-?>");
        this.binding = aboutAuthorsActivityBinding;
    }

    public View getSnackAnchorView(boolean z) {
        View root = getBinding$vlc_android_release().getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public boolean getDisplayTitle() {
        return this.displayTitle;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Activity activity = this;
        ViewDataBinding contentView = DataBindingUtil.setContentView(activity, R.layout.about_authors_activity);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        setBinding$vlc_android_release((AboutAuthorsActivityBinding) contentView);
        setSupportActionBar((MaterialToolbar) findViewById(R.id.main_toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setHomeAsUpIndicator(R.drawable.ic_close_up);
        }
        setTitle(getString(R.string.authors));
        getBinding$vlc_android_release().authorsList.setLayoutManager(new LinearLayoutManager(this));
        loadAuthors();
        if (AndroidDevices.INSTANCE.isTv()) {
            HelpersKt.applyOverscanMargin(activity);
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private final void loadAuthors() {
        LifecycleOwnerKt.getLifecycleScope(this).launchWhenStarted(new AuthorsActivity$loadAuthors$1(this, (Continuation<? super AuthorsActivity$loadAuthors$1>) null));
    }
}
