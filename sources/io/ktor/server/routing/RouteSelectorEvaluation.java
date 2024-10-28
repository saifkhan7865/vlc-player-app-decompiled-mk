package io.ktor.server.routing;

import io.ktor.http.HttpStatusCode;
import io.ktor.http.Parameters;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 \u00072\u00020\u0001:\u0003\u0007\b\tB\u000f\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0001\u0002\n\u000b¨\u0006\f"}, d2 = {"Lio/ktor/server/routing/RouteSelectorEvaluation;", "", "succeeded", "", "(Z)V", "getSucceeded", "()Z", "Companion", "Failure", "Success", "Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "Lio/ktor/server/routing/RouteSelectorEvaluation$Success;", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: RouteSelector.kt */
public abstract class RouteSelectorEvaluation {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final RouteSelectorEvaluation Constant = new Success(1.0d, (Parameters) null, 0, 6, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final RouteSelectorEvaluation ConstantPath = new Success(1.0d, (Parameters) null, 1, 2, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Failure Failed = new Failure(0.0d, HttpStatusCode.Companion.getNotFound());
    /* access modifiers changed from: private */
    public static final Failure FailedMethod = new Failure(0.02d, HttpStatusCode.Companion.getMethodNotAllowed());
    /* access modifiers changed from: private */
    public static final Failure FailedParameter = new Failure(0.01d, HttpStatusCode.Companion.getBadRequest());
    /* access modifiers changed from: private */
    public static final Failure FailedPath = new Failure(0.0d, HttpStatusCode.Companion.getNotFound());
    /* access modifiers changed from: private */
    public static final RouteSelectorEvaluation Missing = new Success(0.2d, (Parameters) null, 0, 6, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final RouteSelectorEvaluation Transparent = new Success(-1.0d, (Parameters) null, 0, 6, (DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final RouteSelectorEvaluation WildcardPath = new Success(0.5d, (Parameters) null, 1, 2, (DefaultConstructorMarker) null);
    public static final double qualityConstant = 1.0d;
    public static final double qualityFailedMethod = 0.02d;
    public static final double qualityFailedParameter = 0.01d;
    public static final double qualityMethodParameter = 0.8d;
    public static final double qualityMissing = 0.2d;
    public static final double qualityParameter = 0.8d;
    public static final double qualityParameterWithPrefixOrSuffix = 0.9d;
    public static final double qualityPathParameter = 0.8d;
    public static final double qualityQueryParameter = 1.0d;
    public static final double qualityTailcard = 0.1d;
    public static final double qualityTransparent = -1.0d;
    public static final double qualityWildcard = 0.5d;
    private final boolean succeeded;

    public /* synthetic */ RouteSelectorEvaluation(boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(z);
    }

    private RouteSelectorEvaluation(boolean z) {
        this.succeeded = z;
    }

    public final boolean getSucceeded() {
        return this.succeeded;
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0007HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0007HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u001a"}, d2 = {"Lio/ktor/server/routing/RouteSelectorEvaluation$Success;", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "quality", "", "parameters", "Lio/ktor/http/Parameters;", "segmentIncrement", "", "(DLio/ktor/http/Parameters;I)V", "getParameters", "()Lio/ktor/http/Parameters;", "getQuality", "()D", "getSegmentIncrement", "()I", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RouteSelector.kt */
    public static final class Success extends RouteSelectorEvaluation {
        private final Parameters parameters;
        private final double quality;
        private final int segmentIncrement;

        public static /* synthetic */ Success copy$default(Success success, double d, Parameters parameters2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                d = success.quality;
            }
            if ((i2 & 2) != 0) {
                parameters2 = success.parameters;
            }
            if ((i2 & 4) != 0) {
                i = success.segmentIncrement;
            }
            return success.copy(d, parameters2, i);
        }

        public final double component1() {
            return this.quality;
        }

        public final Parameters component2() {
            return this.parameters;
        }

        public final int component3() {
            return this.segmentIncrement;
        }

        public final Success copy(double d, Parameters parameters2, int i) {
            Intrinsics.checkNotNullParameter(parameters2, "parameters");
            return new Success(d, parameters2, i);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Success)) {
                return false;
            }
            Success success = (Success) obj;
            return Double.compare(this.quality, success.quality) == 0 && Intrinsics.areEqual((Object) this.parameters, (Object) success.parameters) && this.segmentIncrement == success.segmentIncrement;
        }

        public int hashCode() {
            return (((Double.doubleToLongBits(this.quality) * 31) + this.parameters.hashCode()) * 31) + this.segmentIncrement;
        }

        public String toString() {
            return "Success(quality=" + this.quality + ", parameters=" + this.parameters + ", segmentIncrement=" + this.segmentIncrement + ')';
        }

        public final double getQuality() {
            return this.quality;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ Success(double d, Parameters parameters2, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(d, (i2 & 2) != 0 ? Parameters.Companion.getEmpty() : parameters2, (i2 & 4) != 0 ? 0 : i);
        }

        public final Parameters getParameters() {
            return this.parameters;
        }

        public final int getSegmentIncrement() {
            return this.segmentIncrement;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Success(double d, Parameters parameters2, int i) {
            super(true, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(parameters2, "parameters");
            this.quality = d;
            this.parameters = parameters2;
            this.segmentIncrement = i;
        }
    }

    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0016"}, d2 = {"Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "quality", "", "failureStatusCode", "Lio/ktor/http/HttpStatusCode;", "(DLio/ktor/http/HttpStatusCode;)V", "getFailureStatusCode", "()Lio/ktor/http/HttpStatusCode;", "getQuality", "()D", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RouteSelector.kt */
    public static final class Failure extends RouteSelectorEvaluation {
        private final HttpStatusCode failureStatusCode;
        private final double quality;

        public static /* synthetic */ Failure copy$default(Failure failure, double d, HttpStatusCode httpStatusCode, int i, Object obj) {
            if ((i & 1) != 0) {
                d = failure.quality;
            }
            if ((i & 2) != 0) {
                httpStatusCode = failure.failureStatusCode;
            }
            return failure.copy(d, httpStatusCode);
        }

        public final double component1() {
            return this.quality;
        }

        public final HttpStatusCode component2() {
            return this.failureStatusCode;
        }

        public final Failure copy(double d, HttpStatusCode httpStatusCode) {
            Intrinsics.checkNotNullParameter(httpStatusCode, "failureStatusCode");
            return new Failure(d, httpStatusCode);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Failure)) {
                return false;
            }
            Failure failure = (Failure) obj;
            return Double.compare(this.quality, failure.quality) == 0 && Intrinsics.areEqual((Object) this.failureStatusCode, (Object) failure.failureStatusCode);
        }

        public int hashCode() {
            return (Double.doubleToLongBits(this.quality) * 31) + this.failureStatusCode.hashCode();
        }

        public String toString() {
            return "Failure(quality=" + this.quality + ", failureStatusCode=" + this.failureStatusCode + ')';
        }

        public final double getQuality() {
            return this.quality;
        }

        public final HttpStatusCode getFailureStatusCode() {
            return this.failureStatusCode;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Failure(double d, HttpStatusCode httpStatusCode) {
            super(false, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(httpStatusCode, "failureStatusCode");
            this.quality = d;
            this.failureStatusCode = httpStatusCode;
        }
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0006\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010'\u001a\u00020\u00042\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u001a2\b\b\u0002\u0010+\u001a\u00020,2\b\b\u0002\u0010-\u001a\u00020.H\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0011\u0010\u0013\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0006R\u0011\u0010\u0015\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0006R\u0011\u0010\u0017\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0006R\u000e\u0010\u0019\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\u001aXT¢\u0006\b\n\u0000\u0012\u0004\b\u001e\u0010\u0002R\u000e\u0010\u001f\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001aXT¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\u001aXT¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lio/ktor/server/routing/RouteSelectorEvaluation$Companion;", "", "()V", "Constant", "Lio/ktor/server/routing/RouteSelectorEvaluation;", "getConstant", "()Lio/ktor/server/routing/RouteSelectorEvaluation;", "ConstantPath", "getConstantPath", "Failed", "Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "getFailed", "()Lio/ktor/server/routing/RouteSelectorEvaluation$Failure;", "FailedMethod", "getFailedMethod", "FailedParameter", "getFailedParameter", "FailedPath", "getFailedPath", "Missing", "getMissing", "Transparent", "getTransparent", "WildcardPath", "getWildcardPath", "qualityConstant", "", "qualityFailedMethod", "qualityFailedParameter", "qualityMethodParameter", "getQualityMethodParameter$annotations", "qualityMissing", "qualityParameter", "qualityParameterWithPrefixOrSuffix", "qualityPathParameter", "qualityQueryParameter", "qualityTailcard", "qualityTransparent", "qualityWildcard", "invoke", "succeeded", "", "quality", "parameters", "Lio/ktor/http/Parameters;", "segmentIncrement", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: RouteSelector.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void getQualityMethodParameter$annotations() {
        }

        private Companion() {
        }

        public static /* synthetic */ RouteSelectorEvaluation invoke$default(Companion companion, boolean z, double d, Parameters parameters, int i, int i2, Object obj) {
            if ((i2 & 4) != 0) {
                parameters = Parameters.Companion.getEmpty();
            }
            return companion.invoke(z, d, parameters, (i2 & 8) != 0 ? 0 : i);
        }

        @Deprecated(message = "Please use RouteSelectorEvaluation.Failure() or RouteSelectorEvaluation.Success() constructors")
        public final RouteSelectorEvaluation invoke(boolean z, double d, Parameters parameters, int i) {
            Intrinsics.checkNotNullParameter(parameters, "parameters");
            if (z) {
                return new Success(d, parameters, i);
            }
            return new Failure(d, HttpStatusCode.Companion.getNotFound());
        }

        public final Failure getFailed() {
            return RouteSelectorEvaluation.Failed;
        }

        public final Failure getFailedPath() {
            return RouteSelectorEvaluation.FailedPath;
        }

        public final Failure getFailedMethod() {
            return RouteSelectorEvaluation.FailedMethod;
        }

        public final Failure getFailedParameter() {
            return RouteSelectorEvaluation.FailedParameter;
        }

        public final RouteSelectorEvaluation getMissing() {
            return RouteSelectorEvaluation.Missing;
        }

        public final RouteSelectorEvaluation getConstant() {
            return RouteSelectorEvaluation.Constant;
        }

        public final RouteSelectorEvaluation getTransparent() {
            return RouteSelectorEvaluation.Transparent;
        }

        public final RouteSelectorEvaluation getConstantPath() {
            return RouteSelectorEvaluation.ConstantPath;
        }

        public final RouteSelectorEvaluation getWildcardPath() {
            return RouteSelectorEvaluation.WildcardPath;
        }
    }
}
