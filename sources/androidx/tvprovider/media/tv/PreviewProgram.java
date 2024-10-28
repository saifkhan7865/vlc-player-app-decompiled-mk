package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Build;
import androidx.tvprovider.media.tv.BasePreviewProgram;
import androidx.tvprovider.media.tv.TvContractCompat;
import j$.util.Objects;

public final class PreviewProgram extends BasePreviewProgram {
    private static final int INVALID_INT_VALUE = -1;
    private static final long INVALID_LONG_VALUE = -1;
    public static final String[] PROJECTION = getProjection();

    PreviewProgram(Builder builder) {
        super(builder);
    }

    public long getChannelId() {
        Long asLong = this.mValues.getAsLong("channel_id");
        if (asLong == null) {
            return -1;
        }
        return asLong.longValue();
    }

    public int getWeight() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.PreviewPrograms.COLUMN_WEIGHT);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PreviewProgram)) {
            return false;
        }
        return this.mValues.equals(((PreviewProgram) obj).mValues);
    }

    public boolean hasAnyUpdatedValues(PreviewProgram previewProgram) {
        for (String next : previewProgram.mValues.keySet()) {
            if (!Objects.deepEquals(previewProgram.mValues.get(next), this.mValues.get(next))) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        return "PreviewProgram{" + this.mValues.toString() + "}";
    }

    public ContentValues toContentValues() {
        return toContentValues(false);
    }

    public ContentValues toContentValues(boolean z) {
        ContentValues contentValues = super.toContentValues(z);
        if (Build.VERSION.SDK_INT < 26) {
            contentValues.remove("channel_id");
            contentValues.remove(TvContractCompat.PreviewPrograms.COLUMN_WEIGHT);
        }
        return contentValues;
    }

    public static PreviewProgram fromCursor(Cursor cursor) {
        Builder builder = new Builder();
        BasePreviewProgram.setFieldsFromCursor(cursor, builder);
        int columnIndex = cursor.getColumnIndex("channel_id");
        if (columnIndex >= 0 && !cursor.isNull(columnIndex)) {
            builder.setChannelId(cursor.getLong(columnIndex));
        }
        int columnIndex2 = cursor.getColumnIndex(TvContractCompat.PreviewPrograms.COLUMN_WEIGHT);
        if (columnIndex2 >= 0 && !cursor.isNull(columnIndex2)) {
            builder.setWeight(cursor.getInt(columnIndex2));
        }
        return builder.build();
    }

    private static String[] getProjection() {
        return (String[]) CollectionUtils.concatAll(BasePreviewProgram.PROJECTION, new String[]{"channel_id", TvContractCompat.PreviewPrograms.COLUMN_WEIGHT});
    }

    public static final class Builder extends BasePreviewProgram.Builder<Builder> {
        public Builder() {
        }

        public Builder(PreviewProgram previewProgram) {
            this.mValues = new ContentValues(previewProgram.mValues);
        }

        public Builder setChannelId(long j) {
            this.mValues.put("channel_id", Long.valueOf(j));
            return this;
        }

        public Builder setWeight(int i) {
            this.mValues.put(TvContractCompat.PreviewPrograms.COLUMN_WEIGHT, Integer.valueOf(i));
            return this;
        }

        public PreviewProgram build() {
            return new PreviewProgram(this);
        }
    }
}
