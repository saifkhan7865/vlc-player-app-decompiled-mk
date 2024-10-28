package org.videolan.vlc.gui.preferences.search;

import androidx.recyclerview.widget.DiffUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"org/videolan/vlc/gui/preferences/search/PreferenceItemAdapterKt$cb$1", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lorg/videolan/vlc/gui/preferences/search/PreferenceItem;", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreferenceItemAdapter.kt */
public final class PreferenceItemAdapterKt$cb$1 extends DiffUtil.ItemCallback<PreferenceItem> {
    public boolean areContentsTheSame(PreferenceItem preferenceItem, PreferenceItem preferenceItem2) {
        Intrinsics.checkNotNullParameter(preferenceItem, "oldItem");
        Intrinsics.checkNotNullParameter(preferenceItem2, "newItem");
        return true;
    }

    PreferenceItemAdapterKt$cb$1() {
    }

    public boolean areItemsTheSame(PreferenceItem preferenceItem, PreferenceItem preferenceItem2) {
        Intrinsics.checkNotNullParameter(preferenceItem, "oldItem");
        Intrinsics.checkNotNullParameter(preferenceItem2, "newItem");
        return Intrinsics.areEqual((Object) preferenceItem, (Object) preferenceItem2);
    }
}
