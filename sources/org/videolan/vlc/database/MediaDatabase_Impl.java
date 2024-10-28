package org.videolan.vlc.database;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.videolan.resources.Constants;
import org.videolan.vlc.ArtworkProvider;

public final class MediaDatabase_Impl extends MediaDatabase {
    private volatile BrowserFavDao _browserFavDao;
    private volatile CustomDirectoryDao _customDirectoryDao;
    private volatile ExternalSubDao _externalSubDao;
    private volatile SlaveDao _slaveDao;
    private volatile WidgetDao _widgetDao;

    /* access modifiers changed from: protected */
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(35) {
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `external_subtitles_table` (`idSubtitle` TEXT NOT NULL, `subtitlePath` TEXT NOT NULL, `mediaPath` TEXT NOT NULL, `subLanguageID` TEXT NOT NULL, `movieReleaseName` TEXT NOT NULL, PRIMARY KEY(`mediaPath`, `idSubtitle`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `SLAVES_table` (`slave_media_mrl` TEXT NOT NULL, `slave_type` INTEGER NOT NULL, `slave_priority` INTEGER NOT NULL, `slave_uri` TEXT NOT NULL, PRIMARY KEY(`slave_media_mrl`, `slave_uri`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `fav_table` (`uri` TEXT NOT NULL, `type` INTEGER NOT NULL, `title` TEXT NOT NULL, `icon_url` TEXT, PRIMARY KEY(`uri`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `CustomDirectory` (`path` TEXT NOT NULL, PRIMARY KEY(`path`))");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `widget_table` (`id` INTEGER NOT NULL, `width` INTEGER NOT NULL, `height` INTEGER NOT NULL, `theme` INTEGER NOT NULL, `type` INTEGER NOT NULL, `light_theme` INTEGER NOT NULL, `background_color` INTEGER NOT NULL, `foreground_color` INTEGER NOT NULL, `forward_delay` INTEGER NOT NULL, `rewind_delay` INTEGER NOT NULL, `opacity` INTEGER NOT NULL, `show_configure` INTEGER NOT NULL, `show_seek` INTEGER NOT NULL, `show_cover` INTEGER NOT NULL, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '7309a5bb3da6bed3da98b3c73d7b11bb')");
            }

            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `external_subtitles_table`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `SLAVES_table`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `fav_table`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `CustomDirectory`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `widget_table`");
                List<RoomDatabase.Callback> access$000 = MediaDatabase_Impl.this.mCallbacks;
                if (access$000 != null) {
                    for (RoomDatabase.Callback onDestructiveMigration : access$000) {
                        onDestructiveMigration.onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                List<RoomDatabase.Callback> access$100 = MediaDatabase_Impl.this.mCallbacks;
                if (access$100 != null) {
                    for (RoomDatabase.Callback onCreate : access$100) {
                        onCreate.onCreate(supportSQLiteDatabase);
                    }
                }
            }

            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase unused = MediaDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                MediaDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                List<RoomDatabase.Callback> access$400 = MediaDatabase_Impl.this.mCallbacks;
                if (access$400 != null) {
                    for (RoomDatabase.Callback onOpen : access$400) {
                        onOpen.onOpen(supportSQLiteDatabase);
                    }
                }
            }

            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                SupportSQLiteDatabase supportSQLiteDatabase2 = supportSQLiteDatabase;
                HashMap hashMap = new HashMap(5);
                hashMap.put("idSubtitle", new TableInfo.Column("idSubtitle", "TEXT", true, 2, (String) null, 1));
                hashMap.put("subtitlePath", new TableInfo.Column("subtitlePath", "TEXT", true, 0, (String) null, 1));
                hashMap.put("mediaPath", new TableInfo.Column("mediaPath", "TEXT", true, 1, (String) null, 1));
                hashMap.put("subLanguageID", new TableInfo.Column("subLanguageID", "TEXT", true, 0, (String) null, 1));
                hashMap.put("movieReleaseName", new TableInfo.Column("movieReleaseName", "TEXT", true, 0, (String) null, 1));
                TableInfo tableInfo = new TableInfo("external_subtitles_table", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase2, "external_subtitles_table");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "external_subtitles_table(org.videolan.vlc.mediadb.models.ExternalSub).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(4);
                hashMap2.put("slave_media_mrl", new TableInfo.Column("slave_media_mrl", "TEXT", true, 1, (String) null, 1));
                hashMap2.put("slave_type", new TableInfo.Column("slave_type", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("slave_priority", new TableInfo.Column("slave_priority", "INTEGER", true, 0, (String) null, 1));
                hashMap2.put("slave_uri", new TableInfo.Column("slave_uri", "TEXT", true, 2, (String) null, 1));
                TableInfo tableInfo2 = new TableInfo("SLAVES_table", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase2, "SLAVES_table");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "SLAVES_table(org.videolan.vlc.mediadb.models.Slave).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(4);
                hashMap3.put(Constants.KEY_URI, new TableInfo.Column(Constants.KEY_URI, "TEXT", true, 1, (String) null, 1));
                hashMap3.put("type", new TableInfo.Column("type", "INTEGER", true, 0, (String) null, 1));
                hashMap3.put("title", new TableInfo.Column("title", "TEXT", true, 0, (String) null, 1));
                hashMap3.put("icon_url", new TableInfo.Column("icon_url", "TEXT", false, 0, (String) null, 1));
                TableInfo tableInfo3 = new TableInfo("fav_table", hashMap3, new HashSet(0), new HashSet(0));
                TableInfo read3 = TableInfo.read(supportSQLiteDatabase2, "fav_table");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, "fav_table(org.videolan.vlc.mediadb.models.BrowserFav).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                HashMap hashMap4 = new HashMap(1);
                hashMap4.put(ArtworkProvider.PATH, new TableInfo.Column(ArtworkProvider.PATH, "TEXT", true, 1, (String) null, 1));
                TableInfo tableInfo4 = new TableInfo("CustomDirectory", hashMap4, new HashSet(0), new HashSet(0));
                TableInfo read4 = TableInfo.read(supportSQLiteDatabase2, "CustomDirectory");
                if (!tableInfo4.equals(read4)) {
                    return new RoomOpenHelper.ValidationResult(false, "CustomDirectory(org.videolan.vlc.mediadb.models.CustomDirectory).\n Expected:\n" + tableInfo4 + "\n Found:\n" + read4);
                }
                HashMap hashMap5 = new HashMap(14);
                hashMap5.put("id", new TableInfo.Column("id", "INTEGER", true, 1, (String) null, 1));
                hashMap5.put("width", new TableInfo.Column("width", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("height", new TableInfo.Column("height", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("theme", new TableInfo.Column("theme", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("type", new TableInfo.Column("type", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("light_theme", new TableInfo.Column("light_theme", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("background_color", new TableInfo.Column("background_color", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("foreground_color", new TableInfo.Column("foreground_color", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("forward_delay", new TableInfo.Column("forward_delay", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("rewind_delay", new TableInfo.Column("rewind_delay", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("opacity", new TableInfo.Column("opacity", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("show_configure", new TableInfo.Column("show_configure", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("show_seek", new TableInfo.Column("show_seek", "INTEGER", true, 0, (String) null, 1));
                hashMap5.put("show_cover", new TableInfo.Column("show_cover", "INTEGER", true, 0, (String) null, 1));
                TableInfo tableInfo5 = new TableInfo("widget_table", hashMap5, new HashSet(0), new HashSet(0));
                TableInfo read5 = TableInfo.read(supportSQLiteDatabase2, "widget_table");
                if (tableInfo5.equals(read5)) {
                    return new RoomOpenHelper.ValidationResult(true, (String) null);
                }
                return new RoomOpenHelper.ValidationResult(false, "widget_table(org.videolan.vlc.mediadb.models.Widget).\n Expected:\n" + tableInfo5 + "\n Found:\n" + read5);
            }
        }, "7309a5bb3da6bed3da98b3c73d7b11bb", "d082e6a1f063198a26013f8e4648ccba")).build());
    }

    /* access modifiers changed from: protected */
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "external_subtitles_table", "SLAVES_table", "fav_table", "CustomDirectory", "widget_table");
    }

    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `external_subtitles_table`");
            writableDatabase.execSQL("DELETE FROM `SLAVES_table`");
            writableDatabase.execSQL("DELETE FROM `fav_table`");
            writableDatabase.execSQL("DELETE FROM `CustomDirectory`");
            writableDatabase.execSQL("DELETE FROM `widget_table`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    /* access modifiers changed from: protected */
    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(ExternalSubDao.class, ExternalSubDao_Impl.getRequiredConverters());
        hashMap.put(SlaveDao.class, SlaveDao_Impl.getRequiredConverters());
        hashMap.put(BrowserFavDao.class, BrowserFavDao_Impl.getRequiredConverters());
        hashMap.put(WidgetDao.class, WidgetDao_Impl.getRequiredConverters());
        hashMap.put(CustomDirectoryDao.class, CustomDirectoryDao_Impl.getRequiredConverters());
        return hashMap;
    }

    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        return new ArrayList();
    }

    public ExternalSubDao externalSubDao() {
        ExternalSubDao externalSubDao;
        if (this._externalSubDao != null) {
            return this._externalSubDao;
        }
        synchronized (this) {
            if (this._externalSubDao == null) {
                this._externalSubDao = new ExternalSubDao_Impl(this);
            }
            externalSubDao = this._externalSubDao;
        }
        return externalSubDao;
    }

    public SlaveDao slaveDao() {
        SlaveDao slaveDao;
        if (this._slaveDao != null) {
            return this._slaveDao;
        }
        synchronized (this) {
            if (this._slaveDao == null) {
                this._slaveDao = new SlaveDao_Impl(this);
            }
            slaveDao = this._slaveDao;
        }
        return slaveDao;
    }

    public BrowserFavDao browserFavDao() {
        BrowserFavDao browserFavDao;
        if (this._browserFavDao != null) {
            return this._browserFavDao;
        }
        synchronized (this) {
            if (this._browserFavDao == null) {
                this._browserFavDao = new BrowserFavDao_Impl(this);
            }
            browserFavDao = this._browserFavDao;
        }
        return browserFavDao;
    }

    public WidgetDao widgetDao() {
        WidgetDao widgetDao;
        if (this._widgetDao != null) {
            return this._widgetDao;
        }
        synchronized (this) {
            if (this._widgetDao == null) {
                this._widgetDao = new WidgetDao_Impl(this);
            }
            widgetDao = this._widgetDao;
        }
        return widgetDao;
    }

    public CustomDirectoryDao customDirectoryDao() {
        CustomDirectoryDao customDirectoryDao;
        if (this._customDirectoryDao != null) {
            return this._customDirectoryDao;
        }
        synchronized (this) {
            if (this._customDirectoryDao == null) {
                this._customDirectoryDao = new CustomDirectoryDao_Impl(this);
            }
            customDirectoryDao = this._customDirectoryDao;
        }
        return customDirectoryDao;
    }
}
