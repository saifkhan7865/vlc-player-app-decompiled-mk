package org.videolan.vlc;

import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import org.videolan.tools.Settings;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Landroid/content/SharedPreferences;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$settings$2 extends Lambda implements Function0<SharedPreferences> {
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$settings$2(MediaParsingService mediaParsingService) {
        super(0);
        this.this$0 = mediaParsingService;
    }

    public final SharedPreferences invoke() {
        return (SharedPreferences) Settings.INSTANCE.getInstance(this.this$0);
    }
}
