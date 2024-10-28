package androidx.car.app;

import android.os.RemoteException;
import android.util.Log;
import androidx.car.app.serialization.BundlerException;
import j$.util.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.InvalidParameterException;

public final class FailureResponse {
    public static final int BUNDLER_EXCEPTION = 1;
    public static final int ILLEGAL_STATE_EXCEPTION = 2;
    public static final int INVALID_PARAMETER_EXCEPTION = 3;
    public static final int REMOTE_EXCEPTION = 6;
    public static final int RUNTIME_EXCEPTION = 5;
    public static final int SECURITY_EXCEPTION = 4;
    public static final int UNKNOWN_ERROR = 0;
    private final int mErrorType;
    private final String mStackTrace;

    @Retention(RetentionPolicy.SOURCE)
    public @interface ErrorType {
    }

    public FailureResponse(Throwable th) {
        this.mStackTrace = Log.getStackTraceString((Throwable) Objects.requireNonNull(th));
        if (th instanceof BundlerException) {
            this.mErrorType = 1;
        } else if (th instanceof IllegalStateException) {
            this.mErrorType = 2;
        } else if (th instanceof InvalidParameterException) {
            this.mErrorType = 3;
        } else if (th instanceof SecurityException) {
            this.mErrorType = 4;
        } else if (th instanceof RuntimeException) {
            this.mErrorType = 5;
        } else if (th instanceof RemoteException) {
            this.mErrorType = 6;
        } else {
            this.mErrorType = 0;
        }
    }

    private FailureResponse() {
        this.mStackTrace = null;
        this.mErrorType = 0;
    }

    public String getStackTrace() {
        return (String) Objects.requireNonNull(this.mStackTrace);
    }

    public int getErrorType() {
        return this.mErrorType;
    }

    public int hashCode() {
        return Objects.hash(Integer.valueOf(this.mErrorType), this.mStackTrace);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FailureResponse)) {
            return false;
        }
        FailureResponse failureResponse = (FailureResponse) obj;
        if (this.mErrorType != failureResponse.mErrorType || !Objects.equals(this.mStackTrace, failureResponse.mStackTrace)) {
            return false;
        }
        return true;
    }
}
