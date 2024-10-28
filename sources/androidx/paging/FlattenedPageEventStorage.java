package androidx.paging;

import androidx.core.app.NotificationCompat;
import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0001\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0013J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00130\u0015J\u0016\u0010\u0016\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0017H\u0002J\u0016\u0010\u0018\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019H\u0002J\u0016\u0010\u001a\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u001bH\u0002J\u0016\u0010\u001c\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00028\u00000\u001dH\u0002R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Landroidx/paging/FlattenedPageEventStorage;", "T", "", "()V", "mediatorStates", "Landroidx/paging/LoadStates;", "pages", "Lkotlin/collections/ArrayDeque;", "Landroidx/paging/TransformablePage;", "placeholdersAfter", "", "placeholdersBefore", "receivedFirstEvent", "", "sourceStates", "Landroidx/paging/MutableLoadStateCollection;", "add", "", "event", "Landroidx/paging/PageEvent;", "getAsEvents", "", "handleInsert", "Landroidx/paging/PageEvent$Insert;", "handleLoadStateUpdate", "Landroidx/paging/PageEvent$LoadStateUpdate;", "handlePageDrop", "Landroidx/paging/PageEvent$Drop;", "handleStaticList", "Landroidx/paging/PageEvent$StaticList;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CachedPageEventFlow.kt */
public final class FlattenedPageEventStorage<T> {
    private LoadStates mediatorStates;
    private final ArrayDeque<TransformablePage<T>> pages = new ArrayDeque<>();
    private int placeholdersAfter;
    private int placeholdersBefore;
    private boolean receivedFirstEvent;
    private final MutableLoadStateCollection sourceStates = new MutableLoadStateCollection();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CachedPageEventFlow.kt */
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
                androidx.paging.LoadType r1 = androidx.paging.LoadType.PREPEND     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                androidx.paging.LoadType r1 = androidx.paging.LoadType.REFRESH     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlattenedPageEventStorage.WhenMappings.<clinit>():void");
        }
    }

    public final void add(PageEvent<T> pageEvent) {
        Intrinsics.checkNotNullParameter(pageEvent, NotificationCompat.CATEGORY_EVENT);
        this.receivedFirstEvent = true;
        if (pageEvent instanceof PageEvent.Insert) {
            handleInsert((PageEvent.Insert) pageEvent);
        } else if (pageEvent instanceof PageEvent.Drop) {
            handlePageDrop((PageEvent.Drop) pageEvent);
        } else if (pageEvent instanceof PageEvent.LoadStateUpdate) {
            handleLoadStateUpdate((PageEvent.LoadStateUpdate) pageEvent);
        } else if (pageEvent instanceof PageEvent.StaticList) {
            handleStaticList((PageEvent.StaticList) pageEvent);
        }
    }

    private final void handlePageDrop(PageEvent.Drop<T> drop) {
        this.sourceStates.set(drop.getLoadType(), LoadState.NotLoading.Companion.getIncomplete$paging_common());
        int i = WhenMappings.$EnumSwitchMapping$0[drop.getLoadType().ordinal()];
        int i2 = 0;
        if (i == 1) {
            this.placeholdersBefore = drop.getPlaceholdersRemaining();
            int pageCount = drop.getPageCount();
            while (i2 < pageCount) {
                this.pages.removeFirst();
                i2++;
            }
        } else if (i == 2) {
            this.placeholdersAfter = drop.getPlaceholdersRemaining();
            int pageCount2 = drop.getPageCount();
            while (i2 < pageCount2) {
                this.pages.removeLast();
                i2++;
            }
        } else {
            throw new IllegalArgumentException("Page drop type must be prepend or append");
        }
    }

    private final void handleInsert(PageEvent.Insert<T> insert) {
        this.sourceStates.set(insert.getSourceLoadStates());
        this.mediatorStates = insert.getMediatorLoadStates();
        int i = WhenMappings.$EnumSwitchMapping$0[insert.getLoadType().ordinal()];
        if (i == 1) {
            this.placeholdersBefore = insert.getPlaceholdersBefore();
            Iterator it = RangesKt.downTo(insert.getPages().size() - 1, 0).iterator();
            while (it.hasNext()) {
                this.pages.addFirst(insert.getPages().get(((IntIterator) it).nextInt()));
            }
        } else if (i == 2) {
            this.placeholdersAfter = insert.getPlaceholdersAfter();
            this.pages.addAll(insert.getPages());
        } else if (i == 3) {
            this.pages.clear();
            this.placeholdersAfter = insert.getPlaceholdersAfter();
            this.placeholdersBefore = insert.getPlaceholdersBefore();
            this.pages.addAll(insert.getPages());
        }
    }

    private final void handleLoadStateUpdate(PageEvent.LoadStateUpdate<T> loadStateUpdate) {
        this.sourceStates.set(loadStateUpdate.getSource());
        this.mediatorStates = loadStateUpdate.getMediator();
    }

    private final void handleStaticList(PageEvent.StaticList<T> staticList) {
        if (staticList.getSourceLoadStates() != null) {
            this.sourceStates.set(staticList.getSourceLoadStates());
        }
        if (staticList.getMediatorLoadStates() != null) {
            this.mediatorStates = staticList.getMediatorLoadStates();
        }
        this.pages.clear();
        this.placeholdersAfter = 0;
        this.placeholdersBefore = 0;
        this.pages.add(new TransformablePage(0, staticList.getData()));
    }

    public final List<PageEvent<T>> getAsEvents() {
        if (!this.receivedFirstEvent) {
            return CollectionsKt.emptyList();
        }
        List<PageEvent<T>> arrayList = new ArrayList<>();
        LoadStates snapshot = this.sourceStates.snapshot();
        if (!this.pages.isEmpty()) {
            arrayList.add(PageEvent.Insert.Companion.Refresh(CollectionsKt.toList(this.pages), this.placeholdersBefore, this.placeholdersAfter, snapshot, this.mediatorStates));
        } else {
            arrayList.add(new PageEvent.LoadStateUpdate(snapshot, this.mediatorStates));
        }
        return arrayList;
    }
}
