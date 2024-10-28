package org.videolan.vlc.database;

import android.content.Context;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\bE\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010S\u001a\u00020T2\u0006\u0010U\u001a\u00020V\u001a\u000e\u0010W\u001a\u00020X2\u0006\u0010Y\u001a\u00020Z\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000\"\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0011\u0010\u0011\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\"\u0011\u0010\u0013\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010\"\u0011\u0010\u0015\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010\"\u0011\u0010\u0017\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010\"\u0011\u0010\u0019\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0010\"\u0011\u0010\u001b\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0010\"\u0011\u0010\u001d\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0010\"\u0011\u0010\u001f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0010\"\u0011\u0010!\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0010\"\u0011\u0010#\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0010\"\u0011\u0010%\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0010\"\u0011\u0010'\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b(\u0010\u0010\"\u0011\u0010)\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0010\"\u0011\u0010+\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b,\u0010\u0010\"\u0011\u0010-\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0010\"\u0011\u0010/\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b0\u0010\u0010\"\u0011\u00101\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0010\"\u0011\u00103\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b4\u0010\u0010\"\u0011\u00105\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0010\"\u0011\u00107\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b8\u0010\u0010\"\u0011\u00109\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0010\"\u0011\u0010;\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b<\u0010\u0010\"\u0011\u0010=\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0010\"\u0011\u0010?\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b@\u0010\u0010\"\u0011\u0010A\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0010\"\u0011\u0010C\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\bD\u0010\u0010\"\u0011\u0010E\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0010\"\u0011\u0010G\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\bH\u0010\u0010\"\u0011\u0010I\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u0010\"\u0011\u0010K\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\bL\u0010\u0010\"\u0011\u0010M\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\u0010\"\u0011\u0010O\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\bP\u0010\u0010\"\u0011\u0010Q\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0010¨\u0006["}, d2 = {"CUSTOM_DIRECTORY_TABLE_NAME", "", "DIR_TABLE_NAME", "EXTERNAL_SUBTITLES_TABLE_NAME", "FAV_TABLE_NAME", "HISTORY_TABLE_NAME", "MEDIA_TABLE_NAME", "MRL_TABLE_NAME", "PLAYLIST_MEDIA_TABLE_NAME", "PLAYLIST_TABLE_NAME", "SEARCHHISTORY_TABLE_NAME", "SLAVES_TABLE_NAME", "WIDGET_TABLE_NAME", "migration_10_11", "Landroidx/room/migration/Migration;", "getMigration_10_11", "()Landroidx/room/migration/Migration;", "migration_11_12", "getMigration_11_12", "migration_12_13", "getMigration_12_13", "migration_13_14", "getMigration_13_14", "migration_14_15", "getMigration_14_15", "migration_15_16", "getMigration_15_16", "migration_16_17", "getMigration_16_17", "migration_17_18", "getMigration_17_18", "migration_18_19", "getMigration_18_19", "migration_19_20", "getMigration_19_20", "migration_1_2", "getMigration_1_2", "migration_20_21", "getMigration_20_21", "migration_21_22", "getMigration_21_22", "migration_22_23", "getMigration_22_23", "migration_23_24", "getMigration_23_24", "migration_24_25", "getMigration_24_25", "migration_25_26", "getMigration_25_26", "migration_26_27", "getMigration_26_27", "migration_27_28", "getMigration_27_28", "migration_28_29", "getMigration_28_29", "migration_29_30", "getMigration_29_30", "migration_2_3", "getMigration_2_3", "migration_30_31", "getMigration_30_31", "migration_31_32", "getMigration_31_32", "migration_32_33", "getMigration_32_33", "migration_33_34", "getMigration_33_34", "migration_34_35", "getMigration_34_35", "migration_3_4", "getMigration_3_4", "migration_4_5", "getMigration_4_5", "migration_5_6", "getMigration_5_6", "migration_6_7", "getMigration_6_7", "migration_7_8", "getMigration_7_8", "migration_8_9", "getMigration_8_9", "migration_9_10", "getMigration_9_10", "dropUnnecessaryTables", "", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "populateDB", "Lkotlinx/coroutines/Job;", "context", "Landroid/content/Context;", "mediadb_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Migrations.kt */
public final class MigrationsKt {
    private static final String CUSTOM_DIRECTORY_TABLE_NAME = "CustomDirectory";
    private static final String DIR_TABLE_NAME = "directories_table";
    private static final String EXTERNAL_SUBTITLES_TABLE_NAME = "external_subtitles_table";
    private static final String FAV_TABLE_NAME = "fav_table";
    private static final String HISTORY_TABLE_NAME = "history_table";
    private static final String MEDIA_TABLE_NAME = "media_table";
    private static final String MRL_TABLE_NAME = "mrl_table";
    private static final String PLAYLIST_MEDIA_TABLE_NAME = "playlist_media_table";
    private static final String PLAYLIST_TABLE_NAME = "playlist_table";
    private static final String SEARCHHISTORY_TABLE_NAME = "searchhistory_table";
    private static final String SLAVES_TABLE_NAME = "SLAVES_table";
    private static final String WIDGET_TABLE_NAME = "widget_table";
    private static final Migration migration_10_11 = new MigrationsKt$migration_10_11$1();
    private static final Migration migration_11_12 = new MigrationsKt$migration_11_12$1();
    private static final Migration migration_12_13 = new MigrationsKt$migration_12_13$1();
    private static final Migration migration_13_14 = new MigrationsKt$migration_13_14$1();
    private static final Migration migration_14_15 = new MigrationsKt$migration_14_15$1();
    private static final Migration migration_15_16 = new MigrationsKt$migration_15_16$1();
    private static final Migration migration_16_17 = new MigrationsKt$migration_16_17$1();
    private static final Migration migration_17_18 = new MigrationsKt$migration_17_18$1();
    private static final Migration migration_18_19 = new MigrationsKt$migration_18_19$1();
    private static final Migration migration_19_20 = new MigrationsKt$migration_19_20$1();
    private static final Migration migration_1_2 = new MigrationsKt$migration_1_2$1();
    private static final Migration migration_20_21 = new MigrationsKt$migration_20_21$1();
    private static final Migration migration_21_22 = new MigrationsKt$migration_21_22$1();
    private static final Migration migration_22_23 = new MigrationsKt$migration_22_23$1();
    private static final Migration migration_23_24 = new MigrationsKt$migration_23_24$1();
    private static final Migration migration_24_25 = new MigrationsKt$migration_24_25$1();
    private static final Migration migration_25_26 = new MigrationsKt$migration_25_26$1();
    private static final Migration migration_26_27 = new MigrationsKt$migration_26_27$1();
    private static final Migration migration_27_28 = new MigrationsKt$migration_27_28$1();
    private static final Migration migration_28_29 = new MigrationsKt$migration_28_29$1();
    private static final Migration migration_29_30 = new MigrationsKt$migration_29_30$1();
    private static final Migration migration_2_3 = new MigrationsKt$migration_2_3$1();
    private static final Migration migration_30_31 = new MigrationsKt$migration_30_31$1();
    private static final Migration migration_31_32 = new MigrationsKt$migration_31_32$1();
    private static final Migration migration_32_33 = new MigrationsKt$migration_32_33$1();
    private static final Migration migration_33_34 = new MigrationsKt$migration_33_34$1();
    private static final Migration migration_34_35 = new MigrationsKt$migration_34_35$1();
    private static final Migration migration_3_4 = new MigrationsKt$migration_3_4$1();
    private static final Migration migration_4_5 = new MigrationsKt$migration_4_5$1();
    private static final Migration migration_5_6 = new MigrationsKt$migration_5_6$1();
    private static final Migration migration_6_7 = new MigrationsKt$migration_6_7$1();
    private static final Migration migration_7_8 = new MigrationsKt$migration_7_8$1();
    private static final Migration migration_8_9 = new MigrationsKt$migration_8_9$1();
    private static final Migration migration_9_10 = new MigrationsKt$migration_9_10$1();

    public static final void dropUnnecessaryTables(SupportSQLiteDatabase supportSQLiteDatabase) {
        Intrinsics.checkNotNullParameter(supportSQLiteDatabase, "database");
        supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS directories_table;");
        supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS media_table;");
        supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS playlist_media_table;");
        supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS playlist_table;");
        supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS searchhistory_table;");
        supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS mrl_table;");
        supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS history_table;");
    }

    public static final Migration getMigration_1_2() {
        return migration_1_2;
    }

    public static final Migration getMigration_2_3() {
        return migration_2_3;
    }

    public static final Migration getMigration_3_4() {
        return migration_3_4;
    }

    public static final Migration getMigration_4_5() {
        return migration_4_5;
    }

    public static final Migration getMigration_5_6() {
        return migration_5_6;
    }

    public static final Migration getMigration_6_7() {
        return migration_6_7;
    }

    public static final Migration getMigration_7_8() {
        return migration_7_8;
    }

    public static final Migration getMigration_8_9() {
        return migration_8_9;
    }

    public static final Migration getMigration_9_10() {
        return migration_9_10;
    }

    public static final Migration getMigration_10_11() {
        return migration_10_11;
    }

    public static final Migration getMigration_11_12() {
        return migration_11_12;
    }

    public static final Migration getMigration_12_13() {
        return migration_12_13;
    }

    public static final Migration getMigration_13_14() {
        return migration_13_14;
    }

    public static final Migration getMigration_14_15() {
        return migration_14_15;
    }

    public static final Migration getMigration_15_16() {
        return migration_15_16;
    }

    public static final Migration getMigration_16_17() {
        return migration_16_17;
    }

    public static final Migration getMigration_17_18() {
        return migration_17_18;
    }

    public static final Migration getMigration_18_19() {
        return migration_18_19;
    }

    public static final Migration getMigration_19_20() {
        return migration_19_20;
    }

    public static final Migration getMigration_20_21() {
        return migration_20_21;
    }

    public static final Migration getMigration_21_22() {
        return migration_21_22;
    }

    public static final Migration getMigration_22_23() {
        return migration_22_23;
    }

    public static final Migration getMigration_23_24() {
        return migration_23_24;
    }

    public static final Migration getMigration_24_25() {
        return migration_24_25;
    }

    public static final Migration getMigration_25_26() {
        return migration_25_26;
    }

    public static final Migration getMigration_26_27() {
        return migration_26_27;
    }

    public static final Migration getMigration_27_28() {
        return migration_27_28;
    }

    public static final Migration getMigration_28_29() {
        return migration_28_29;
    }

    public static final Migration getMigration_29_30() {
        return migration_29_30;
    }

    public static final Migration getMigration_30_31() {
        return migration_30_31;
    }

    public static final Migration getMigration_31_32() {
        return migration_31_32;
    }

    public static final Migration getMigration_32_33() {
        return migration_32_33;
    }

    public static final Migration getMigration_33_34() {
        return migration_33_34;
    }

    public static final Migration getMigration_34_35() {
        return migration_34_35;
    }

    public static final Job populateDB(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), (CoroutineStart) null, new MigrationsKt$populateDB$1(context, (Continuation<? super MigrationsKt$populateDB$1>) null), 2, (Object) null);
    }
}
