package org.videolan.vlc.webserver;

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
import org.videolan.resources.Constants;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;
import org.videolan.vlc.webserver.databinding.RemoteAccessConnectionItemBindingImpl;
import org.videolan.vlc.webserver.databinding.RemoteAccessShareActivityBindingImpl;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_REMOTEACCESSCONNECTIONITEM = 1;
    private static final int LAYOUT_REMOTEACCESSSHAREACTIVITY = 2;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(2);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.remote_access_connection_item, 1);
        sparseIntArray.put(R.layout.remote_access_share_activity, 2);
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
            if ("layout/remote_access_share_activity_0".equals(tag)) {
                return new RemoteAccessShareActivityBindingImpl(dataBindingComponent, new View[]{view});
            }
            throw new IllegalArgumentException("The tag for remote_access_share_activity is invalid. Received: " + tag);
        } else if ("layout/remote_access_connection_item_0".equals(tag)) {
            return new RemoteAccessConnectionItemBindingImpl(dataBindingComponent, view);
        } else {
            throw new IllegalArgumentException("The tag for remote_access_connection_item is invalid. Received: " + tag);
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        int i2;
        if (!(viewArr == null || viewArr.length == 0 || (i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i)) <= 0)) {
            Object tag = viewArr[0].getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            } else if (i2 == 2) {
                if ("layout/remote_access_share_activity_0".equals(tag)) {
                    return new RemoteAccessShareActivityBindingImpl(dataBindingComponent, viewArr);
                }
                throw new IllegalArgumentException("The tag for remote_access_share_activity is invalid. Received: " + tag);
            }
        }
        return null;
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
            SparseArray<String> sparseArray = new SparseArray<>(83);
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
            sparseArray.put(27, "imageWidth");
            sparseArray.put(28, "inError");
            sparseArray.put(29, "inSelection");
            sparseArray.put(30, "isBanned");
            sparseArray.put(31, "isBannedParent");
            sparseArray.put(32, "isFavorite");
            sparseArray.put(33, "isLoading");
            sparseArray.put(34, "isNetwork");
            sparseArray.put(35, "isOTG");
            sparseArray.put(36, "isPresent");
            sparseArray.put(37, "isSD");
            sparseArray.put(38, "isSquare");
            sparseArray.put(39, "isTv");
            sparseArray.put(40, "item");
            sparseArray.put(41, "length");
            sparseArray.put(42, "library");
            sparseArray.put(43, "masked");
            sparseArray.put(44, "max");
            sparseArray.put(45, "media");
            sparseArray.put(46, "menuItem");
            sparseArray.put(47, DuplicationWarningDialog.OPTION_KEY);
            sparseArray.put(48, ArtworkProvider.PATH);
            sparseArray.put(49, "played");
            sparseArray.put(50, "player");
            sparseArray.put(51, ArtworkProvider.PLAYLIST);
            sparseArray.put(52, Constants.PLAY_EXTRA_START_TIME);
            sparseArray.put(53, "progress");
            sparseArray.put(54, "protocol");
            sparseArray.put(55, "query");
            sparseArray.put(56, "releaseYear");
            sparseArray.put(57, "renderer");
            sparseArray.put(58, "resolution");
            sparseArray.put(59, "scaleName");
            sparseArray.put(60, "scaleType");
            sparseArray.put(61, "scanned");
            sparseArray.put(62, "searchAggregate");
            sparseArray.put(63, "seen");
            sparseArray.put(64, Constants.SELECTED_ITEM);
            sparseArray.put(65, "showCover");
            sparseArray.put(66, "showFavorites");
            sparseArray.put(67, "showItemProgress");
            sparseArray.put(68, "showProgress");
            sparseArray.put(69, "showTranslation");
            sparseArray.put(70, "sizeTitleText");
            sparseArray.put(71, "sizeValueContentDescription");
            sparseArray.put(72, "sizeValueText");
            sparseArray.put(73, OAuth2RequestParameters.State);
            sparseArray.put(74, "stopAfterThis");
            sparseArray.put(75, "subTitle");
            sparseArray.put(76, "subtitleItem");
            sparseArray.put(77, RtspHeaders.Values.TIME);
            sparseArray.put(78, "title");
            sparseArray.put(79, "topmargin");
            sparseArray.put(80, "totalDuration");
            sparseArray.put(81, "track");
            sparseArray.put(82, "viewmodel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(2);
            sKeys = hashMap;
            hashMap.put("layout/remote_access_connection_item_0", Integer.valueOf(R.layout.remote_access_connection_item));
            hashMap.put("layout/remote_access_share_activity_0", Integer.valueOf(R.layout.remote_access_share_activity));
        }
    }
}
