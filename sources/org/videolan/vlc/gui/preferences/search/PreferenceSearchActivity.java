package org.videolan.vlc.gui.preferences.search;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;
import org.videolan.vlc.databinding.PreferencesSearchActivityBinding;
import org.videolan.vlc.gui.BaseActivity;
import org.videolan.vlc.gui.preferences.PreferencesActivityKt;
import org.videolan.vlc.gui.preferences.search.PreferenceItemAdapter;
import org.videolan.vlc.viewmodels.PreferenceSearchModel;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \"2\u00020\u00012\u00020\u00022\u00020\u0003:\u0001\"B\u0005¢\u0006\u0002\u0010\u0004J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016J*\u0010\u000f\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0012H\u0016J\u0012\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\fH\u0014J*\u0010 \u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010!\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX.¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lorg/videolan/vlc/gui/preferences/search/PreferenceSearchActivity;", "Lorg/videolan/vlc/gui/BaseActivity;", "Landroid/text/TextWatcher;", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapter$ClickHandler;", "()V", "adapter", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItemAdapter;", "binding", "Lorg/videolan/vlc/databinding/PreferencesSearchActivityBinding;", "viewmodel", "Lorg/videolan/vlc/viewmodels/PreferenceSearchModel;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "", "count", "after", "getSnackAnchorView", "Landroid/view/View;", "overAudioPlayer", "", "onClick", "item", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItem;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "onTextChanged", "before", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferenceSearchActivity.kt */
public final class PreferenceSearchActivity extends BaseActivity implements TextWatcher, PreferenceItemAdapter.ClickHandler {
    /* access modifiers changed from: private */
    public static final String ACTION_RESULT = Constants.buildPkgString("search.result");
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public PreferenceItemAdapter adapter;
    /* access modifiers changed from: private */
    public PreferencesSearchActivityBinding binding;
    private PreferenceSearchModel viewmodel;

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public View getSnackAnchorView(boolean z) {
        return findViewById(16908290);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0113, code lost:
        if (r8 != null) goto L_0x0139;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r8) {
        /*
            r7 = this;
            super.onCreate(r8)
            android.view.LayoutInflater r8 = r7.getLayoutInflater()
            org.videolan.vlc.databinding.PreferencesSearchActivityBinding r8 = org.videolan.vlc.databinding.PreferencesSearchActivityBinding.inflate(r8)
            java.lang.String r0 = "inflate(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r0)
            r7.binding = r8
            java.lang.String r0 = "binding"
            r1 = 0
            if (r8 != 0) goto L_0x001b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r8 = r1
        L_0x001b:
            android.view.View r8 = r8.getRoot()
            r7.setContentView((android.view.View) r8)
            org.videolan.vlc.databinding.PreferencesSearchActivityBinding r8 = r7.binding
            if (r8 != 0) goto L_0x002a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r8 = r1
        L_0x002a:
            android.widget.ImageView r8 = r8.closeButton
            org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$$ExternalSyntheticLambda0 r2 = new org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$$ExternalSyntheticLambda0
            r2.<init>(r7)
            r8.setOnClickListener(r2)
            androidx.lifecycle.ViewModelProvider r8 = new androidx.lifecycle.ViewModelProvider
            r2 = r7
            androidx.lifecycle.ViewModelStoreOwner r2 = (androidx.lifecycle.ViewModelStoreOwner) r2
            org.videolan.vlc.viewmodels.PreferenceSearchModel$Factory r3 = new org.videolan.vlc.viewmodels.PreferenceSearchModel$Factory
            r4 = r7
            android.content.Context r4 = (android.content.Context) r4
            r3.<init>(r4)
            androidx.lifecycle.ViewModelProvider$Factory r3 = (androidx.lifecycle.ViewModelProvider.Factory) r3
            r8.<init>((androidx.lifecycle.ViewModelStoreOwner) r2, (androidx.lifecycle.ViewModelProvider.Factory) r3)
            java.lang.Class<org.videolan.vlc.viewmodels.PreferenceSearchModel> r2 = org.videolan.vlc.viewmodels.PreferenceSearchModel.class
            androidx.lifecycle.ViewModel r8 = r8.get(r2)
            org.videolan.vlc.viewmodels.PreferenceSearchModel r8 = (org.videolan.vlc.viewmodels.PreferenceSearchModel) r8
            r7.viewmodel = r8
            org.videolan.vlc.databinding.PreferencesSearchActivityBinding r8 = r7.binding
            if (r8 != 0) goto L_0x0058
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r8 = r1
        L_0x0058:
            android.widget.EditText r8 = r8.searchText
            r2 = r7
            android.text.TextWatcher r2 = (android.text.TextWatcher) r2
            r8.addTextChangedListener(r2)
            org.videolan.vlc.viewmodels.PreferenceSearchModel r8 = r7.viewmodel
            java.lang.String r2 = "viewmodel"
            if (r8 != 0) goto L_0x006a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r8 = r1
        L_0x006a:
            org.videolan.tools.livedata.LiveDataset r8 = r8.getFiltered()
            r3 = r7
            androidx.lifecycle.LifecycleOwner r3 = (androidx.lifecycle.LifecycleOwner) r3
            org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$onCreate$2 r5 = new org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$onCreate$2
            r5.<init>(r7)
            kotlin.jvm.functions.Function1 r5 = (kotlin.jvm.functions.Function1) r5
            org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$sam$androidx_lifecycle_Observer$0 r6 = new org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$sam$androidx_lifecycle_Observer$0
            r6.<init>(r5)
            androidx.lifecycle.Observer r6 = (androidx.lifecycle.Observer) r6
            r8.observe(r3, r6)
            org.videolan.vlc.viewmodels.PreferenceSearchModel r8 = r7.viewmodel
            if (r8 != 0) goto L_0x008a
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r8 = r1
        L_0x008a:
            androidx.lifecycle.MutableLiveData r8 = r8.getShowTranslations()
            org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$onCreate$3 r2 = new org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$onCreate$3
            r2.<init>(r7)
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$sam$androidx_lifecycle_Observer$0 r5 = new org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$sam$androidx_lifecycle_Observer$0
            r5.<init>(r2)
            androidx.lifecycle.Observer r5 = (androidx.lifecycle.Observer) r5
            r8.observe(r3, r5)
            org.videolan.vlc.gui.preferences.search.PreferenceItemAdapter r8 = new org.videolan.vlc.gui.preferences.search.PreferenceItemAdapter
            r2 = r7
            org.videolan.vlc.gui.preferences.search.PreferenceItemAdapter$ClickHandler r2 = (org.videolan.vlc.gui.preferences.search.PreferenceItemAdapter.ClickHandler) r2
            r8.<init>(r2)
            r7.adapter = r8
            org.videolan.vlc.databinding.PreferencesSearchActivityBinding r8 = r7.binding
            if (r8 != 0) goto L_0x00b1
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r8 = r1
        L_0x00b1:
            androidx.recyclerview.widget.RecyclerView r8 = r8.list
            org.videolan.vlc.gui.preferences.search.PreferenceItemAdapter r2 = r7.adapter
            if (r2 != 0) goto L_0x00bd
            java.lang.String r2 = "adapter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r2 = r1
        L_0x00bd:
            androidx.recyclerview.widget.RecyclerView$Adapter r2 = (androidx.recyclerview.widget.RecyclerView.Adapter) r2
            r8.setAdapter(r2)
            org.videolan.vlc.databinding.PreferencesSearchActivityBinding r8 = r7.binding
            if (r8 != 0) goto L_0x00ca
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r8 = r1
        L_0x00ca:
            androidx.recyclerview.widget.RecyclerView r8 = r8.list
            androidx.recyclerview.widget.LinearLayoutManager r2 = new androidx.recyclerview.widget.LinearLayoutManager
            r2.<init>(r4)
            androidx.recyclerview.widget.RecyclerView$LayoutManager r2 = (androidx.recyclerview.widget.RecyclerView.LayoutManager) r2
            r8.setLayoutManager(r2)
            org.videolan.vlc.databinding.PreferencesSearchActivityBinding r8 = r7.binding
            if (r8 != 0) goto L_0x00de
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r8 = r1
        L_0x00de:
            android.widget.ImageView r8 = r8.translateButton
            r2 = 0
            r8.setSelected(r2)
            org.videolan.vlc.databinding.PreferencesSearchActivityBinding r8 = r7.binding
            if (r8 != 0) goto L_0x00ec
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r8 = r1
        L_0x00ec:
            android.widget.ImageView r8 = r8.translateButton
            org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$$ExternalSyntheticLambda1 r3 = new org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity$$ExternalSyntheticLambda1
            r3.<init>(r7)
            r8.setOnClickListener(r3)
            org.videolan.resources.AppContextProvider r8 = org.videolan.resources.AppContextProvider.INSTANCE
            java.lang.String r8 = r8.getLocale()
            if (r8 == 0) goto L_0x0115
            r3 = r8
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            boolean r3 = kotlin.text.StringsKt.isBlank(r3)
            r3 = r3 ^ 1
            if (r3 == 0) goto L_0x010a
            goto L_0x010b
        L_0x010a:
            r8 = r1
        L_0x010b:
            if (r8 == 0) goto L_0x0115
            org.videolan.tools.LocaleUtils r3 = org.videolan.tools.LocaleUtils.INSTANCE
            java.util.Locale r8 = r3.getLocaleFromString(r8)
            if (r8 != 0) goto L_0x0139
        L_0x0115:
            int r8 = android.os.Build.VERSION.SDK_INT
            r3 = 24
            if (r8 < r3) goto L_0x012c
            android.content.res.Resources r8 = r7.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            android.os.LocaleList r8 = androidx.car.app.CarContext$$ExternalSyntheticApiModelOutline0.m((android.content.res.Configuration) r8)
            java.util.Locale r8 = r8.get(r2)
            goto L_0x0136
        L_0x012c:
            android.content.res.Resources r8 = r7.getResources()
            android.content.res.Configuration r8 = r8.getConfiguration()
            java.util.Locale r8 = r8.locale
        L_0x0136:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
        L_0x0139:
            java.lang.String r8 = r8.getLanguage()
            java.lang.String r2 = "en"
            boolean r8 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r2)
            if (r8 == 0) goto L_0x0155
            org.videolan.vlc.databinding.PreferencesSearchActivityBinding r8 = r7.binding
            if (r8 != 0) goto L_0x014d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            goto L_0x014e
        L_0x014d:
            r1 = r8
        L_0x014e:
            android.widget.ImageView r8 = r1.translateButton
            android.view.View r8 = (android.view.View) r8
            org.videolan.tools.KotlinExtensionsKt.setGone(r8)
        L_0x0155:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.preferences.search.PreferenceSearchActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$0(PreferenceSearchActivity preferenceSearchActivity, View view) {
        Intrinsics.checkNotNullParameter(preferenceSearchActivity, "this$0");
        preferenceSearchActivity.finish();
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$1(PreferenceSearchActivity preferenceSearchActivity, View view) {
        Intrinsics.checkNotNullParameter(preferenceSearchActivity, "this$0");
        PreferenceSearchModel preferenceSearchModel = preferenceSearchActivity.viewmodel;
        PreferencesSearchActivityBinding preferencesSearchActivityBinding = null;
        if (preferenceSearchModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
            preferenceSearchModel = null;
        }
        PreferencesSearchActivityBinding preferencesSearchActivityBinding2 = preferenceSearchActivity.binding;
        if (preferencesSearchActivityBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            preferencesSearchActivityBinding = preferencesSearchActivityBinding2;
        }
        preferenceSearchModel.switchTranslations(preferencesSearchActivityBinding.searchText.getText().toString());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        PreferencesSearchActivityBinding preferencesSearchActivityBinding = this.binding;
        if (preferencesSearchActivityBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            preferencesSearchActivityBinding = null;
        }
        preferencesSearchActivityBinding.searchText.requestFocus();
        super.onResume();
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        String obj;
        if (charSequence != null && (obj = charSequence.toString()) != null) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String lowerCase = obj.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (lowerCase != null) {
                PreferenceSearchModel preferenceSearchModel = this.viewmodel;
                PreferenceItemAdapter preferenceItemAdapter = null;
                if (preferenceSearchModel == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("viewmodel");
                    preferenceSearchModel = null;
                }
                preferenceSearchModel.filter(lowerCase);
                PreferenceItemAdapter preferenceItemAdapter2 = this.adapter;
                if (preferenceItemAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    preferenceItemAdapter = preferenceItemAdapter2;
                }
                preferenceItemAdapter.setQuery(lowerCase);
            }
        }
    }

    public void onClick(PreferenceItem preferenceItem) {
        Intrinsics.checkNotNullParameter(preferenceItem, "item");
        Intent intent = new Intent(ACTION_RESULT);
        intent.putExtra(PreferencesActivityKt.EXTRA_PREF_END_POINT, preferenceItem);
        Unit unit = Unit.INSTANCE;
        setResult(-1, intent);
        finish();
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lorg/videolan/vlc/gui/preferences/search/PreferenceSearchActivity$Companion;", "", "()V", "ACTION_RESULT", "", "getACTION_RESULT", "()Ljava/lang/String;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PreferenceSearchActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getACTION_RESULT() {
            return PreferenceSearchActivity.ACTION_RESULT;
        }
    }
}
