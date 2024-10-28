package org.videolan.vlc.webserver.utils;

import java.security.MessageDigest;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\u0006"}, d2 = {"Lorg/videolan/vlc/webserver/utils/CypherUtils;", "", "()V", "hash", "", "message", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: CypherUtils.kt */
public final class CypherUtils {
    public static final CypherUtils INSTANCE = new CypherUtils();

    private CypherUtils() {
    }

    public final String hash(String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        MessageDigest instance = MessageDigest.getInstance("SHA-256");
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        byte[] digest = instance.digest(bytes);
        Intrinsics.checkNotNull(digest);
        String str2 = "";
        for (byte valueOf : digest) {
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            String format = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(valueOf)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            sb.append(format);
            str2 = sb.toString();
        }
        return str2;
    }
}
