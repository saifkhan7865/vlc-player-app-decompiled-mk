package androidx.room;

import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "invoke", "(Landroidx/sqlite/db/SupportSQLiteDatabase;)Ljava/lang/Long;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AutoClosingRoomOpenHelper.kt */
final class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setMaximumSize$1 extends Lambda implements Function1<SupportSQLiteDatabase, Long> {
    final /* synthetic */ long $numBytes;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$setMaximumSize$1(long j) {
        super(1);
        this.$numBytes = j;
    }

    public final Long invoke(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "db");
        return Long.valueOf(supportSQLiteDatabase.setMaximumSize(this.$numBytes));
    }
}
