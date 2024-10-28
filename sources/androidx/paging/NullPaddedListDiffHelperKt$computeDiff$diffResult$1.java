package androidx.paging;

import androidx.recyclerview.widget.DiffUtil;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u001a\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\u0005H\u0016J\b\u0010\u000b\u001a\u00020\u0005H\u0016¨\u0006\f"}, d2 = {"androidx/paging/NullPaddedListDiffHelperKt$computeDiff$diffResult$1", "Landroidx/recyclerview/widget/DiffUtil$Callback;", "areContentsTheSame", "", "oldItemPosition", "", "newItemPosition", "areItemsTheSame", "getChangePayload", "", "getNewListSize", "getOldListSize", "paging-runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NullPaddedListDiffHelper.kt */
public final class NullPaddedListDiffHelperKt$computeDiff$diffResult$1 extends DiffUtil.Callback {
    final /* synthetic */ DiffUtil.ItemCallback<T> $diffCallback;
    final /* synthetic */ NullPaddedList<T> $newList;
    final /* synthetic */ int $newSize;
    final /* synthetic */ int $oldSize;
    final /* synthetic */ NullPaddedList<T> $this_computeDiff;

    NullPaddedListDiffHelperKt$computeDiff$diffResult$1(NullPaddedList<T> nullPaddedList, NullPaddedList<T> nullPaddedList2, DiffUtil.ItemCallback<T> itemCallback, int i, int i2) {
        this.$this_computeDiff = nullPaddedList;
        this.$newList = nullPaddedList2;
        this.$diffCallback = itemCallback;
        this.$oldSize = i;
        this.$newSize = i2;
    }

    public Object getChangePayload(int i, int i2) {
        T fromStorage = this.$this_computeDiff.getFromStorage(i);
        T fromStorage2 = this.$newList.getFromStorage(i2);
        if (fromStorage == fromStorage2) {
            return true;
        }
        return this.$diffCallback.getChangePayload(fromStorage, fromStorage2);
    }

    public int getOldListSize() {
        return this.$oldSize;
    }

    public int getNewListSize() {
        return this.$newSize;
    }

    public boolean areItemsTheSame(int i, int i2) {
        T fromStorage = this.$this_computeDiff.getFromStorage(i);
        T fromStorage2 = this.$newList.getFromStorage(i2);
        if (fromStorage == fromStorage2) {
            return true;
        }
        return this.$diffCallback.areItemsTheSame(fromStorage, fromStorage2);
    }

    public boolean areContentsTheSame(int i, int i2) {
        T fromStorage = this.$this_computeDiff.getFromStorage(i);
        T fromStorage2 = this.$newList.getFromStorage(i2);
        if (fromStorage == fromStorage2) {
            return true;
        }
        return this.$diffCallback.areContentsTheSame(fromStorage, fromStorage2);
    }
}
