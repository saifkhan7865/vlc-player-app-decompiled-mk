package io.ktor.http.cio.internals;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\b\u0003\b\u0000\u0018\u0000 \u0014*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002:\u0002\u0014\u0015B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\u0002\u0010\u0005JL\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00100\u0012R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0016"}, d2 = {"Lio/ktor/http/cio/internals/AsciiCharTree;", "T", "", "root", "Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "(Lio/ktor/http/cio/internals/AsciiCharTree$Node;)V", "getRoot", "()Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "search", "", "sequence", "", "fromIdx", "", "end", "lowerCase", "", "stopPredicate", "Lkotlin/Function2;", "", "Companion", "Node", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AsciiCharTree.kt */
public final class AsciiCharTree<T> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Node<T> root;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\t\u0018\u0000*\u0004\b\u0001\u0010\u00012\u00020\u0002B/\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00000\u0006¢\u0006\u0002\u0010\bR!\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00000\n¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011¨\u0006\u0013"}, d2 = {"Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "T", "", "ch", "", "exact", "", "children", "(CLjava/util/List;Ljava/util/List;)V", "array", "", "getArray", "()[Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "[Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "getCh", "()C", "getChildren", "()Ljava/util/List;", "getExact", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AsciiCharTree.kt */
    public static final class Node<T> {
        private final Node<T>[] array;
        private final char ch;
        private final List<Node<T>> children;
        private final List<T> exact;

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v0, resolved type: io.ktor.http.cio.internals.AsciiCharTree$Node<T>} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v1, resolved type: io.ktor.http.cio.internals.AsciiCharTree$Node<T>} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v1, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v0, resolved type: io.ktor.http.cio.internals.AsciiCharTree$Node} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v2, resolved type: io.ktor.http.cio.internals.AsciiCharTree$Node<T>} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v3, resolved type: io.ktor.http.cio.internals.AsciiCharTree$Node<T>} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public Node(char r8, java.util.List<? extends T> r9, java.util.List<io.ktor.http.cio.internals.AsciiCharTree.Node<T>> r10) {
            /*
                r7 = this;
                java.lang.String r0 = "exact"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
                java.lang.String r0 = "children"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                r7.<init>()
                r7.ch = r8
                r7.exact = r9
                r7.children = r10
                r8 = 256(0x100, float:3.59E-43)
                io.ktor.http.cio.internals.AsciiCharTree$Node[] r9 = new io.ktor.http.cio.internals.AsciiCharTree.Node[r8]
                r10 = 0
                r0 = 0
            L_0x0019:
                if (r0 >= r8) goto L_0x0046
                java.util.List<io.ktor.http.cio.internals.AsciiCharTree$Node<T>> r1 = r7.children
                java.lang.Iterable r1 = (java.lang.Iterable) r1
                java.util.Iterator r1 = r1.iterator()
                r2 = 0
                r4 = r2
                r3 = 0
            L_0x0026:
                boolean r5 = r1.hasNext()
                if (r5 == 0) goto L_0x003d
                java.lang.Object r5 = r1.next()
                r6 = r5
                io.ktor.http.cio.internals.AsciiCharTree$Node r6 = (io.ktor.http.cio.internals.AsciiCharTree.Node) r6
                char r6 = r6.ch
                if (r6 != r0) goto L_0x0026
                if (r3 == 0) goto L_0x003a
                goto L_0x0041
            L_0x003a:
                r3 = 1
                r4 = r5
                goto L_0x0026
            L_0x003d:
                if (r3 != 0) goto L_0x0040
                goto L_0x0041
            L_0x0040:
                r2 = r4
            L_0x0041:
                r9[r0] = r2
                int r0 = r0 + 1
                goto L_0x0019
            L_0x0046:
                r7.array = r9
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.cio.internals.AsciiCharTree.Node.<init>(char, java.util.List, java.util.List):void");
        }

        public final char getCh() {
            return this.ch;
        }

        public final List<Node<T>> getChildren() {
            return this.children;
        }

        public final List<T> getExact() {
            return this.exact;
        }

        public final Node<T>[] getArray() {
            return this.array;
        }
    }

    public AsciiCharTree(Node<T> node) {
        Intrinsics.checkNotNullParameter(node, "root");
        this.root = node;
    }

    public final Node<T> getRoot() {
        return this.root;
    }

    public static /* synthetic */ List search$default(AsciiCharTree asciiCharTree, CharSequence charSequence, int i, int i2, boolean z, Function2 function2, int i3, Object obj) {
        int i4 = (i3 & 2) != 0 ? 0 : i;
        if ((i3 & 4) != 0) {
            i2 = charSequence.length();
        }
        return asciiCharTree.search(charSequence, i4, i2, (i3 & 8) != 0 ? false : z, function2);
    }

    public final List<T> search(CharSequence charSequence, int i, int i2, boolean z, Function2<? super Character, ? super Integer, Boolean> function2) {
        Intrinsics.checkNotNullParameter(charSequence, "sequence");
        Intrinsics.checkNotNullParameter(function2, "stopPredicate");
        if (charSequence.length() != 0) {
            Node<T> node = this.root;
            while (i < i2) {
                char charAt = charSequence.charAt(i);
                if (function2.invoke(Character.valueOf(charAt), Integer.valueOf(charAt)).booleanValue()) {
                    break;
                }
                Node<T> node2 = node.getArray()[charAt];
                if (node2 == null) {
                    node = z ? node.getArray()[Character.toLowerCase(charAt)] : null;
                    if (node == null) {
                        return CollectionsKt.emptyList();
                    }
                } else {
                    node = node2;
                }
                i++;
            }
            return node.getExact();
        }
        throw new IllegalArgumentException("Couldn't search in char tree for empty string");
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\f\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0001\u0010\u0005*\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\bJR\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00050\u0004\"\b\b\u0001\u0010\u0005*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000b0\n2\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\rJr\u0010\u0003\u001a\u00020\u000f\"\b\b\u0001\u0010\u0005*\u00020\u00012\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00050\u00120\u00112\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00050\b2\u0006\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u000b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000b0\n2\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u0002H\u0005\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000e0\rH\u0002¨\u0006\u0015"}, d2 = {"Lio/ktor/http/cio/internals/AsciiCharTree$Companion;", "", "()V", "build", "Lio/ktor/http/cio/internals/AsciiCharTree;", "T", "", "from", "", "length", "Lkotlin/Function1;", "", "charAt", "Lkotlin/Function2;", "", "", "resultList", "", "Lio/ktor/http/cio/internals/AsciiCharTree$Node;", "maxLength", "idx", "ktor-http-cio"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AsciiCharTree.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final <T extends CharSequence> AsciiCharTree<T> build(List<? extends T> list) {
            Intrinsics.checkNotNullParameter(list, TypedValues.TransitionType.S_FROM);
            return build(list, AsciiCharTree$Companion$build$1.INSTANCE, AsciiCharTree$Companion$build$2.INSTANCE);
        }

        public final <T> AsciiCharTree<T> build(List<? extends T> list, Function1<? super T, Integer> function1, Function2<? super T, ? super Integer, Character> function2) {
            Object obj;
            Intrinsics.checkNotNullParameter(list, TypedValues.TransitionType.S_FROM);
            Intrinsics.checkNotNullParameter(function1, "length");
            Intrinsics.checkNotNullParameter(function2, "charAt");
            Iterable<Object> iterable = list;
            Iterator it = iterable.iterator();
            if (!it.hasNext()) {
                obj = null;
            } else {
                Object next = it.next();
                if (!it.hasNext()) {
                    obj = next;
                } else {
                    Comparable invoke = function1.invoke(next);
                    do {
                        Object next2 = it.next();
                        Comparable invoke2 = function1.invoke(next2);
                        if (invoke.compareTo(invoke2) < 0) {
                            next = next2;
                            invoke = invoke2;
                        }
                    } while (it.hasNext());
                }
                obj = next;
            }
            if (obj != null) {
                int intValue = function1.invoke(obj).intValue();
                if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
                    for (Object invoke3 : iterable) {
                        if (function1.invoke(invoke3).intValue() == 0) {
                            throw new IllegalArgumentException("There should be no empty entries");
                        }
                    }
                }
                ArrayList arrayList = new ArrayList();
                List list2 = arrayList;
                build(list2, list, intValue, 0, function1, function2);
                arrayList.trimToSize();
                return new AsciiCharTree<>(new Node(0, CollectionsKt.emptyList(), list2));
            }
            throw new NoSuchElementException("Unable to build char tree from an empty list");
        }

        private final <T> void build(List<Node<T>> list, List<? extends T> list2, int i, int i2, Function1<? super T, Integer> function1, Function2<? super T, ? super Integer, Character> function2) {
            Function1<? super T, Integer> function12 = function1;
            Map linkedHashMap = new LinkedHashMap();
            for (Object next : list2) {
                Character invoke = function2.invoke(next, Integer.valueOf(i2));
                invoke.charValue();
                Object obj = linkedHashMap.get(invoke);
                if (obj == null) {
                    obj = new ArrayList();
                    linkedHashMap.put(invoke, obj);
                }
                ((List) obj).add(next);
            }
            Function2<? super T, ? super Integer, Character> function22 = function2;
            for (Map.Entry entry : linkedHashMap.entrySet()) {
                char charValue = ((Character) entry.getKey()).charValue();
                int i3 = i2 + 1;
                ArrayList arrayList = new ArrayList();
                Companion companion = AsciiCharTree.Companion;
                List list3 = arrayList;
                Iterable iterable = (List) entry.getValue();
                Collection arrayList2 = new ArrayList();
                for (Object next2 : iterable) {
                    if (function12.invoke(next2).intValue() > i3) {
                        arrayList2.add(next2);
                    }
                }
                companion.build(list3, (List) arrayList2, i, i3, function1, function2);
                arrayList.trimToSize();
                Collection arrayList3 = new ArrayList();
                for (Object next3 : iterable) {
                    if (function12.invoke(next3).intValue() == i3) {
                        arrayList3.add(next3);
                    }
                }
                list.add(new Node(charValue, (List) arrayList3, list3));
            }
        }
    }
}
