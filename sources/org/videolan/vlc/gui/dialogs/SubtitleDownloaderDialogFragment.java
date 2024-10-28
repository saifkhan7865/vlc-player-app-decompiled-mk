package org.videolan.vlc.gui.dialogs;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.os.BundleKt;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.vlc.databinding.SubtitleDownloaderDialogBinding;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.gui.view.LanguageSelector;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.viewmodels.SubtitlesModel;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 42\u00020\u0001:\u00014B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\b\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020#H\u0016J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020&H\u0016J\u0012\u0010'\u001a\u00020\u001b2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J$\u0010*\u001a\u00020!2\u0006\u0010+\u001a\u00020,2\b\u0010-\u001a\u0004\u0018\u00010.2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\b\u0010/\u001a\u00020\u001bH\u0016J\u0010\u00100\u001a\u00020\u001b2\u0006\u00101\u001a\u00020)H\u0016J\u001a\u00102\u001a\u00020\u001b2\u0006\u00103\u001a\u00020!2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0002R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX.¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0010@BX\u000e¢\u0006\b\n\u0000\"\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\rX.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X.¢\u0006\u0002\n\u0000¨\u00065"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SubtitleDownloaderDialogFragment;", "Lorg/videolan/vlc/gui/dialogs/VLCBottomSheetDialogFragment;", "()V", "binding", "Lorg/videolan/vlc/databinding/SubtitleDownloaderDialogBinding;", "downloadAdapter", "Lorg/videolan/vlc/gui/dialogs/SubtitlesAdapter;", "historyAdapter", "listEventActor", "Lkotlinx/coroutines/channels/SendChannel;", "Lorg/videolan/vlc/gui/dialogs/SubtitleEvent;", "getListEventActor$annotations", "names", "", "", "value", "Lorg/videolan/vlc/gui/dialogs/SubDownloadDialogState;", "state", "setState", "(Lorg/videolan/vlc/gui/dialogs/SubDownloadDialogState;)V", "toast", "Landroid/widget/Toast;", "uris", "Landroid/net/Uri;", "viewModel", "Lorg/videolan/vlc/viewmodels/SubtitlesModel;", "focusOnView", "", "scrollView", "Landroidx/core/widget/NestedScrollView;", "getDefaultState", "", "initialFocusedView", "Landroid/view/View;", "needToManageOrientation", "", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onResume", "onSaveInstanceState", "outState", "onViewCreated", "view", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: SubtitleDownloaderDialogFragment.kt */
public final class SubtitleDownloaderDialogFragment extends VLCBottomSheetDialogFragment {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public SubtitleDownloaderDialogBinding binding;
    /* access modifiers changed from: private */
    public SubtitlesAdapter downloadAdapter;
    /* access modifiers changed from: private */
    public SubtitlesAdapter historyAdapter;
    private final SendChannel<SubtitleEvent> listEventActor = ActorKt.actor$default(LifecycleOwnerKt.getLifecycleScope(this), (CoroutineContext) null, 0, (CoroutineStart) null, (Function1) null, new SubtitleDownloaderDialogFragment$listEventActor$1(this, (Continuation<? super SubtitleDownloaderDialogFragment$listEventActor$1>) null), 15, (Object) null);
    private List<String> names;
    private SubDownloadDialogState state = SubDownloadDialogState.Download;
    /* access modifiers changed from: private */
    public Toast toast;
    private List<? extends Uri> uris;
    /* access modifiers changed from: private */
    public SubtitlesModel viewModel;

    private static /* synthetic */ void getListEventActor$annotations() {
    }

    public int getDefaultState() {
        return 3;
    }

    public boolean needToManageOrientation() {
        return true;
    }

    public View initialFocusedView() {
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding = this.binding;
        if (subtitleDownloaderDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding = null;
        }
        TextView textView = subtitleDownloaderDialogBinding.movieName;
        Intrinsics.checkNotNullExpressionValue(textView, "movieName");
        return textView;
    }

    private final void setState(SubDownloadDialogState subDownloadDialogState) {
        this.state = subDownloadDialogState;
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding = this.binding;
        if (subtitleDownloaderDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding = null;
        }
        subtitleDownloaderDialogBinding.setState(subDownloadDialogState);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005e, code lost:
        r9 = r9.getStringArrayList("MEDIA_NAMES");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0020, code lost:
        if (r2 != null) goto L_0x0044;
     */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0064  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00d6  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00da  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x00e1  */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r9) {
        /*
            r8 = this;
            super.onCreate(r9)
            r0 = 33
            java.lang.String r1 = "MEDIA_PATHS"
            if (r9 == 0) goto L_0x0022
            int r2 = android.os.Build.VERSION.SDK_INT
            if (r2 < r0) goto L_0x0014
            java.lang.Class<android.net.Uri> r2 = android.net.Uri.class
            java.util.ArrayList r2 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.os.Bundle) r9, (java.lang.String) r1, (java.lang.Class) r2)
            goto L_0x0018
        L_0x0014:
            java.util.ArrayList r2 = r9.getParcelableArrayList(r1)
        L_0x0018:
            if (r2 == 0) goto L_0x0022
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.List r2 = kotlin.collections.CollectionsKt.toList(r2)
            if (r2 != 0) goto L_0x0044
        L_0x0022:
            android.os.Bundle r2 = r8.getArguments()
            if (r2 == 0) goto L_0x0040
            int r3 = android.os.Build.VERSION.SDK_INT
            if (r3 < r0) goto L_0x0033
            java.lang.Class<android.net.Uri> r0 = android.net.Uri.class
            java.util.ArrayList r0 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.os.Bundle) r2, (java.lang.String) r1, (java.lang.Class) r0)
            goto L_0x0037
        L_0x0033:
            java.util.ArrayList r0 = r2.getParcelableArrayList(r1)
        L_0x0037:
            if (r0 == 0) goto L_0x0040
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.List r2 = kotlin.collections.CollectionsKt.toList(r0)
            goto L_0x0044
        L_0x0040:
            java.util.List r2 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0044:
            r8.uris = r2
            java.lang.String r0 = "MEDIA_NAMES"
            if (r9 == 0) goto L_0x0058
            java.util.ArrayList r9 = r9.getStringArrayList(r0)
            if (r9 == 0) goto L_0x0058
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.List r9 = kotlin.collections.CollectionsKt.toList(r9)
            if (r9 != 0) goto L_0x006f
        L_0x0058:
            android.os.Bundle r9 = r8.getArguments()
            if (r9 == 0) goto L_0x006b
            java.util.ArrayList r9 = r9.getStringArrayList(r0)
            if (r9 == 0) goto L_0x006b
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.List r9 = kotlin.collections.CollectionsKt.toList(r9)
            goto L_0x006f
        L_0x006b:
            java.util.List r9 = kotlin.collections.CollectionsKt.emptyList()
        L_0x006f:
            r8.names = r9
            androidx.lifecycle.ViewModelProvider r9 = new androidx.lifecycle.ViewModelProvider
            androidx.fragment.app.FragmentActivity r0 = r8.requireActivity()
            java.lang.String r1 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            androidx.lifecycle.ViewModelStoreOwner r0 = (androidx.lifecycle.ViewModelStoreOwner) r0
            org.videolan.vlc.viewmodels.SubtitlesModel$Factory r1 = new org.videolan.vlc.viewmodels.SubtitlesModel$Factory
            android.content.Context r2 = r8.requireContext()
            java.lang.String r3 = "requireContext(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.util.List<? extends android.net.Uri> r3 = r8.uris
            java.lang.String r4 = "uris"
            r5 = 0
            if (r3 != 0) goto L_0x0094
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r3 = r5
        L_0x0094:
            r6 = 0
            java.lang.Object r3 = r3.get(r6)
            android.net.Uri r3 = (android.net.Uri) r3
            java.util.List<java.lang.String> r7 = r8.names
            if (r7 != 0) goto L_0x00a5
            java.lang.String r7 = "names"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r7)
            r7 = r5
        L_0x00a5:
            java.lang.Object r7 = r7.get(r6)
            java.lang.String r7 = (java.lang.String) r7
            r1.<init>(r2, r3, r7)
            androidx.lifecycle.ViewModelProvider$Factory r1 = (androidx.lifecycle.ViewModelProvider.Factory) r1
            r9.<init>((androidx.lifecycle.ViewModelStoreOwner) r0, (androidx.lifecycle.ViewModelProvider.Factory) r1)
            java.util.List<? extends android.net.Uri> r0 = r8.uris
            if (r0 != 0) goto L_0x00bb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r0 = r5
        L_0x00bb:
            java.lang.Object r0 = r0.get(r6)
            android.net.Uri r0 = (android.net.Uri) r0
            java.lang.String r0 = r0.getPath()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.lang.Class<org.videolan.vlc.viewmodels.SubtitlesModel> r1 = org.videolan.vlc.viewmodels.SubtitlesModel.class
            androidx.lifecycle.ViewModel r9 = r9.get(r0, r1)
            org.videolan.vlc.viewmodels.SubtitlesModel r9 = (org.videolan.vlc.viewmodels.SubtitlesModel) r9
            r8.viewModel = r9
            java.util.List<? extends android.net.Uri> r9 = r8.uris
            if (r9 != 0) goto L_0x00da
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L_0x00db
        L_0x00da:
            r5 = r9
        L_0x00db:
            boolean r9 = r5.isEmpty()
            if (r9 == 0) goto L_0x00e4
            r8.dismiss()
        L_0x00e4:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.dialogs.SubtitleDownloaderDialogFragment.onCreate(android.os.Bundle):void");
    }

    public void onResume() {
        SubtitlesModel subtitlesModel = this.viewModel;
        SubtitlesModel subtitlesModel2 = null;
        if (subtitlesModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            subtitlesModel = null;
        }
        if (Intrinsics.areEqual((Object) subtitlesModel.isApiLoading().getValue(), (Object) false)) {
            SubtitlesModel subtitlesModel3 = this.viewModel;
            if (subtitlesModel3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            } else {
                subtitlesModel2 = subtitlesModel3;
            }
            subtitlesModel2.onRefresh();
        }
        super.onResume();
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        CharSequence charSequence;
        Intrinsics.checkNotNullParameter(layoutInflater, "inflater");
        super.onCreateView(layoutInflater, viewGroup, bundle);
        SubtitleDownloaderDialogBinding inflate = SubtitleDownloaderDialogBinding.inflate(layoutInflater, viewGroup, false);
        Intrinsics.checkNotNullExpressionValue(inflate, "inflate(...)");
        this.binding = inflate;
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding = null;
        if (inflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            inflate = null;
        }
        SubtitlesModel subtitlesModel = this.viewModel;
        if (subtitlesModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            subtitlesModel = null;
        }
        inflate.setViewmodel(subtitlesModel);
        List<? extends Uri> list = this.uris;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uris");
            list = null;
        }
        if (list.size() < 2) {
            SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding2 = this.binding;
            if (subtitleDownloaderDialogBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                subtitleDownloaderDialogBinding2 = null;
            }
            subtitleDownloaderDialogBinding2.subDownloadNext.setVisibility(8);
        }
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding3 = this.binding;
        if (subtitleDownloaderDialogBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding3 = null;
        }
        subtitleDownloaderDialogBinding3.subDownloadNext.setOnClickListener(new SubtitleDownloaderDialogFragment$$ExternalSyntheticLambda0(this));
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding4 = this.binding;
        if (subtitleDownloaderDialogBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding4 = null;
        }
        TextView textView = subtitleDownloaderDialogBinding4.movieName;
        List<String> list2 = this.names;
        if (list2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("names");
            list2 = null;
        }
        String str = (String) CollectionsKt.firstOrNull(list2);
        if (str != null) {
            charSequence = str;
        } else {
            List<? extends Uri> list3 = this.uris;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uris");
                list3 = null;
            }
            charSequence = ((Uri) list3.get(0)).getLastPathSegment();
        }
        textView.setText(charSequence);
        setState(SubDownloadDialogState.Download);
        this.downloadAdapter = new SubtitlesAdapter(this.listEventActor);
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding5 = this.binding;
        if (subtitleDownloaderDialogBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding5 = null;
        }
        RecyclerView recyclerView = subtitleDownloaderDialogBinding5.subsDownloadList;
        SubtitlesAdapter subtitlesAdapter = this.downloadAdapter;
        if (subtitlesAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadAdapter");
            subtitlesAdapter = null;
        }
        recyclerView.setAdapter(subtitlesAdapter);
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding6 = this.binding;
        if (subtitleDownloaderDialogBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding6 = null;
        }
        subtitleDownloaderDialogBinding6.subsDownloadList.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.historyAdapter = new SubtitlesAdapter(this.listEventActor);
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding7 = this.binding;
        if (subtitleDownloaderDialogBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding7 = null;
        }
        RecyclerView recyclerView2 = subtitleDownloaderDialogBinding7.subsHistoryList;
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "subsHistoryList");
        SubtitlesAdapter subtitlesAdapter2 = this.historyAdapter;
        if (subtitlesAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            subtitlesAdapter2 = null;
        }
        recyclerView2.setAdapter(subtitlesAdapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(getActivity()));
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding8 = this.binding;
        if (subtitleDownloaderDialogBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding8 = null;
        }
        subtitleDownloaderDialogBinding8.searchButton.setOnClickListener(new SubtitleDownloaderDialogFragment$$ExternalSyntheticLambda1(this));
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding9 = this.binding;
        if (subtitleDownloaderDialogBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding9 = null;
        }
        subtitleDownloaderDialogBinding9.cancelButton.setOnClickListener(new SubtitleDownloaderDialogFragment$$ExternalSyntheticLambda2(this));
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding10 = this.binding;
        if (subtitleDownloaderDialogBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding10 = null;
        }
        subtitleDownloaderDialogBinding10.subDownloadSearch.setOnClickListener(new SubtitleDownloaderDialogFragment$$ExternalSyntheticLambda3(this));
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding11 = this.binding;
        if (subtitleDownloaderDialogBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding11 = null;
        }
        subtitleDownloaderDialogBinding11.subDownloadHistory.setOnClickListener(new SubtitleDownloaderDialogFragment$$ExternalSyntheticLambda4(this));
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding12 = this.binding;
        if (subtitleDownloaderDialogBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding12 = null;
        }
        subtitleDownloaderDialogBinding12.languageListSpinner.setOnItemsSelectListener(new SubtitleDownloaderDialogFragment$onCreateView$6(this));
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding13 = this.binding;
        if (subtitleDownloaderDialogBinding13 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding13 = null;
        }
        subtitleDownloaderDialogBinding13.retryButton.setOnClickListener(new SubtitleDownloaderDialogFragment$$ExternalSyntheticLambda5(this));
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding14 = this.binding;
        if (subtitleDownloaderDialogBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding14 = null;
        }
        LanguageSelector languageSelector = subtitleDownloaderDialogBinding14.languageListSpinner;
        SubtitlesModel subtitlesModel2 = this.viewModel;
        if (subtitlesModel2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            subtitlesModel2 = null;
        }
        Iterable<String> lastUsedLanguage = subtitlesModel2.getLastUsedLanguage();
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(lastUsedLanguage, 10));
        for (String str2 : lastUsedLanguage) {
            SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding15 = this.binding;
            if (subtitleDownloaderDialogBinding15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                subtitleDownloaderDialogBinding15 = null;
            }
            arrayList.add(Integer.valueOf(ArraysKt.indexOf((T[]) subtitleDownloaderDialogBinding15.languageListSpinner.getAllValuesOfLanguages(), str2)));
        }
        languageSelector.setSelection((List) arrayList);
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding16 = this.binding;
        if (subtitleDownloaderDialogBinding16 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding16 = null;
        }
        subtitleDownloaderDialogBinding16.episode.setOnEditorActionListener(new SubtitleDownloaderDialogFragment$$ExternalSyntheticLambda6(this));
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding17 = this.binding;
        if (subtitleDownloaderDialogBinding17 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            subtitleDownloaderDialogBinding = subtitleDownloaderDialogBinding17;
        }
        View root = subtitleDownloaderDialogBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "getRoot(...)");
        return root;
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$0(SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(subtitleDownloaderDialogFragment, "this$0");
        List<? extends Uri> list = subtitleDownloaderDialogFragment.uris;
        List<String> list2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uris");
            list = null;
        }
        if (list.size() > 1) {
            MediaUtils mediaUtils = MediaUtils.INSTANCE;
            FragmentActivity requireActivity = subtitleDownloaderDialogFragment.requireActivity();
            Intrinsics.checkNotNullExpressionValue(requireActivity, "requireActivity(...)");
            List<? extends Uri> list3 = subtitleDownloaderDialogFragment.uris;
            if (list3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uris");
                list3 = null;
            }
            List<? extends Uri> list4 = subtitleDownloaderDialogFragment.uris;
            if (list4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("uris");
                list4 = null;
            }
            List<T> takeLast = CollectionsKt.takeLast(list3, list4.size() - 1);
            List<String> list5 = subtitleDownloaderDialogFragment.names;
            if (list5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("names");
                list5 = null;
            }
            List<String> list6 = subtitleDownloaderDialogFragment.names;
            if (list6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("names");
            } else {
                list2 = list6;
            }
            mediaUtils.showSubtitleDownloaderDialogFragment(requireActivity, takeLast, CollectionsKt.takeLast(list5, list2.size() - 1));
        }
        subtitleDownloaderDialogFragment.dismiss();
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$1(SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(subtitleDownloaderDialogFragment, "this$0");
        UiTools.INSTANCE.setKeyboardVisibility(view, false);
        SubtitlesModel subtitlesModel = subtitleDownloaderDialogFragment.viewModel;
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding = null;
        if (subtitlesModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            subtitlesModel = null;
        }
        subtitlesModel.search(false);
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding2 = subtitleDownloaderDialogFragment.binding;
        if (subtitleDownloaderDialogBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            subtitleDownloaderDialogBinding = subtitleDownloaderDialogBinding2;
        }
        NestedScrollView nestedScrollView = subtitleDownloaderDialogBinding.scrollView;
        Intrinsics.checkNotNullExpressionValue(nestedScrollView, "scrollView");
        subtitleDownloaderDialogFragment.focusOnView(nestedScrollView);
        subtitleDownloaderDialogFragment.setState(SubDownloadDialogState.Download);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$2(SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(subtitleDownloaderDialogFragment, "this$0");
        subtitleDownloaderDialogFragment.setState(SubDownloadDialogState.Download);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$3(SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(subtitleDownloaderDialogFragment, "this$0");
        UiTools uiTools = UiTools.INSTANCE;
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding = subtitleDownloaderDialogFragment.binding;
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding2 = null;
        if (subtitleDownloaderDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding = null;
        }
        uiTools.setKeyboardVisibility(subtitleDownloaderDialogBinding.name, true);
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding3 = subtitleDownloaderDialogFragment.binding;
        if (subtitleDownloaderDialogBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            subtitleDownloaderDialogBinding2 = subtitleDownloaderDialogBinding3;
        }
        subtitleDownloaderDialogBinding2.name.requestFocus();
        subtitleDownloaderDialogFragment.setState(SubDownloadDialogState.Search);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$4(SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(subtitleDownloaderDialogFragment, "this$0");
        subtitleDownloaderDialogFragment.setState(subtitleDownloaderDialogFragment.state == SubDownloadDialogState.History ? SubDownloadDialogState.Download : SubDownloadDialogState.History);
    }

    /* access modifiers changed from: private */
    public static final void onCreateView$lambda$5(SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment, View view) {
        Intrinsics.checkNotNullParameter(subtitleDownloaderDialogFragment, "this$0");
        SubtitlesModel subtitlesModel = subtitleDownloaderDialogFragment.viewModel;
        if (subtitlesModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            subtitlesModel = null;
        }
        subtitlesModel.onRefresh();
    }

    /* access modifiers changed from: private */
    public static final boolean onCreateView$lambda$7(SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment, TextView textView, int i, KeyEvent keyEvent) {
        Intrinsics.checkNotNullParameter(subtitleDownloaderDialogFragment, "this$0");
        if (i != 6 && keyEvent.getKeyCode() != 66) {
            return false;
        }
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding = subtitleDownloaderDialogFragment.binding;
        if (subtitleDownloaderDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding = null;
        }
        subtitleDownloaderDialogBinding.searchButton.callOnClick();
        return true;
    }

    public void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, bundle);
        SubtitlesModel subtitlesModel = this.viewModel;
        SubtitlesModel subtitlesModel2 = null;
        if (subtitlesModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            subtitlesModel = null;
        }
        subtitlesModel.getResult().observe(getViewLifecycleOwner(), new SubtitleDownloaderDialogFragmentKt$sam$androidx_lifecycle_Observer$0(new SubtitleDownloaderDialogFragment$onViewCreated$1(this)));
        SubtitlesModel subtitlesModel3 = this.viewModel;
        if (subtitlesModel3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
            subtitlesModel3 = null;
        }
        subtitlesModel3.isApiLoading().observe(getViewLifecycleOwner(), new SubtitleDownloaderDialogFragmentKt$sam$androidx_lifecycle_Observer$0(new SubtitleDownloaderDialogFragment$onViewCreated$2(this)));
        SubtitlesModel subtitlesModel4 = this.viewModel;
        if (subtitlesModel4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("viewModel");
        } else {
            subtitlesModel2 = subtitlesModel4;
        }
        subtitlesModel2.getHistory().observe(this, new SubtitleDownloaderDialogFragmentKt$sam$androidx_lifecycle_Observer$0(new SubtitleDownloaderDialogFragment$onViewCreated$3(this)));
    }

    public void onConfigurationChanged(Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "newConfig");
        super.onConfigurationChanged(configuration);
        SubtitleDownloaderDialogBinding subtitleDownloaderDialogBinding = this.binding;
        if (subtitleDownloaderDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            subtitleDownloaderDialogBinding = null;
        }
        NestedScrollView nestedScrollView = subtitleDownloaderDialogBinding.scrollView;
        Intrinsics.checkNotNullExpressionValue(nestedScrollView, "scrollView");
        focusOnView(nestedScrollView);
    }

    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        List<? extends Uri> list = this.uris;
        List<String> list2 = null;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("uris");
            list = null;
        }
        bundle.putParcelableArrayList("MEDIA_PATHS", new ArrayList(list));
        List<String> list3 = this.names;
        if (list3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("names");
        } else {
            list2 = list3;
        }
        bundle.putStringArrayList("MEDIA_NAMES", new ArrayList(list2));
    }

    /* access modifiers changed from: private */
    public final void focusOnView(NestedScrollView nestedScrollView) {
        nestedScrollView.smoothScrollTo(0, 0);
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006¨\u0006\n"}, d2 = {"Lorg/videolan/vlc/gui/dialogs/SubtitleDownloaderDialogFragment$Companion;", "", "()V", "newInstance", "Lorg/videolan/vlc/gui/dialogs/SubtitleDownloaderDialogFragment;", "mediaUris", "", "Landroid/net/Uri;", "mediaTitles", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: SubtitleDownloaderDialogFragment.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SubtitleDownloaderDialogFragment newInstance(List<? extends Uri> list, List<String> list2) {
            Intrinsics.checkNotNullParameter(list, "mediaUris");
            Intrinsics.checkNotNullParameter(list2, "mediaTitles");
            SubtitleDownloaderDialogFragment subtitleDownloaderDialogFragment = new SubtitleDownloaderDialogFragment();
            subtitleDownloaderDialogFragment.setArguments(BundleKt.bundleOf(TuplesKt.to("MEDIA_PATHS", new ArrayList(list)), TuplesKt.to("MEDIA_NAMES", list2)));
            return subtitleDownloaderDialogFragment;
        }
    }
}
