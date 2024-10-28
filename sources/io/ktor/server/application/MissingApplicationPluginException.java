package io.ktor.server.application;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.util.AttributeKey;
import io.ktor.util.internal.ExceptionUtilsJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00060\u0001j\u0002`\u00022\b\u0012\u0004\u0012\u00020\u00000\u0003B\u0011\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u0000H\u0016R\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u000e"}, d2 = {"Lio/ktor/server/application/MissingApplicationPluginException;", "Ljava/lang/IllegalStateException;", "Lkotlin/IllegalStateException;", "Lkotlinx/coroutines/CopyableThrowable;", "key", "Lio/ktor/util/AttributeKey;", "(Lio/ktor/util/AttributeKey;)V", "getKey", "()Lio/ktor/util/AttributeKey;", "message", "", "getMessage", "()Ljava/lang/String;", "createCopy", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationPlugin.kt */
public final class MissingApplicationPluginException extends IllegalStateException implements CopyableThrowable<MissingApplicationPluginException> {
    private final AttributeKey<?> key;

    public final AttributeKey<?> getKey() {
        return this.key;
    }

    public MissingApplicationPluginException(AttributeKey<?> attributeKey) {
        Intrinsics.checkNotNullParameter(attributeKey, LeanbackPreferenceDialogFragment.ARG_KEY);
        this.key = attributeKey;
    }

    public String getMessage() {
        return "Application plugin " + this.key.getName() + " is not installed";
    }

    public MissingApplicationPluginException createCopy() {
        MissingApplicationPluginException missingApplicationPluginException = new MissingApplicationPluginException(this.key);
        ExceptionUtilsJvmKt.initCauseBridge(missingApplicationPluginException, this);
        return missingApplicationPluginException;
    }
}
