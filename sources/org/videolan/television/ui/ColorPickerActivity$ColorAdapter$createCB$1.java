package org.videolan.television.ui;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.videolan.vlc.gui.DiffUtilAdapter;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J(\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u00020\tj\b\u0012\u0004\u0012\u00020\u0002`\n2\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\u000b"}, d2 = {"org/videolan/television/ui/ColorPickerActivity$ColorAdapter$createCB$1", "Lorg/videolan/vlc/gui/DiffUtilAdapter$DiffCallback;", "", "areContentsTheSame", "", "oldItemPosition", "newItemPosition", "areItemsTheSame", "getChangePayload", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ColorPickerActivity.kt */
public final class ColorPickerActivity$ColorAdapter$createCB$1 extends DiffUtilAdapter.DiffCallback<Integer> {
    public boolean areContentsTheSame(int i, int i2) {
        return false;
    }

    public boolean areItemsTheSame(int i, int i2) {
        return false;
    }

    ColorPickerActivity$ColorAdapter$createCB$1() {
    }

    public ArrayList<Integer> getChangePayload(int i, int i2) {
        return CollectionsKt.arrayListOf(5);
    }
}
