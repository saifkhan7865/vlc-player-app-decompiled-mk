package org.videolan.vlc.gui.browser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.Tools;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.medialibrary.media.Storage;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "folder", "", "<anonymous parameter 1>", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: StorageBrowserFragment.kt */
final class StorageBrowserFragment$onStart$2 extends Lambda implements Function2<String, Boolean, Unit> {
    final /* synthetic */ StorageBrowserFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    StorageBrowserFragment$onStart$2(StorageBrowserFragment storageBrowserFragment) {
        super(2);
        this.this$0 = storageBrowserFragment;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((String) obj, ((Boolean) obj2).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "folder");
        BaseBrowserAdapter adapter = this.this$0.getAdapter();
        Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type org.videolan.vlc.gui.browser.StorageBrowserAdapter");
        String[] bannedFolders = Medialibrary.getInstance().bannedFolders();
        Intrinsics.checkNotNullExpressionValue(bannedFolders, "bannedFolders(...)");
        ((StorageBrowserAdapter) adapter).setBannedFolders(ArraysKt.toList((T[]) (Object[]) bannedFolders));
        StorageBrowserFragment storageBrowserFragment = this.this$0;
        int i = 0;
        for (Object next : this.this$0.getAdapter().getDataset()) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            MediaLibraryItem mediaLibraryItem = (MediaLibraryItem) next;
            StringBuilder sb = new StringBuilder();
            Intrinsics.checkNotNull(mediaLibraryItem, "null cannot be cast to non-null type org.videolan.medialibrary.media.Storage");
            sb.append(Tools.mlEncodeMrl(((Storage) mediaLibraryItem).getUri().toString()));
            sb.append('/');
            if (Intrinsics.areEqual((Object) sb.toString(), (Object) str)) {
                storageBrowserFragment.getAdapter().notifyItemChanged(i);
            }
            i = i2;
        }
    }
}
