package org.videolan.vlc.util;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016J\b\u0010\u0007\u001a\u00020\u0003H\u0016J\b\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\u0003H\u0016J\b\u0010\r\u001a\u00020\u0003H\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016J\b\u0010\u0010\u001a\u00020\u0003H\u0016J\b\u0010\u0011\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/util/SortModule;", "", "canSortBy", "", "sort", "", "canSortByAlbum", "canSortByArtist", "canSortByDuration", "canSortByFileNameName", "canSortByFileSize", "canSortByInsertionDate", "canSortByLastModified", "canSortByMediaNumber", "canSortByName", "canSortByPlayCount", "canSortByReleaseDate", "canSortByTrackId", "", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ModelsHelper.kt */
public interface SortModule {
    boolean canSortBy(int i);

    boolean canSortByAlbum();

    boolean canSortByArtist();

    boolean canSortByDuration();

    boolean canSortByFileNameName();

    boolean canSortByFileSize();

    boolean canSortByInsertionDate();

    boolean canSortByLastModified();

    boolean canSortByMediaNumber();

    boolean canSortByName();

    boolean canSortByPlayCount();

    boolean canSortByReleaseDate();

    boolean canSortByTrackId();

    void sort(int i);

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    /* compiled from: ModelsHelper.kt */
    public static final class DefaultImpls {
        public static boolean canSortByAlbum(SortModule sortModule) {
            return false;
        }

        public static boolean canSortByArtist(SortModule sortModule) {
            return false;
        }

        public static boolean canSortByDuration(SortModule sortModule) {
            return false;
        }

        public static boolean canSortByFileNameName(SortModule sortModule) {
            return false;
        }

        public static boolean canSortByFileSize(SortModule sortModule) {
            return false;
        }

        public static boolean canSortByInsertionDate(SortModule sortModule) {
            return false;
        }

        public static boolean canSortByLastModified(SortModule sortModule) {
            return false;
        }

        public static boolean canSortByMediaNumber(SortModule sortModule) {
            return false;
        }

        public static boolean canSortByName(SortModule sortModule) {
            return true;
        }

        public static boolean canSortByPlayCount(SortModule sortModule) {
            return false;
        }

        public static boolean canSortByReleaseDate(SortModule sortModule) {
            return false;
        }

        public static boolean canSortByTrackId(SortModule sortModule) {
            return false;
        }

        public static boolean canSortBy(SortModule sortModule, int i) {
            if (i == 12) {
                return sortModule.canSortByTrackId();
            }
            if (i == 15) {
                return sortModule.canSortByMediaNumber();
            }
            switch (i) {
                case 0:
                    return true;
                case 1:
                    return sortModule.canSortByName();
                case 2:
                    return sortModule.canSortByDuration();
                case 3:
                    return sortModule.canSortByInsertionDate();
                case 4:
                    return sortModule.canSortByLastModified();
                case 5:
                    return sortModule.canSortByReleaseDate();
                case 6:
                    return sortModule.canSortByFileSize();
                case 7:
                    return sortModule.canSortByArtist();
                case 8:
                    return sortModule.canSortByPlayCount();
                case 9:
                    return sortModule.canSortByAlbum();
                case 10:
                    return sortModule.canSortByFileNameName();
                default:
                    return false;
            }
        }
    }
}
