package androidx.paging;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "T", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: PagingDataDiffer.kt */
final class PagingDataDiffer$presentNewList$transformedLastAccessedIndex$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ LoadStates $mediatorLoadStates;
    final /* synthetic */ HintReceiver $newHintReceiver;
    final /* synthetic */ PagePresenter<T> $newPresenter;
    final /* synthetic */ Ref.BooleanRef $onListPresentableCalled;
    final /* synthetic */ List<TransformablePage<T>> $pages;
    final /* synthetic */ int $placeholdersAfter;
    final /* synthetic */ int $placeholdersBefore;
    final /* synthetic */ LoadStates $sourceLoadStates;
    final /* synthetic */ PagingDataDiffer<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PagingDataDiffer$presentNewList$transformedLastAccessedIndex$1(PagingDataDiffer<T> pagingDataDiffer, PagePresenter<T> pagePresenter, Ref.BooleanRef booleanRef, HintReceiver hintReceiver, LoadStates loadStates, List<TransformablePage<T>> list, int i, int i2, LoadStates loadStates2) {
        super(0);
        this.this$0 = pagingDataDiffer;
        this.$newPresenter = pagePresenter;
        this.$onListPresentableCalled = booleanRef;
        this.$newHintReceiver = hintReceiver;
        this.$mediatorLoadStates = loadStates;
        this.$pages = list;
        this.$placeholdersBefore = i;
        this.$placeholdersAfter = i2;
        this.$sourceLoadStates = loadStates2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x003c, code lost:
        r10 = r10.getData();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void invoke() {
        /*
            r12 = this;
            androidx.paging.PagingDataDiffer<T> r0 = r12.this$0
            androidx.paging.PagePresenter<T> r1 = r12.$newPresenter
            r0.presenter = r1
            kotlin.jvm.internal.Ref$BooleanRef r0 = r12.$onListPresentableCalled
            r1 = 1
            r0.element = r1
            androidx.paging.PagingDataDiffer<T> r0 = r12.this$0
            androidx.paging.HintReceiver r2 = r12.$newHintReceiver
            r0.hintReceiver = r2
            androidx.paging.LoadStates r0 = r12.$mediatorLoadStates
            java.util.List<androidx.paging.TransformablePage<T>> r2 = r12.$pages
            int r3 = r12.$placeholdersBefore
            int r4 = r12.$placeholdersAfter
            androidx.paging.HintReceiver r5 = r12.$newHintReceiver
            androidx.paging.LoadStates r6 = r12.$sourceLoadStates
            androidx.paging.Logger r7 = androidx.paging.LoggerKt.getLOGGER()
            if (r7 == 0) goto L_0x00c5
            r8 = 3
            boolean r9 = r7.isLoggable(r8)
            if (r9 != r1) goto L_0x00c5
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Presenting data:\n                            |   first item: "
            r9.<init>(r10)
            java.lang.Object r10 = kotlin.collections.CollectionsKt.firstOrNull(r2)
            androidx.paging.TransformablePage r10 = (androidx.paging.TransformablePage) r10
            r11 = 0
            if (r10 == 0) goto L_0x0047
            java.util.List r10 = r10.getData()
            if (r10 == 0) goto L_0x0047
            java.lang.Object r10 = kotlin.collections.CollectionsKt.firstOrNull(r10)
            goto L_0x0048
        L_0x0047:
            r10 = r11
        L_0x0048:
            r9.append(r10)
            java.lang.String r10 = "\n                            |   last item: "
            r9.append(r10)
            java.lang.Object r2 = kotlin.collections.CollectionsKt.lastOrNull(r2)
            androidx.paging.TransformablePage r2 = (androidx.paging.TransformablePage) r2
            if (r2 == 0) goto L_0x0063
            java.util.List r2 = r2.getData()
            if (r2 == 0) goto L_0x0063
            java.lang.Object r2 = kotlin.collections.CollectionsKt.lastOrNull(r2)
            goto L_0x0064
        L_0x0063:
            r2 = r11
        L_0x0064:
            r9.append(r2)
            java.lang.String r2 = "\n                            |   placeholdersBefore: "
            r9.append(r2)
            r9.append(r3)
            java.lang.String r2 = "\n                            |   placeholdersAfter: "
            r9.append(r2)
            r9.append(r4)
            java.lang.String r2 = "\n                            |   hintReceiver: "
            r9.append(r2)
            r9.append(r5)
            java.lang.String r2 = "\n                            |   sourceLoadStates: "
            r9.append(r2)
            r9.append(r6)
            java.lang.String r2 = "\n                        "
            r9.append(r2)
            java.lang.String r2 = r9.toString()
            if (r0 == 0) goto L_0x00ac
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r3.append(r2)
            java.lang.String r2 = "|   mediatorLoadStates: "
            r3.append(r2)
            r3.append(r0)
            r0 = 10
            r3.append(r0)
            java.lang.String r2 = r3.toString()
        L_0x00ac:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r0.append(r2)
            java.lang.String r2 = "|)"
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r0 = kotlin.text.StringsKt.trimMargin$default(r0, r11, r1, r11)
            r7.log(r8, r0, r11)
        L_0x00c5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PagingDataDiffer$presentNewList$transformedLastAccessedIndex$1.invoke():void");
    }
}
