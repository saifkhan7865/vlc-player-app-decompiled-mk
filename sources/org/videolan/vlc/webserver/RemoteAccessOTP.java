package org.videolan.vlc.webserver;

import android.content.Context;
import androidx.core.app.NotificationManagerCompat;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.videolan.vlc.gui.helpers.NotificationHelper;
import org.videolan.vlc.webserver.ssl.SecretGenerator;
import org.videolan.vlc.webserver.utils.CypherUtils;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\b\u0010\t\u001a\u00020\u0005H\u0002J\u000e\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\bR\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/videolan/vlc/webserver/RemoteAccessOTP;", "", "()V", "codes", "Ljava/util/ArrayList;", "Lorg/videolan/vlc/webserver/OTPCode;", "Lkotlin/collections/ArrayList;", "generateCode", "", "generateOTPCode", "getFirstValidCode", "appContext", "Landroid/content/Context;", "removeCodeWithChallenge", "", "challenge", "verifyCode", "", "saltedCode", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessOTP.kt */
public final class RemoteAccessOTP {
    public static final RemoteAccessOTP INSTANCE = new RemoteAccessOTP();
    private static final ArrayList<OTPCode> codes = new ArrayList<>();

    private RemoteAccessOTP() {
    }

    private final OTPCode generateOTPCode() {
        OTPCode oTPCode = new OTPCode(generateCode(), SecretGenerator.INSTANCE.generateRandomString(), System.currentTimeMillis() + ((long) 60000));
        codes.add(oTPCode);
        return oTPCode;
    }

    public final String generateCode() {
        return String.valueOf(new SecureRandom().nextInt(8999) + 1000);
    }

    public final boolean verifyCode(Context context, String str) {
        Intrinsics.checkNotNullParameter(context, "appContext");
        Intrinsics.checkNotNullParameter(str, "saltedCode");
        for (OTPCode oTPCode : codes) {
            CypherUtils cypherUtils = CypherUtils.INSTANCE;
            if (Intrinsics.areEqual((Object) cypherUtils.hash(oTPCode.getCode() + oTPCode.getChallenge()), (Object) str) && System.currentTimeMillis() < oTPCode.getExpiration()) {
                NotificationManagerCompat.from(context).cancel(45);
                codes.remove(oTPCode);
                return true;
            }
        }
        return false;
    }

    public final OTPCode getFirstValidCode(Context context) {
        Intrinsics.checkNotNullParameter(context, "appContext");
        ArrayList<Number> arrayList = new ArrayList<>();
        int i = 0;
        for (Object next : codes) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            OTPCode oTPCode = (OTPCode) next;
            if (System.currentTimeMillis() < oTPCode.getExpiration()) {
                return oTPCode;
            }
            arrayList.add(Integer.valueOf(i));
            i = i2;
        }
        CollectionsKt.sortDescending(arrayList);
        for (Number intValue : arrayList) {
            codes.remove(intValue.intValue());
        }
        OTPCode generateOTPCode = generateOTPCode();
        NotificationManagerCompat.from(context).notify(45, NotificationHelper.INSTANCE.createRemoteAccessOtpNotification(context, generateOTPCode.getCode()));
        return generateOTPCode;
    }

    public final void removeCodeWithChallenge(String str) {
        Object obj;
        Intrinsics.checkNotNullParameter(str, "challenge");
        ArrayList<OTPCode> arrayList = codes;
        Collection collection = arrayList;
        Iterator it = arrayList.iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) str, (Object) ((OTPCode) obj).getChallenge())) {
                break;
            }
        }
        TypeIntrinsics.asMutableCollection(collection).remove(obj);
    }
}
