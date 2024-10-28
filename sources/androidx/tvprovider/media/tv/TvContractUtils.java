package androidx.tvprovider.media.tv;

import android.media.tv.TvContentRating;
import android.text.TextUtils;
import android.util.Log;
import androidx.transition.WindowIdApi18$$ExternalSyntheticApiModelOutline0;
import java.util.ArrayList;

public class TvContractUtils {
    private static final boolean DEBUG = false;
    private static final String DELIMITER = ",";
    static final TvContentRating[] EMPTY = new TvContentRating[0];
    private static final String TAG = "TvContractUtils";

    public static TvContentRating[] stringToContentRatings(String str) {
        if (TextUtils.isEmpty(str)) {
            return EMPTY;
        }
        String[] split = str.split("\\s*,\\s*", -1);
        ArrayList arrayList = new ArrayList(split.length);
        for (String str2 : split) {
            try {
                arrayList.add(WindowIdApi18$$ExternalSyntheticApiModelOutline0.m(str2));
            } catch (IllegalArgumentException e) {
                Log.w(TAG, "Can't parse the content rating: '" + str2 + "', skipping", e);
            }
        }
        if (arrayList.size() == 0) {
            return EMPTY;
        }
        return (TvContentRating[]) arrayList.toArray(new TvContentRating[arrayList.size()]);
    }

    public static String contentRatingsToString(TvContentRating[] tvContentRatingArr) {
        if (tvContentRatingArr == null || tvContentRatingArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(tvContentRatingArr[0].flattenToString());
        for (int i = 1; i < tvContentRatingArr.length; i++) {
            sb.append(",");
            sb.append(tvContentRatingArr[i].flattenToString());
        }
        return sb.toString();
    }

    public static String[] stringToAudioLanguages(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split("\\s*,\\s*");
    }

    public static String audioLanguagesToString(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder(strArr[0]);
        for (int i = 1; i < strArr.length; i++) {
            sb.append(",");
            sb.append(strArr[i]);
        }
        return sb.toString();
    }

    private TvContractUtils() {
    }
}
