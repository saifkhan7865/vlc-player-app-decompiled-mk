package org.videolan.vlc.gui.browser;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.databinding.ViewDataBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.Storage;
import org.videolan.resources.AndroidDevices;
import org.videolan.vlc.R;
import org.videolan.vlc.gui.browser.BaseBrowserAdapter;
import org.videolan.vlc.gui.helpers.MedialibraryUtils;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\bH\u0016J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\bH\u0002J \u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\"\u0010 \u001a\u00020\u00132\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"R\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J0\u0010 \u001a\u00020\u00132\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"R\u00020\u00012\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u000fH\u0016J\u001a\u0010&\u001a\u00020\u00132\u0010\u0010!\u001a\f\u0012\u0004\u0012\u00020#0\"R\u00020\u0001H\u0016J\u0016\u0010'\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)H@¢\u0006\u0002\u0010*J\u000e\u0010+\u001a\u00020\u00132\u0006\u0010(\u001a\u00020)R \u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X.¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\b0\u000fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lorg/videolan/vlc/gui/browser/StorageBrowserAdapter;", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter;", "browserContainer", "Lorg/videolan/vlc/gui/browser/BrowserContainer;", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Lorg/videolan/vlc/gui/browser/BrowserContainer;)V", "bannedFolders", "", "", "getBannedFolders", "()Ljava/util/List;", "setBannedFolders", "(Ljava/util/List;)V", "customDirsLocation", "mediaDirsLocation", "", "updateJob", "Lkotlinx/coroutines/Job;", "checkBoxAction", "", "v", "Landroid/view/View;", "mrl", "hasDiscoveredChildren", "", "path", "itemFocusChanged", "position", "", "hasFocus", "bindingContainer", "Lorg/videolan/vlc/gui/browser/BrowserItemBindingContainer;", "onBindViewHolder", "holder", "Lorg/videolan/vlc/gui/browser/BaseBrowserAdapter$ViewHolder;", "Landroidx/databinding/ViewDataBinding;", "payloads", "", "onViewRecycled", "updateListState", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateMediaDirs", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: StorageBrowserAdapter.kt */
public final class StorageBrowserAdapter extends BaseBrowserAdapter {
    private List<String> bannedFolders = CollectionsKt.emptyList();
    /* access modifiers changed from: private */
    public List<String> customDirsLocation;
    /* access modifiers changed from: private */
    public List<String> mediaDirsLocation = new ArrayList();
    /* access modifiers changed from: private */
    public Job updateJob;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public StorageBrowserAdapter(BrowserContainer<MediaLibraryItem> browserContainer) {
        super(browserContainer, 0, false, false, 14, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(browserContainer, "browserContainer");
        updateMediaDirs(browserContainer.containerActivity());
    }

    public final List<String> getBannedFolders() {
        return this.bannedFolders;
    }

    public final void setBannedFolders(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.bannedFolders = list;
    }

    public void onBindViewHolder(BaseBrowserAdapter.ViewHolder<ViewDataBinding> viewHolder, int i) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        BaseBrowserAdapter.MediaViewHolder mediaViewHolder = (BaseBrowserAdapter.MediaViewHolder) viewHolder;
        mediaViewHolder.setJob(BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new StorageBrowserAdapter$onBindViewHolder$1(this, i, mediaViewHolder, (Continuation<? super StorageBrowserAdapter$onBindViewHolder$1>) null), 3, (Object) null));
    }

    public void onBindViewHolder(BaseBrowserAdapter.ViewHolder<ViewDataBinding> viewHolder, int i, List<Object> list) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        Intrinsics.checkNotNullParameter(list, "payloads");
        if (!(!list.isEmpty()) || !(list.get(0) instanceof CharSequence)) {
            super.onBindViewHolder(viewHolder, i, list);
            return;
        }
        MedialibraryUtils medialibraryUtils = MedialibraryUtils.INSTANCE;
        MediaLibraryItem item = getItem(i);
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.videolan.medialibrary.media.Storage");
        Uri uri = ((Storage) item).getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "getUri(...)");
        if (!medialibraryUtils.isBanned(uri, this.bannedFolders)) {
            BaseBrowserAdapter.MediaViewHolder mediaViewHolder = (BaseBrowserAdapter.MediaViewHolder) viewHolder;
            mediaViewHolder.getBindingContainer().getText().setVisibility(0);
            TextView text = mediaViewHolder.getBindingContainer().getText();
            Object obj = list.get(0);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.CharSequence");
            Context context = mediaViewHolder.getBindingContainer().getText().getContext();
            Intrinsics.checkNotNullExpressionValue(context, "getContext(...)");
            text.setText(KextensionsKt.getDescriptionSpan((CharSequence) obj, context));
        }
    }

    public void itemFocusChanged(int i, boolean z, BrowserItemBindingContainer browserItemBindingContainer) {
        Intrinsics.checkNotNullParameter(browserItemBindingContainer, "bindingContainer");
        if (AndroidDevices.INSTANCE.isTv() && !getBrowserContainer().isRootDirectory() && i >= 0) {
            boolean z2 = true;
            if (i <= getItemCount() - 1) {
                MediaLibraryItem item = getItem(i);
                Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.videolan.medialibrary.media.Storage");
                String uri = ((Storage) item).getUri().toString();
                Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
                boolean isBanned = MedialibraryUtils.INSTANCE.isBanned(uri, this.bannedFolders);
                Context context = browserItemBindingContainer.getContainer().getContext();
                if (!isBanned || MedialibraryUtils.INSTANCE.isStrictlyBanned(uri, this.bannedFolders)) {
                    z2 = false;
                }
                float f = 1.0f;
                if (!isBanned && !z2 && !z) {
                    f = 0.0f;
                }
                browserItemBindingContainer.getBanIcon().animate().alpha(f);
                browserItemBindingContainer.getBanIcon().setImageDrawable(ContextCompat.getDrawable(context, (isBanned || z2) ? R.drawable.ic_banned : R.drawable.ic_ban));
            }
        }
    }

    public void onViewRecycled(BaseBrowserAdapter.ViewHolder<ViewDataBinding> viewHolder) {
        Intrinsics.checkNotNullParameter(viewHolder, "holder");
        BaseBrowserAdapter.MediaViewHolder mediaViewHolder = (BaseBrowserAdapter.MediaViewHolder) viewHolder;
        Job job = mediaViewHolder.getJob();
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        mediaViewHolder.setJob((Job) null);
        super.onViewRecycled(viewHolder);
    }

    /* access modifiers changed from: private */
    public final boolean hasDiscoveredChildren(String str) {
        for (String startsWith$default : this.mediaDirsLocation) {
            if (StringsKt.startsWith$default(startsWith$default, str, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object updateListState(android.content.Context r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof org.videolan.vlc.gui.browser.StorageBrowserAdapter$updateListState$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.vlc.gui.browser.StorageBrowserAdapter$updateListState$1 r0 = (org.videolan.vlc.gui.browser.StorageBrowserAdapter$updateListState$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.gui.browser.StorageBrowserAdapter$updateListState$1 r0 = new org.videolan.vlc.gui.browser.StorageBrowserAdapter$updateListState$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r5 = (org.videolan.vlc.gui.browser.StorageBrowserAdapter) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x004c
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            r4.updateMediaDirs(r5)
            kotlinx.coroutines.Job r5 = r4.updateJob
            if (r5 == 0) goto L_0x004b
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r5 = r5.join(r0)
            if (r5 != r1) goto L_0x004b
            return r1
        L_0x004b:
            r5 = r4
        L_0x004c:
            kotlinx.coroutines.Job r6 = r5.updateJob
            if (r6 == 0) goto L_0x005e
            boolean r6 = r6.isCancelled()
            if (r6 != 0) goto L_0x005e
            r6 = 0
            int r0 = r5.getItemCount()
            r5.notifyItemRangeChanged(r6, r0)
        L_0x005e:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.StorageBrowserAdapter.updateListState(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void updateMediaDirs(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.mediaDirsLocation.clear();
        this.updateJob = BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new StorageBrowserAdapter$updateMediaDirs$1(this, context, (Continuation<? super StorageBrowserAdapter$updateMediaDirs$1>) null), 3, (Object) null);
    }

    public void checkBoxAction(View view, String str) {
        Intrinsics.checkNotNullParameter(view, "v");
        Intrinsics.checkNotNullParameter(str, "mrl");
        IStorageFragmentDelegate storageDelegate = getBrowserContainer().getStorageDelegate();
        if (storageDelegate != null) {
            storageDelegate.checkBoxAction(view, str);
        }
    }
}
