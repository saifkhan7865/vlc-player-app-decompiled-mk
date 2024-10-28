package androidx.car.app.model;

import android.text.Spanned;
import android.text.style.CharacterStyle;
import j$.util.Objects;
import java.util.List;

public final class ModelUtils {
    public static void validateAllNonBrowsableRowsHaveDistance(List<Item> list) {
        int i = 0;
        int i2 = 0;
        for (Item next : list) {
            if (next instanceof Row) {
                Row row = (Row) next;
                if (!row.isBrowsable()) {
                    i++;
                }
                if (checkRowHasSpanType(row, DistanceSpan.class)) {
                    i2++;
                }
            } else {
                throw new IllegalArgumentException("Item in the list is not a Row");
            }
        }
        if (i > i2) {
            throw new IllegalArgumentException("All non-browsable rows must have a distance span attached to either its title or texts");
        }
    }

    public static void validateAllRowsHaveDistanceOrDuration(List<Item> list) {
        for (Item next : list) {
            if (next instanceof Row) {
                Row row = (Row) next;
                if (!checkRowHasSpanType(row, DistanceSpan.class) && !checkRowHasSpanType(row, DurationSpan.class)) {
                    throw new IllegalArgumentException("All rows must have either a distance or duration span attached to either its title or texts");
                }
            } else {
                throw new IllegalArgumentException("Item in the list is not a Row");
            }
        }
    }

    public static void validateAllRowsHaveOnlySmallImages(List<Item> list) {
        for (Item next : list) {
            if (next instanceof Row) {
                Row row = (Row) next;
                if (row.getImage() != null && row.getRowImageType() == 2) {
                    throw new IllegalArgumentException("Rows must only use small-sized images");
                }
            } else {
                throw new IllegalArgumentException("Item in the list is not a Row");
            }
        }
    }

    public static void validateNoRowsHaveBothMarkersAndImages(List<Item> list) {
        for (Item next : list) {
            if (next instanceof Row) {
                Row row = (Row) next;
                Metadata metadata = row.getMetadata();
                if (metadata != null) {
                    boolean z = true;
                    boolean z2 = row.getImage() != null;
                    Place place = metadata.getPlace();
                    if (place == null || place.getMarker() == null) {
                        z = false;
                    }
                    if (z2 && z) {
                        throw new IllegalArgumentException("Rows can't have both a marker and an image");
                    }
                }
            } else {
                throw new IllegalArgumentException("Item in the list is not a Row");
            }
        }
    }

    private static boolean checkRowHasSpanType(Row row, Class<? extends CharacterStyle> cls) {
        if (checkCarTextHasSpanType((CarText) Objects.requireNonNull(row.getTitle()), cls)) {
            return true;
        }
        List<CarText> texts = row.getTexts();
        for (int i = 0; i < texts.size(); i++) {
            if (checkCarTextHasSpanType(texts.get(i), cls)) {
                return true;
            }
        }
        return false;
    }

    private static boolean checkCarTextHasSpanType(CarText carText, Class<? extends CharacterStyle> cls) {
        if (carText.isEmpty()) {
            return false;
        }
        Spanned spanned = (Spanned) carText.toCharSequence();
        for (Object obj : spanned.getSpans(0, spanned.length(), Object.class)) {
            int spanStart = spanned.getSpanStart(obj);
            int spanEnd = spanned.getSpanEnd(obj);
            if (cls.isInstance(obj) && spanStart >= 0 && spanStart != spanEnd && spanStart < spanned.length()) {
                return true;
            }
        }
        return false;
    }

    private ModelUtils() {
    }
}
