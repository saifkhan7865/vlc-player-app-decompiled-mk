package androidx.paging;

import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import androidx.paging.ViewportHint;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0000\u0018\u0000 8*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u000289B\u0015\b\u0016\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006B)\u0012\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b¢\u0006\u0002\u0010\rJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000bJ\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001d\u001a\u00020\u000bH\u0002J\u001e\u0010 \u001a\u00020\u001f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\"2\u0006\u0010#\u001a\u00020$H\u0002J\u0010\u0010%\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020'H\u0002J\u0015\u0010(\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u001d\u001a\u00020\u000b¢\u0006\u0002\u0010)J\u0015\u0010*\u001a\u00028\u00002\u0006\u0010+\u001a\u00020\u000bH\u0016¢\u0006\u0002\u0010)J\u0006\u0010,\u001a\u00020-J\u001e\u0010.\u001a\u00020\u001f2\f\u0010/\u001a\b\u0012\u0004\u0012\u00028\u00000\u00052\u0006\u0010#\u001a\u00020$H\u0002J\u001c\u00100\u001a\u00020\u001f2\f\u00101\u001a\b\u0012\u0004\u0012\u00028\u0000022\u0006\u0010#\u001a\u00020$J\f\u00103\u001a\b\u0012\u0004\u0012\u00028\u000004J\b\u00105\u001a\u000206H\u0016J\u0018\u00107\u001a\u00020\u000b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\bH\u0002R\u0014\u0010\u000e\u001a\u00020\u000b8BX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u000b8BX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\t0\u0013X\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b@RX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b@RX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0014\u0010\u0017\u001a\u00020\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0010R\u001e\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b@RX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010¨\u0006:"}, d2 = {"Landroidx/paging/PagePresenter;", "T", "", "Landroidx/paging/NullPaddedList;", "insertEvent", "Landroidx/paging/PageEvent$Insert;", "(Landroidx/paging/PageEvent$Insert;)V", "pages", "", "Landroidx/paging/TransformablePage;", "placeholdersBefore", "", "placeholdersAfter", "(Ljava/util/List;II)V", "originalPageOffsetFirst", "getOriginalPageOffsetFirst", "()I", "originalPageOffsetLast", "getOriginalPageOffsetLast", "", "<set-?>", "getPlaceholdersAfter", "getPlaceholdersBefore", "size", "getSize", "storageCount", "getStorageCount", "accessHintForPresenterIndex", "Landroidx/paging/ViewportHint$Access;", "index", "checkIndex", "", "dropPages", "drop", "Landroidx/paging/PageEvent$Drop;", "callback", "Landroidx/paging/PagePresenter$ProcessPageEventCallback;", "dropPagesWithOffsets", "pageOffsetsToDrop", "Lkotlin/ranges/IntRange;", "get", "(I)Ljava/lang/Object;", "getFromStorage", "localIndex", "initializeHint", "Landroidx/paging/ViewportHint$Initial;", "insertPage", "insert", "processEvent", "pageEvent", "Landroidx/paging/PageEvent;", "snapshot", "Landroidx/paging/ItemSnapshotList;", "toString", "", "fullCount", "Companion", "ProcessPageEventCallback", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PagePresenter.kt */
public final class PagePresenter<T> implements NullPaddedList<T> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final PagePresenter<Object> INITIAL = new PagePresenter<>(PageEvent.Insert.Companion.getEMPTY_REFRESH_LOCAL());
    private final List<TransformablePage<T>> pages;
    private int placeholdersAfter;
    private int placeholdersBefore;
    private int storageCount;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\b`\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u001a\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u000bH&J \u0010\t\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H&ø\u0001\u0000\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0013À\u0006\u0001"}, d2 = {"Landroidx/paging/PagePresenter$ProcessPageEventCallback;", "", "onChanged", "", "position", "", "count", "onInserted", "onRemoved", "onStateUpdate", "source", "Landroidx/paging/LoadStates;", "mediator", "loadType", "Landroidx/paging/LoadType;", "fromMediator", "", "loadState", "Landroidx/paging/LoadState;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PagePresenter.kt */
    public interface ProcessPageEventCallback {
        void onChanged(int i, int i2);

        void onInserted(int i, int i2);

        void onRemoved(int i, int i2);

        void onStateUpdate(LoadStates loadStates, LoadStates loadStates2);

        void onStateUpdate(LoadType loadType, boolean z, LoadState loadState);
    }

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PagePresenter.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                androidx.paging.LoadType[] r0 = androidx.paging.LoadType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.paging.LoadType r1 = androidx.paging.LoadType.REFRESH     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.paging.LoadType r1 = androidx.paging.LoadType.PREPEND     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagePresenter.WhenMappings.<clinit>():void");
        }
    }

    public PagePresenter(List<TransformablePage<T>> list, int i, int i2) {
        Intrinsics.checkNotNullParameter(list, "pages");
        this.pages = CollectionsKt.toMutableList(list);
        this.storageCount = fullCount(list);
        this.placeholdersBefore = i;
        this.placeholdersAfter = i2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PagePresenter(PageEvent.Insert<T> insert) {
        this(insert.getPages(), insert.getPlaceholdersBefore(), insert.getPlaceholdersAfter());
        Intrinsics.checkNotNullParameter(insert, "insertEvent");
    }

    public int getStorageCount() {
        return this.storageCount;
    }

    private final int getOriginalPageOffsetFirst() {
        Integer minOrNull = ArraysKt.minOrNull(((TransformablePage) CollectionsKt.first(this.pages)).getOriginalPageOffsets());
        Intrinsics.checkNotNull(minOrNull);
        return minOrNull.intValue();
    }

    private final int getOriginalPageOffsetLast() {
        Integer maxOrNull = ArraysKt.maxOrNull(((TransformablePage) CollectionsKt.last(this.pages)).getOriginalPageOffsets());
        Intrinsics.checkNotNull(maxOrNull);
        return maxOrNull.intValue();
    }

    public int getPlaceholdersBefore() {
        return this.placeholdersBefore;
    }

    public int getPlaceholdersAfter() {
        return this.placeholdersAfter;
    }

    private final void checkIndex(int i) {
        if (i < 0 || i >= getSize()) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size: " + getSize());
        }
    }

    public String toString() {
        int storageCount2 = getStorageCount();
        ArrayList arrayList = new ArrayList(storageCount2);
        for (int i = 0; i < storageCount2; i++) {
            arrayList.add(getFromStorage(i));
        }
        String joinToString$default = CollectionsKt.joinToString$default(arrayList, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null);
        return "[(" + getPlaceholdersBefore() + " placeholders), " + joinToString$default + ", (" + getPlaceholdersAfter() + " placeholders)]";
    }

    public final T get(int i) {
        checkIndex(i);
        int placeholdersBefore2 = i - getPlaceholdersBefore();
        if (placeholdersBefore2 < 0 || placeholdersBefore2 >= getStorageCount()) {
            return null;
        }
        return getFromStorage(placeholdersBefore2);
    }

    public final ItemSnapshotList<T> snapshot() {
        int placeholdersBefore2 = getPlaceholdersBefore();
        int placeholdersAfter2 = getPlaceholdersAfter();
        Collection arrayList = new ArrayList();
        for (TransformablePage data : this.pages) {
            CollectionsKt.addAll(arrayList, data.getData());
        }
        return new ItemSnapshotList<>(placeholdersBefore2, placeholdersAfter2, (List) arrayList);
    }

    public T getFromStorage(int i) {
        int size = this.pages.size();
        int i2 = 0;
        while (i2 < size) {
            int size2 = this.pages.get(i2).getData().size();
            if (size2 > i) {
                break;
            }
            i -= size2;
            i2++;
        }
        return this.pages.get(i2).getData().get(i);
    }

    public int getSize() {
        return getPlaceholdersBefore() + getStorageCount() + getPlaceholdersAfter();
    }

    private final int fullCount(List<TransformablePage<T>> list) {
        int i = 0;
        for (TransformablePage data : list) {
            i += data.getData().size();
        }
        return i;
    }

    public final void processEvent(PageEvent<T> pageEvent, ProcessPageEventCallback processPageEventCallback) {
        Intrinsics.checkNotNullParameter(pageEvent, "pageEvent");
        Intrinsics.checkNotNullParameter(processPageEventCallback, "callback");
        if (pageEvent instanceof PageEvent.Insert) {
            insertPage((PageEvent.Insert) pageEvent, processPageEventCallback);
        } else if (pageEvent instanceof PageEvent.Drop) {
            dropPages((PageEvent.Drop) pageEvent, processPageEventCallback);
        } else if (pageEvent instanceof PageEvent.LoadStateUpdate) {
            PageEvent.LoadStateUpdate loadStateUpdate = (PageEvent.LoadStateUpdate) pageEvent;
            processPageEventCallback.onStateUpdate(loadStateUpdate.getSource(), loadStateUpdate.getMediator());
        } else if (pageEvent instanceof PageEvent.StaticList) {
            throw new IllegalStateException("Paging received an event to display a static list, while still actively loading\nfrom an existing generation of PagingData. If you see this exception, it is most\nlikely a bug in the library. Please file a bug so we can fix it at:\nhttps://issuetracker.google.com/issues/new?component=413106");
        }
    }

    public final ViewportHint.Initial initializeHint() {
        int storageCount2 = getStorageCount() / 2;
        return new ViewportHint.Initial(storageCount2, storageCount2, getOriginalPageOffsetFirst(), getOriginalPageOffsetLast());
    }

    public final ViewportHint.Access accessHintForPresenterIndex(int i) {
        int i2 = 0;
        int placeholdersBefore2 = i - getPlaceholdersBefore();
        while (placeholdersBefore2 >= this.pages.get(i2).getData().size() && i2 < CollectionsKt.getLastIndex(this.pages)) {
            placeholdersBefore2 -= this.pages.get(i2).getData().size();
            i2++;
        }
        return this.pages.get(i2).viewportHintFor(placeholdersBefore2, i - getPlaceholdersBefore(), ((getSize() - i) - getPlaceholdersAfter()) - 1, getOriginalPageOffsetFirst(), getOriginalPageOffsetLast());
    }

    private final void insertPage(PageEvent.Insert<T> insert, ProcessPageEventCallback processPageEventCallback) {
        int fullCount = fullCount(insert.getPages());
        int size = getSize();
        int i = WhenMappings.$EnumSwitchMapping$0[insert.getLoadType().ordinal()];
        if (i != 1) {
            if (i == 2) {
                int min = Math.min(getPlaceholdersBefore(), fullCount);
                int i2 = fullCount - min;
                this.pages.addAll(0, insert.getPages());
                this.storageCount = getStorageCount() + fullCount;
                this.placeholdersBefore = insert.getPlaceholdersBefore();
                processPageEventCallback.onChanged(getPlaceholdersBefore() - min, min);
                processPageEventCallback.onInserted(0, i2);
                int size2 = (getSize() - size) - i2;
                if (size2 > 0) {
                    processPageEventCallback.onInserted(0, size2);
                } else if (size2 < 0) {
                    processPageEventCallback.onRemoved(0, -size2);
                }
            } else if (i == 3) {
                int min2 = Math.min(getPlaceholdersAfter(), fullCount);
                int placeholdersBefore2 = getPlaceholdersBefore() + getStorageCount();
                int i3 = fullCount - min2;
                List<TransformablePage<T>> list = this.pages;
                list.addAll(list.size(), insert.getPages());
                this.storageCount = getStorageCount() + fullCount;
                this.placeholdersAfter = insert.getPlaceholdersAfter();
                processPageEventCallback.onChanged(placeholdersBefore2, min2);
                processPageEventCallback.onInserted(placeholdersBefore2 + min2, i3);
                int size3 = (getSize() - size) - i3;
                if (size3 > 0) {
                    processPageEventCallback.onInserted(getSize() - size3, size3);
                } else if (size3 < 0) {
                    processPageEventCallback.onRemoved(getSize(), -size3);
                }
            }
            processPageEventCallback.onStateUpdate(insert.getSourceLoadStates(), insert.getMediatorLoadStates());
            return;
        }
        throw new IllegalStateException("Paging received a refresh event in the middle of an actively loading generation\nof PagingData. If you see this exception, it is most likely a bug in the library.\nPlease file a bug so we can fix it at:\nhttps://issuetracker.google.com/issues/new?component=413106");
    }

    private final int dropPagesWithOffsets(IntRange intRange) {
        Iterator<TransformablePage<T>> it = this.pages.iterator();
        int i = 0;
        while (it.hasNext()) {
            TransformablePage next = it.next();
            int[] originalPageOffsets = next.getOriginalPageOffsets();
            int length = originalPageOffsets.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    break;
                } else if (intRange.contains(originalPageOffsets[i2])) {
                    i += next.getData().size();
                    it.remove();
                    break;
                } else {
                    i2++;
                }
            }
        }
        return i;
    }

    private final void dropPages(PageEvent.Drop<T> drop, ProcessPageEventCallback processPageEventCallback) {
        int size = getSize();
        if (drop.getLoadType() == LoadType.PREPEND) {
            int placeholdersBefore2 = getPlaceholdersBefore();
            this.storageCount = getStorageCount() - dropPagesWithOffsets(new IntRange(drop.getMinPageOffset(), drop.getMaxPageOffset()));
            this.placeholdersBefore = drop.getPlaceholdersRemaining();
            int size2 = getSize() - size;
            if (size2 > 0) {
                processPageEventCallback.onInserted(0, size2);
            } else if (size2 < 0) {
                processPageEventCallback.onRemoved(0, -size2);
            }
            int max = Math.max(0, placeholdersBefore2 + size2);
            int placeholdersRemaining = drop.getPlaceholdersRemaining() - max;
            if (placeholdersRemaining > 0) {
                processPageEventCallback.onChanged(max, placeholdersRemaining);
            }
            processPageEventCallback.onStateUpdate(LoadType.PREPEND, false, LoadState.NotLoading.Companion.getIncomplete$paging_common());
            return;
        }
        int placeholdersAfter2 = getPlaceholdersAfter();
        this.storageCount = getStorageCount() - dropPagesWithOffsets(new IntRange(drop.getMinPageOffset(), drop.getMaxPageOffset()));
        this.placeholdersAfter = drop.getPlaceholdersRemaining();
        int size3 = getSize() - size;
        if (size3 > 0) {
            processPageEventCallback.onInserted(size, size3);
        } else if (size3 < 0) {
            processPageEventCallback.onRemoved(size + size3, -size3);
        }
        int placeholdersRemaining2 = drop.getPlaceholdersRemaining() - (placeholdersAfter2 - (size3 < 0 ? Math.min(placeholdersAfter2, -size3) : 0));
        if (placeholdersRemaining2 > 0) {
            processPageEventCallback.onChanged(getSize() - drop.getPlaceholdersRemaining(), placeholdersRemaining2);
        }
        processPageEventCallback.onStateUpdate(LoadType.APPEND, false, LoadState.NotLoading.Companion.getIncomplete$paging_common());
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0004\"\b\b\u0001\u0010\u0006*\u00020\u00012\u000e\u0010\u0007\u001a\n\u0012\u0004\u0012\u0002H\u0006\u0018\u00010\bH\u0000¢\u0006\u0002\b\tR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/paging/PagePresenter$Companion;", "", "()V", "INITIAL", "Landroidx/paging/PagePresenter;", "initial", "T", "event", "Landroidx/paging/PageEvent$Insert;", "initial$paging_common", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PagePresenter.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T> PagePresenter<T> initial$paging_common(PageEvent.Insert<T> insert) {
            if (insert != null) {
                return new PagePresenter<>(insert);
            }
            PagePresenter<T> access$getINITIAL$cp = PagePresenter.INITIAL;
            Intrinsics.checkNotNull(access$getINITIAL$cp, "null cannot be cast to non-null type androidx.paging.PagePresenter<T of androidx.paging.PagePresenter.Companion.initial>");
            return access$getINITIAL$cp;
        }
    }
}
