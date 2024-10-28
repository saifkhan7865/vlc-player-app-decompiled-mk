package retrofit2;

import j$.util.Objects;
import javax.annotation.Nullable;
import org.fusesource.jansi.AnsiRenderer;

public class HttpException extends RuntimeException {
    private final int code;
    private final String message;
    private final transient Response<?> response;

    private static String getMessage(Response<?> response2) {
        Objects.requireNonNull(response2, "response == null");
        return "HTTP " + response2.code() + AnsiRenderer.CODE_TEXT_SEPARATOR + response2.message();
    }

    public HttpException(Response<?> response2) {
        super(getMessage(response2));
        this.code = response2.code();
        this.message = response2.message();
        this.response = response2;
    }

    public int code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    @Nullable
    public Response<?> response() {
        return this.response;
    }
}
