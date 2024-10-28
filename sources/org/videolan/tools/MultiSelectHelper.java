package org.videolan.tools;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001d\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0002¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0015\u001a\u00020\u0016J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u0018J\b\u0010\u0019\u001a\u00020\u0011H\u0007J\u000e\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u0011J\u0016\u0010\u001c\u001a\u00020\u00162\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u001d\u001a\u00020\u0011J\u0018\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u00112\b\b\u0002\u0010\u001f\u001a\u00020\nR\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0005\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000R!\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\u00110\u0010j\b\u0012\u0004\u0012\u00020\u0011`\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006 "}, d2 = {"Lorg/videolan/tools/MultiSelectHelper;", "T", "", "adapter", "Lorg/videolan/tools/MultiSelectAdapter;", "payloadvalue", "(Lorg/videolan/tools/MultiSelectAdapter;Ljava/lang/Object;)V", "getAdapter", "()Lorg/videolan/tools/MultiSelectAdapter;", "inActionMode", "", "getInActionMode", "()Z", "setInActionMode", "(Z)V", "selectionMap", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getSelectionMap", "()Ljava/util/ArrayList;", "clearSelection", "", "getSelection", "", "getSelectionCount", "isSelected", "position", "toggleActionMode", "itemCount", "toggleSelection", "forceShift", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MultiSelectHelper.kt */
public final class MultiSelectHelper<T> {
    private final MultiSelectAdapter<T> adapter;
    private boolean inActionMode;
    private final Object payloadvalue;
    private final ArrayList<Integer> selectionMap;

    public MultiSelectHelper(MultiSelectAdapter<T> multiSelectAdapter, Object obj) {
        Intrinsics.checkNotNullParameter(multiSelectAdapter, "adapter");
        Intrinsics.checkNotNullParameter(obj, "payloadvalue");
        this.adapter = multiSelectAdapter;
        this.payloadvalue = obj;
        this.selectionMap = new ArrayList<>();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ MultiSelectHelper(MultiSelectAdapter multiSelectAdapter, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(multiSelectAdapter, (i & 2) != 0 ? 0 : obj);
    }

    public final MultiSelectAdapter<T> getAdapter() {
        return this.adapter;
    }

    public final ArrayList<Integer> getSelectionMap() {
        return this.selectionMap;
    }

    public final boolean getInActionMode() {
        return this.inActionMode;
    }

    public final void setInActionMode(boolean z) {
        this.inActionMode = z;
    }

    public final List<T> getSelection() {
        ArrayList arrayList = new ArrayList(this.selectionMap.size());
        int size = this.selectionMap.size();
        for (int i = 0; i < size; i++) {
            MultiSelectAdapter<T> multiSelectAdapter = this.adapter;
            Integer num = this.selectionMap.get(i);
            Intrinsics.checkNotNullExpressionValue(num, "get(...)");
            T item = multiSelectAdapter.getItem(num.intValue());
            if (item != null) {
                arrayList.add(item);
            }
        }
        return arrayList;
    }

    public final int getSelectionCount() {
        return this.selectionMap.size();
    }

    public final void toggleActionMode(boolean z, int i) {
        this.inActionMode = z;
        this.adapter.notifyItemRangeChanged(0, i, this.payloadvalue);
    }

    public static /* synthetic */ void toggleSelection$default(MultiSelectHelper multiSelectHelper, int i, boolean z, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            z = false;
        }
        multiSelectHelper.toggleSelection(i, z);
    }

    public final void toggleSelection(int i, boolean z) {
        if ((KeyHelper.INSTANCE.isShiftPressed() || z) && this.selectionMap.size() != 0) {
            HashSet<Number> hashSet = new HashSet<>();
            int size = this.selectionMap.size();
            for (int i2 = 0; i2 < size; i2++) {
                hashSet.add(this.selectionMap.get(i2));
            }
            Integer num = this.selectionMap.get(0);
            Intrinsics.checkNotNullExpressionValue(num, "get(...)");
            int intValue = num.intValue();
            this.selectionMap.clear();
            int min = Math.min(intValue, i);
            int max = Math.max(intValue, i);
            if (min <= max) {
                while (true) {
                    this.selectionMap.add(Integer.valueOf(min));
                    hashSet.add(Integer.valueOf(min));
                    if (min == max) {
                        break;
                    }
                    min++;
                }
            }
            for (Number intValue2 : hashSet) {
                this.adapter.notifyItemChanged(intValue2.intValue(), this.payloadvalue);
            }
            return;
        }
        if (isSelected(i)) {
            this.selectionMap.remove(Integer.valueOf(i));
        } else {
            this.selectionMap.add(Integer.valueOf(i));
        }
        this.adapter.notifyItemChanged(i, this.payloadvalue);
    }

    public final void clearSelection() {
        if (this.selectionMap.size() != 0) {
            Integer num = (Integer) CollectionsKt.minOrNull(this.selectionMap);
            Integer num2 = (Integer) CollectionsKt.maxOrNull(this.selectionMap);
            this.selectionMap.clear();
            MultiSelectAdapter<T> multiSelectAdapter = this.adapter;
            int i = 0;
            int intValue = num != null ? num.intValue() : 0;
            if (num2 != null) {
                i = num2.intValue();
            }
            multiSelectAdapter.notifyItemRangeChanged(intValue, i, this.payloadvalue);
        }
    }

    public final boolean isSelected(int i) {
        return this.selectionMap.contains(Integer.valueOf(i));
    }
}
