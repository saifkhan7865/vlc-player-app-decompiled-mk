package androidx.tvprovider.media.tv;

import java.util.Arrays;

public class CollectionUtils {
    public static <T> T[] concatAll(T[] tArr, T[]... tArr2) {
        int length = tArr.length;
        for (T[] length2 : tArr2) {
            length += length2.length;
        }
        T[] copyOf = Arrays.copyOf(tArr, length);
        int length3 = tArr.length;
        for (T[] tArr3 : tArr2) {
            System.arraycopy(tArr3, 0, copyOf, length3, tArr3.length);
            length3 += tArr3.length;
        }
        return copyOf;
    }

    private CollectionUtils() {
    }
}
