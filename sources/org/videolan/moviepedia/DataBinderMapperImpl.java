package org.videolan.moviepedia;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import androidx.tvprovider.media.tv.TvContractCompat;
import io.ktor.http.ContentDisposition;
import io.ktor.server.auth.OAuth2RequestParameters;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.videolan.moviepedia.databinding.MoviepediaActivityBindingImpl;
import org.videolan.moviepedia.databinding.MoviepediaItemBindingImpl;
import org.videolan.resources.Constants;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_MOVIEPEDIAACTIVITY = 1;
    private static final int LAYOUT_MOVIEPEDIAITEM = 2;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(2);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.moviepedia_activity, 1);
        sparseIntArray.put(R.layout.moviepedia_item, 2);
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        } else if (i2 != 1) {
            if (i2 != 2) {
                return null;
            }
            if ("layout/moviepedia_item_0".equals(tag)) {
                return new MoviepediaItemBindingImpl(dataBindingComponent, view);
            }
            throw new IllegalArgumentException("The tag for moviepedia_item is invalid. Received: " + tag);
        } else if ("layout/moviepedia_activity_0".equals(tag)) {
            return new MoviepediaActivityBindingImpl(dataBindingComponent, view);
        } else {
            throw new IllegalArgumentException("The tag for moviepedia_activity is invalid. Received: " + tag);
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        if (viewArr == null || viewArr.length == 0 || INTERNAL_LAYOUT_ID_LOOKUP.get(i) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    public int getLayoutId(String str) {
        Integer num;
        if (str == null || (num = InnerLayoutIdLookup.sKeys.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }

    public String convertBrIdToString(int i) {
        return InnerBrLookup.sKeys.get(i);
    }

    public List<DataBinderMapper> collectDependencies() {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new org.videolan.medialibrary.DataBinderMapperImpl());
        arrayList.add(new org.videolan.vlc.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(84);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "ab_repeat_a");
            sparseArray.put(2, "ab_repeat_b");
            sparseArray.put(3, TvContractCompat.PreviewProgramColumns.COLUMN_AUTHOR);
            sparseArray.put(4, "bgColor");
            sparseArray.put(5, "bookmark");
            sparseArray.put(6, "can_shuffle");
            sparseArray.put(7, Constants.CATEGORY);
            sparseArray.put(8, "chapter");
            sparseArray.put(9, "checkEnabled");
            sparseArray.put(10, "clicHandler");
            sparseArray.put(11, "contentDescription");
            sparseArray.put(12, "cover");
            sparseArray.put(13, "coverWidth");
            sparseArray.put(14, TvContractCompat.Channels.COLUMN_DESCRIPTION);
            sparseArray.put(15, "dialog");
            sparseArray.put(16, "empty");
            sparseArray.put(17, "extraTitleText");
            sparseArray.put(18, "extraValueText");
            sparseArray.put(19, "favorite");
            sparseArray.put(20, ContentDisposition.Parameters.FileName);
            sparseArray.put(21, "filesText");
            sparseArray.put(22, "forceCoverHiding");
            sparseArray.put(23, SecondaryActivity.KEY_FRAGMENT);
            sparseArray.put(24, "handler");
            sparseArray.put(25, "hasContextMenu");
            sparseArray.put(26, "holder");
            sparseArray.put(27, "imageUrl");
            sparseArray.put(28, "imageWidth");
            sparseArray.put(29, "inError");
            sparseArray.put(30, "inSelection");
            sparseArray.put(31, "isBanned");
            sparseArray.put(32, "isBannedParent");
            sparseArray.put(33, "isFavorite");
            sparseArray.put(34, "isLoading");
            sparseArray.put(35, "isNetwork");
            sparseArray.put(36, "isOTG");
            sparseArray.put(37, "isPresent");
            sparseArray.put(38, "isSD");
            sparseArray.put(39, "isSquare");
            sparseArray.put(40, "isTv");
            sparseArray.put(41, "item");
            sparseArray.put(42, "length");
            sparseArray.put(43, "library");
            sparseArray.put(44, "masked");
            sparseArray.put(45, "max");
            sparseArray.put(46, "media");
            sparseArray.put(47, "menuItem");
            sparseArray.put(48, DuplicationWarningDialog.OPTION_KEY);
            sparseArray.put(49, ArtworkProvider.PATH);
            sparseArray.put(50, "played");
            sparseArray.put(51, "player");
            sparseArray.put(52, ArtworkProvider.PLAYLIST);
            sparseArray.put(53, Constants.PLAY_EXTRA_START_TIME);
            sparseArray.put(54, "progress");
            sparseArray.put(55, "protocol");
            sparseArray.put(56, "query");
            sparseArray.put(57, "releaseYear");
            sparseArray.put(58, "renderer");
            sparseArray.put(59, "resolution");
            sparseArray.put(60, "scaleName");
            sparseArray.put(61, "scaleType");
            sparseArray.put(62, "scanned");
            sparseArray.put(63, "searchAggregate");
            sparseArray.put(64, "seen");
            sparseArray.put(65, Constants.SELECTED_ITEM);
            sparseArray.put(66, "showCover");
            sparseArray.put(67, "showFavorites");
            sparseArray.put(68, "showItemProgress");
            sparseArray.put(69, "showProgress");
            sparseArray.put(70, "showTranslation");
            sparseArray.put(71, "sizeTitleText");
            sparseArray.put(72, "sizeValueContentDescription");
            sparseArray.put(73, "sizeValueText");
            sparseArray.put(74, OAuth2RequestParameters.State);
            sparseArray.put(75, "stopAfterThis");
            sparseArray.put(76, "subTitle");
            sparseArray.put(77, "subtitleItem");
            sparseArray.put(78, RtspHeaders.Values.TIME);
            sparseArray.put(79, "title");
            sparseArray.put(80, "topmargin");
            sparseArray.put(81, "totalDuration");
            sparseArray.put(82, "track");
            sparseArray.put(83, "viewmodel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(2);
            sKeys = hashMap;
            hashMap.put("layout/moviepedia_activity_0", Integer.valueOf(R.layout.moviepedia_activity));
            hashMap.put("layout/moviepedia_item_0", Integer.valueOf(R.layout.moviepedia_item));
        }
    }
}
