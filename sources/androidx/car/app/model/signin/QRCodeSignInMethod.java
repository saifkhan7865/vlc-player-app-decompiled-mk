package androidx.car.app.model.signin;

import android.net.Uri;
import androidx.car.app.annotations.RequiresCarApi;
import androidx.car.app.model.signin.SignInTemplate;
import j$.util.Objects;

@RequiresCarApi(4)
public final class QRCodeSignInMethod implements SignInTemplate.SignInMethod {
    private final Uri mUri;

    public QRCodeSignInMethod(Uri uri) {
        this.mUri = (Uri) Objects.requireNonNull(uri);
    }

    public Uri getUri() {
        return (Uri) Objects.requireNonNull(this.mUri);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QRCodeSignInMethod)) {
            return false;
        }
        return Objects.equals(this.mUri, ((QRCodeSignInMethod) obj).mUri);
    }

    public int hashCode() {
        return Objects.hash(this.mUri);
    }

    private QRCodeSignInMethod() {
        this.mUri = null;
    }
}
