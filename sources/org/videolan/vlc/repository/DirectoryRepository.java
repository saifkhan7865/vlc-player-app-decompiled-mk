package org.videolan.vlc.repository;

import android.content.Context;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.tools.IOScopedObject;
import org.videolan.tools.SingletonHolder;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.database.CustomDirectoryDao;
import org.videolan.vlc.mediadb.models.CustomDirectory;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bH@¢\u0006\u0002\u0010\u000bJ\u000e\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH@¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\u0012H@¢\u0006\u0002\u0010\u0010J\u001c\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00140\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H@¢\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/videolan/vlc/repository/DirectoryRepository;", "Lorg/videolan/tools/IOScopedObject;", "customDirectoryDao", "Lorg/videolan/vlc/database/CustomDirectoryDao;", "(Lorg/videolan/vlc/database/CustomDirectoryDao;)V", "addCustomDirectory", "Lkotlinx/coroutines/Job;", "path", "", "customDirectoryExists", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCustomDirectory", "getCustomDirectories", "", "Lorg/videolan/vlc/mediadb/models/CustomDirectory;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getMediaDirectories", "", "getMediaDirectoriesList", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: DirectoryRepository.kt */
public final class DirectoryRepository extends IOScopedObject {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public final CustomDirectoryDao customDirectoryDao;

    public DirectoryRepository(CustomDirectoryDao customDirectoryDao2) {
        Intrinsics.checkNotNullParameter(customDirectoryDao2, "customDirectoryDao");
        this.customDirectoryDao = customDirectoryDao2;
    }

    public final Job addCustomDirectory(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new DirectoryRepository$addCustomDirectory$1(this, str, (Continuation<? super DirectoryRepository$addCustomDirectory$1>) null), 3, (Object) null);
    }

    public final Object getCustomDirectories(Continuation<? super List<CustomDirectory>> continuation) {
        return BuildersKt.withContext(getCoroutineContext(), new DirectoryRepository$getCustomDirectories$2(this, (Continuation<? super DirectoryRepository$getCustomDirectories$2>) null), continuation);
    }

    public final Job deleteCustomDirectory(String str) {
        Intrinsics.checkNotNullParameter(str, ArtworkProvider.PATH);
        return BuildersKt__Builders_commonKt.launch$default(this, (CoroutineContext) null, (CoroutineStart) null, new DirectoryRepository$deleteCustomDirectory$1(this, str, (Continuation<? super DirectoryRepository$deleteCustomDirectory$1>) null), 3, (Object) null);
    }

    public final Object customDirectoryExists(String str, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(getCoroutineContext(), new DirectoryRepository$customDirectoryExists$2(this, str, (Continuation<? super DirectoryRepository$customDirectoryExists$2>) null), continuation);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0088 A[LOOP:1: B:22:0x0082->B:24:0x0088, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getMediaDirectoriesList(android.content.Context r5, kotlin.coroutines.Continuation<? super java.util.List<? extends org.videolan.medialibrary.interfaces.media.MediaWrapper>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof org.videolan.vlc.repository.DirectoryRepository$getMediaDirectoriesList$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            org.videolan.vlc.repository.DirectoryRepository$getMediaDirectoriesList$1 r0 = (org.videolan.vlc.repository.DirectoryRepository$getMediaDirectoriesList$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.repository.DirectoryRepository$getMediaDirectoriesList$1 r0 = new org.videolan.vlc.repository.DirectoryRepository$getMediaDirectoriesList$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            android.content.Context r5 = (android.content.Context) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0044
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r5
            r0.label = r3
            java.lang.Object r6 = r4.getMediaDirectories(r0)
            if (r6 != r1) goto L_0x0044
            return r1
        L_0x0044:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r6 = r6.iterator()
        L_0x0051:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x006d
            java.lang.Object r1 = r6.next()
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2
            java.io.File r3 = new java.io.File
            r3.<init>(r2)
            boolean r2 = r3.exists()
            if (r2 == 0) goto L_0x0051
            r0.add(r1)
            goto L_0x0051
        L_0x006d:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r6 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r0, r1)
            r6.<init>(r1)
            java.util.Collection r6 = (java.util.Collection) r6
            java.util.Iterator r0 = r0.iterator()
        L_0x0082:
            boolean r1 = r0.hasNext()
            if (r1 == 0) goto L_0x0096
            java.lang.Object r1 = r0.next()
            java.lang.String r1 = (java.lang.String) r1
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = org.videolan.vlc.repository.DirectoryRepositoryKt.createDirectory(r1, r5)
            r6.add(r1)
            goto L_0x0082
        L_0x0096:
            java.util.List r6 = (java.util.List) r6
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.repository.DirectoryRepository.getMediaDirectoriesList(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0081 A[LOOP:0: B:17:0x007b->B:19:0x0081, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getMediaDirectories(kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof org.videolan.vlc.repository.DirectoryRepository$getMediaDirectories$1
            if (r0 == 0) goto L_0x0014
            r0 = r5
            org.videolan.vlc.repository.DirectoryRepository$getMediaDirectories$1 r0 = (org.videolan.vlc.repository.DirectoryRepository$getMediaDirectories$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r5 = r0.label
            int r5 = r5 - r2
            r0.label = r5
            goto L_0x0019
        L_0x0014:
            org.videolan.vlc.repository.DirectoryRepository$getMediaDirectories$1 r0 = new org.videolan.vlc.repository.DirectoryRepository$getMediaDirectories$1
            r0.<init>(r4, r5)
        L_0x0019:
            java.lang.Object r5 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x003a
            if (r2 != r3) goto L_0x0032
            java.lang.Object r1 = r0.L$1
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r0 = r0.L$0
            java.util.List r0 = (java.util.List) r0
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0068
        L_0x0032:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x003a:
            kotlin.ResultKt.throwOnFailure(r5)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.List r5 = (java.util.List) r5
            org.videolan.resources.AndroidDevices r2 = org.videolan.resources.AndroidDevices.INSTANCE
            java.lang.String r2 = r2.getEXTERNAL_PUBLIC_DIRECTORY()
            r5.add(r2)
            org.videolan.resources.AndroidDevices r2 = org.videolan.resources.AndroidDevices.INSTANCE
            java.util.List r2 = r2.getExternalStorageDirectories()
            java.util.Collection r2 = (java.util.Collection) r2
            r5.addAll(r2)
            r0.L$0 = r5
            r0.L$1 = r5
            r0.label = r3
            java.lang.Object r0 = r4.getCustomDirectories(r0)
            if (r0 != r1) goto L_0x0065
            return r1
        L_0x0065:
            r1 = r5
            r5 = r0
            r0 = r1
        L_0x0068:
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r2 = new java.util.ArrayList
            r3 = 10
            int r3 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r5, r3)
            r2.<init>(r3)
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r5 = r5.iterator()
        L_0x007b:
            boolean r3 = r5.hasNext()
            if (r3 == 0) goto L_0x008f
            java.lang.Object r3 = r5.next()
            org.videolan.vlc.mediadb.models.CustomDirectory r3 = (org.videolan.vlc.mediadb.models.CustomDirectory) r3
            java.lang.String r3 = r3.getPath()
            r2.add(r3)
            goto L_0x007b
        L_0x008f:
            java.util.List r2 = (java.util.List) r2
            java.util.Collection r2 = (java.util.Collection) r2
            r1.addAll(r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.repository.DirectoryRepository.getMediaDirectories(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lorg/videolan/vlc/repository/DirectoryRepository$Companion;", "Lorg/videolan/tools/SingletonHolder;", "Lorg/videolan/vlc/repository/DirectoryRepository;", "Landroid/content/Context;", "()V", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: DirectoryRepository.kt */
    public static final class Companion extends SingletonHolder<DirectoryRepository, Context> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
            super(AnonymousClass1.INSTANCE);
        }
    }
}
