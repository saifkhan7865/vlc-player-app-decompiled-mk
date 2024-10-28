package org.videolan.vlc;

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
import org.videolan.vlc.databinding.AboutAuthorsActivityBindingImpl;
import org.videolan.vlc.databinding.AboutAuthorsItemBindingImpl;
import org.videolan.vlc.databinding.ActivityBetaWelcomeBindingImpl;
import org.videolan.vlc.databinding.AudioAlbumTrackItemBindingImpl;
import org.videolan.vlc.databinding.AudioBrowserBindingImpl;
import org.videolan.vlc.databinding.AudioBrowserCardItemBindingImpl;
import org.videolan.vlc.databinding.AudioBrowserItemBindingImpl;
import org.videolan.vlc.databinding.AudioBrowserSeparatorBindingImpl;
import org.videolan.vlc.databinding.AudioPlayerBindingImpl;
import org.videolan.vlc.databinding.AudioPlayerBindingLandImpl;
import org.videolan.vlc.databinding.BookmarkItemBindingImpl;
import org.videolan.vlc.databinding.BrowserItemBindingImpl;
import org.videolan.vlc.databinding.BrowserItemSeparatorBindingImpl;
import org.videolan.vlc.databinding.CardBrowserItemBindingImpl;
import org.videolan.vlc.databinding.ChapterListItemBindingImpl;
import org.videolan.vlc.databinding.ContextItemBindingImpl;
import org.videolan.vlc.databinding.ContextualSheetBindingImpl;
import org.videolan.vlc.databinding.CoverMediaSwitcherItemBindingImpl;
import org.videolan.vlc.databinding.CoverMediaSwitcherItemBindingLandImpl;
import org.videolan.vlc.databinding.DialogAboutVersionBindingImpl;
import org.videolan.vlc.databinding.DialogAddToGroupBindingImpl;
import org.videolan.vlc.databinding.DialogAudioControlsSettingsBindingImpl;
import org.videolan.vlc.databinding.DialogDisplaySettingsBindingImpl;
import org.videolan.vlc.databinding.DialogDuplicationWarningBindingImpl;
import org.videolan.vlc.databinding.DialogExtDeviceBindingImpl;
import org.videolan.vlc.databinding.DialogLicenseBindingImpl;
import org.videolan.vlc.databinding.DialogNorificationPermissionBindingImpl;
import org.videolan.vlc.databinding.DialogPlaybackSpeedBindingImpl;
import org.videolan.vlc.databinding.DialogPlaylistBindingImpl;
import org.videolan.vlc.databinding.DialogRenderersBindingImpl;
import org.videolan.vlc.databinding.DialogTimePickerBindingImpl;
import org.videolan.vlc.databinding.DialogUpdateBindingImpl;
import org.videolan.vlc.databinding.DialogVideoControlsSettingsBindingImpl;
import org.videolan.vlc.databinding.DialogWhatsNewBindingImpl;
import org.videolan.vlc.databinding.DialogWidgetExplanationBindingImpl;
import org.videolan.vlc.databinding.DialogWidgetMigrationBindingImpl;
import org.videolan.vlc.databinding.DirectoryBrowserBindingImpl;
import org.videolan.vlc.databinding.EqualizerBindingImpl;
import org.videolan.vlc.databinding.HeaderMediaListActivityBindingImpl;
import org.videolan.vlc.databinding.HistoryItemBindingImpl;
import org.videolan.vlc.databinding.HistoryItemCardBindingImpl;
import org.videolan.vlc.databinding.InfoActivityBindingImpl;
import org.videolan.vlc.databinding.ItemRendererBindingImpl;
import org.videolan.vlc.databinding.LibraryItemBindingImpl;
import org.videolan.vlc.databinding.LicenseActivityBindingImpl;
import org.videolan.vlc.databinding.MlWizardActivityBindingImpl;
import org.videolan.vlc.databinding.MrlCardItemBindingImpl;
import org.videolan.vlc.databinding.MrlDummyItemBindingImpl;
import org.videolan.vlc.databinding.MrlItemBindingImpl;
import org.videolan.vlc.databinding.MrlPanelBindingImpl;
import org.videolan.vlc.databinding.MrlPanelBindingV21Impl;
import org.videolan.vlc.databinding.OtpCodeBindingImpl;
import org.videolan.vlc.databinding.PinCodeActivityBindingImpl;
import org.videolan.vlc.databinding.PlayerHudBindingImpl;
import org.videolan.vlc.databinding.PlayerHudRightBindingImpl;
import org.videolan.vlc.databinding.PlayerOptionItemBindingImpl;
import org.videolan.vlc.databinding.PlayerOverlayTrackItemBindingImpl;
import org.videolan.vlc.databinding.PlayerOverlayTracksBindingImpl;
import org.videolan.vlc.databinding.PlayerOverlayTracksBindingLandImpl;
import org.videolan.vlc.databinding.PlaylistItemBindingImpl;
import org.videolan.vlc.databinding.PlaylistsFragmentBindingImpl;
import org.videolan.vlc.databinding.PreferenceItemBindingImpl;
import org.videolan.vlc.databinding.PreferencesSearchActivityBindingImpl;
import org.videolan.vlc.databinding.SearchActivityBindingImpl;
import org.videolan.vlc.databinding.SearchItemBindingImpl;
import org.videolan.vlc.databinding.SendCrashActivityBindingImpl;
import org.videolan.vlc.databinding.SimpleItemBindingImpl;
import org.videolan.vlc.databinding.SortDisplaySettingBindingImpl;
import org.videolan.vlc.databinding.SubtitleDownloadItemBindingImpl;
import org.videolan.vlc.databinding.SubtitleDownloaderDialogBindingImpl;
import org.videolan.vlc.databinding.VideoGridBindingImpl;
import org.videolan.vlc.databinding.VideoGridCardBindingImpl;
import org.videolan.vlc.databinding.VideoListCardBindingImpl;
import org.videolan.vlc.databinding.VideoScaleItemBindingImpl;
import org.videolan.vlc.databinding.VideoTrackItemBindingImpl;
import org.videolan.vlc.databinding.VlcLoginDialogBindingImpl;
import org.videolan.vlc.databinding.VlcProgressDialogBindingImpl;
import org.videolan.vlc.databinding.VlcQuestionDialogBindingImpl;
import org.videolan.vlc.databinding.WidgetContentFullPlayerBindingImpl;
import org.videolan.vlc.databinding.WidgetMiniBindingImpl;
import org.videolan.vlc.databinding.WidgetMiniInitialBindingImpl;
import org.videolan.vlc.databinding.WidgetMiniPlayerConfigureBindingImpl;
import org.videolan.vlc.gui.SecondaryActivity;
import org.videolan.vlc.gui.dialogs.DuplicationWarningDialog;

public class DataBinderMapperImpl extends DataBinderMapper {
    private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP;
    private static final int LAYOUT_ABOUTAUTHORSACTIVITY = 1;
    private static final int LAYOUT_ABOUTAUTHORSITEM = 2;
    private static final int LAYOUT_ACTIVITYBETAWELCOME = 3;
    private static final int LAYOUT_AUDIOALBUMTRACKITEM = 4;
    private static final int LAYOUT_AUDIOBROWSER = 5;
    private static final int LAYOUT_AUDIOBROWSERCARDITEM = 6;
    private static final int LAYOUT_AUDIOBROWSERITEM = 7;
    private static final int LAYOUT_AUDIOBROWSERSEPARATOR = 8;
    private static final int LAYOUT_AUDIOPLAYER = 9;
    private static final int LAYOUT_BOOKMARKITEM = 10;
    private static final int LAYOUT_BROWSERITEM = 11;
    private static final int LAYOUT_BROWSERITEMSEPARATOR = 12;
    private static final int LAYOUT_CARDBROWSERITEM = 13;
    private static final int LAYOUT_CHAPTERLISTITEM = 14;
    private static final int LAYOUT_CONTEXTITEM = 15;
    private static final int LAYOUT_CONTEXTUALSHEET = 16;
    private static final int LAYOUT_COVERMEDIASWITCHERITEM = 17;
    private static final int LAYOUT_DIALOGABOUTVERSION = 18;
    private static final int LAYOUT_DIALOGADDTOGROUP = 19;
    private static final int LAYOUT_DIALOGAUDIOCONTROLSSETTINGS = 20;
    private static final int LAYOUT_DIALOGDISPLAYSETTINGS = 21;
    private static final int LAYOUT_DIALOGDUPLICATIONWARNING = 22;
    private static final int LAYOUT_DIALOGEXTDEVICE = 23;
    private static final int LAYOUT_DIALOGLICENSE = 24;
    private static final int LAYOUT_DIALOGNORIFICATIONPERMISSION = 25;
    private static final int LAYOUT_DIALOGPLAYBACKSPEED = 26;
    private static final int LAYOUT_DIALOGPLAYLIST = 27;
    private static final int LAYOUT_DIALOGRENDERERS = 28;
    private static final int LAYOUT_DIALOGTIMEPICKER = 29;
    private static final int LAYOUT_DIALOGUPDATE = 30;
    private static final int LAYOUT_DIALOGVIDEOCONTROLSSETTINGS = 31;
    private static final int LAYOUT_DIALOGWHATSNEW = 32;
    private static final int LAYOUT_DIALOGWIDGETEXPLANATION = 33;
    private static final int LAYOUT_DIALOGWIDGETMIGRATION = 34;
    private static final int LAYOUT_DIRECTORYBROWSER = 35;
    private static final int LAYOUT_EQUALIZER = 36;
    private static final int LAYOUT_HEADERMEDIALISTACTIVITY = 37;
    private static final int LAYOUT_HISTORYITEM = 38;
    private static final int LAYOUT_HISTORYITEMCARD = 39;
    private static final int LAYOUT_INFOACTIVITY = 40;
    private static final int LAYOUT_ITEMRENDERER = 41;
    private static final int LAYOUT_LIBRARYITEM = 42;
    private static final int LAYOUT_LICENSEACTIVITY = 43;
    private static final int LAYOUT_MLWIZARDACTIVITY = 44;
    private static final int LAYOUT_MRLCARDITEM = 45;
    private static final int LAYOUT_MRLDUMMYITEM = 46;
    private static final int LAYOUT_MRLITEM = 47;
    private static final int LAYOUT_MRLPANEL = 48;
    private static final int LAYOUT_OTPCODE = 49;
    private static final int LAYOUT_PINCODEACTIVITY = 50;
    private static final int LAYOUT_PLAYERHUD = 51;
    private static final int LAYOUT_PLAYERHUDRIGHT = 52;
    private static final int LAYOUT_PLAYEROPTIONITEM = 53;
    private static final int LAYOUT_PLAYEROVERLAYTRACKITEM = 54;
    private static final int LAYOUT_PLAYEROVERLAYTRACKS = 55;
    private static final int LAYOUT_PLAYLISTITEM = 56;
    private static final int LAYOUT_PLAYLISTSFRAGMENT = 57;
    private static final int LAYOUT_PREFERENCEITEM = 58;
    private static final int LAYOUT_PREFERENCESSEARCHACTIVITY = 59;
    private static final int LAYOUT_SEARCHACTIVITY = 60;
    private static final int LAYOUT_SEARCHITEM = 61;
    private static final int LAYOUT_SENDCRASHACTIVITY = 62;
    private static final int LAYOUT_SIMPLEITEM = 63;
    private static final int LAYOUT_SORTDISPLAYSETTING = 64;
    private static final int LAYOUT_SUBTITLEDOWNLOADERDIALOG = 66;
    private static final int LAYOUT_SUBTITLEDOWNLOADITEM = 65;
    private static final int LAYOUT_VIDEOGRID = 67;
    private static final int LAYOUT_VIDEOGRIDCARD = 68;
    private static final int LAYOUT_VIDEOLISTCARD = 69;
    private static final int LAYOUT_VIDEOSCALEITEM = 70;
    private static final int LAYOUT_VIDEOTRACKITEM = 71;
    private static final int LAYOUT_VLCLOGINDIALOG = 72;
    private static final int LAYOUT_VLCPROGRESSDIALOG = 73;
    private static final int LAYOUT_VLCQUESTIONDIALOG = 74;
    private static final int LAYOUT_WIDGETCONTENTFULLPLAYER = 75;
    private static final int LAYOUT_WIDGETMINI = 76;
    private static final int LAYOUT_WIDGETMINIINITIAL = 77;
    private static final int LAYOUT_WIDGETMINIPLAYERCONFIGURE = 78;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(78);
        INTERNAL_LAYOUT_ID_LOOKUP = sparseIntArray;
        sparseIntArray.put(R.layout.about_authors_activity, 1);
        sparseIntArray.put(R.layout.about_authors_item, 2);
        sparseIntArray.put(R.layout.activity_beta_welcome, 3);
        sparseIntArray.put(R.layout.audio_album_track_item, 4);
        sparseIntArray.put(R.layout.audio_browser, 5);
        sparseIntArray.put(R.layout.audio_browser_card_item, 6);
        sparseIntArray.put(R.layout.audio_browser_item, 7);
        sparseIntArray.put(R.layout.audio_browser_separator, 8);
        sparseIntArray.put(R.layout.audio_player, 9);
        sparseIntArray.put(R.layout.bookmark_item, 10);
        sparseIntArray.put(R.layout.browser_item, 11);
        sparseIntArray.put(R.layout.browser_item_separator, 12);
        sparseIntArray.put(R.layout.card_browser_item, 13);
        sparseIntArray.put(R.layout.chapter_list_item, 14);
        sparseIntArray.put(R.layout.context_item, 15);
        sparseIntArray.put(R.layout.contextual_sheet, 16);
        sparseIntArray.put(R.layout.cover_media_switcher_item, 17);
        sparseIntArray.put(R.layout.dialog_about_version, 18);
        sparseIntArray.put(R.layout.dialog_add_to_group, 19);
        sparseIntArray.put(R.layout.dialog_audio_controls_settings, 20);
        sparseIntArray.put(R.layout.dialog_display_settings, 21);
        sparseIntArray.put(R.layout.dialog_duplication_warning, 22);
        sparseIntArray.put(R.layout.dialog_ext_device, 23);
        sparseIntArray.put(R.layout.dialog_license, 24);
        sparseIntArray.put(R.layout.dialog_norification_permission, 25);
        sparseIntArray.put(R.layout.dialog_playback_speed, 26);
        sparseIntArray.put(R.layout.dialog_playlist, 27);
        sparseIntArray.put(R.layout.dialog_renderers, 28);
        sparseIntArray.put(R.layout.dialog_time_picker, 29);
        sparseIntArray.put(R.layout.dialog_update, 30);
        sparseIntArray.put(R.layout.dialog_video_controls_settings, 31);
        sparseIntArray.put(R.layout.dialog_whats_new, 32);
        sparseIntArray.put(R.layout.dialog_widget_explanation, 33);
        sparseIntArray.put(R.layout.dialog_widget_migration, 34);
        sparseIntArray.put(R.layout.directory_browser, 35);
        sparseIntArray.put(R.layout.equalizer, 36);
        sparseIntArray.put(R.layout.header_media_list_activity, 37);
        sparseIntArray.put(R.layout.history_item, 38);
        sparseIntArray.put(R.layout.history_item_card, 39);
        sparseIntArray.put(R.layout.info_activity, 40);
        sparseIntArray.put(R.layout.item_renderer, 41);
        sparseIntArray.put(R.layout.library_item, 42);
        sparseIntArray.put(R.layout.license_activity, 43);
        sparseIntArray.put(R.layout.ml_wizard_activity, 44);
        sparseIntArray.put(R.layout.mrl_card_item, 45);
        sparseIntArray.put(R.layout.mrl_dummy_item, 46);
        sparseIntArray.put(R.layout.mrl_item, 47);
        sparseIntArray.put(R.layout.mrl_panel, 48);
        sparseIntArray.put(R.layout.otp_code, 49);
        sparseIntArray.put(R.layout.pin_code_activity, 50);
        sparseIntArray.put(R.layout.player_hud, 51);
        sparseIntArray.put(R.layout.player_hud_right, 52);
        sparseIntArray.put(R.layout.player_option_item, 53);
        sparseIntArray.put(R.layout.player_overlay_track_item, 54);
        sparseIntArray.put(R.layout.player_overlay_tracks, 55);
        sparseIntArray.put(R.layout.playlist_item, 56);
        sparseIntArray.put(R.layout.playlists_fragment, 57);
        sparseIntArray.put(R.layout.preference_item, 58);
        sparseIntArray.put(R.layout.preferences_search_activity, 59);
        sparseIntArray.put(R.layout.search_activity, 60);
        sparseIntArray.put(R.layout.search_item, 61);
        sparseIntArray.put(R.layout.send_crash_activity, 62);
        sparseIntArray.put(R.layout.simple_item, 63);
        sparseIntArray.put(R.layout.sort_display_setting, 64);
        sparseIntArray.put(R.layout.subtitle_download_item, 65);
        sparseIntArray.put(R.layout.subtitle_downloader_dialog, 66);
        sparseIntArray.put(R.layout.video_grid, 67);
        sparseIntArray.put(R.layout.video_grid_card, 68);
        sparseIntArray.put(R.layout.video_list_card, 69);
        sparseIntArray.put(R.layout.video_scale_item, 70);
        sparseIntArray.put(R.layout.video_track_item, 71);
        sparseIntArray.put(R.layout.vlc_login_dialog, 72);
        sparseIntArray.put(R.layout.vlc_progress_dialog, 73);
        sparseIntArray.put(R.layout.vlc_question_dialog, 74);
        sparseIntArray.put(R.layout.widget_content_full_player, 75);
        sparseIntArray.put(R.layout.widget_mini, 76);
        sparseIntArray.put(R.layout.widget_mini_initial, 77);
        sparseIntArray.put(R.layout.widget_mini_player_configure, 78);
    }

    private final ViewDataBinding internalGetViewDataBinding0(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 1:
                if ("layout/about_authors_activity_0".equals(obj)) {
                    return new AboutAuthorsActivityBindingImpl(dataBindingComponent, new View[]{view});
                }
                throw new IllegalArgumentException("The tag for about_authors_activity is invalid. Received: " + obj);
            case 2:
                if ("layout/about_authors_item_0".equals(obj)) {
                    return new AboutAuthorsItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for about_authors_item is invalid. Received: " + obj);
            case 3:
                if ("layout/activity_beta_welcome_0".equals(obj)) {
                    return new ActivityBetaWelcomeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for activity_beta_welcome is invalid. Received: " + obj);
            case 4:
                if ("layout/audio_album_track_item_0".equals(obj)) {
                    return new AudioAlbumTrackItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for audio_album_track_item is invalid. Received: " + obj);
            case 5:
                if ("layout/audio_browser_0".equals(obj)) {
                    return new AudioBrowserBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for audio_browser is invalid. Received: " + obj);
            case 6:
                if ("layout/audio_browser_card_item_0".equals(obj)) {
                    return new AudioBrowserCardItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for audio_browser_card_item is invalid. Received: " + obj);
            case 7:
                if ("layout/audio_browser_item_0".equals(obj)) {
                    return new AudioBrowserItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for audio_browser_item is invalid. Received: " + obj);
            case 8:
                if ("layout/audio_browser_separator_0".equals(obj)) {
                    return new AudioBrowserSeparatorBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for audio_browser_separator is invalid. Received: " + obj);
            case 9:
                if ("layout/audio_player_0".equals(obj)) {
                    return new AudioPlayerBindingImpl(dataBindingComponent, view);
                }
                if ("layout-land/audio_player_0".equals(obj)) {
                    return new AudioPlayerBindingLandImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for audio_player is invalid. Received: " + obj);
            case 10:
                if ("layout/bookmark_item_0".equals(obj)) {
                    return new BookmarkItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for bookmark_item is invalid. Received: " + obj);
            case 11:
                if ("layout/browser_item_0".equals(obj)) {
                    return new BrowserItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for browser_item is invalid. Received: " + obj);
            case 12:
                if ("layout/browser_item_separator_0".equals(obj)) {
                    return new BrowserItemSeparatorBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for browser_item_separator is invalid. Received: " + obj);
            case 13:
                if ("layout/card_browser_item_0".equals(obj)) {
                    return new CardBrowserItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for card_browser_item is invalid. Received: " + obj);
            case 14:
                if ("layout/chapter_list_item_0".equals(obj)) {
                    return new ChapterListItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for chapter_list_item is invalid. Received: " + obj);
            case 15:
                if ("layout/context_item_0".equals(obj)) {
                    return new ContextItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for context_item is invalid. Received: " + obj);
            case 16:
                if ("layout/contextual_sheet_0".equals(obj)) {
                    return new ContextualSheetBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for contextual_sheet is invalid. Received: " + obj);
            case 17:
                if ("layout/cover_media_switcher_item_0".equals(obj)) {
                    return new CoverMediaSwitcherItemBindingImpl(dataBindingComponent, view);
                }
                if ("layout-land/cover_media_switcher_item_0".equals(obj)) {
                    return new CoverMediaSwitcherItemBindingLandImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for cover_media_switcher_item is invalid. Received: " + obj);
            case 18:
                if ("layout/dialog_about_version_0".equals(obj)) {
                    return new DialogAboutVersionBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_about_version is invalid. Received: " + obj);
            case 19:
                if ("layout/dialog_add_to_group_0".equals(obj)) {
                    return new DialogAddToGroupBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_add_to_group is invalid. Received: " + obj);
            case 20:
                if ("layout/dialog_audio_controls_settings_0".equals(obj)) {
                    return new DialogAudioControlsSettingsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_audio_controls_settings is invalid. Received: " + obj);
            case 21:
                if ("layout/dialog_display_settings_0".equals(obj)) {
                    return new DialogDisplaySettingsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_display_settings is invalid. Received: " + obj);
            case 22:
                if ("layout/dialog_duplication_warning_0".equals(obj)) {
                    return new DialogDuplicationWarningBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_duplication_warning is invalid. Received: " + obj);
            case 23:
                if ("layout/dialog_ext_device_0".equals(obj)) {
                    return new DialogExtDeviceBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_ext_device is invalid. Received: " + obj);
            case 24:
                if ("layout/dialog_license_0".equals(obj)) {
                    return new DialogLicenseBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_license is invalid. Received: " + obj);
            case 25:
                if ("layout/dialog_norification_permission_0".equals(obj)) {
                    return new DialogNorificationPermissionBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_norification_permission is invalid. Received: " + obj);
            case 26:
                if ("layout/dialog_playback_speed_0".equals(obj)) {
                    return new DialogPlaybackSpeedBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_playback_speed is invalid. Received: " + obj);
            case 27:
                if ("layout/dialog_playlist_0".equals(obj)) {
                    return new DialogPlaylistBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_playlist is invalid. Received: " + obj);
            case 28:
                if ("layout/dialog_renderers_0".equals(obj)) {
                    return new DialogRenderersBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_renderers is invalid. Received: " + obj);
            case 29:
                if ("layout/dialog_time_picker_0".equals(obj)) {
                    return new DialogTimePickerBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_time_picker is invalid. Received: " + obj);
            case 30:
                if ("layout/dialog_update_0".equals(obj)) {
                    return new DialogUpdateBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_update is invalid. Received: " + obj);
            case 31:
                if ("layout/dialog_video_controls_settings_0".equals(obj)) {
                    return new DialogVideoControlsSettingsBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_video_controls_settings is invalid. Received: " + obj);
            case 32:
                if ("layout/dialog_whats_new_0".equals(obj)) {
                    return new DialogWhatsNewBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_whats_new is invalid. Received: " + obj);
            case 33:
                if ("layout/dialog_widget_explanation_0".equals(obj)) {
                    return new DialogWidgetExplanationBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_widget_explanation is invalid. Received: " + obj);
            case 34:
                if ("layout/dialog_widget_migration_0".equals(obj)) {
                    return new DialogWidgetMigrationBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for dialog_widget_migration is invalid. Received: " + obj);
            case 35:
                if ("layout/directory_browser_0".equals(obj)) {
                    return new DirectoryBrowserBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for directory_browser is invalid. Received: " + obj);
            case 36:
                if ("layout/equalizer_0".equals(obj)) {
                    return new EqualizerBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for equalizer is invalid. Received: " + obj);
            case 37:
                if ("layout/header_media_list_activity_0".equals(obj)) {
                    return new HeaderMediaListActivityBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for header_media_list_activity is invalid. Received: " + obj);
            case 38:
                if ("layout/history_item_0".equals(obj)) {
                    return new HistoryItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for history_item is invalid. Received: " + obj);
            case 39:
                if ("layout/history_item_card_0".equals(obj)) {
                    return new HistoryItemCardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for history_item_card is invalid. Received: " + obj);
            case 40:
                if ("layout/info_activity_0".equals(obj)) {
                    return new InfoActivityBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for info_activity is invalid. Received: " + obj);
            case 41:
                if ("layout/item_renderer_0".equals(obj)) {
                    return new ItemRendererBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for item_renderer is invalid. Received: " + obj);
            case 42:
                if ("layout/library_item_0".equals(obj)) {
                    return new LibraryItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for library_item is invalid. Received: " + obj);
            case 43:
                if ("layout/license_activity_0".equals(obj)) {
                    return new LicenseActivityBindingImpl(dataBindingComponent, new View[]{view});
                }
                throw new IllegalArgumentException("The tag for license_activity is invalid. Received: " + obj);
            case 44:
                if ("layout/ml_wizard_activity_0".equals(obj)) {
                    return new MlWizardActivityBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for ml_wizard_activity is invalid. Received: " + obj);
            case 45:
                if ("layout/mrl_card_item_0".equals(obj)) {
                    return new MrlCardItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for mrl_card_item is invalid. Received: " + obj);
            case 46:
                if ("layout/mrl_dummy_item_0".equals(obj)) {
                    return new MrlDummyItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for mrl_dummy_item is invalid. Received: " + obj);
            case 47:
                if ("layout/mrl_item_0".equals(obj)) {
                    return new MrlItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for mrl_item is invalid. Received: " + obj);
            case 48:
                if ("layout-v21/mrl_panel_0".equals(obj)) {
                    return new MrlPanelBindingV21Impl(dataBindingComponent, view);
                }
                if ("layout/mrl_panel_0".equals(obj)) {
                    return new MrlPanelBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for mrl_panel is invalid. Received: " + obj);
            case 49:
                if ("layout/otp_code_0".equals(obj)) {
                    return new OtpCodeBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for otp_code is invalid. Received: " + obj);
            case 50:
                if ("layout/pin_code_activity_0".equals(obj)) {
                    return new PinCodeActivityBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for pin_code_activity is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    private final ViewDataBinding internalGetViewDataBinding1(DataBindingComponent dataBindingComponent, View view, int i, Object obj) {
        switch (i) {
            case 51:
                if ("layout/player_hud_0".equals(obj)) {
                    return new PlayerHudBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for player_hud is invalid. Received: " + obj);
            case 52:
                if ("layout/player_hud_right_0".equals(obj)) {
                    return new PlayerHudRightBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for player_hud_right is invalid. Received: " + obj);
            case 53:
                if ("layout/player_option_item_0".equals(obj)) {
                    return new PlayerOptionItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for player_option_item is invalid. Received: " + obj);
            case 54:
                if ("layout/player_overlay_track_item_0".equals(obj)) {
                    return new PlayerOverlayTrackItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for player_overlay_track_item is invalid. Received: " + obj);
            case 55:
                if ("layout-land/player_overlay_tracks_0".equals(obj)) {
                    return new PlayerOverlayTracksBindingLandImpl(dataBindingComponent, view);
                }
                if ("layout/player_overlay_tracks_0".equals(obj)) {
                    return new PlayerOverlayTracksBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for player_overlay_tracks is invalid. Received: " + obj);
            case 56:
                if ("layout/playlist_item_0".equals(obj)) {
                    return new PlaylistItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for playlist_item is invalid. Received: " + obj);
            case 57:
                if ("layout/playlists_fragment_0".equals(obj)) {
                    return new PlaylistsFragmentBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for playlists_fragment is invalid. Received: " + obj);
            case 58:
                if ("layout/preference_item_0".equals(obj)) {
                    return new PreferenceItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for preference_item is invalid. Received: " + obj);
            case 59:
                if ("layout/preferences_search_activity_0".equals(obj)) {
                    return new PreferencesSearchActivityBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for preferences_search_activity is invalid. Received: " + obj);
            case 60:
                if ("layout/search_activity_0".equals(obj)) {
                    return new SearchActivityBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for search_activity is invalid. Received: " + obj);
            case 61:
                if ("layout/search_item_0".equals(obj)) {
                    return new SearchItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for search_item is invalid. Received: " + obj);
            case 62:
                if ("layout/send_crash_activity_0".equals(obj)) {
                    return new SendCrashActivityBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for send_crash_activity is invalid. Received: " + obj);
            case 63:
                if ("layout/simple_item_0".equals(obj)) {
                    return new SimpleItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for simple_item is invalid. Received: " + obj);
            case 64:
                if ("layout/sort_display_setting_0".equals(obj)) {
                    return new SortDisplaySettingBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for sort_display_setting is invalid. Received: " + obj);
            case 65:
                if ("layout/subtitle_download_item_0".equals(obj)) {
                    return new SubtitleDownloadItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for subtitle_download_item is invalid. Received: " + obj);
            case 66:
                if ("layout/subtitle_downloader_dialog_0".equals(obj)) {
                    return new SubtitleDownloaderDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for subtitle_downloader_dialog is invalid. Received: " + obj);
            case 67:
                if ("layout/video_grid_0".equals(obj)) {
                    return new VideoGridBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for video_grid is invalid. Received: " + obj);
            case 68:
                if ("layout/video_grid_card_0".equals(obj)) {
                    return new VideoGridCardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for video_grid_card is invalid. Received: " + obj);
            case 69:
                if ("layout/video_list_card_0".equals(obj)) {
                    return new VideoListCardBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for video_list_card is invalid. Received: " + obj);
            case 70:
                if ("layout/video_scale_item_0".equals(obj)) {
                    return new VideoScaleItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for video_scale_item is invalid. Received: " + obj);
            case 71:
                if ("layout/video_track_item_0".equals(obj)) {
                    return new VideoTrackItemBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for video_track_item is invalid. Received: " + obj);
            case 72:
                if ("layout/vlc_login_dialog_0".equals(obj)) {
                    return new VlcLoginDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for vlc_login_dialog is invalid. Received: " + obj);
            case 73:
                if ("layout/vlc_progress_dialog_0".equals(obj)) {
                    return new VlcProgressDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for vlc_progress_dialog is invalid. Received: " + obj);
            case 74:
                if ("layout/vlc_question_dialog_0".equals(obj)) {
                    return new VlcQuestionDialogBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for vlc_question_dialog is invalid. Received: " + obj);
            case 75:
                if ("layout/widget_content_full_player_0".equals(obj)) {
                    return new WidgetContentFullPlayerBindingImpl(dataBindingComponent, new View[]{view});
                }
                throw new IllegalArgumentException("The tag for widget_content_full_player is invalid. Received: " + obj);
            case 76:
                if ("layout/widget_mini_0".equals(obj)) {
                    return new WidgetMiniBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for widget_mini is invalid. Received: " + obj);
            case 77:
                if ("layout/widget_mini_initial_0".equals(obj)) {
                    return new WidgetMiniInitialBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for widget_mini_initial is invalid. Received: " + obj);
            case 78:
                if ("layout/widget_mini_player_configure_0".equals(obj)) {
                    return new WidgetMiniPlayerConfigureBindingImpl(dataBindingComponent, view);
                }
                throw new IllegalArgumentException("The tag for widget_mini_player_configure is invalid. Received: " + obj);
            default:
                return null;
        }
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View view, int i) {
        int i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i);
        if (i2 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag != null) {
            int i3 = (i2 - 1) / 50;
            if (i3 == 0) {
                return internalGetViewDataBinding0(dataBindingComponent, view, i2, tag);
            }
            if (i3 != 1) {
                return null;
            }
            return internalGetViewDataBinding1(dataBindingComponent, view, i2, tag);
        }
        throw new RuntimeException("view must have a tag");
    }

    public ViewDataBinding getDataBinder(DataBindingComponent dataBindingComponent, View[] viewArr, int i) {
        int i2;
        if (!(viewArr == null || viewArr.length == 0 || (i2 = INTERNAL_LAYOUT_ID_LOOKUP.get(i)) <= 0)) {
            Object tag = viewArr[0].getTag();
            if (tag == null) {
                throw new RuntimeException("view must have a tag");
            } else if (i2 != 1) {
                if (i2 != 43) {
                    if (i2 == 75) {
                        if ("layout/widget_content_full_player_0".equals(tag)) {
                            return new WidgetContentFullPlayerBindingImpl(dataBindingComponent, viewArr);
                        }
                        throw new IllegalArgumentException("The tag for widget_content_full_player is invalid. Received: " + tag);
                    }
                } else if ("layout/license_activity_0".equals(tag)) {
                    return new LicenseActivityBindingImpl(dataBindingComponent, viewArr);
                } else {
                    throw new IllegalArgumentException("The tag for license_activity is invalid. Received: " + tag);
                }
            } else if ("layout/about_authors_activity_0".equals(tag)) {
                return new AboutAuthorsActivityBindingImpl(dataBindingComponent, viewArr);
            } else {
                throw new IllegalArgumentException("The tag for about_authors_activity is invalid. Received: " + tag);
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
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new org.videolan.medialibrary.DataBinderMapperImpl());
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
            HashMap<String, Integer> hashMap = new HashMap<>(82);
            sKeys = hashMap;
            hashMap.put("layout/about_authors_activity_0", Integer.valueOf(R.layout.about_authors_activity));
            hashMap.put("layout/about_authors_item_0", Integer.valueOf(R.layout.about_authors_item));
            hashMap.put("layout/activity_beta_welcome_0", Integer.valueOf(R.layout.activity_beta_welcome));
            hashMap.put("layout/audio_album_track_item_0", Integer.valueOf(R.layout.audio_album_track_item));
            hashMap.put("layout/audio_browser_0", Integer.valueOf(R.layout.audio_browser));
            hashMap.put("layout/audio_browser_card_item_0", Integer.valueOf(R.layout.audio_browser_card_item));
            hashMap.put("layout/audio_browser_item_0", Integer.valueOf(R.layout.audio_browser_item));
            hashMap.put("layout/audio_browser_separator_0", Integer.valueOf(R.layout.audio_browser_separator));
            hashMap.put("layout/audio_player_0", Integer.valueOf(R.layout.audio_player));
            hashMap.put("layout-land/audio_player_0", Integer.valueOf(R.layout.audio_player));
            hashMap.put("layout/bookmark_item_0", Integer.valueOf(R.layout.bookmark_item));
            hashMap.put("layout/browser_item_0", Integer.valueOf(R.layout.browser_item));
            hashMap.put("layout/browser_item_separator_0", Integer.valueOf(R.layout.browser_item_separator));
            hashMap.put("layout/card_browser_item_0", Integer.valueOf(R.layout.card_browser_item));
            hashMap.put("layout/chapter_list_item_0", Integer.valueOf(R.layout.chapter_list_item));
            hashMap.put("layout/context_item_0", Integer.valueOf(R.layout.context_item));
            hashMap.put("layout/contextual_sheet_0", Integer.valueOf(R.layout.contextual_sheet));
            hashMap.put("layout/cover_media_switcher_item_0", Integer.valueOf(R.layout.cover_media_switcher_item));
            hashMap.put("layout-land/cover_media_switcher_item_0", Integer.valueOf(R.layout.cover_media_switcher_item));
            hashMap.put("layout/dialog_about_version_0", Integer.valueOf(R.layout.dialog_about_version));
            hashMap.put("layout/dialog_add_to_group_0", Integer.valueOf(R.layout.dialog_add_to_group));
            hashMap.put("layout/dialog_audio_controls_settings_0", Integer.valueOf(R.layout.dialog_audio_controls_settings));
            hashMap.put("layout/dialog_display_settings_0", Integer.valueOf(R.layout.dialog_display_settings));
            hashMap.put("layout/dialog_duplication_warning_0", Integer.valueOf(R.layout.dialog_duplication_warning));
            hashMap.put("layout/dialog_ext_device_0", Integer.valueOf(R.layout.dialog_ext_device));
            hashMap.put("layout/dialog_license_0", Integer.valueOf(R.layout.dialog_license));
            hashMap.put("layout/dialog_norification_permission_0", Integer.valueOf(R.layout.dialog_norification_permission));
            hashMap.put("layout/dialog_playback_speed_0", Integer.valueOf(R.layout.dialog_playback_speed));
            hashMap.put("layout/dialog_playlist_0", Integer.valueOf(R.layout.dialog_playlist));
            hashMap.put("layout/dialog_renderers_0", Integer.valueOf(R.layout.dialog_renderers));
            hashMap.put("layout/dialog_time_picker_0", Integer.valueOf(R.layout.dialog_time_picker));
            hashMap.put("layout/dialog_update_0", Integer.valueOf(R.layout.dialog_update));
            hashMap.put("layout/dialog_video_controls_settings_0", Integer.valueOf(R.layout.dialog_video_controls_settings));
            hashMap.put("layout/dialog_whats_new_0", Integer.valueOf(R.layout.dialog_whats_new));
            hashMap.put("layout/dialog_widget_explanation_0", Integer.valueOf(R.layout.dialog_widget_explanation));
            hashMap.put("layout/dialog_widget_migration_0", Integer.valueOf(R.layout.dialog_widget_migration));
            hashMap.put("layout/directory_browser_0", Integer.valueOf(R.layout.directory_browser));
            hashMap.put("layout/equalizer_0", Integer.valueOf(R.layout.equalizer));
            hashMap.put("layout/header_media_list_activity_0", Integer.valueOf(R.layout.header_media_list_activity));
            hashMap.put("layout/history_item_0", Integer.valueOf(R.layout.history_item));
            hashMap.put("layout/history_item_card_0", Integer.valueOf(R.layout.history_item_card));
            hashMap.put("layout/info_activity_0", Integer.valueOf(R.layout.info_activity));
            hashMap.put("layout/item_renderer_0", Integer.valueOf(R.layout.item_renderer));
            hashMap.put("layout/library_item_0", Integer.valueOf(R.layout.library_item));
            hashMap.put("layout/license_activity_0", Integer.valueOf(R.layout.license_activity));
            hashMap.put("layout/ml_wizard_activity_0", Integer.valueOf(R.layout.ml_wizard_activity));
            hashMap.put("layout/mrl_card_item_0", Integer.valueOf(R.layout.mrl_card_item));
            hashMap.put("layout/mrl_dummy_item_0", Integer.valueOf(R.layout.mrl_dummy_item));
            hashMap.put("layout/mrl_item_0", Integer.valueOf(R.layout.mrl_item));
            hashMap.put("layout-v21/mrl_panel_0", Integer.valueOf(R.layout.mrl_panel));
            hashMap.put("layout/mrl_panel_0", Integer.valueOf(R.layout.mrl_panel));
            hashMap.put("layout/otp_code_0", Integer.valueOf(R.layout.otp_code));
            hashMap.put("layout/pin_code_activity_0", Integer.valueOf(R.layout.pin_code_activity));
            hashMap.put("layout/player_hud_0", Integer.valueOf(R.layout.player_hud));
            hashMap.put("layout/player_hud_right_0", Integer.valueOf(R.layout.player_hud_right));
            hashMap.put("layout/player_option_item_0", Integer.valueOf(R.layout.player_option_item));
            hashMap.put("layout/player_overlay_track_item_0", Integer.valueOf(R.layout.player_overlay_track_item));
            hashMap.put("layout-land/player_overlay_tracks_0", Integer.valueOf(R.layout.player_overlay_tracks));
            hashMap.put("layout/player_overlay_tracks_0", Integer.valueOf(R.layout.player_overlay_tracks));
            hashMap.put("layout/playlist_item_0", Integer.valueOf(R.layout.playlist_item));
            hashMap.put("layout/playlists_fragment_0", Integer.valueOf(R.layout.playlists_fragment));
            hashMap.put("layout/preference_item_0", Integer.valueOf(R.layout.preference_item));
            hashMap.put("layout/preferences_search_activity_0", Integer.valueOf(R.layout.preferences_search_activity));
            hashMap.put("layout/search_activity_0", Integer.valueOf(R.layout.search_activity));
            hashMap.put("layout/search_item_0", Integer.valueOf(R.layout.search_item));
            hashMap.put("layout/send_crash_activity_0", Integer.valueOf(R.layout.send_crash_activity));
            hashMap.put("layout/simple_item_0", Integer.valueOf(R.layout.simple_item));
            hashMap.put("layout/sort_display_setting_0", Integer.valueOf(R.layout.sort_display_setting));
            hashMap.put("layout/subtitle_download_item_0", Integer.valueOf(R.layout.subtitle_download_item));
            hashMap.put("layout/subtitle_downloader_dialog_0", Integer.valueOf(R.layout.subtitle_downloader_dialog));
            hashMap.put("layout/video_grid_0", Integer.valueOf(R.layout.video_grid));
            hashMap.put("layout/video_grid_card_0", Integer.valueOf(R.layout.video_grid_card));
            hashMap.put("layout/video_list_card_0", Integer.valueOf(R.layout.video_list_card));
            hashMap.put("layout/video_scale_item_0", Integer.valueOf(R.layout.video_scale_item));
            hashMap.put("layout/video_track_item_0", Integer.valueOf(R.layout.video_track_item));
            hashMap.put("layout/vlc_login_dialog_0", Integer.valueOf(R.layout.vlc_login_dialog));
            hashMap.put("layout/vlc_progress_dialog_0", Integer.valueOf(R.layout.vlc_progress_dialog));
            hashMap.put("layout/vlc_question_dialog_0", Integer.valueOf(R.layout.vlc_question_dialog));
            hashMap.put("layout/widget_content_full_player_0", Integer.valueOf(R.layout.widget_content_full_player));
            hashMap.put("layout/widget_mini_0", Integer.valueOf(R.layout.widget_mini));
            hashMap.put("layout/widget_mini_initial_0", Integer.valueOf(R.layout.widget_mini_initial));
            hashMap.put("layout/widget_mini_player_configure_0", Integer.valueOf(R.layout.widget_mini_player_configure));
        }
    }
}
