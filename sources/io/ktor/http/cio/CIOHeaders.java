package io.ktor.http.cio;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Headers;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010&\n\u0002\u0010 \n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u0017B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0010\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00120\u00110\nH\u0016J\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0014\u001a\u00020\u000bH\u0002J\u0018\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u00122\u0006\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0016\u001a\u00020\u0006H\u0016J\u000e\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0016R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R!\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8BX\u0002¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\r¨\u0006\u0018"}, d2 = {"Lio/ktor/http/cio/CIOHeaders;", "Lio/ktor/http/Headers;", "headers", "Lio/ktor/http/cio/HttpHeadersMap;", "(Lio/ktor/http/cio/HttpHeadersMap;)V", "caseInsensitiveName", "", "getCaseInsensitiveName", "()Z", "names", "", "", "getNames", "()Ljava/util/Set;", "names$delegate", "Lkotlin/Lazy;", "entries", "", "", "get", "name", "getAll", "isEmpty", "Entry", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIOHeaders.kt */
public final class CIOHeaders implements Headers {
    /* access modifiers changed from: private */
    public final HttpHeadersMap headers;
    private final Lazy names$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new CIOHeaders$names$2(this));

    public boolean getCaseInsensitiveName() {
        return true;
    }

    public CIOHeaders(HttpHeadersMap httpHeadersMap) {
        Intrinsics.checkNotNullParameter(httpHeadersMap, "headers");
        this.headers = httpHeadersMap;
    }

    public boolean contains(String str) {
        return Headers.DefaultImpls.contains(this, str);
    }

    public boolean contains(String str, String str2) {
        return Headers.DefaultImpls.contains(this, str, str2);
    }

    public void forEach(Function2<? super String, ? super List<String>, Unit> function2) {
        Headers.DefaultImpls.forEach(this, function2);
    }

    private final Set<String> getNames() {
        return (Set) this.names$delegate.getValue();
    }

    public Set<String> names() {
        return getNames();
    }

    public String get(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        CharSequence charSequence = this.headers.get(str);
        if (charSequence != null) {
            return charSequence.toString();
        }
        return null;
    }

    public List<String> getAll(String str) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        List<String> list = SequencesKt.toList(SequencesKt.map(this.headers.getAll(str), CIOHeaders$getAll$1.INSTANCE));
        if (!list.isEmpty()) {
            return list;
        }
        return null;
    }

    public boolean isEmpty() {
        return this.headers.getSize() == 0;
    }

    public Set<Map.Entry<String, List<String>>> entries() {
        Iterable until = RangesKt.until(0, this.headers.getSize());
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(until, 10));
        Iterator it = until.iterator();
        while (it.hasNext()) {
            arrayList.add(new Entry(((IntIterator) it).nextInt()));
        }
        return CollectionsKt.toSet((List) arrayList);
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u0004\u0018\u00002\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00038VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lio/ktor/http/cio/CIOHeaders$Entry;", "", "", "", "idx", "", "(Lio/ktor/http/cio/CIOHeaders;I)V", "key", "getKey", "()Ljava/lang/String;", "value", "getValue", "()Ljava/util/List;", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CIOHeaders.kt */
    private final class Entry implements Map.Entry<String, List<? extends String>>, KMappedMarker {
        private final int idx;

        public /* bridge */ /* synthetic */ Object setValue(Object obj) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public List<String> setValue(List<String> list) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public Entry(int i) {
            this.idx = i;
        }

        public String getKey() {
            return CIOHeaders.this.headers.nameAt(this.idx).toString();
        }

        public List<String> getValue() {
            return CollectionsKt.listOf(CIOHeaders.this.headers.valueAt(this.idx).toString());
        }
    }
}
