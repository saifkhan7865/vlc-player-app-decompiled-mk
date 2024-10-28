package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import androidx.tvprovider.media.tv.BaseProgram;
import androidx.tvprovider.media.tv.TvContractCompat;

public final class Program extends BaseProgram implements Comparable<Program> {
    private static final long INVALID_LONG_VALUE = -1;
    private static final int IS_RECORDING_PROHIBITED = 1;
    public static final String[] PROJECTION = getProjection();

    Program(Builder builder) {
        super(builder);
    }

    public long getChannelId() {
        Long asLong = this.mValues.getAsLong("channel_id");
        if (asLong == null) {
            return -1;
        }
        return asLong.longValue();
    }

    public long getStartTimeUtcMillis() {
        Long asLong = this.mValues.getAsLong("start_time_utc_millis");
        if (asLong == null) {
            return -1;
        }
        return asLong.longValue();
    }

    public long getEndTimeUtcMillis() {
        Long asLong = this.mValues.getAsLong("end_time_utc_millis");
        if (asLong == null) {
            return -1;
        }
        return asLong.longValue();
    }

    public String[] getBroadcastGenres() {
        return TvContractCompat.Programs.Genres.decode(this.mValues.getAsString("broadcast_genre"));
    }

    public boolean isRecordingProhibited() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED);
        return asInteger != null && asInteger.intValue() == 1;
    }

    public int hashCode() {
        return this.mValues.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Program)) {
            return false;
        }
        return this.mValues.equals(((Program) obj).mValues);
    }

    public int compareTo(Program program) {
        return (this.mValues.getAsLong("start_time_utc_millis").longValue() > program.mValues.getAsLong("start_time_utc_millis").longValue() ? 1 : (this.mValues.getAsLong("start_time_utc_millis").longValue() == program.mValues.getAsLong("start_time_utc_millis").longValue() ? 0 : -1));
    }

    public String toString() {
        return "Program{" + this.mValues.toString() + "}";
    }

    public ContentValues toContentValues() {
        ContentValues contentValues = super.toContentValues();
        if (Build.VERSION.SDK_INT < 24) {
            contentValues.remove(TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED);
        }
        return contentValues;
    }

    public static Program fromCursor(Cursor cursor) {
        int columnIndex;
        Builder builder = new Builder();
        BaseProgram.setFieldsFromCursor(cursor, builder);
        int columnIndex2 = cursor.getColumnIndex("channel_id");
        if (columnIndex2 >= 0 && !cursor.isNull(columnIndex2)) {
            builder.setChannelId(cursor.getLong(columnIndex2));
        }
        int columnIndex3 = cursor.getColumnIndex("broadcast_genre");
        if (columnIndex3 >= 0 && !cursor.isNull(columnIndex3)) {
            builder.setBroadcastGenres(TvContractCompat.Programs.Genres.decode(cursor.getString(columnIndex3)));
        }
        int columnIndex4 = cursor.getColumnIndex("start_time_utc_millis");
        if (columnIndex4 >= 0 && !cursor.isNull(columnIndex4)) {
            builder.setStartTimeUtcMillis(cursor.getLong(columnIndex4));
        }
        int columnIndex5 = cursor.getColumnIndex("end_time_utc_millis");
        if (columnIndex5 >= 0 && !cursor.isNull(columnIndex5)) {
            builder.setEndTimeUtcMillis(cursor.getLong(columnIndex5));
        }
        if (Build.VERSION.SDK_INT >= 24 && (columnIndex = cursor.getColumnIndex(TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED)) >= 0 && !cursor.isNull(columnIndex)) {
            int i = cursor.getInt(columnIndex);
            boolean z = true;
            if (i != 1) {
                z = false;
            }
            builder.setRecordingProhibited(z);
        }
        return builder.build();
    }

    private static String[] getProjection() {
        String[] strArr = {"channel_id", "broadcast_genre", "start_time_utc_millis", "end_time_utc_millis"};
        if (Build.VERSION.SDK_INT >= 24) {
            return (String[]) CollectionUtils.concatAll(BaseProgram.PROJECTION, strArr, new String[]{TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED});
        }
        return (String[]) CollectionUtils.concatAll(BaseProgram.PROJECTION, strArr);
    }

    public static class Builder extends BaseProgram.Builder<Builder> {
        public Builder() {
        }

        public Builder(Program program) {
            this.mValues = new ContentValues(program.mValues);
        }

        public Builder setChannelId(long j) {
            this.mValues.put("channel_id", Long.valueOf(j));
            return this;
        }

        public Builder setStartTimeUtcMillis(long j) {
            this.mValues.put("start_time_utc_millis", Long.valueOf(j));
            return this;
        }

        public Builder setEndTimeUtcMillis(long j) {
            this.mValues.put("end_time_utc_millis", Long.valueOf(j));
            return this;
        }

        public Builder setBroadcastGenres(String[] strArr) {
            this.mValues.put("broadcast_genre", TvContractCompat.Programs.Genres.encode(strArr));
            return this;
        }

        public Builder setRecordingProhibited(boolean z) {
            this.mValues.put(TvContractCompat.Programs.COLUMN_RECORDING_PROHIBITED, Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Program build() {
            return new Program(this);
        }
    }
}
