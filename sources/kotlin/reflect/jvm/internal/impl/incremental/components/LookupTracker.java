package kotlin.reflect.jvm.internal.impl.incremental.components;

import io.ktor.http.ContentDisposition;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

/* compiled from: LookupTracker.kt */
public interface LookupTracker {
    boolean getRequiresPosition();

    void record(String str, Position position, String str2, ScopeKind scopeKind, String str3);

    /* compiled from: LookupTracker.kt */
    public static final class DO_NOTHING implements LookupTracker {
        public static final DO_NOTHING INSTANCE = new DO_NOTHING();

        public boolean getRequiresPosition() {
            return false;
        }

        public void record(String str, Position position, String str2, ScopeKind scopeKind, String str3) {
            Intrinsics.checkNotNullParameter(str, "filePath");
            Intrinsics.checkNotNullParameter(position, Constants.PLAY_EXTRA_START_TIME);
            Intrinsics.checkNotNullParameter(str2, "scopeFqName");
            Intrinsics.checkNotNullParameter(scopeKind, "scopeKind");
            Intrinsics.checkNotNullParameter(str3, ContentDisposition.Parameters.Name);
        }

        private DO_NOTHING() {
        }
    }
}
