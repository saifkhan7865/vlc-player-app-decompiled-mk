package org.videolan.vlc.widget;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.R;
import org.videolan.vlc.databinding.WidgetMiniPlayerConfigureBinding;
import org.videolan.vlc.gui.BaseActivity;
import org.videolan.vlc.gui.dialogs.WidgetExplanationDialog;
import org.videolan.vlc.mediadb.models.Widget;
import org.videolan.vlc.widget.utils.WidgetCache;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\fH\u0016J\u0012\u0010\u001a\u001a\u00020\u00162\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\f2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\f2\u0006\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0016H\u0002J\b\u0010$\u001a\u00020\u0016H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\u00020\fXD¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0010X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006%"}, d2 = {"Lorg/videolan/vlc/widget/MiniPlayerConfigureActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "()V", "appWidgetId", "", "getAppWidgetId", "()I", "setAppWidgetId", "(I)V", "binding", "Lorg/videolan/vlc/databinding/WidgetMiniPlayerConfigureBinding;", "displayTitle", "", "getDisplayTitle", "()Z", "model", "Lorg/videolan/vlc/widget/WidgetViewModel;", "getModel$vlc_android_release", "()Lorg/videolan/vlc/widget/WidgetViewModel;", "setModel$vlc_android_release", "(Lorg/videolan/vlc/widget/WidgetViewModel;)V", "finish", "", "getSnackAnchorView", "Landroidx/coordinatorlayout/widget/CoordinatorLayout;", "overAudioPlayer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "onWidgetContainerClicked", "updatePreview", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MiniPlayerConfigureActivity.kt */
public final class MiniPlayerConfigureActivity extends BaseActivity {
    private int appWidgetId;
    /* access modifiers changed from: private */
    public WidgetMiniPlayerConfigureBinding binding;
    private final boolean displayTitle = true;
    public WidgetViewModel model;

    public final WidgetViewModel getModel$vlc_android_release() {
        WidgetViewModel widgetViewModel = this.model;
        if (widgetViewModel != null) {
            return widgetViewModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("model");
        return null;
    }

    public final void setModel$vlc_android_release(WidgetViewModel widgetViewModel) {
        Intrinsics.checkNotNullParameter(widgetViewModel, "<set-?>");
        this.model = widgetViewModel;
    }

    public boolean getDisplayTitle() {
        return this.displayTitle;
    }

    public final int getAppWidgetId() {
        return this.appWidgetId;
    }

    public final void setAppWidgetId(int i) {
        this.appWidgetId = i;
    }

    public CoordinatorLayout getSnackAnchorView(boolean z) {
        WidgetMiniPlayerConfigureBinding widgetMiniPlayerConfigureBinding = this.binding;
        if (widgetMiniPlayerConfigureBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            widgetMiniPlayerConfigureBinding = null;
        }
        CoordinatorLayout coordinatorLayout = widgetMiniPlayerConfigureBinding.coordinator;
        Intrinsics.checkNotNullExpressionValue(coordinatorLayout, "coordinator");
        return coordinatorLayout;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x004d, code lost:
        r3 = r3.getExtras();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r9) {
        /*
            r8 = this;
            super.onCreate(r9)
            android.view.LayoutInflater r0 = r8.getLayoutInflater()
            org.videolan.vlc.databinding.WidgetMiniPlayerConfigureBinding r0 = org.videolan.vlc.databinding.WidgetMiniPlayerConfigureBinding.inflate(r0)
            java.lang.String r1 = "inflate(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r8.binding = r0
            r1 = 0
            java.lang.String r2 = "binding"
            if (r0 != 0) goto L_0x001b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r1
        L_0x001b:
            android.view.View r0 = r0.getRoot()
            r8.setContentView((android.view.View) r0)
            r0 = 0
            r8.setResult(r0)
            int r3 = org.videolan.vlc.R.id.main_toolbar
            android.view.View r3 = r8.findViewById(r3)
            com.google.android.material.appbar.MaterialToolbar r3 = (com.google.android.material.appbar.MaterialToolbar) r3
            androidx.appcompat.widget.Toolbar r3 = (androidx.appcompat.widget.Toolbar) r3
            r8.setSupportActionBar(r3)
            androidx.appcompat.app.ActionBar r3 = r8.getSupportActionBar()
            if (r3 == 0) goto L_0x003c
            r3.setDisplayHomeAsUpEnabled(r0)
        L_0x003c:
            int r3 = org.videolan.vlc.R.string.configure_widget
            java.lang.String r3 = r8.getString(r3)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r8.setTitle(r3)
            android.content.Intent r3 = r8.getIntent()
            if (r3 == 0) goto L_0x005a
            android.os.Bundle r3 = r3.getExtras()
            if (r3 == 0) goto L_0x005a
            java.lang.String r4 = "appWidgetId"
            int r3 = r3.getInt(r4, r0)
            goto L_0x005b
        L_0x005a:
            r3 = 0
        L_0x005b:
            r8.appWidgetId = r3
            if (r3 != 0) goto L_0x0063
            r8.finish()
            return
        L_0x0063:
            androidx.lifecycle.ViewModelProvider r3 = new androidx.lifecycle.ViewModelProvider
            r4 = r8
            androidx.lifecycle.ViewModelStoreOwner r4 = (androidx.lifecycle.ViewModelStoreOwner) r4
            org.videolan.vlc.widget.WidgetViewModel$Factory r5 = new org.videolan.vlc.widget.WidgetViewModel$Factory
            r6 = r8
            android.content.Context r6 = (android.content.Context) r6
            int r7 = r8.appWidgetId
            r5.<init>(r6, r7)
            androidx.lifecycle.ViewModelProvider$Factory r5 = (androidx.lifecycle.ViewModelProvider.Factory) r5
            r3.<init>((androidx.lifecycle.ViewModelStoreOwner) r4, (androidx.lifecycle.ViewModelProvider.Factory) r5)
            java.lang.Class<org.videolan.vlc.widget.WidgetViewModel> r4 = org.videolan.vlc.widget.WidgetViewModel.class
            androidx.lifecycle.ViewModel r3 = r3.get(r4)
            org.videolan.vlc.widget.WidgetViewModel r3 = (org.videolan.vlc.widget.WidgetViewModel) r3
            r8.setModel$vlc_android_release(r3)
            org.videolan.vlc.widget.WidgetViewModel r3 = r8.getModel$vlc_android_release()
            androidx.lifecycle.LiveData r3 = r3.getWidget()
            r4 = r8
            androidx.lifecycle.LifecycleOwner r4 = (androidx.lifecycle.LifecycleOwner) r4
            org.videolan.vlc.widget.MiniPlayerConfigureActivity$onCreate$1 r5 = new org.videolan.vlc.widget.MiniPlayerConfigureActivity$onCreate$1
            r5.<init>(r8)
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            org.videolan.vlc.widget.MiniPlayerConfigureActivity$sam$androidx_lifecycle_Observer$0 r6 = new org.videolan.vlc.widget.MiniPlayerConfigureActivity$sam$androidx_lifecycle_Observer$0
            r6.<init>(r5)
            androidx.lifecycle.Observer r6 = (androidx.lifecycle.Observer) r6
            r3.observe(r4, r6)
            r3 = 1
            if (r9 != 0) goto L_0x00d0
            org.videolan.vlc.gui.preferences.widgets.PreferencesWidgets r9 = new org.videolan.vlc.gui.preferences.widgets.PreferencesWidgets
            r9.<init>()
            kotlin.Pair[] r4 = new kotlin.Pair[r3]
            int r5 = r8.appWidgetId
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.String r6 = "WIDGET_ID"
            kotlin.Pair r5 = kotlin.TuplesKt.to(r6, r5)
            r4[r0] = r5
            android.os.Bundle r4 = androidx.core.os.BundleKt.bundleOf(r4)
            r9.setArguments(r4)
            androidx.fragment.app.FragmentManager r4 = r8.getSupportFragmentManager()
            androidx.fragment.app.FragmentTransaction r4 = r4.beginTransaction()
            int r5 = org.videolan.vlc.R.id.fragment_placeholder
            androidx.fragment.app.Fragment r9 = (androidx.fragment.app.Fragment) r9
            androidx.fragment.app.FragmentTransaction r9 = r4.replace(r5, r9)
            r9.commit()
        L_0x00d0:
            org.videolan.vlc.databinding.WidgetMiniPlayerConfigureBinding r9 = r8.binding
            if (r9 != 0) goto L_0x00d8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r9 = r1
        L_0x00d8:
            androidx.appcompat.widget.SwitchCompat r9 = r9.previewPlaying
            org.videolan.tools.Settings r4 = org.videolan.tools.Settings.INSTANCE
            java.lang.Object r4 = r4.getInstance(r8)
            android.content.SharedPreferences r4 = (android.content.SharedPreferences) r4
            java.lang.String r5 = "widgets_preview_playing"
            boolean r4 = r4.getBoolean(r5, r3)
            r9.setChecked(r4)
            org.videolan.vlc.databinding.WidgetMiniPlayerConfigureBinding r9 = r8.binding
            if (r9 != 0) goto L_0x00f3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L_0x00f4
        L_0x00f3:
            r1 = r9
        L_0x00f4:
            androidx.appcompat.widget.SwitchCompat r9 = r1.previewPlaying
            org.videolan.vlc.widget.MiniPlayerConfigureActivity$$ExternalSyntheticLambda0 r1 = new org.videolan.vlc.widget.MiniPlayerConfigureActivity$$ExternalSyntheticLambda0
            r1.<init>(r8)
            r9.setOnCheckedChangeListener(r1)
            android.content.SharedPreferences r9 = r8.getSettings()
            java.lang.String r1 = "widgets_tips_shown"
            boolean r9 = r9.getBoolean(r1, r0)
            if (r9 != 0) goto L_0x0123
            org.videolan.vlc.gui.dialogs.WidgetExplanationDialog r9 = new org.videolan.vlc.gui.dialogs.WidgetExplanationDialog
            r9.<init>()
            androidx.fragment.app.FragmentManager r0 = r8.getSupportFragmentManager()
            java.lang.String r2 = "fragment_widget_explanation"
            r9.show((androidx.fragment.app.FragmentManager) r0, (java.lang.String) r2)
            android.content.SharedPreferences r9 = r8.getSettings()
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r3)
            org.videolan.tools.SettingsKt.putSingle(r9, r1, r0)
        L_0x0123:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.widget.MiniPlayerConfigureActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$1(MiniPlayerConfigureActivity miniPlayerConfigureActivity, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(miniPlayerConfigureActivity, "this$0");
        SettingsKt.putSingle((SharedPreferences) Settings.INSTANCE.getInstance(miniPlayerConfigureActivity), SettingsKt.WIDGETS_PREVIEW_PLAYING, Boolean.valueOf(z));
        miniPlayerConfigureActivity.updatePreview();
    }

    /* access modifiers changed from: private */
    public final void updatePreview() {
        Widget value = getModel$vlc_android_release().getWidget().getValue();
        if (value != null) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, (CoroutineStart) null, new MiniPlayerConfigureActivity$updatePreview$1$1(this, value, (Continuation<? super MiniPlayerConfigureActivity$updatePreview$1$1>) null), 3, (Object) null);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        Intrinsics.checkNotNullParameter(menu, "menu");
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.widget_configure_option, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(menuItem, "item");
        int itemId = menuItem.getItemId();
        if (itemId == R.id.widget_configure_done) {
            onWidgetContainerClicked();
            finish();
            return true;
        } else if (itemId != R.id.widget_info) {
            return super.onOptionsItemSelected(menuItem);
        } else {
            new WidgetExplanationDialog().show(getSupportFragmentManager(), "fragment_widget_explanation");
            return true;
        }
    }

    public void finish() {
        onWidgetContainerClicked();
        super.finish();
    }

    private final void onWidgetContainerClicked() {
        Widget value = getModel$vlc_android_release().getWidget().getValue();
        if (value != null) {
            WidgetCache.INSTANCE.clear(value);
        }
        Intent intent = new Intent(MiniPlayerAppWidgetProvider.Companion.getACTION_WIDGET_INIT());
        intent.setComponent(new ComponentName(getApplicationContext(), MiniPlayerAppWidgetProvider.class));
        sendBroadcast(intent);
        Intent intent2 = new Intent();
        intent2.putExtra("appWidgetId", this.appWidgetId);
        setResult(-1, intent2);
    }
}
