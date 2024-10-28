package org.videolan.vlc.util;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.VersionMigration$migrateToVersion2$2", f = "VersionMigration.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VersionMigration.kt */
final class VersionMigration$migrateToVersion2$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
    final /* synthetic */ Context $context;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VersionMigration$migrateToVersion2$2(Context context, Continuation<? super VersionMigration$migrateToVersion2$2> continuation) {
        super(2, continuation);
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        VersionMigration$migrateToVersion2$2 versionMigration$migrateToVersion2$2 = new VersionMigration$migrateToVersion2$2(this.$context, continuation);
        versionMigration$migrateToVersion2$2.L$0 = obj;
        return versionMigration$migrateToVersion2$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<Object> continuation) {
        return ((VersionMigration$migrateToVersion2$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            try {
                File externalFilesDir = this.$context.getExternalFilesDir((String) null);
                if (externalFilesDir == null) {
                    return null;
                }
                File[] listFiles = new File(externalFilesDir.getAbsolutePath() + Medialibrary.MEDIALIB_FOLDER_NAME).listFiles();
                if (listFiles == null) {
                    return null;
                }
                Intrinsics.checkNotNull(listFiles);
                for (File file : listFiles) {
                    if (file.isFile()) {
                        FileUtils fileUtils = FileUtils.INSTANCE;
                        Intrinsics.checkNotNull(file);
                        fileUtils.deleteFile(file);
                    }
                }
                return Unit.INSTANCE;
            } catch (IOException e) {
                return Boxing.boxInt(Log.e(coroutineScope.getClass().getSimpleName(), e.getMessage(), e));
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
