package org.videolan.television.ui.details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.FragmentActivity;
import androidx.leanback.app.BackgroundManager;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.television.R;
import org.videolan.television.databinding.ActivityMediaListTvBinding;
import org.videolan.television.ui.TvUtil;
import org.videolan.television.ui.TvUtilKt;
import org.videolan.television.ui.browser.BaseTvActivity;
import org.videolan.television.ui.dialogs.ConfirmationTvActivity;
import org.videolan.vlc.gui.dialogs.SavePlaylistDialog;
import org.videolan.vlc.gui.helpers.UiTools;
import org.videolan.vlc.interfaces.ITVEventsHandler;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.viewmodels.mobile.PlaylistViewModel;

@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 ,2\u00020\u00012\u00020\u0002:\u0001,B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0018\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0016J\u0018\u0010\u001e\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0016J\u0018\u0010\u001f\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0016J\u0018\u0010 \u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0016J\u0018\u0010!\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0016J\u0018\u0010\"\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0016J\u0018\u0010#\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0016H\u0016J\u0012\u0010$\u001a\u00020\u00142\b\u0010%\u001a\u0004\u0018\u00010&H\u0014J\u0010\u0010'\u001a\u00020\u00142\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010(\u001a\u00020\u0014H\u0014J\u0010\u0010)\u001a\u00020\u00142\u0006\u0010*\u001a\u00020&H\u0014J\b\u0010+\u001a\u00020\u0014H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X.¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u000fX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X.¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lorg/videolan/television/ui/details/MediaListActivity;", "Lorg/videolan/television/ui/browser/BaseTvActivity;", "Lorg/videolan/vlc/interfaces/ITVEventsHandler;", "()V", "adapter", "Lorg/videolan/television/ui/details/MediaListAdapter;", "backgroundManager", "Landroidx/leanback/app/BackgroundManager;", "binding", "Lorg/videolan/television/databinding/ActivityMediaListTvBinding;", "getBinding$television_release", "()Lorg/videolan/television/databinding/ActivityMediaListTvBinding;", "setBinding$television_release", "(Lorg/videolan/television/databinding/ActivityMediaListTvBinding;)V", "item", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "lateSelectedItem", "viewModel", "Lorg/videolan/vlc/viewmodels/mobile/PlaylistViewModel;", "onActivityResult", "", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onClickAddToPlaylist", "v", "Landroid/view/View;", "position", "onClickAppend", "onClickMoveDown", "onClickMoveUp", "onClickPlay", "onClickPlayNext", "onClickRemove", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onFocusChanged", "onResume", "onSaveInstanceState", "outState", "refresh", "Companion", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaListActivity.kt */
public final class MediaListActivity extends BaseTvActivity implements ITVEventsHandler {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int REQUEST_DELETE_PLAYLIST = 1;
    /* access modifiers changed from: private */
    public MediaListAdapter adapter;
    private BackgroundManager backgroundManager;
    public ActivityMediaListTvBinding binding;
    private MediaLibraryItem item;
    private MediaLibraryItem lateSelectedItem;
    private PlaylistViewModel viewModel;

    /* access modifiers changed from: protected */
    public void refresh() {
    }

    public final ActivityMediaListTvBinding getBinding$television_release() {
        ActivityMediaListTvBinding activityMediaListTvBinding = this.binding;
        if (activityMediaListTvBinding != null) {
            return activityMediaListTvBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    public final void setBinding$television_release(ActivityMediaListTvBinding activityMediaListTvBinding) {
        Intrinsics.checkNotNullParameter(activityMediaListTvBinding, "<set-?>");
        this.binding = activityMediaListTvBinding;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0033, code lost:
        if (r11 == null) goto L_0x0035;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCreate(android.os.Bundle r11) {
        /*
            r10 = this;
            super.onCreate(r11)
            r0 = r10
            android.app.Activity r0 = (android.app.Activity) r0
            int r1 = org.videolan.television.R.layout.activity_media_list_tv
            androidx.databinding.ViewDataBinding r1 = androidx.databinding.DataBindingUtil.setContentView(r0, r1)
            java.lang.String r2 = "setContentView(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            org.videolan.television.databinding.ActivityMediaListTvBinding r1 = (org.videolan.television.databinding.ActivityMediaListTvBinding) r1
            r10.setBinding$television_release(r1)
            r1 = 33
            java.lang.String r2 = "item"
            r3 = 0
            if (r11 == 0) goto L_0x0035
            int r4 = android.os.Build.VERSION.SDK_INT
            if (r4 < r1) goto L_0x002a
            java.lang.Class<android.os.Parcelable> r4 = android.os.Parcelable.class
            java.lang.Object r11 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.os.Bundle) r11, (java.lang.String) r2, (java.lang.Class) r4)
            android.os.Parcelable r11 = (android.os.Parcelable) r11
            goto L_0x0033
        L_0x002a:
            android.os.Parcelable r11 = r11.getParcelable(r2)
            boolean r4 = r11 instanceof android.os.Parcelable
            if (r4 != 0) goto L_0x0033
            r11 = r3
        L_0x0033:
            if (r11 != 0) goto L_0x0054
        L_0x0035:
            android.content.Intent r11 = r10.getIntent()
            java.lang.String r4 = "getIntent(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r4)
            int r4 = android.os.Build.VERSION.SDK_INT
            if (r4 < r1) goto L_0x004b
            java.lang.Class<android.os.Parcelable> r1 = android.os.Parcelable.class
            java.lang.Object r11 = org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0.m((android.content.Intent) r11, (java.lang.String) r2, (java.lang.Class) r1)
            android.os.Parcelable r11 = (android.os.Parcelable) r11
            goto L_0x0054
        L_0x004b:
            android.os.Parcelable r11 = r11.getParcelableExtra(r2)
            boolean r1 = r11 instanceof android.os.Parcelable
            if (r1 != 0) goto L_0x0054
            r11 = r3
        L_0x0054:
            java.lang.String r1 = "null cannot be cast to non-null type org.videolan.medialibrary.media.MediaLibraryItem"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11, r1)
            org.videolan.medialibrary.media.MediaLibraryItem r11 = (org.videolan.medialibrary.media.MediaLibraryItem) r11
            r10.item = r11
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            org.videolan.medialibrary.media.MediaLibraryItem r1 = r10.item
            if (r1 != 0) goto L_0x0069
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r3
        L_0x0069:
            r11.setItem(r1)
            androidx.leanback.app.BackgroundManager r11 = androidx.leanback.app.BackgroundManager.getInstance(r0)
            java.lang.String r0 = "getInstance(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r0)
            r10.backgroundManager = r11
            java.lang.String r0 = "backgroundManager"
            if (r11 != 0) goto L_0x007f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r11 = r3
        L_0x007f:
            boolean r11 = r11.isAttached()
            if (r11 != 0) goto L_0x0098
            androidx.leanback.app.BackgroundManager r11 = r10.backgroundManager
            if (r11 != 0) goto L_0x008d
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r11 = r3
        L_0x008d:
            org.videolan.television.databinding.ActivityMediaListTvBinding r0 = r10.getBinding$television_release()
            android.view.View r0 = r0.getRoot()
            r11.attachToView(r0)
        L_0x0098:
            androidx.recyclerview.widget.LinearLayoutManager r11 = new androidx.recyclerview.widget.LinearLayoutManager
            r0 = r10
            android.content.Context r0 = (android.content.Context) r0
            r11.<init>(r0)
            androidx.recyclerview.widget.DividerItemDecoration r1 = new androidx.recyclerview.widget.DividerItemDecoration
            int r4 = r11.getOrientation()
            r1.<init>(r0, r4)
            int r4 = org.videolan.television.R.drawable.divider_tv_card_content_dark
            android.graphics.drawable.Drawable r0 = androidx.core.content.ContextCompat.getDrawable(r0, r4)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r1.setDrawable(r0)
            org.videolan.television.ui.details.MediaListAdapter r0 = new org.videolan.television.ui.details.MediaListAdapter
            org.videolan.medialibrary.media.MediaLibraryItem r4 = r10.item
            if (r4 != 0) goto L_0x00bf
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r4 = r3
        L_0x00bf:
            int r4 = r4.getItemType()
            r5 = r10
            org.videolan.vlc.interfaces.ITVEventsHandler r5 = (org.videolan.vlc.interfaces.ITVEventsHandler) r5
            r0.<init>(r4, r5)
            r10.adapter = r0
            org.videolan.television.databinding.ActivityMediaListTvBinding r0 = r10.getBinding$television_release()
            org.videolan.television.ui.FocusableRecyclerView r0 = r0.mediaList
            androidx.recyclerview.widget.RecyclerView$LayoutManager r11 = (androidx.recyclerview.widget.RecyclerView.LayoutManager) r11
            r0.setLayoutManager(r11)
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            org.videolan.television.ui.FocusableRecyclerView r11 = r11.mediaList
            org.videolan.television.ui.details.MediaListAdapter r0 = r10.adapter
            java.lang.String r4 = "adapter"
            if (r0 != 0) goto L_0x00e6
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r0 = r3
        L_0x00e6:
            androidx.recyclerview.widget.RecyclerView$Adapter r0 = (androidx.recyclerview.widget.RecyclerView.Adapter) r0
            r11.setAdapter(r0)
            org.videolan.television.ui.details.MediaListAdapter r11 = r10.adapter
            if (r11 != 0) goto L_0x00f3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            r11 = r3
        L_0x00f3:
            org.videolan.medialibrary.media.MediaLibraryItem r0 = r10.item
            if (r0 != 0) goto L_0x00fb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r3
        L_0x00fb:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r0 = r0.getTracks()
            java.lang.String r4 = "getTracks(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            java.lang.Object[] r0 = (java.lang.Object[]) r0
            java.util.List r0 = kotlin.collections.ArraysKt.toList((T[]) r0)
            r11.update(r0)
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            org.videolan.television.ui.FocusableRecyclerView r11 = r11.mediaList
            androidx.recyclerview.widget.RecyclerView$ItemDecoration r1 = (androidx.recyclerview.widget.RecyclerView.ItemDecoration) r1
            r11.addItemDecoration(r1)
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            org.videolan.medialibrary.media.MediaLibraryItem r0 = r10.item
            if (r0 != 0) goto L_0x0124
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r3
        L_0x0124:
            java.lang.String r0 = r0.getTitle()
            r11.setTitle(r0)
            org.videolan.medialibrary.media.MediaLibraryItem r11 = r10.item
            if (r11 != 0) goto L_0x0133
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r11 = r3
        L_0x0133:
            java.lang.String r11 = r11.getDescription()
            r0 = 8
            if (r11 == 0) goto L_0x0157
            java.lang.CharSequence r11 = (java.lang.CharSequence) r11
            int r11 = r11.length()
            if (r11 <= 0) goto L_0x0157
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            org.videolan.medialibrary.media.MediaLibraryItem r1 = r10.item
            if (r1 != 0) goto L_0x014f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r3
        L_0x014f:
            java.lang.String r1 = r1.getDescription()
            r11.setSubtitle(r1)
            goto L_0x0160
        L_0x0157:
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            android.widget.TextView r11 = r11.albumSubtitle
            r11.setVisibility(r0)
        L_0x0160:
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            org.videolan.medialibrary.media.MediaLibraryItem r1 = r10.item
            if (r1 != 0) goto L_0x016c
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r1 = r3
        L_0x016c:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r1.getTracks()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            java.lang.Object[] r1 = (java.lang.Object[]) r1
            int r4 = r1.length
            r5 = 0
            r7 = 0
        L_0x0179:
            if (r7 >= r4) goto L_0x0187
            r8 = r1[r7]
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r8
            long r8 = r8.getLength()
            long r5 = r5 + r8
            int r7 = r7 + 1
            goto L_0x0179
        L_0x0187:
            java.lang.String r1 = org.videolan.medialibrary.Tools.millisToString(r5)
            r11.setTotalTime(r1)
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            androidx.appcompat.widget.AppCompatImageButton r11 = r11.play
            r11.requestFocus()
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            androidx.appcompat.widget.AppCompatImageButton r11 = r11.play
            org.videolan.television.ui.details.MediaListActivity$$ExternalSyntheticLambda0 r1 = new org.videolan.television.ui.details.MediaListActivity$$ExternalSyntheticLambda0
            r1.<init>(r10)
            r11.setOnClickListener(r1)
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            androidx.appcompat.widget.AppCompatImageButton r11 = r11.append
            org.videolan.television.ui.details.MediaListActivity$$ExternalSyntheticLambda1 r1 = new org.videolan.television.ui.details.MediaListActivity$$ExternalSyntheticLambda1
            r1.<init>(r10)
            r11.setOnClickListener(r1)
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            androidx.appcompat.widget.AppCompatImageButton r11 = r11.insertNext
            org.videolan.television.ui.details.MediaListActivity$$ExternalSyntheticLambda2 r1 = new org.videolan.television.ui.details.MediaListActivity$$ExternalSyntheticLambda2
            r1.<init>(r10)
            r11.setOnClickListener(r1)
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            androidx.appcompat.widget.AppCompatImageButton r11 = r11.addPlaylist
            org.videolan.television.ui.details.MediaListActivity$$ExternalSyntheticLambda3 r1 = new org.videolan.television.ui.details.MediaListActivity$$ExternalSyntheticLambda3
            r1.<init>(r10)
            r11.setOnClickListener(r1)
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            androidx.appcompat.widget.AppCompatImageButton r11 = r11.delete
            org.videolan.television.ui.details.MediaListActivity$$ExternalSyntheticLambda4 r1 = new org.videolan.television.ui.details.MediaListActivity$$ExternalSyntheticLambda4
            r1.<init>(r10)
            r11.setOnClickListener(r1)
            org.videolan.medialibrary.media.MediaLibraryItem r11 = r10.item
            if (r11 != 0) goto L_0x01e5
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r11 = r3
        L_0x01e5:
            int r11 = r11.getItemType()
            r1 = 16
            if (r11 != r1) goto L_0x0221
            org.videolan.medialibrary.media.MediaLibraryItem r11 = r10.item
            if (r11 != 0) goto L_0x01f5
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r11 = r3
        L_0x01f5:
            org.videolan.vlc.viewmodels.mobile.PlaylistViewModel r11 = org.videolan.television.ui.details.MediaListActivityKt.getViewModel(r10, r11)
            r10.viewModel = r11
            if (r11 != 0) goto L_0x0203
            java.lang.String r11 = "viewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r11)
            goto L_0x0204
        L_0x0203:
            r3 = r11
        L_0x0204:
            org.videolan.vlc.providers.medialibrary.TracksProvider r11 = r3.getTracksProvider()
            androidx.lifecycle.LiveData r11 = r11.getPagedList()
            r0 = r10
            androidx.lifecycle.LifecycleOwner r0 = (androidx.lifecycle.LifecycleOwner) r0
            org.videolan.television.ui.details.MediaListActivity$onCreate$7 r1 = new org.videolan.television.ui.details.MediaListActivity$onCreate$7
            r1.<init>(r10)
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            org.videolan.television.ui.details.MediaListActivityKt$sam$androidx_lifecycle_Observer$0 r2 = new org.videolan.television.ui.details.MediaListActivityKt$sam$androidx_lifecycle_Observer$0
            r2.<init>(r1)
            androidx.lifecycle.Observer r2 = (androidx.lifecycle.Observer) r2
            r11.observe(r0, r2)
            goto L_0x022a
        L_0x0221:
            org.videolan.television.databinding.ActivityMediaListTvBinding r11 = r10.getBinding$television_release()
            androidx.appcompat.widget.AppCompatImageButton r11 = r11.delete
            r11.setVisibility(r0)
        L_0x022a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.details.MediaListActivity.onCreate(android.os.Bundle):void");
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$1(MediaListActivity mediaListActivity, View view) {
        Intrinsics.checkNotNullParameter(mediaListActivity, "this$0");
        MediaLibraryItem mediaLibraryItem = mediaListActivity.item;
        MediaLibraryItem mediaLibraryItem2 = null;
        if (mediaLibraryItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            mediaLibraryItem = null;
        }
        if (mediaLibraryItem instanceof Playlist) {
            TvUtil tvUtil = TvUtil.INSTANCE;
            Activity activity = mediaListActivity;
            MediaLibraryItem mediaLibraryItem3 = mediaListActivity.item;
            if (mediaLibraryItem3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("item");
            } else {
                mediaLibraryItem2 = mediaLibraryItem3;
            }
            TvUtil.playPlaylist$default(tvUtil, activity, (Playlist) mediaLibraryItem2, 0, 4, (Object) null);
            return;
        }
        TvUtil tvUtil2 = TvUtil.INSTANCE;
        Activity activity2 = mediaListActivity;
        MediaLibraryItem mediaLibraryItem4 = mediaListActivity.item;
        if (mediaLibraryItem4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
        } else {
            mediaLibraryItem2 = mediaLibraryItem4;
        }
        MediaWrapper[] tracks = mediaLibraryItem2.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
        TvUtil.playMedia$default(tvUtil2, activity2, ArraysKt.toMutableList((T[]) (Object[]) tracks), 0, 4, (Object) null);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$2(MediaListActivity mediaListActivity, View view) {
        Intrinsics.checkNotNullParameter(mediaListActivity, "this$0");
        MediaUtils mediaUtils = MediaUtils.INSTANCE;
        Context context = mediaListActivity;
        MediaLibraryItem mediaLibraryItem = mediaListActivity.item;
        if (mediaLibraryItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            mediaLibraryItem = null;
        }
        MediaWrapper[] tracks = mediaLibraryItem.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
        mediaUtils.appendMedia(context, tracks);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$3(MediaListActivity mediaListActivity, View view) {
        Intrinsics.checkNotNullParameter(mediaListActivity, "this$0");
        MediaUtils mediaUtils = MediaUtils.INSTANCE;
        Context context = mediaListActivity;
        MediaLibraryItem mediaLibraryItem = mediaListActivity.item;
        if (mediaLibraryItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            mediaLibraryItem = null;
        }
        mediaUtils.insertNext(context, mediaLibraryItem.getTracks());
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$4(MediaListActivity mediaListActivity, View view) {
        Intrinsics.checkNotNullParameter(mediaListActivity, "this$0");
        UiTools uiTools = UiTools.INSTANCE;
        FragmentActivity fragmentActivity = mediaListActivity;
        MediaLibraryItem mediaLibraryItem = mediaListActivity.item;
        if (mediaLibraryItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            mediaLibraryItem = null;
        }
        MediaWrapper[] tracks = mediaLibraryItem.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
        uiTools.addToPlaylist(fragmentActivity, tracks, SavePlaylistDialog.KEY_NEW_TRACKS);
    }

    /* access modifiers changed from: private */
    public static final void onCreate$lambda$5(MediaListActivity mediaListActivity, View view) {
        Intrinsics.checkNotNullParameter(mediaListActivity, "this$0");
        if (!UiTools.INSTANCE.showPinIfNeeded(mediaListActivity)) {
            Intent intent = new Intent(mediaListActivity, ConfirmationTvActivity.class);
            intent.putExtra(ConfirmationTvActivity.CONFIRMATION_DIALOG_TITLE, mediaListActivity.getString(R.string.validation_delete_playlist));
            intent.putExtra(ConfirmationTvActivity.CONFIRMATION_DIALOG_TEXT, mediaListActivity.getString(R.string.validation_delete_playlist_text));
            mediaListActivity.startActivityForResult(intent, 1);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == 1) {
            PlaylistViewModel playlistViewModel = this.viewModel;
            if (playlistViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playlistViewModel = null;
            }
            MediaLibraryItem playlist = playlistViewModel.getPlaylist();
            Intrinsics.checkNotNull(playlist, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.Playlist");
            ((Playlist) playlist).delete();
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        CoroutineScope lifecycleScope = LifecycleOwnerKt.getLifecycleScope(this);
        Activity activity = this;
        BackgroundManager backgroundManager2 = this.backgroundManager;
        MediaLibraryItem mediaLibraryItem = null;
        if (backgroundManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("backgroundManager");
            backgroundManager2 = null;
        }
        MediaLibraryItem mediaLibraryItem2 = this.item;
        if (mediaLibraryItem2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
        } else {
            mediaLibraryItem = mediaLibraryItem2;
        }
        TvUtilKt.updateBackground(lifecycleScope, activity, backgroundManager2, mediaLibraryItem);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
        Intrinsics.checkNotNullParameter(bundle, "outState");
        super.onSaveInstanceState(bundle);
        MediaLibraryItem mediaLibraryItem = this.item;
        if (mediaLibraryItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            mediaLibraryItem = null;
        }
        bundle.putParcelable("item", mediaLibraryItem);
    }

    public void onClickPlay(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "v");
        MediaLibraryItem mediaLibraryItem = this.item;
        MediaLibraryItem mediaLibraryItem2 = null;
        if (mediaLibraryItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            mediaLibraryItem = null;
        }
        if (mediaLibraryItem instanceof Playlist) {
            TvUtil tvUtil = TvUtil.INSTANCE;
            Activity activity = this;
            MediaLibraryItem mediaLibraryItem3 = this.item;
            if (mediaLibraryItem3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("item");
            } else {
                mediaLibraryItem2 = mediaLibraryItem3;
            }
            tvUtil.playPlaylist(activity, (Playlist) mediaLibraryItem2, i);
            return;
        }
        TvUtil tvUtil2 = TvUtil.INSTANCE;
        Activity activity2 = this;
        MediaLibraryItem mediaLibraryItem4 = this.item;
        if (mediaLibraryItem4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
        } else {
            mediaLibraryItem2 = mediaLibraryItem4;
        }
        MediaWrapper[] tracks = mediaLibraryItem2.getTracks();
        Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
        tvUtil2.playMedia(activity2, ArraysKt.toList((T[]) (Object[]) tracks), i);
    }

    public void onClickPlayNext(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "v");
        MediaUtils mediaUtils = MediaUtils.INSTANCE;
        Context context = this;
        MediaLibraryItem mediaLibraryItem = this.item;
        if (mediaLibraryItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            mediaLibraryItem = null;
        }
        mediaUtils.insertNext(context, mediaLibraryItem.getTracks()[i]);
    }

    public void onClickAppend(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "v");
        MediaUtils mediaUtils = MediaUtils.INSTANCE;
        Context context = this;
        MediaLibraryItem mediaLibraryItem = this.item;
        if (mediaLibraryItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            mediaLibraryItem = null;
        }
        mediaUtils.appendMedia(context, mediaLibraryItem.getTracks()[i]);
    }

    public void onClickAddToPlaylist(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "v");
        UiTools uiTools = UiTools.INSTANCE;
        FragmentActivity fragmentActivity = this;
        MediaWrapper[] mediaWrapperArr = new MediaWrapper[1];
        MediaLibraryItem mediaLibraryItem = this.item;
        if (mediaLibraryItem == null) {
            Intrinsics.throwUninitializedPropertyAccessException("item");
            mediaLibraryItem = null;
        }
        MediaWrapper mediaWrapper = mediaLibraryItem.getTracks()[i];
        Intrinsics.checkNotNullExpressionValue(mediaWrapper, "get(...)");
        mediaWrapperArr[0] = mediaWrapper;
        uiTools.addToPlaylist(fragmentActivity, mediaWrapperArr, SavePlaylistDialog.KEY_NEW_TRACKS);
    }

    public void onClickMoveUp(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (!UiTools.INSTANCE.showPinIfNeeded(this)) {
            PlaylistViewModel playlistViewModel = this.viewModel;
            if (playlistViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playlistViewModel = null;
            }
            MediaLibraryItem playlist = playlistViewModel.getPlaylist();
            Intrinsics.checkNotNull(playlist, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.Playlist");
            ((Playlist) playlist).move(i, i - 1);
        }
    }

    public void onClickMoveDown(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (!UiTools.INSTANCE.showPinIfNeeded(this)) {
            PlaylistViewModel playlistViewModel = this.viewModel;
            if (playlistViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playlistViewModel = null;
            }
            MediaLibraryItem playlist = playlistViewModel.getPlaylist();
            Intrinsics.checkNotNull(playlist, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.Playlist");
            ((Playlist) playlist).move(i, i + 1);
        }
    }

    public void onClickRemove(View view, int i) {
        Intrinsics.checkNotNullParameter(view, "v");
        if (!UiTools.INSTANCE.showPinIfNeeded(this)) {
            PlaylistViewModel playlistViewModel = this.viewModel;
            if (playlistViewModel == null) {
                Intrinsics.throwUninitializedPropertyAccessException("viewModel");
                playlistViewModel = null;
            }
            MediaLibraryItem playlist = playlistViewModel.getPlaylist();
            Intrinsics.checkNotNull(playlist, "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.Playlist");
            ((Playlist) playlist).remove(i);
        }
    }

    public void onFocusChanged(MediaLibraryItem mediaLibraryItem) {
        Intrinsics.checkNotNullParameter(mediaLibraryItem, "item");
        if (!Intrinsics.areEqual((Object) mediaLibraryItem, (Object) this.lateSelectedItem)) {
            CoroutineScope lifecycleScope = LifecycleOwnerKt.getLifecycleScope(this);
            Activity activity = this;
            BackgroundManager backgroundManager2 = this.backgroundManager;
            if (backgroundManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("backgroundManager");
                backgroundManager2 = null;
            }
            TvUtilKt.updateBackground(lifecycleScope, activity, backgroundManager2, mediaLibraryItem);
        }
        this.lateSelectedItem = mediaLibraryItem;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lorg/videolan/television/ui/details/MediaListActivity$Companion;", "", "()V", "REQUEST_DELETE_PLAYLIST", "", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: MediaListActivity.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
