package org.videolan.vlc.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.VersionMigration", f = "VersionMigration.kt", i = {0}, l = {142, 323}, m = "migrateToVersion2", n = {"context"}, s = {"L$0"})
/* compiled from: VersionMigration.kt */
final class VersionMigration$migrateToVersion2$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VersionMigration this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VersionMigration$migrateToVersion2$1(VersionMigration versionMigration, Continuation<? super VersionMigration$migrateToVersion2$1> continuation) {
        super(continuation);
        this.this$0 = versionMigration;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.migrateToVersion2((Context) null, this);
    }
}
