package kotlin.io.path;

import java.nio.file.Path;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.SequenceScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "Ljava/nio/file/Path;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "kotlin.io.path.PathTreeWalk$bfsIterator$1", f = "PathTreeWalk.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1}, l = {184, 190}, m = "invokeSuspend", n = {"$this$iterator", "queue", "entriesReader", "pathNode", "this_$iv", "path$iv", "$this$iterator", "queue", "entriesReader"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2"})
/* compiled from: PathTreeWalk.kt */
final class PathTreeWalk$bfsIterator$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super Path>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    final /* synthetic */ PathTreeWalk this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PathTreeWalk$bfsIterator$1(PathTreeWalk pathTreeWalk, Continuation<? super PathTreeWalk$bfsIterator$1> continuation) {
        super(2, continuation);
        this.this$0 = pathTreeWalk;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PathTreeWalk$bfsIterator$1 pathTreeWalk$bfsIterator$1 = new PathTreeWalk$bfsIterator$1(this.this$0, continuation);
        pathTreeWalk$bfsIterator$1.L$0 = obj;
        return pathTreeWalk$bfsIterator$1;
    }

    public final Object invoke(SequenceScope<? super Path> sequenceScope, Continuation<? super Unit> continuation) {
        return ((PathTreeWalk$bfsIterator$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0089  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00f7  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0139 A[SYNTHETIC] */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0046
            if (r1 == r4) goto L_0x0027
            if (r1 != r2) goto L_0x001f
            java.lang.Object r1 = r12.L$2
            kotlin.io.path.DirectoryEntriesReader r1 = (kotlin.io.path.DirectoryEntriesReader) r1
            java.lang.Object r5 = r12.L$1
            kotlin.collections.ArrayDeque r5 = (kotlin.collections.ArrayDeque) r5
            java.lang.Object r6 = r12.L$0
            kotlin.sequences.SequenceScope r6 = (kotlin.sequences.SequenceScope) r6
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x007f
        L_0x001f:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x0027:
            java.lang.Object r1 = r12.L$5
            java.nio.file.Path r1 = io.ktor.util.NioPathKt$$ExternalSyntheticApiModelOutline0.m((java.lang.Object) r1)
            java.lang.Object r5 = r12.L$4
            kotlin.io.path.PathTreeWalk r5 = (kotlin.io.path.PathTreeWalk) r5
            java.lang.Object r6 = r12.L$3
            kotlin.io.path.PathNode r6 = (kotlin.io.path.PathNode) r6
            java.lang.Object r7 = r12.L$2
            kotlin.io.path.DirectoryEntriesReader r7 = (kotlin.io.path.DirectoryEntriesReader) r7
            java.lang.Object r8 = r12.L$1
            kotlin.collections.ArrayDeque r8 = (kotlin.collections.ArrayDeque) r8
            java.lang.Object r9 = r12.L$0
            kotlin.sequences.SequenceScope r9 = (kotlin.sequences.SequenceScope) r9
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x00d8
        L_0x0046:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            kotlin.sequences.SequenceScope r13 = (kotlin.sequences.SequenceScope) r13
            kotlin.collections.ArrayDeque r1 = new kotlin.collections.ArrayDeque
            r1.<init>()
            kotlin.io.path.DirectoryEntriesReader r5 = new kotlin.io.path.DirectoryEntriesReader
            kotlin.io.path.PathTreeWalk r6 = r12.this$0
            boolean r6 = r6.getFollowLinks()
            r5.<init>(r6)
            kotlin.io.path.PathNode r6 = new kotlin.io.path.PathNode
            kotlin.io.path.PathTreeWalk r7 = r12.this$0
            java.nio.file.Path r7 = r7.start
            kotlin.io.path.PathTreeWalk r8 = r12.this$0
            java.nio.file.Path r8 = r8.start
            kotlin.io.path.PathTreeWalk r9 = r12.this$0
            java.nio.file.LinkOption[] r9 = r9.getLinkOptions()
            java.lang.Object r8 = kotlin.io.path.PathTreeWalkKt.keyOf(r8, r9)
            r6.<init>(r7, r8, r3)
            r1.addLast(r6)
            r6 = r13
            r11 = r5
            r5 = r1
            r1 = r11
        L_0x007f:
            r13 = r5
            java.util.Collection r13 = (java.util.Collection) r13
            boolean r13 = r13.isEmpty()
            r13 = r13 ^ r4
            if (r13 == 0) goto L_0x0139
            java.lang.Object r13 = r5.removeFirst()
            kotlin.io.path.PathNode r13 = (kotlin.io.path.PathNode) r13
            kotlin.io.path.PathTreeWalk r7 = r12.this$0
            java.nio.file.Path r8 = r13.getPath()
            java.nio.file.LinkOption[] r9 = r7.getLinkOptions()
            int r10 = r9.length
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r9, r10)
            java.nio.file.LinkOption[] r9 = (java.nio.file.LinkOption[]) r9
            int r10 = r9.length
            java.lang.Object[] r9 = java.util.Arrays.copyOf(r9, r10)
            java.nio.file.LinkOption[] r9 = (java.nio.file.LinkOption[]) r9
            boolean r9 = java.nio.file.Files.isDirectory(r8, r9)
            if (r9 == 0) goto L_0x010c
            boolean r9 = kotlin.io.path.PathTreeWalkKt.createsCycle(r13)
            if (r9 != 0) goto L_0x0102
            boolean r9 = r7.getIncludeDirectories()
            if (r9 == 0) goto L_0x00df
            r9 = r12
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r12.L$0 = r6
            r12.L$1 = r5
            r12.L$2 = r1
            r12.L$3 = r13
            r12.L$4 = r7
            r12.L$5 = r8
            r12.label = r4
            java.lang.Object r9 = r6.yield(r8, r9)
            if (r9 != r0) goto L_0x00d1
            return r0
        L_0x00d1:
            r9 = r6
            r6 = r13
            r11 = r7
            r7 = r1
            r1 = r8
            r8 = r5
            r5 = r11
        L_0x00d8:
            r13 = r6
            r6 = r9
            r11 = r8
            r8 = r1
            r1 = r7
            r7 = r5
            r5 = r11
        L_0x00df:
            java.nio.file.LinkOption[] r7 = r7.getLinkOptions()
            int r9 = r7.length
            java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r9)
            java.nio.file.LinkOption[] r7 = (java.nio.file.LinkOption[]) r7
            int r9 = r7.length
            java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r9)
            java.nio.file.LinkOption[] r7 = (java.nio.file.LinkOption[]) r7
            boolean r7 = java.nio.file.Files.isDirectory(r8, r7)
            if (r7 == 0) goto L_0x007f
            java.util.List r13 = r1.readEntries(r13)
            java.util.Collection r13 = (java.util.Collection) r13
            r5.addAll(r13)
            goto L_0x007f
        L_0x0102:
            java.nio.file.FileSystemLoopException r13 = new java.nio.file.FileSystemLoopException
            java.lang.String r0 = r8.toString()
            r13.<init>(r0)
            throw r13
        L_0x010c:
            java.nio.file.LinkOption[] r13 = new java.nio.file.LinkOption[r4]
            r7 = 0
            java.nio.file.LinkOption r9 = kotlin.io.path.LinkFollowing$$ExternalSyntheticApiModelOutline0.m()
            r13[r7] = r9
            java.lang.Object[] r13 = java.util.Arrays.copyOf(r13, r4)
            java.nio.file.LinkOption[] r13 = (java.nio.file.LinkOption[]) r13
            boolean r13 = java.nio.file.Files.exists(r8, r13)
            if (r13 == 0) goto L_0x007f
            r13 = r12
            kotlin.coroutines.Continuation r13 = (kotlin.coroutines.Continuation) r13
            r12.L$0 = r6
            r12.L$1 = r5
            r12.L$2 = r1
            r12.L$3 = r3
            r12.L$4 = r3
            r12.L$5 = r3
            r12.label = r2
            java.lang.Object r13 = r6.yield(r8, r13)
            if (r13 != r0) goto L_0x007f
            return r0
        L_0x0139:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.path.PathTreeWalk$bfsIterator$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
