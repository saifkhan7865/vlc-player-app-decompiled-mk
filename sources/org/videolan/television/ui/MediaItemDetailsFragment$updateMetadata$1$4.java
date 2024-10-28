package org.videolan.television.ui;

import androidx.leanback.widget.DetailsOverviewRow;
import androidx.leanback.widget.DiffCallback;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.Row;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0018\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0002H\u0016¨\u0006\b"}, d2 = {"org/videolan/television/ui/MediaItemDetailsFragment$updateMetadata$1$4", "Landroidx/leanback/widget/DiffCallback;", "Landroidx/leanback/widget/Row;", "areContentsTheSame", "", "oldItem", "newItem", "areItemsTheSame", "television_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaItemDetailsFragment.kt */
public final class MediaItemDetailsFragment$updateMetadata$1$4 extends DiffCallback<Row> {
    MediaItemDetailsFragment$updateMetadata$1$4() {
    }

    public boolean areItemsTheSame(Row row, Row row2) {
        Intrinsics.checkNotNullParameter(row, "oldItem");
        Intrinsics.checkNotNullParameter(row2, "newItem");
        if (!(row instanceof DetailsOverviewRow) || !(row2 instanceof DetailsOverviewRow) || !Intrinsics.areEqual(((DetailsOverviewRow) row).getItem(), ((DetailsOverviewRow) row2).getItem())) {
            if ((row instanceof ListRow) && (row2 instanceof ListRow)) {
                ListRow listRow = (ListRow) row;
                ListRow listRow2 = (ListRow) row2;
                if (!(Intrinsics.areEqual((Object) listRow.getContentDescription(), (Object) listRow2.getContentDescription()) && listRow.getAdapter().size() == listRow2.getAdapter().size() && listRow.getId() == listRow2.getId())) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    public boolean areContentsTheSame(Row row, Row row2) {
        Intrinsics.checkNotNullParameter(row, "oldItem");
        Intrinsics.checkNotNullParameter(row2, "newItem");
        if (!(row instanceof DetailsOverviewRow) || !(row2 instanceof DetailsOverviewRow)) {
            return true;
        }
        Object item = ((DetailsOverviewRow) row).getItem();
        Intrinsics.checkNotNull(item, "null cannot be cast to non-null type org.videolan.television.ui.MediaItemDetails");
        Object item2 = ((DetailsOverviewRow) row2).getItem();
        Intrinsics.checkNotNull(item2, "null cannot be cast to non-null type org.videolan.television.ui.MediaItemDetails");
        return Intrinsics.areEqual((Object) (MediaItemDetails) item, (Object) (MediaItemDetails) item2);
    }
}
