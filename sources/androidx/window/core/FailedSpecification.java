package androidx.window.core;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B-\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u000f\u0010\u001b\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0002\u0010\u0017J/\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010\u0007\u001a\u00020\u00062\u0017\u0010\u001d\u001a\u0013\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u001f0\u001e¢\u0006\u0002\b H\u0016R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006!"}, d2 = {"Landroidx/window/core/FailedSpecification;", "T", "", "Landroidx/window/core/SpecificationComputer;", "value", "tag", "", "message", "logger", "Landroidx/window/core/Logger;", "verificationMode", "Landroidx/window/core/VerificationMode;", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;Landroidx/window/core/Logger;Landroidx/window/core/VerificationMode;)V", "exception", "Landroidx/window/core/WindowStrictModeException;", "getException", "()Landroidx/window/core/WindowStrictModeException;", "getLogger", "()Landroidx/window/core/Logger;", "getMessage", "()Ljava/lang/String;", "getTag", "getValue", "()Ljava/lang/Object;", "Ljava/lang/Object;", "getVerificationMode", "()Landroidx/window/core/VerificationMode;", "compute", "require", "condition", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "window_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SpecificationComputer.kt */
final class FailedSpecification<T> extends SpecificationComputer<T> {
    private final WindowStrictModeException exception;
    private final Logger logger;
    private final String message;
    private final String tag;
    private final T value;
    private final VerificationMode verificationMode;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SpecificationComputer.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                androidx.window.core.VerificationMode[] r0 = androidx.window.core.VerificationMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.window.core.VerificationMode r1 = androidx.window.core.VerificationMode.STRICT     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.window.core.VerificationMode r1 = androidx.window.core.VerificationMode.LOG     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                androidx.window.core.VerificationMode r1 = androidx.window.core.VerificationMode.QUIET     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.window.core.FailedSpecification.WhenMappings.<clinit>():void");
        }
    }

    public final T getValue() {
        return this.value;
    }

    public final String getTag() {
        return this.tag;
    }

    public final String getMessage() {
        return this.message;
    }

    public final Logger getLogger() {
        return this.logger;
    }

    public final VerificationMode getVerificationMode() {
        return this.verificationMode;
    }

    public FailedSpecification(T t, String str, String str2, Logger logger2, VerificationMode verificationMode2) {
        Intrinsics.checkNotNullParameter(t, "value");
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(str2, "message");
        Intrinsics.checkNotNullParameter(logger2, "logger");
        Intrinsics.checkNotNullParameter(verificationMode2, "verificationMode");
        this.value = t;
        this.tag = str;
        this.message = str2;
        this.logger = logger2;
        this.verificationMode = verificationMode2;
        WindowStrictModeException windowStrictModeException = new WindowStrictModeException(createMessage(t, str2));
        StackTraceElement[] stackTrace = windowStrictModeException.getStackTrace();
        Intrinsics.checkNotNullExpressionValue(stackTrace, "stackTrace");
        windowStrictModeException.setStackTrace((StackTraceElement[]) ArraysKt.drop((T[]) (Object[]) stackTrace, 2).toArray(new StackTraceElement[0]));
        this.exception = windowStrictModeException;
    }

    public final WindowStrictModeException getException() {
        return this.exception;
    }

    public SpecificationComputer<T> require(String str, Function1<? super T, Boolean> function1) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(function1, "condition");
        return this;
    }

    public T compute() {
        int i = WhenMappings.$EnumSwitchMapping$0[this.verificationMode.ordinal()];
        if (i == 1) {
            throw this.exception;
        } else if (i == 2) {
            this.logger.debug(this.tag, createMessage(this.value, this.message));
            return null;
        } else if (i == 3) {
            return null;
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }
}
