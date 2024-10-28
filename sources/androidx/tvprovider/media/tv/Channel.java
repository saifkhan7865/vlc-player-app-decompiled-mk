package androidx.tvprovider.media.tv;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import androidx.tvprovider.media.tv.TvContractCompat;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

public final class Channel {
    private static final long INVALID_CHANNEL_ID = -1;
    private static final int INVALID_INT_VALUE = -1;
    private static final int IS_BROWSABLE = 1;
    private static final int IS_LOCKED = 1;
    private static final int IS_SEARCHABLE = 1;
    private static final int IS_SYSTEM_APPROVED = 1;
    private static final int IS_TRANSIENT = 1;
    public static final String[] PROJECTION = getProjection();
    ContentValues mValues;

    Channel(Builder builder) {
        this.mValues = builder.mValues;
    }

    public long getId() {
        Long asLong = this.mValues.getAsLong("_id");
        if (asLong == null) {
            return -1;
        }
        return asLong.longValue();
    }

    public String getPackageName() {
        return this.mValues.getAsString(TvContractCompat.BaseTvColumns.COLUMN_PACKAGE_NAME);
    }

    public String getInputId() {
        return this.mValues.getAsString("input_id");
    }

    public String getType() {
        return this.mValues.getAsString("type");
    }

    public String getDisplayNumber() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER);
    }

    public String getDisplayName() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_DISPLAY_NAME);
    }

    public String getDescription() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_DESCRIPTION);
    }

    public String getVideoFormat() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_VIDEO_FORMAT);
    }

    public int getOriginalNetworkId() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public int getTransportStreamId() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_TRANSPORT_STREAM_ID);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public int getServiceId() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_SERVICE_ID);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public String getAppLinkText() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_APP_LINK_TEXT);
    }

    public int getAppLinkColor() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_APP_LINK_COLOR);
        if (asInteger == null) {
            return -1;
        }
        return asInteger.intValue();
    }

    public Uri getAppLinkIconUri() {
        String asString = this.mValues.getAsString(TvContractCompat.Channels.COLUMN_APP_LINK_ICON_URI);
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    public Uri getAppLinkPosterArtUri() {
        String asString = this.mValues.getAsString(TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI);
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    public Uri getAppLinkIntentUri() {
        String asString = this.mValues.getAsString(TvContractCompat.Channels.COLUMN_APP_LINK_INTENT_URI);
        if (asString == null) {
            return null;
        }
        return Uri.parse(asString);
    }

    public Intent getAppLinkIntent() throws URISyntaxException {
        String asString = this.mValues.getAsString(TvContractCompat.Channels.COLUMN_APP_LINK_INTENT_URI);
        if (asString == null) {
            return null;
        }
        return Intent.parseUri(asString.toString(), 1);
    }

    public String getNetworkAffiliation() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_NETWORK_AFFILIATION);
    }

    public boolean isSearchable() {
        Integer asInteger = this.mValues.getAsInteger("searchable");
        if (asInteger == null || asInteger.intValue() == 1) {
            return true;
        }
        return false;
    }

    public byte[] getInternalProviderDataByteArray() {
        return this.mValues.getAsByteArray("internal_provider_data");
    }

    public String getServiceType() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_SERVICE_TYPE);
    }

    public Long getInternalProviderFlag1() {
        return this.mValues.getAsLong("internal_provider_flag1");
    }

    public Long getInternalProviderFlag2() {
        return this.mValues.getAsLong("internal_provider_flag2");
    }

    public Long getInternalProviderFlag3() {
        return this.mValues.getAsLong("internal_provider_flag3");
    }

    public Long getInternalProviderFlag4() {
        return this.mValues.getAsLong("internal_provider_flag4");
    }

    public String getInternalProviderId() {
        return this.mValues.getAsString("internal_provider_id");
    }

    public boolean isTransient() {
        Integer asInteger = this.mValues.getAsInteger("transient");
        return asInteger != null && asInteger.intValue() == 1;
    }

    public boolean isBrowsable() {
        Integer asInteger = this.mValues.getAsInteger("browsable");
        return asInteger != null && asInteger.intValue() == 1;
    }

    public boolean isSystemApproved() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_SYSTEM_APPROVED);
        return asInteger != null && asInteger.intValue() == 1;
    }

    public int getConfigurationDisplayOrder() {
        return this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_CONFIGURATION_DISPLAY_ORDER).intValue();
    }

    public String getSystemChannelKey() {
        return this.mValues.getAsString(TvContractCompat.Channels.COLUMN_SYSTEM_CHANNEL_KEY);
    }

    public boolean isLocked() {
        Integer asInteger = this.mValues.getAsInteger(TvContractCompat.Channels.COLUMN_LOCKED);
        return asInteger != null && asInteger.intValue() == 1;
    }

    public int hashCode() {
        return this.mValues.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Channel)) {
            return false;
        }
        return this.mValues.equals(((Channel) obj).mValues);
    }

    public String toString() {
        return "Channel{" + this.mValues.toString() + "}";
    }

    public ContentValues toContentValues() {
        return toContentValues(false);
    }

    public ContentValues toContentValues(boolean z) {
        ContentValues contentValues = new ContentValues(this.mValues);
        if (Build.VERSION.SDK_INT < 23) {
            contentValues.remove(TvContractCompat.Channels.COLUMN_APP_LINK_COLOR);
            contentValues.remove(TvContractCompat.Channels.COLUMN_APP_LINK_TEXT);
            contentValues.remove(TvContractCompat.Channels.COLUMN_APP_LINK_ICON_URI);
            contentValues.remove(TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI);
            contentValues.remove(TvContractCompat.Channels.COLUMN_APP_LINK_INTENT_URI);
            contentValues.remove("internal_provider_flag1");
            contentValues.remove("internal_provider_flag2");
            contentValues.remove("internal_provider_flag3");
            contentValues.remove("internal_provider_flag4");
        }
        if (Build.VERSION.SDK_INT < 26) {
            contentValues.remove("internal_provider_id");
            contentValues.remove("transient");
            contentValues.remove(TvContractCompat.Channels.COLUMN_CONFIGURATION_DISPLAY_ORDER);
            contentValues.remove(TvContractCompat.Channels.COLUMN_SYSTEM_CHANNEL_KEY);
        }
        if (!z) {
            contentValues.remove("browsable");
            contentValues.remove(TvContractCompat.Channels.COLUMN_LOCKED);
        }
        if (Build.VERSION.SDK_INT < 26 || !z) {
            contentValues.remove(TvContractCompat.Channels.COLUMN_SYSTEM_APPROVED);
        }
        return contentValues;
    }

    public static Channel fromCursor(Cursor cursor) {
        Builder builder = new Builder();
        int columnIndex = cursor.getColumnIndex("_id");
        if (columnIndex >= 0 && !cursor.isNull(columnIndex)) {
            builder.setId(cursor.getLong(columnIndex));
        }
        int columnIndex2 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_DESCRIPTION);
        if (columnIndex2 >= 0 && !cursor.isNull(columnIndex2)) {
            builder.setDescription(cursor.getString(columnIndex2));
        }
        int columnIndex3 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_DISPLAY_NAME);
        if (columnIndex3 >= 0 && !cursor.isNull(columnIndex3)) {
            builder.setDisplayName(cursor.getString(columnIndex3));
        }
        int columnIndex4 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER);
        if (columnIndex4 >= 0 && !cursor.isNull(columnIndex4)) {
            builder.setDisplayNumber(cursor.getString(columnIndex4));
        }
        int columnIndex5 = cursor.getColumnIndex("input_id");
        if (columnIndex5 >= 0 && !cursor.isNull(columnIndex5)) {
            builder.setInputId(cursor.getString(columnIndex5));
        }
        int columnIndex6 = cursor.getColumnIndex("internal_provider_data");
        if (columnIndex6 >= 0 && !cursor.isNull(columnIndex6)) {
            builder.setInternalProviderData(cursor.getBlob(columnIndex6));
        }
        int columnIndex7 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_NETWORK_AFFILIATION);
        if (columnIndex7 >= 0 && !cursor.isNull(columnIndex7)) {
            builder.setNetworkAffiliation(cursor.getString(columnIndex7));
        }
        int columnIndex8 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID);
        if (columnIndex8 >= 0 && !cursor.isNull(columnIndex8)) {
            builder.setOriginalNetworkId(cursor.getInt(columnIndex8));
        }
        int columnIndex9 = cursor.getColumnIndex(TvContractCompat.BaseTvColumns.COLUMN_PACKAGE_NAME);
        if (columnIndex9 >= 0 && !cursor.isNull(columnIndex9)) {
            builder.setPackageName(cursor.getString(columnIndex9));
        }
        int columnIndex10 = cursor.getColumnIndex("searchable");
        boolean z = false;
        if (columnIndex10 >= 0 && !cursor.isNull(columnIndex10)) {
            builder.setSearchable(cursor.getInt(columnIndex10) == 1);
        }
        int columnIndex11 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_SERVICE_ID);
        if (columnIndex11 >= 0 && !cursor.isNull(columnIndex11)) {
            builder.setServiceId(cursor.getInt(columnIndex11));
        }
        int columnIndex12 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_SERVICE_TYPE);
        if (columnIndex12 >= 0 && !cursor.isNull(columnIndex12)) {
            builder.setServiceType(cursor.getString(columnIndex12));
        }
        int columnIndex13 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_TRANSPORT_STREAM_ID);
        if (columnIndex13 >= 0 && !cursor.isNull(columnIndex13)) {
            builder.setTransportStreamId(cursor.getInt(columnIndex13));
        }
        int columnIndex14 = cursor.getColumnIndex("type");
        if (columnIndex14 >= 0 && !cursor.isNull(columnIndex14)) {
            builder.setType(cursor.getString(columnIndex14));
        }
        int columnIndex15 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_VIDEO_FORMAT);
        if (columnIndex15 >= 0 && !cursor.isNull(columnIndex15)) {
            builder.setVideoFormat(cursor.getString(columnIndex15));
        }
        int columnIndex16 = cursor.getColumnIndex("browsable");
        if (columnIndex16 >= 0 && !cursor.isNull(columnIndex16)) {
            builder.setBrowsable(cursor.getInt(columnIndex16) == 1);
        }
        int columnIndex17 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_LOCKED);
        if (columnIndex17 >= 0 && !cursor.isNull(columnIndex17)) {
            builder.setLocked(cursor.getInt(columnIndex17) == 1);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            int columnIndex18 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_APP_LINK_COLOR);
            if (columnIndex18 >= 0 && !cursor.isNull(columnIndex18)) {
                builder.setAppLinkColor(cursor.getInt(columnIndex18));
            }
            int columnIndex19 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_APP_LINK_ICON_URI);
            if (columnIndex19 >= 0 && !cursor.isNull(columnIndex19)) {
                builder.setAppLinkIconUri(Uri.parse(cursor.getString(columnIndex19)));
            }
            int columnIndex20 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_APP_LINK_INTENT_URI);
            if (columnIndex20 >= 0 && !cursor.isNull(columnIndex20)) {
                builder.setAppLinkIntentUri(Uri.parse(cursor.getString(columnIndex20)));
            }
            int columnIndex21 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI);
            if (columnIndex21 >= 0 && !cursor.isNull(columnIndex21)) {
                builder.setAppLinkPosterArtUri(Uri.parse(cursor.getString(columnIndex21)));
            }
            int columnIndex22 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_APP_LINK_TEXT);
            if (columnIndex22 >= 0 && !cursor.isNull(columnIndex22)) {
                builder.setAppLinkText(cursor.getString(columnIndex22));
            }
            int columnIndex23 = cursor.getColumnIndex("internal_provider_flag1");
            if (columnIndex23 >= 0 && !cursor.isNull(columnIndex23)) {
                builder.setInternalProviderFlag1(cursor.getLong(columnIndex23));
            }
            int columnIndex24 = cursor.getColumnIndex("internal_provider_flag2");
            if (columnIndex24 >= 0 && !cursor.isNull(columnIndex24)) {
                builder.setInternalProviderFlag2(cursor.getLong(columnIndex24));
            }
            int columnIndex25 = cursor.getColumnIndex("internal_provider_flag3");
            if (columnIndex25 >= 0 && !cursor.isNull(columnIndex25)) {
                builder.setInternalProviderFlag3(cursor.getLong(columnIndex25));
            }
            int columnIndex26 = cursor.getColumnIndex("internal_provider_flag4");
            if (columnIndex26 >= 0 && !cursor.isNull(columnIndex26)) {
                builder.setInternalProviderFlag4(cursor.getLong(columnIndex26));
            }
        }
        if (Build.VERSION.SDK_INT >= 26) {
            int columnIndex27 = cursor.getColumnIndex("internal_provider_id");
            if (columnIndex27 >= 0 && !cursor.isNull(columnIndex27)) {
                builder.setInternalProviderId(cursor.getString(columnIndex27));
            }
            int columnIndex28 = cursor.getColumnIndex("transient");
            if (columnIndex28 >= 0 && !cursor.isNull(columnIndex28)) {
                builder.setTransient(cursor.getInt(columnIndex28) == 1);
            }
            int columnIndex29 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_SYSTEM_APPROVED);
            if (columnIndex29 >= 0 && !cursor.isNull(columnIndex29)) {
                if (cursor.getInt(columnIndex29) == 1) {
                    z = true;
                }
                builder.setSystemApproved(z);
            }
            int columnIndex30 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_CONFIGURATION_DISPLAY_ORDER);
            if (columnIndex30 >= 0 && !cursor.isNull(columnIndex30)) {
                builder.setConfigurationDisplayOrder(cursor.getInt(columnIndex30));
            }
            int columnIndex31 = cursor.getColumnIndex(TvContractCompat.Channels.COLUMN_SYSTEM_CHANNEL_KEY);
            if (columnIndex31 >= 0 && !cursor.isNull(columnIndex31)) {
                builder.setSystemChannelKey(cursor.getString(columnIndex31));
            }
        }
        return builder.build();
    }

    private static String[] getProjection() {
        String[] strArr = {"_id", TvContractCompat.Channels.COLUMN_DESCRIPTION, TvContractCompat.Channels.COLUMN_DISPLAY_NAME, TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER, "input_id", "internal_provider_data", TvContractCompat.Channels.COLUMN_NETWORK_AFFILIATION, TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID, TvContractCompat.BaseTvColumns.COLUMN_PACKAGE_NAME, "searchable", TvContractCompat.Channels.COLUMN_SERVICE_ID, TvContractCompat.Channels.COLUMN_SERVICE_TYPE, TvContractCompat.Channels.COLUMN_TRANSPORT_STREAM_ID, "type", TvContractCompat.Channels.COLUMN_VIDEO_FORMAT, "browsable", TvContractCompat.Channels.COLUMN_LOCKED};
        String[] strArr2 = {TvContractCompat.Channels.COLUMN_APP_LINK_COLOR, TvContractCompat.Channels.COLUMN_APP_LINK_ICON_URI, TvContractCompat.Channels.COLUMN_APP_LINK_INTENT_URI, TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI, TvContractCompat.Channels.COLUMN_APP_LINK_TEXT, "internal_provider_flag1", "internal_provider_flag2", "internal_provider_flag3", "internal_provider_flag4"};
        if (Build.VERSION.SDK_INT >= 26) {
            return (String[]) CollectionUtils.concatAll(strArr, strArr2, new String[]{"internal_provider_id", "transient", TvContractCompat.Channels.COLUMN_SYSTEM_APPROVED, TvContractCompat.Channels.COLUMN_CONFIGURATION_DISPLAY_ORDER, TvContractCompat.Channels.COLUMN_SYSTEM_CHANNEL_KEY});
        } else if (Build.VERSION.SDK_INT < 23) {
            return strArr;
        } else {
            return (String[]) CollectionUtils.concatAll(strArr, strArr2);
        }
    }

    public static final class Builder {
        ContentValues mValues;

        public Builder() {
            this.mValues = new ContentValues();
        }

        public Builder(Channel channel) {
            this.mValues = new ContentValues(channel.mValues);
        }

        /* access modifiers changed from: package-private */
        public Builder setId(long j) {
            this.mValues.put("_id", Long.valueOf(j));
            return this;
        }

        /* access modifiers changed from: package-private */
        public Builder setPackageName(String str) {
            this.mValues.put(TvContractCompat.BaseTvColumns.COLUMN_PACKAGE_NAME, str);
            return this;
        }

        public Builder setInputId(String str) {
            this.mValues.put("input_id", str);
            return this;
        }

        public Builder setType(String str) {
            this.mValues.put("type", str);
            return this;
        }

        public Builder setDisplayNumber(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_DISPLAY_NUMBER, str);
            return this;
        }

        public Builder setDisplayName(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_DISPLAY_NAME, str);
            return this;
        }

        public Builder setDescription(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_DESCRIPTION, str);
            return this;
        }

        public Builder setVideoFormat(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_VIDEO_FORMAT, str);
            return this;
        }

        public Builder setOriginalNetworkId(int i) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_ORIGINAL_NETWORK_ID, Integer.valueOf(i));
            return this;
        }

        public Builder setTransportStreamId(int i) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_TRANSPORT_STREAM_ID, Integer.valueOf(i));
            return this;
        }

        public Builder setServiceId(int i) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_SERVICE_ID, Integer.valueOf(i));
            return this;
        }

        public Builder setInternalProviderData(byte[] bArr) {
            this.mValues.put("internal_provider_data", bArr);
            return this;
        }

        public Builder setInternalProviderData(String str) {
            this.mValues.put("internal_provider_data", str.getBytes(Charset.defaultCharset()));
            return this;
        }

        public Builder setAppLinkText(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_TEXT, str);
            return this;
        }

        public Builder setAppLinkColor(int i) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_COLOR, Integer.valueOf(i));
            return this;
        }

        public Builder setAppLinkIconUri(Uri uri) {
            String str;
            ContentValues contentValues = this.mValues;
            if (uri == null) {
                str = null;
            } else {
                str = uri.toString();
            }
            contentValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_ICON_URI, str);
            return this;
        }

        public Builder setAppLinkPosterArtUri(Uri uri) {
            String str;
            ContentValues contentValues = this.mValues;
            if (uri == null) {
                str = null;
            } else {
                str = uri.toString();
            }
            contentValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_POSTER_ART_URI, str);
            return this;
        }

        public Builder setAppLinkIntent(Intent intent) {
            return setAppLinkIntentUri(Uri.parse(intent.toUri(1)));
        }

        public Builder setAppLinkIntentUri(Uri uri) {
            String str;
            ContentValues contentValues = this.mValues;
            if (uri == null) {
                str = null;
            } else {
                str = uri.toString();
            }
            contentValues.put(TvContractCompat.Channels.COLUMN_APP_LINK_INTENT_URI, str);
            return this;
        }

        public Builder setNetworkAffiliation(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_NETWORK_AFFILIATION, str);
            return this;
        }

        public Builder setSearchable(boolean z) {
            this.mValues.put("searchable", Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Builder setServiceType(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_SERVICE_TYPE, str);
            return this;
        }

        public Builder setInternalProviderFlag1(long j) {
            this.mValues.put("internal_provider_flag1", Long.valueOf(j));
            return this;
        }

        public Builder setInternalProviderFlag2(long j) {
            this.mValues.put("internal_provider_flag2", Long.valueOf(j));
            return this;
        }

        public Builder setInternalProviderFlag3(long j) {
            this.mValues.put("internal_provider_flag3", Long.valueOf(j));
            return this;
        }

        public Builder setInternalProviderFlag4(long j) {
            this.mValues.put("internal_provider_flag4", Long.valueOf(j));
            return this;
        }

        public Builder setInternalProviderId(String str) {
            this.mValues.put("internal_provider_id", str);
            return this;
        }

        public Builder setTransient(boolean z) {
            this.mValues.put("transient", Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Builder setBrowsable(boolean z) {
            this.mValues.put("browsable", Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Builder setSystemApproved(boolean z) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_SYSTEM_APPROVED, Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Builder setConfigurationDisplayOrder(int i) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_CONFIGURATION_DISPLAY_ORDER, Integer.valueOf(i));
            return this;
        }

        public Builder setSystemChannelKey(String str) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_SYSTEM_CHANNEL_KEY, str);
            return this;
        }

        public Builder setLocked(boolean z) {
            this.mValues.put(TvContractCompat.Channels.COLUMN_LOCKED, Integer.valueOf(z ? 1 : 0));
            return this;
        }

        public Channel build() {
            return new Channel(this);
        }
    }
}
