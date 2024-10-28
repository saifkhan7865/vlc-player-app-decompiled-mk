package org.videolan.medialibrary;

import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import io.ktor.util.date.GMTDateParser;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.fusesource.jansi.AnsiRenderer;
import org.slf4j.Marker;
import org.videolan.libvlc.util.VLCUtil;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;

public class Tools {
    private static final String TAG = "VLC/Tools";
    private static final ThreadLocal<NumberFormat> TWO_DIGITS = new ThreadLocal<NumberFormat>() {
        /* access modifiers changed from: protected */
        public NumberFormat initialValue() {
            NumberFormat instance = NumberFormat.getInstance(Locale.US);
            if (instance instanceof DecimalFormat) {
                ((DecimalFormat) instance).applyPattern("00");
            }
            return instance;
        }
    };

    public static Uri convertLocalUri(Uri uri) {
        return (!TextUtils.equals(uri.getScheme(), "file") || !uri.getPath().startsWith("/sdcard")) ? uri : Uri.parse(uri.toString().replace("/sdcard", Environment.getExternalStorageDirectory().getPath()));
    }

    public static boolean isArrayEmpty(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    public static String getProgressText(MediaWrapper mediaWrapper) {
        long time = mediaWrapper.getTime();
        if (time <= 0) {
            return "";
        }
        return String.format("%s / %s", new Object[]{millisToString(time, true, false, false), millisToString(mediaWrapper.getLength(), true, false, false)});
    }

    public static String millisToString(long j) {
        return millisToString(j, false, true, false);
    }

    public static String millisToText(long j) {
        return millisToString(j, true, true, false);
    }

    public static String millisToTextLarge(long j) {
        return millisToString(j, true, true, true);
    }

    public static String getResolution(MediaWrapper mediaWrapper) {
        if (mediaWrapper.getWidth() <= 0 || mediaWrapper.getHeight() <= 0) {
            return "";
        }
        return String.format(Locale.US, "%dx%d", new Object[]{Integer.valueOf(mediaWrapper.getWidth()), Integer.valueOf(mediaWrapper.getHeight())});
    }

    public static void setMediaDescription(MediaLibraryItem mediaLibraryItem) {
        if (mediaLibraryItem.getItemType() == 32) {
            MediaWrapper mediaWrapper = (MediaWrapper) mediaLibraryItem;
            if (mediaWrapper.getType() == 0) {
                String millisToString = mediaWrapper.getLength() == 0 ? null : mediaWrapper.getTime() == 0 ? millisToString(mediaWrapper.getLength()) : getProgressText(mediaWrapper);
                boolean isEmpty = true ^ TextUtils.isEmpty(millisToString);
                StringBuilder sb = new StringBuilder();
                if (isEmpty) {
                    sb.append(millisToString);
                } else {
                    sb.append(millisToString(mediaWrapper.getLength()));
                }
                mediaLibraryItem.setDescription(sb.toString());
            } else if (mediaWrapper.getType() == 1) {
                String referenceArtist = mediaWrapper.getReferenceArtist();
                String album = mediaWrapper.getAlbum();
                StringBuilder sb2 = new StringBuilder();
                boolean z = !TextUtils.isEmpty(referenceArtist);
                boolean isEmpty2 = true ^ TextUtils.isEmpty(album);
                if (z && isEmpty2) {
                    sb2.append(referenceArtist);
                    sb2.append(" - ");
                    sb2.append(album);
                } else if (z) {
                    sb2.append(referenceArtist);
                } else {
                    sb2.append(album);
                }
                mediaLibraryItem.setDescription(sb2.toString());
            }
        }
    }

    public static String millisToString(long j, boolean z, boolean z2, boolean z3) {
        String str;
        String str2;
        String str3;
        StringBuilder sb = new StringBuilder();
        if (j < 0) {
            j = -j;
            sb.append("-");
        }
        long j2 = j / 1000;
        int i = (int) (j2 % 60);
        long j3 = j2 / 60;
        int i2 = (int) (j3 % 60);
        int i3 = (int) (j3 / 60);
        String str4 = AnsiRenderer.CODE_TEXT_SEPARATOR;
        if (z) {
            if (i3 > 0) {
                sb.append(i3);
                sb.append(GMTDateParser.HOURS);
                if (z3) {
                    str3 = str4;
                } else {
                    str3 = "";
                }
                sb.append(str3);
            }
            if (i2 > 0) {
                sb.append(i2);
                sb.append("min");
                if (z3) {
                    str2 = str4;
                } else {
                    str2 = "";
                }
                sb.append(str2);
            }
            if ((z2 || sb.length() == 0) && i > 0) {
                sb.append(i);
                sb.append("s");
                if (!z3) {
                    str4 = "";
                }
                sb.append(str4);
            }
        } else if (i3 > 0) {
            sb.append(i3);
            sb.append(AbstractJsonLexerKt.COLON);
            if (z3) {
                str = str4;
            } else {
                str = "";
            }
            sb.append(str);
            ThreadLocal<NumberFormat> threadLocal = TWO_DIGITS;
            sb.append(threadLocal.get().format((long) i2));
            sb.append(AbstractJsonLexerKt.COLON);
            if (!z3) {
                str4 = "";
            }
            sb.append(str4);
            sb.append(threadLocal.get().format((long) i));
        } else {
            sb.append(i2);
            sb.append(AbstractJsonLexerKt.COLON);
            if (!z3) {
                str4 = "";
            }
            sb.append(str4);
            sb.append(TWO_DIGITS.get().format((long) i));
        }
        return sb.toString();
    }

    public static String mlEncodeMrl(String str) {
        if (str.startsWith("/")) {
            str = "file://" + str;
        }
        return VLCUtil.encodeVLCString(str.replace(AnsiRenderer.CODE_TEXT_SEPARATOR, "%20").replace(Marker.ANY_NON_NULL_MARKER, "%2B"));
    }

    public static String encodeVLCMrl(String str) {
        if (str.startsWith("/")) {
            str = "file://" + str;
        }
        return VLCUtil.encodeVLCString(str);
    }

    public static Boolean hasSubString(String str, String str2) {
        return Boolean.valueOf(Pattern.compile(Pattern.quote(str2), 2).matcher(str).find());
    }
}
