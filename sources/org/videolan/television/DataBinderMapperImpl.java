package org.videolan.television;

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
import org.videolan.television.databinding.ActivityColorPickerBindingImpl;
import org.videolan.television.databinding.ActivityMediaListTvBindingImpl;
import org.videolan.television.databinding.ActivityMediaListTvItemBindingImpl;
import org.videolan.television.databinding.ColorPickerItemBindingImpl;
import org.videolan.television.databinding.MediaBrowserTvItemBindingImpl;
import org.videolan.television.databinding.MediaBrowserTvItemListBindingImpl;
import org.videolan.television.databinding.MovieBrowserTvItemBindingImpl;
import org.videolan.television.databinding.MovieBrowserTvItemListBindingImpl;
import org.videolan.television.databinding.SongHeaderItemBindingImpl;
import org.videolan.television.databinding.TvAudioPlayerBindingImpl;
import org.videolan.television.databinding.TvPlaylistItemBindingImpl;
import org.videolan.television.databinding.TvVideoDetailsBindingImpl;
import org.videolan.vlc.ArtworkProvider;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ACTIVITYCOLORPICKER = 1;
    private static final int LAYOUT_ACTIVITYMEDIALISTTV = 2;
    private static final int LAYOUT_ACTIVITYMEDIALISTTVITEM = 3;
    private static final int LAYOUT_COLORPICKERITEM = 4;
    private static final int LAYOUT_MEDIABROWSERTVITEM = 5;
    private static final int LAYOUT_MEDIABROWSERTVITEMLIST = 6;
    private static final int LAYOUT_MOVIEBROWSERTVITEM = 7;
    private static final int LAYOUT_MOVIEBROWSERTVITEMLIST = 8;
    private static final int LAYOUT_SONGHEADERITEM = 9;
    private static final int LAYOUT_TVAUDIOPLAYER = 10;
    private static final int LAYOUT_TVPLAYLISTITEM = 11;
    private static final int LAYOUT_TVVIDEODETAILS = 12;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(12);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.activity_color_picker, 1);
        sparseIntArray.put(R.layout.activity_media_list_tv, 2);
        sparseIntArray.put(R.layout.activity_media_list_tv_item, 3);
        sparseIntArray.put(R.layout.color_picker_item, 4);
        sparseIntArray.put(R.layout.media_browser_tv_item, 5);
        sparseIntArray.put(R.layout.media_browser_tv_item_list, 6);
        sparseIntArray.put(R.layout.movie_browser_tv_item, 7);
        sparseIntArray.put(R.layout.movie_browser_tv_item_list, 8);
        sparseIntArray.put(R.layout.song_header_item, 9);
        sparseIntArray.put(R.layout.tv_audio_player, 10);
        sparseIntArray.put(R.layout.tv_playlist_item, 11);
        sparseIntArray.put(R.layout.tv_video_details, 12);
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            switch (i2) {
                case 1:
                    if ("layout/activity_color_picker_0".equals(tag)) {
                        return new ActivityColorPickerBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_color_picker is invalid. Received: " + tag);
                case 2:
                    if ("layout/activity_media_list_tv_0".equals(tag)) {
                        return new ActivityMediaListTvBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_media_list_tv is invalid. Received: " + tag);
                case 3:
                    if ("layout/activity_media_list_tv_item_0".equals(tag)) {
                        return new ActivityMediaListTvItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for activity_media_list_tv_item is invalid. Received: " + tag);
                case 4:
                    if ("layout/color_picker_item_0".equals(tag)) {
                        return new ColorPickerItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for color_picker_item is invalid. Received: " + tag);
                case 5:
                    if ("layout/media_browser_tv_item_0".equals(tag)) {
                        return new MediaBrowserTvItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for media_browser_tv_item is invalid. Received: " + tag);
                case 6:
                    if ("layout/media_browser_tv_item_list_0".equals(tag)) {
                        return new MediaBrowserTvItemListBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for media_browser_tv_item_list is invalid. Received: " + tag);
                case 7:
                    if ("layout/movie_browser_tv_item_0".equals(tag)) {
                        return new MovieBrowserTvItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for movie_browser_tv_item is invalid. Received: " + tag);
                case 8:
                    if ("layout/movie_browser_tv_item_list_0".equals(tag)) {
                        return new MovieBrowserTvItemListBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for movie_browser_tv_item_list is invalid. Received: " + tag);
                case 9:
                    if ("layout/song_header_item_0".equals(tag)) {
                        return new SongHeaderItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for song_header_item is invalid. Received: " + tag);
                case 10:
                    if ("layout/tv_audio_player_0".equals(tag)) {
                        return new TvAudioPlayerBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for tv_audio_player is invalid. Received: " + tag);
                case 11:
                    if ("layout/tv_playlist_item_0".equals(tag)) {
                        return new TvPlaylistItemBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for tv_playlist_item is invalid. Received: " + tag);
                case 12:
                    if ("layout/tv_video_details_0".equals(tag)) {
                        return new TvVideoDetailsBindingImpl(dataBindingComponent, view);
                    }
                    throw new IllegalArgumentException("The tag for tv_video_details is invalid. Received: " + tag);
                default:
                    return null;
            }
        } else {
            throw new RuntimeException("view must have a tag");
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
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new org.videolan.medialibrary.DataBinderMapperImpl());
        arrayList.add(new org.videolan.moviepedia.DataBinderMapperImpl());
        arrayList.add(new org.videolan.vlc.DataBinderMapperImpl());
        return arrayList;
    }

    private static class InnerBrLookup {
        static final SparseArray<String> sKeys;

        private InnerBrLookup() {
        }

        static {
            SparseArray<String> sparseArray = new SparseArray<>(90);
            sKeys = sparseArray;
            sparseArray.put(0, "_all");
            sparseArray.put(1, "ab_repeat_a");
            sparseArray.put(2, "ab_repeat_b");
            sparseArray.put(3, TvContractCompat.PreviewProgramColumns.COLUMN_AUTHOR);
            sparseArray.put(4, "badge");
            sparseArray.put(5, "bgColor");
            sparseArray.put(6, "bookmark");
            sparseArray.put(7, "can_shuffle");
            sparseArray.put(8, Constants.CATEGORY);
            sparseArray.put(9, "chapter");
            sparseArray.put(10, "checkEnabled");
            sparseArray.put(11, "clicHandler");
            sparseArray.put(12, "contentDescription");
            sparseArray.put(13, "cover");
            sparseArray.put(14, "coverWidth");
            sparseArray.put(15, TvContractCompat.Channels.COLUMN_DESCRIPTION);
            sparseArray.put(16, "dialog");
            sparseArray.put(17, "empty");
            sparseArray.put(18, "extraTitleText");
            sparseArray.put(19, "extraValueText");
            sparseArray.put(20, "favorite");
            sparseArray.put(21, ContentDisposition.Parameters.FileName);
            sparseArray.put(22, "filesText");
            sparseArray.put(23, "forceCoverHiding");
            sparseArray.put(24, SecondaryActivity.KEY_FRAGMENT);
            sparseArray.put(25, "handler");
            sparseArray.put(26, "hasContent");
            sparseArray.put(27, "hasContextMenu");
            sparseArray.put(28, "headerText");
            sparseArray.put(29, "holder");
            sparseArray.put(30, "imageUrl");
            sparseArray.put(31, "imageWidth");
            sparseArray.put(32, "inError");
            sparseArray.put(33, "inSelection");
            sparseArray.put(34, "isBanned");
            sparseArray.put(35, "isBannedParent");
            sparseArray.put(36, "isFavorite");
            sparseArray.put(37, "isLoading");
            sparseArray.put(38, "isNetwork");
            sparseArray.put(39, "isOTG");
            sparseArray.put(40, "isPresent");
            sparseArray.put(41, "isSD");
            sparseArray.put(42, "isSquare");
            sparseArray.put(43, "isTv");
            sparseArray.put(44, "item");
            sparseArray.put(45, "length");
            sparseArray.put(46, "library");
            sparseArray.put(47, "masked");
            sparseArray.put(48, "max");
            sparseArray.put(49, "media");
            sparseArray.put(50, "menuItem");
            sparseArray.put(51, DuplicationWarningDialog.OPTION_KEY);
            sparseArray.put(52, ArtworkProvider.PATH);
            sparseArray.put(53, "played");
            sparseArray.put(54, "player");
            sparseArray.put(55, ArtworkProvider.PLAYLIST);
            sparseArray.put(56, Constants.PLAY_EXTRA_START_TIME);
            sparseArray.put(57, "progress");
            sparseArray.put(58, "protocol");
            sparseArray.put(59, "query");
            sparseArray.put(60, "releaseYear");
            sparseArray.put(61, "renderer");
            sparseArray.put(62, "resolution");
            sparseArray.put(63, "scaleName");
            sparseArray.put(64, "scaleType");
            sparseArray.put(65, "scanned");
            sparseArray.put(66, "searchAggregate");
            sparseArray.put(67, "seen");
            sparseArray.put(68, Constants.SELECTED_ITEM);
            sparseArray.put(69, "showCover");
            sparseArray.put(70, "showFavorites");
            sparseArray.put(71, "showItemProgress");
            sparseArray.put(72, "showProgress");
            sparseArray.put(73, "showSeen");
            sparseArray.put(74, "showTranslation");
            sparseArray.put(75, "sizeTitleText");
            sparseArray.put(76, "sizeValueContentDescription");
            sparseArray.put(77, "sizeValueText");
            sparseArray.put(78, OAuth2RequestParameters.State);
            sparseArray.put(79, "stopAfterThis");
            sparseArray.put(80, "subTitle");
            sparseArray.put(81, "subtitle");
            sparseArray.put(82, "subtitleItem");
            sparseArray.put(83, RtspHeaders.Values.TIME);
            sparseArray.put(84, "title");
            sparseArray.put(85, "topmargin");
            sparseArray.put(86, "totalDuration");
            sparseArray.put(87, "totalTime");
            sparseArray.put(88, "track");
            sparseArray.put(89, "viewmodel");
        }
    }

    private static class InnerLayoutIdLookup {
        static final HashMap<String, Integer> sKeys;

        private InnerLayoutIdLookup() {
        }

        static {
            HashMap<String, Integer> hashMap = new HashMap<>(12);
            sKeys = hashMap;
            hashMap.put("layout/activity_color_picker_0", Integer.valueOf(R.layout.activity_color_picker));
            hashMap.put("layout/activity_media_list_tv_0", Integer.valueOf(R.layout.activity_media_list_tv));
            hashMap.put("layout/activity_media_list_tv_item_0", Integer.valueOf(R.layout.activity_media_list_tv_item));
            hashMap.put("layout/color_picker_item_0", Integer.valueOf(R.layout.color_picker_item));
            hashMap.put("layout/media_browser_tv_item_0", Integer.valueOf(R.layout.media_browser_tv_item));
            hashMap.put("layout/media_browser_tv_item_list_0", Integer.valueOf(R.layout.media_browser_tv_item_list));
            hashMap.put("layout/movie_browser_tv_item_0", Integer.valueOf(R.layout.movie_browser_tv_item));
            hashMap.put("layout/movie_browser_tv_item_list_0", Integer.valueOf(R.layout.movie_browser_tv_item_list));
            hashMap.put("layout/song_header_item_0", Integer.valueOf(R.layout.song_header_item));
            hashMap.put("layout/tv_audio_player_0", Integer.valueOf(R.layout.tv_audio_player));
            hashMap.put("layout/tv_playlist_item_0", Integer.valueOf(R.layout.tv_playlist_item));
            hashMap.put("layout/tv_video_details_0", Integer.valueOf(R.layout.tv_video_details));
        }
    }
}
