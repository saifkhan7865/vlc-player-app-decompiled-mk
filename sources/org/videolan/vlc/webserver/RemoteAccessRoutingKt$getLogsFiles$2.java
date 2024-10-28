package org.videolan.vlc.webserver;

import java.io.File;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.resources.AndroidDevices;
import org.videolan.resources.AppContextProvider;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Lorg/videolan/vlc/webserver/LogFile;", "Lkotlin/collections/ArrayList;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$getLogsFiles$2", f = "RemoteAccessRouting.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$getLogsFiles$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<LogFile>>, Object> {
    int label;

    RemoteAccessRoutingKt$getLogsFiles$2(Continuation<? super RemoteAccessRoutingKt$getLogsFiles$2> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessRoutingKt$getLogsFiles$2(continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ArrayList<LogFile>> continuation) {
        return ((RemoteAccessRoutingKt$getLogsFiles$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayList arrayList = new ArrayList();
            File[] listFiles = new File(AndroidDevices.INSTANCE.getEXTERNAL_PUBLIC_DIRECTORY()).listFiles();
            Intrinsics.checkNotNull(listFiles);
            for (File file : listFiles) {
                if (file.isFile()) {
                    String name = file.getName();
                    Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
                    if (StringsKt.startsWith$default(name, "vlc_logcat_", false, 2, (Object) null)) {
                        String path = file.getPath();
                        Intrinsics.checkNotNullExpressionValue(path, "getPath(...)");
                        String name2 = file.getName();
                        Intrinsics.checkNotNullExpressionValue(name2, "getName(...)");
                        arrayList.add(new LogFile(path, StringsKt.startsWith$default(name2, "vlc_logcat_remote_access", false, 2, (Object) null) ? "web" : "device"));
                    }
                }
            }
            File externalFilesDir = AppContextProvider.INSTANCE.getAppContext().getExternalFilesDir((String) null);
            Intrinsics.checkNotNull(externalFilesDir);
            File[] listFiles2 = new File(externalFilesDir.getAbsolutePath()).listFiles();
            Intrinsics.checkNotNull(listFiles2);
            for (File file2 : listFiles2) {
                if (file2.isFile()) {
                    String name3 = file2.getName();
                    Intrinsics.checkNotNullExpressionValue(name3, "getName(...)");
                    if (StringsKt.startsWith$default(name3, "vlc_crash", false, 2, (Object) null)) {
                        String path2 = file2.getPath();
                        Intrinsics.checkNotNullExpressionValue(path2, "getPath(...)");
                        arrayList.add(new LogFile(path2, "crash"));
                    }
                }
            }
            return arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
