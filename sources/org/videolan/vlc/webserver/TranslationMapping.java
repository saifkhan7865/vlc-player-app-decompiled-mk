package org.videolan.vlc.webserver;

import android.content.Context;
import android.os.Build;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.json.JSONObject;
import org.videolan.vlc.BuildConfig;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u0007B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/webserver/TranslationMapping;", "", "()V", "generateTranslations", "", "context", "Landroid/content/Context;", "StringMapping", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: TranslationMapping.kt */
public final class TranslationMapping {
    public static final TranslationMapping INSTANCE = new TranslationMapping();

    private TranslationMapping() {
    }

    public final String generateTranslations(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        HashMap hashMap = new HashMap();
        for (StringMapping stringMapping : StringMapping.values()) {
            String name = stringMapping.name();
            String string = context.getString(stringMapping.getId());
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            hashMap.put(name, StringsKt.replace$default(string, "%s", "{msg}", false, 4, (Object) null));
        }
        Map map = hashMap;
        map.put("PORT", "android");
        map.put("DEVICE_NAME", Build.MANUFACTURER + " - " + Build.MODEL);
        map.put("APP_VERSION", BuildConfig.VLC_VERSION_NAME);
        String jSONObject = new JSONObject(MapsKt.toMap(map)).toString();
        Intrinsics.checkNotNullExpressionValue(jSONObject, "toString(...)");
        return jSONObject;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\be\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bRj\u0002\bSj\u0002\bTj\u0002\bUj\u0002\bVj\u0002\bWj\u0002\bXj\u0002\bYj\u0002\bZj\u0002\b[j\u0002\b\\j\u0002\b]j\u0002\b^j\u0002\b_j\u0002\b`j\u0002\baj\u0002\bbj\u0002\bcj\u0002\bdj\u0002\bej\u0002\bfj\u0002\bg¨\u0006h"}, d2 = {"Lorg/videolan/vlc/webserver/TranslationMapping$StringMapping;", "", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "VIDEO", "AUDIO", "BROWSE", "PLAYLISTS", "ARTISTS", "ALBUMS", "TRACKS", "GENRES", "LOG_FILE", "LOG_TYPE", "SEND_FILES", "DOWNLOAD", "NO_MEDIA", "NO_PLAYLIST", "PLAY", "APPEND", "PLAY_AS_AUDIO", "SEARCH", "DISCONNECTED", "FILE", "UPLOAD_REMAINING", "UPLOAD_ALL", "DROP_FILES_TIP", "PREPARING_DOWNLOAD", "RESUME_PLAYBACK", "DISPLAY_LIST", "DISPLAY_GRID", "SEARCH_HINT", "SEARCH_NO_RESULT", "DIRECTORY_EMPTY", "FORBIDDEN", "PLAYBACK_CONTROL_FORBIDDEN", "SEND", "NEW_CODE", "CODE_REQUEST_EXPLANATION", "SSL_BUTTON", "SSL_EXPLANATION_TITLE", "SSL_EXPLANATION", "SSL_EXPLANATION_BROWSER", "SSL_EXPLANATION_ACCEPT", "SEND_LOGS", "LOG_TYPE_WEB", "LOG_TYPE_CRASH", "LOG_TYPE_MOBILE", "LOG_TYPE_CURRENT", "NOTHING_RESUME", "INVALID_LOGIN", "INVALID_OTP", "NEW_STREAM", "ENTER_STREAM", "LEARN_MORE", "VIDEO_GROUP_NONE", "VIDEO_GROUP_BY_FOLDER", "VIDEO_GROUP_BY_NAME", "PLAY_ALL", "DARK_THEME", "LIGHT_THEME", "MORE", "HISTORY", "ADD_FAVORITE", "REMOVE_FAVORITE", "FAVORITES", "STORAGES", "LOCAL_NETWORK", "STREAMS", "LOADING", "ADD_PLAYLIST", "ADDING", "TO", "NEW_PLAYLIST", "CREATE", "ADD", "ABOUT", "CONNECTED_DEVICE", "DEVICE_MODEL", "VLC_VERSION", "REMOTE_ACCESS_TITLE", "REMOTE_ACCESS_HASH_TITLE", "REMOTE_ACCESS_VERSION_TITLE", "REMOTE_ACCESS_VERSION", "REMOTE_ACCESS_HASH", "LIBRARIES", "SLEEP_TIMER", "PLAYBACK_SPEED", "CHAPTERS", "BOOKMARKS", "NO_BOOKMARK", "OK", "CANCEL", "NOT_SET", "SLEEP_IN", "WAIT_CURRENT_MEDIA", "RESET_ON_INTERACTION", "HOURS", "MINUTES", "VIDEO_PLAYER_REMOTE", "REMOTE_KEYBOARD_NAVIGATION", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: TranslationMapping.kt */
    public enum StringMapping {
        VIDEO(R.string.video),
        AUDIO(R.string.audio),
        BROWSE(R.string.browse),
        PLAYLISTS(R.string.playlists),
        ARTISTS(R.string.artists),
        ALBUMS(R.string.albums),
        TRACKS(R.string.tracks),
        GENRES(R.string.genres),
        LOG_FILE(R.string.ra_log_file),
        LOG_TYPE(R.string.ra_log_type),
        SEND_FILES(R.string.ra_send_files),
        DOWNLOAD(R.string.download),
        NO_MEDIA(R.string.nomedia),
        NO_PLAYLIST(R.string.noplaylist),
        PLAY(R.string.play),
        APPEND(R.string.append),
        PLAY_AS_AUDIO(R.string.play_as_audio),
        SEARCH(R.string.search),
        DISCONNECTED(R.string.ra_disconnected),
        FILE(R.string.ra_file),
        UPLOAD_REMAINING(R.string.ra_upload_remaining),
        UPLOAD_ALL(R.string.ra_upload_all),
        DROP_FILES_TIP(R.string.ra_drop_files_tip),
        PREPARING_DOWNLOAD(R.string.ra_prepare_download),
        RESUME_PLAYBACK(R.string.resume_playback_short_title),
        DISPLAY_LIST(R.string.display_in_list),
        DISPLAY_GRID(R.string.display_in_grid),
        SEARCH_HINT(R.string.search_hint),
        SEARCH_NO_RESULT(R.string.search_no_result),
        DIRECTORY_EMPTY(R.string.empty_directory),
        FORBIDDEN(R.string.ra_forbidden),
        PLAYBACK_CONTROL_FORBIDDEN(R.string.ra_playback_forbidden),
        SEND(R.string.send),
        NEW_CODE(R.string.ra_new_code),
        CODE_REQUEST_EXPLANATION(R.string.ra_code_requested_explanation),
        SSL_BUTTON(R.string.ra_ssl_button),
        SSL_EXPLANATION_TITLE(R.string.ra_ssl_explanation_title),
        SSL_EXPLANATION(R.string.ra_ssl_explanation),
        SSL_EXPLANATION_BROWSER(R.string.ra_ssl_explanation_browser),
        SSL_EXPLANATION_ACCEPT(R.string.ra_ssl_explanation_accept),
        SEND_LOGS(R.string.ra_send_logs),
        LOG_TYPE_WEB(R.string.ra_log_web),
        LOG_TYPE_CRASH(R.string.ra_log_crash),
        LOG_TYPE_MOBILE(R.string.ra_log_mobile),
        LOG_TYPE_CURRENT(R.string.ra_log_current),
        NOTHING_RESUME(R.string.resume_playback_error),
        INVALID_LOGIN(R.string.ra_invalid_login),
        INVALID_OTP(R.string.ra_invalid_otp),
        NEW_STREAM(R.string.new_stream),
        ENTER_STREAM(R.string.open_mrl_dialog_msg),
        LEARN_MORE(R.string.learn_more),
        VIDEO_GROUP_NONE(R.string.video_min_group_length_disable),
        VIDEO_GROUP_BY_FOLDER(R.string.video_min_group_length_folder),
        VIDEO_GROUP_BY_NAME(R.string.video_min_group_length_name),
        PLAY_ALL(R.string.play_all),
        DARK_THEME(R.string.dark_theme),
        LIGHT_THEME(R.string.light_theme),
        MORE(R.string.more),
        HISTORY(R.string.history),
        ADD_FAVORITE(R.string.favorites_add),
        REMOVE_FAVORITE(R.string.favorites_remove),
        FAVORITES(R.string.favorites),
        STORAGES(R.string.browser_storages),
        LOCAL_NETWORK(R.string.network_browsing),
        STREAMS(R.string.streams),
        LOADING(R.string.loading),
        ADD_PLAYLIST(R.string.add_to_playlist),
        ADDING(R.string.adding),
        TO(R.string.to),
        NEW_PLAYLIST(R.string.create_new_playlist),
        CREATE(R.string.create),
        ADD(R.string.add),
        ABOUT(R.string.about),
        CONNECTED_DEVICE(R.string.connected_device),
        DEVICE_MODEL(R.string.model),
        VLC_VERSION(R.string.app_name_full),
        REMOTE_ACCESS_TITLE(R.string.remote_access),
        REMOTE_ACCESS_HASH_TITLE(R.string.remote_access_hash_title),
        REMOTE_ACCESS_VERSION_TITLE(R.string.remote_access_version_title),
        REMOTE_ACCESS_VERSION(R.string.remote_access_version),
        REMOTE_ACCESS_HASH(R.string.build_remote_access_revision),
        LIBRARIES(R.string.libraries),
        SLEEP_TIMER(R.string.sleep_title),
        PLAYBACK_SPEED(R.string.playback_speed),
        CHAPTERS(R.string.chapters),
        BOOKMARKS(R.string.bookmarks),
        NO_BOOKMARK(R.string.no_bookmark),
        OK(R.string.ok),
        CANCEL(R.string.cancel),
        NOT_SET(R.string.notset),
        SLEEP_IN(R.string.sleep_in),
        WAIT_CURRENT_MEDIA(R.string.wait_before_sleep),
        RESET_ON_INTERACTION(R.string.reset_on_interaction),
        HOURS(R.string.talkback_hours),
        MINUTES(R.string.talkback_minutes),
        VIDEO_PLAYER_REMOTE(R.string.video_player_remote),
        REMOTE_KEYBOARD_NAVIGATION(R.string.remote_keyboard_navigation);
        
        private final int id;

        public static EnumEntries<StringMapping> getEntries() {
            return $ENTRIES;
        }

        private StringMapping(int i) {
            this.id = i;
        }

        public final int getId() {
            return this.id;
        }

        static {
            StringMapping[] $values;
            $ENTRIES = EnumEntriesKt.enumEntries((E[]) (Enum[]) $values);
        }
    }
}
