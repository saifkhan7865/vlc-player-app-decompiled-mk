package org.videolan.vlc.gui.browser;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "list", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MLStorageBrowserFragment.kt */
final class MLStorageBrowserFragment$onViewCreated$4 extends Lambda implements Function1<List<MediaLibraryItem>, Unit> {
    final /* synthetic */ StorageBrowserAdapter $networkAdapter;
    final /* synthetic */ MLStorageBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MLStorageBrowserFragment$onViewCreated$4(StorageBrowserAdapter storageBrowserAdapter, MLStorageBrowserFragment mLStorageBrowserFragment) {
        super(1);
        this.$networkAdapter = storageBrowserAdapter;
        this.this$0 = mLStorageBrowserFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((List<MediaLibraryItem>) (List) obj);
        return Unit.INSTANCE;
    }

    /* JADX WARNING: type inference failed for: r5v5, types: [java.lang.String] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke(java.util.List<org.videolan.medialibrary.media.MediaLibraryItem> r9) {
        /*
            r8 = this;
            if (r9 == 0) goto L_0x0092
            org.videolan.vlc.gui.browser.StorageBrowserAdapter r0 = r8.$networkAdapter
            org.videolan.vlc.gui.browser.MLStorageBrowserFragment r1 = r8.this$0
            r2 = r9
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Collection r3 = (java.util.Collection) r3
            java.util.Iterator r2 = r2.iterator()
        L_0x0014:
            boolean r4 = r2.hasNext()
            r5 = 0
            if (r4 == 0) goto L_0x003e
            java.lang.Object r4 = r2.next()
            r6 = r4
            org.videolan.medialibrary.media.MediaLibraryItem r6 = (org.videolan.medialibrary.media.MediaLibraryItem) r6
            boolean r7 = r6 instanceof org.videolan.medialibrary.interfaces.media.MediaWrapper
            if (r7 == 0) goto L_0x0014
            org.videolan.medialibrary.interfaces.media.MediaWrapper r6 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r6
            android.net.Uri r6 = r6.getUri()
            if (r6 == 0) goto L_0x0032
            java.lang.String r5 = r6.getScheme()
        L_0x0032:
            java.lang.String r6 = "smb"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r6)
            if (r5 == 0) goto L_0x0014
            r3.add(r4)
            goto L_0x0014
        L_0x003e:
            java.util.List r3 = (java.util.List) r3
            r0.update(r3)
            org.videolan.vlc.gui.view.TitleListView r0 = r1.networkEntry
            java.lang.String r2 = "networkEntry"
            if (r0 != 0) goto L_0x004f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r0 = r5
        L_0x004f:
            org.videolan.vlc.gui.view.EmptyLoadingStateView r0 = r0.getLoading()
            r1.updateNetworkEmptyView(r0)
            org.videolan.vlc.viewmodels.browser.BrowserModel r0 = r1.networkViewModel
            if (r0 != 0) goto L_0x0062
            java.lang.String r0 = "networkViewModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            r0 = r5
        L_0x0062:
            androidx.lifecycle.MutableLiveData r0 = r0.getLoading()
            java.lang.Object r0 = r0.getValue()
            r3 = 0
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r0, (java.lang.Object) r3)
            if (r0 == 0) goto L_0x0092
            org.videolan.vlc.gui.view.TitleListView r0 = r1.networkEntry
            if (r0 != 0) goto L_0x007f
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            goto L_0x0080
        L_0x007f:
            r5 = r0
        L_0x0080:
            org.videolan.vlc.gui.view.EmptyLoadingStateView r0 = r5.getLoading()
            boolean r9 = r9.isEmpty()
            if (r9 == 0) goto L_0x008d
            org.videolan.vlc.gui.view.EmptyLoadingState r9 = org.videolan.vlc.gui.view.EmptyLoadingState.EMPTY
            goto L_0x008f
        L_0x008d:
            org.videolan.vlc.gui.view.EmptyLoadingState r9 = org.videolan.vlc.gui.view.EmptyLoadingState.NONE
        L_0x008f:
            r0.setState(r9)
        L_0x0092:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.browser.MLStorageBrowserFragment$onViewCreated$4.invoke(java.util.List):void");
    }
}
