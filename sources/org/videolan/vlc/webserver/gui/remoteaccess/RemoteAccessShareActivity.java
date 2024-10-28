package org.videolan.vlc.webserver.gui.remoteaccess;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.ActionBar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.appbar.MaterialToolbar;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;
import org.videolan.vlc.gui.BaseActivity;
import org.videolan.vlc.webserver.R;
import org.videolan.vlc.webserver.RemoteAccessServer;
import org.videolan.vlc.webserver.ServerStatus;
import org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBinding;
import org.videolan.vlc.webserver.gui.remoteaccess.adapters.ConnnectionAdapter;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0016J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0012\u0010\u0012\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0018"}, d2 = {"Lorg/videolan/vlc/webserver/gui/remoteaccess/RemoteAccessShareActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "()V", "binding", "Lorg/videolan/vlc/webserver/databinding/RemoteAccessShareActivityBinding;", "connectionAdapter", "Lorg/videolan/vlc/webserver/gui/remoteaccess/adapters/ConnnectionAdapter;", "displayTitle", "", "getDisplayTitle", "()Z", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessShareActivity.kt */
public final class RemoteAccessShareActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public RemoteAccessShareActivityBinding binding;
    /* access modifiers changed from: private */
    public ConnnectionAdapter connectionAdapter;
    private final boolean displayTitle = true;

    public View getSnackAnchorView(boolean z) {
        RemoteAccessShareActivityBinding remoteAccessShareActivityBinding = this.binding;
        if (remoteAccessShareActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            remoteAccessShareActivityBinding = null;
        }
        View root = remoteAccessShareActivityBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    public boolean getDisplayTitle() {
        return this.displayTitle;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ViewDataBinding contentView = DataBindingUtil.setContentView(this, R.layout.remote_access_share_activity);
        Intrinsics.checkNotNullExpressionValue(contentView, "setContentView(...)");
        this.binding = (RemoteAccessShareActivityBinding) contentView;
        setSupportActionBar((MaterialToolbar) findViewById(R.id.main_toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        Intrinsics.checkNotNull(supportActionBar);
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 != null) {
            supportActionBar2.setHomeAsUpIndicator(R.drawable.ic_close_up);
        }
        setTitle(getString(R.string.remote_access));
        RemoteAccessServer remoteAccessServer = (RemoteAccessServer) RemoteAccessServer.Companion.getInstance(getApplicationContext());
        LifecycleOwner lifecycleOwner = this;
        remoteAccessServer.getServerStatus().observe(lifecycleOwner, new RemoteAccessShareActivity$sam$androidx_lifecycle_Observer$0(new RemoteAccessShareActivity$onCreate$1(this, remoteAccessServer)));
        RemoteAccessShareActivityBinding remoteAccessShareActivityBinding = this.binding;
        ConnnectionAdapter connnectionAdapter = null;
        if (remoteAccessShareActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            remoteAccessShareActivityBinding = null;
        }
        remoteAccessShareActivityBinding.statusButton.setOnClickListener(new RemoteAccessShareActivity$$ExternalSyntheticLambda0(remoteAccessServer, this));
        LayoutInflater layoutInflater = getLayoutInflater();
        Intrinsics.checkNotNullExpressionValue(layoutInflater, "getLayoutInflater(...)");
        this.connectionAdapter = new ConnnectionAdapter(layoutInflater, CollectionsKt.emptyList());
        RemoteAccessShareActivityBinding remoteAccessShareActivityBinding2 = this.binding;
        if (remoteAccessShareActivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            remoteAccessShareActivityBinding2 = null;
        }
        remoteAccessShareActivityBinding2.connectionList.setLayoutManager(new LinearLayoutManager(this));
        RemoteAccessShareActivityBinding remoteAccessShareActivityBinding3 = this.binding;
        if (remoteAccessShareActivityBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            remoteAccessShareActivityBinding3 = null;
        }
        RecyclerView recyclerView = remoteAccessShareActivityBinding3.connectionList;
        ConnnectionAdapter connnectionAdapter2 = this.connectionAdapter;
        if (connnectionAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("connectionAdapter");
        } else {
            connnectionAdapter = connnectionAdapter2;
        }
        recyclerView.setAdapter(connnectionAdapter);
        remoteAccessServer.getServerConnections().observe(lifecycleOwner, new RemoteAccessShareActivity$sam$androidx_lifecycle_Observer$0(new RemoteAccessShareActivity$onCreate$3(this)));
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$1(RemoteAccessServer remoteAccessServer, RemoteAccessShareActivity remoteAccessShareActivity, View view) {
        Intrinsics.checkNotNullParameter(remoteAccessServer, "$remoteAccessServer");
        Intrinsics.checkNotNullParameter(remoteAccessShareActivity, "this$0");
        Intent intent = new Intent(remoteAccessServer.getServerStatus().getValue() == ServerStatus.STARTED ? Constants.ACTION_STOP_SERVER : Constants.ACTION_START_SERVER);
        intent.setPackage(remoteAccessShareActivity.getPackageName());
        remoteAccessShareActivity.sendBroadcast(intent);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.remote_access_share, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == 16908332) {
            finish();
        } else if (itemId == R.id.menu_remote_access_onboarding) {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setClassName(this, Constants.REMOTE_ACCESS_ONBOARDING);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
