package org.videolan.vlc.gui;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.util.HelpersKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.LicenseActivityBinding;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\fH\u0016J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0014\u0010\u000b\u001a\u00020\fXD¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lorg/videolan/vlc/gui/LibrariesActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "()V", "adapter", "Lorg/videolan/vlc/gui/LibrariesAdapter;", "binding", "Lorg/videolan/vlc/databinding/LicenseActivityBinding;", "getBinding$vlc_android_release", "()Lorg/videolan/vlc/databinding/LicenseActivityBinding;", "setBinding$vlc_android_release", "(Lorg/videolan/vlc/databinding/LicenseActivityBinding;)V", "displayTitle", "", "getDisplayTitle", "()Z", "model", "Lorg/videolan/vlc/gui/LicenseModel;", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LibrariesActivity.kt */
public final class LibrariesActivity extends BaseActivity {
    private LibrariesAdapter adapter;
    public LicenseActivityBinding binding;
    private final boolean displayTitle = true;
    /* access modifiers changed from: private */
    public LicenseModel model;

    public final LicenseActivityBinding getBinding$vlc_android_release() {
        LicenseActivityBinding licenseActivityBinding = this.binding;
        if (licenseActivityBinding != null) {
            return licenseActivityBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding$vlc_android_release(LicenseActivityBinding licenseActivityBinding) {
        Intrinsics.checkNotNullParameter(licenseActivityBinding, "<set-?>");
        this.binding = licenseActivityBinding;
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
        ViewDataBinding contentView = DataBindingUtil.setContentView(activity, R.layout.license_activity);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        setBinding$vlc_android_release((LicenseActivityBinding) contentView);
        setSupportActionBar((MaterialToolbar) findViewById(R.id.main_toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setHomeAsUpIndicator(R.drawable.ic_close_up);
        }
        setTitle(getString(R.string.libraries));
        getBinding$vlc_android_release().licenses.setLayoutManager(new LinearLayoutManager(this));
        this.adapter = new LibrariesAdapter(new LibrariesActivity$onCreate$1(this));
        RecyclerView recyclerView = getBinding$vlc_android_release().licenses;
        LibrariesAdapter librariesAdapter = this.adapter;
        if (librariesAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            librariesAdapter = null;
        }
        recyclerView.setAdapter(librariesAdapter);
        LicenseModel licenseModel = (LicenseModel) new ViewModelProvider(this).get(LicenseModel.class);
        this.model = licenseModel;
        if (licenseModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("model");
            licenseModel = null;
        }
        LifecycleOwner lifecycleOwner = this;
        licenseModel.getLicenses().observe(lifecycleOwner, new LibrariesActivity$$ExternalSyntheticLambda0(this));
        LifecycleOwnerKt.getLifecycleScope(lifecycleOwner).launchWhenStarted(new LibrariesActivity$onCreate$3(this, (Continuation<? super LibrariesActivity$onCreate$3>) null));
        if (AndroidDevices.INSTANCE.isTv()) {
            HelpersKt.applyOverscanMargin(activity);
        }
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$0(LibrariesActivity librariesActivity, List list) {
        Intrinsics.checkNotNullParameter(librariesActivity, "this$0");
        LibrariesAdapter librariesAdapter = librariesActivity.adapter;
        if (librariesAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            librariesAdapter = null;
        }
        Intrinsics.checkNotNull(list);
        librariesAdapter.update(list);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        if (menuItem.getItemId() == 16908332) {
            finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
