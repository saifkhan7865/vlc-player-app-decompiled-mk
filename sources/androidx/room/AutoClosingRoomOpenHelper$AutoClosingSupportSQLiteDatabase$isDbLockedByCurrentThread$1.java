package androidx.room;

import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.jvm.internal.PropertyReference1Impl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: AutoClosingRoomOpenHelper.kt */
/* synthetic */ class AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isDbLockedByCurrentThread$1 extends PropertyReference1Impl {
    public static final AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isDbLockedByCurrentThread$1 INSTANCE = new AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isDbLockedByCurrentThread$1();

    AutoClosingRoomOpenHelper$AutoClosingSupportSQLiteDatabase$isDbLockedByCurrentThread$1() {
        super(SupportSQLiteDatabase.class, "isDbLockedByCurrentThread", "isDbLockedByCurrentThread()Z", 0);
    }

    public Object get(Object obj) {
        return Boolean.valueOf(((SupportSQLiteDatabase) obj).isDbLockedByCurrentThread());
    }
}
