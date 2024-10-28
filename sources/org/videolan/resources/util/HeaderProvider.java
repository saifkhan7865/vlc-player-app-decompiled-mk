package org.videolan.resources.util;

import androidx.collection.SparseArrayCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0005H\u0007J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0013H\u0007J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u0012\u001a\u00020\u0013H\u0007R$\u0010\u0003\u001a\f\u0012\u0004\u0012\u00020\u00050\u0004j\u0002`\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR!\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00050\u0004j\u0002`\u00060\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001e\u0010\u000f\u001a\f\u0012\u0004\u0012\u00020\u00050\u0004j\u0002`\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\b¨\u0006\u001b"}, d2 = {"Lorg/videolan/resources/util/HeaderProvider;", "", "()V", "headers", "Landroidx/collection/SparseArrayCompat;", "", "Lorg/videolan/resources/util/HeadersIndex;", "getHeaders", "()Landroidx/collection/SparseArrayCompat;", "setHeaders", "(Landroidx/collection/SparseArrayCompat;)V", "liveHeaders", "Landroidx/lifecycle/LiveData;", "getLiveHeaders", "()Landroidx/lifecycle/LiveData;", "privateHeaders", "getPrivateHeaders", "getHeaderForPostion", "position", "", "getPositionForSection", "getPositionForSectionByName", "header", "getSectionforPosition", "isFirstInSection", "", "isLastInSection", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HeaderProvider.kt */
public class HeaderProvider {
    private SparseArrayCompat<String> headers = new SparseArrayCompat<>();
    private final LiveData<SparseArrayCompat<String>> liveHeaders;
    private final SparseArrayCompat<String> privateHeaders = new SparseArrayCompat<>();

    public HeaderProvider() {
        LiveData<SparseArrayCompat<String>> mutableLiveData = new MutableLiveData<>();
        this.liveHeaders = mutableLiveData;
        mutableLiveData.observeForever(new HeaderProviderKt$sam$androidx_lifecycle_Observer$0(new Function1<SparseArrayCompat<String>, Unit>(this) {
            final /* synthetic */ HeaderProvider this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((SparseArrayCompat<String>) (SparseArrayCompat) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(SparseArrayCompat<String> sparseArrayCompat) {
                HeaderProvider headerProvider = this.this$0;
                Intrinsics.checkNotNull(sparseArrayCompat);
                headerProvider.setHeaders(sparseArrayCompat);
            }
        }));
    }

    public final LiveData<SparseArrayCompat<String>> getLiveHeaders() {
        return this.liveHeaders;
    }

    /* access modifiers changed from: protected */
    public final SparseArrayCompat<String> getPrivateHeaders() {
        return this.privateHeaders;
    }

    public final SparseArrayCompat<String> getHeaders() {
        return this.headers;
    }

    public final void setHeaders(SparseArrayCompat<String> sparseArrayCompat) {
        Intrinsics.checkNotNullParameter(sparseArrayCompat, "<set-?>");
        this.headers = sparseArrayCompat;
    }

    public final String getSectionforPosition(int i) {
        int size = this.headers.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return "";
            }
            if (i >= this.headers.keyAt(size)) {
                String valueAt = this.headers.valueAt(size);
                Intrinsics.checkNotNullExpressionValue(valueAt, "valueAt(...)");
                return valueAt;
            }
        }
    }

    public final boolean isFirstInSection(int i) {
        return this.headers.containsKey(i);
    }

    public final boolean isLastInSection(int i) {
        return this.headers.containsKey(i + 1);
    }

    public final int getPositionForSection(int i) {
        int size = this.headers.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return 0;
            }
            if (i >= this.headers.keyAt(size)) {
                return this.headers.keyAt(size);
            }
        }
    }

    public final int getPositionForSectionByName(String str) {
        Intrinsics.checkNotNullParameter(str, "header");
        int size = this.headers.size();
        while (true) {
            size--;
            if (-1 >= size) {
                return 0;
            }
            if (Intrinsics.areEqual((Object) this.headers.valueAt(size), (Object) str)) {
                return this.headers.keyAt(size);
            }
        }
    }

    public final String getHeaderForPostion(int i) {
        return this.headers.get(i);
    }
}
