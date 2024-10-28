package org.videolan.vlc.util;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.VersionMigration", f = "VersionMigration.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1}, l = {70, 73}, m = "migrateVersion", n = {"this", "context", "settings", "lastVersion", "lastMajorVersion", "this", "settings", "lastVersion", "lastMajorVersion"}, s = {"L$0", "L$1", "L$2", "I$0", "I$1", "L$0", "L$1", "I$0", "I$1"})
/* compiled from: VersionMigration.kt */
final class VersionMigration$migrateVersion$1 extends ContinuationImpl {
    int I$0;
    int I$1;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ VersionMigration this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VersionMigration$migrateVersion$1(VersionMigration versionMigration, Continuation<? super VersionMigration$migrateVersion$1> continuation) {
        super(continuation);
        this.this$0 = versionMigration;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.migrateVersion((Context) null, this);
    }
}
