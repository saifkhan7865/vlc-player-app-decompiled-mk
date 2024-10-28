package kotlinx.serialization.json.internal;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.Iterator;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.json.DecodeSequenceMode;
import kotlinx.serialization.json.Json;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u001a:\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\nH\u0000\u001a\u0014\u0010\u000b\u001a\u00020\u0004*\u00020\f2\u0006\u0010\r\u001a\u00020\u0004H\u0002\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\fH\u0002¨\u0006\u0010"}, d2 = {"JsonIterator", "", "T", "mode", "Lkotlinx/serialization/json/DecodeSequenceMode;", "json", "Lkotlinx/serialization/json/Json;", "lexer", "Lkotlinx/serialization/json/internal/ReaderJsonLexer;", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "determineFormat", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "suggested", "tryConsumeStartArray", "", "kotlinx-serialization-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonIterator.kt */
public final class JsonIteratorKt {

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: JsonIterator.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                kotlinx.serialization.json.DecodeSequenceMode[] r0 = kotlinx.serialization.json.DecodeSequenceMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                kotlinx.serialization.json.DecodeSequenceMode r1 = kotlinx.serialization.json.DecodeSequenceMode.WHITESPACE_SEPARATED     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                kotlinx.serialization.json.DecodeSequenceMode r1 = kotlinx.serialization.json.DecodeSequenceMode.ARRAY_WRAPPED     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                kotlinx.serialization.json.DecodeSequenceMode r1 = kotlinx.serialization.json.DecodeSequenceMode.AUTO_DETECT     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.JsonIteratorKt.WhenMappings.<clinit>():void");
        }
    }

    public static final <T> Iterator<T> JsonIterator(DecodeSequenceMode decodeSequenceMode, Json json, ReaderJsonLexer readerJsonLexer, DeserializationStrategy<? extends T> deserializationStrategy) {
        Intrinsics.checkNotNullParameter(decodeSequenceMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(readerJsonLexer, "lexer");
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        int i = WhenMappings.$EnumSwitchMapping$0[determineFormat(readerJsonLexer, decodeSequenceMode).ordinal()];
        if (i == 1) {
            return new JsonIteratorWsSeparated<>(json, readerJsonLexer, deserializationStrategy);
        }
        if (i == 2) {
            return new JsonIteratorArrayWrapped<>(json, readerJsonLexer, deserializationStrategy);
        }
        if (i != 3) {
            throw new NoWhenBranchMatchedException();
        }
        throw new IllegalStateException("AbstractJsonLexer.determineFormat must be called beforehand.".toString());
    }

    private static final DecodeSequenceMode determineFormat(AbstractJsonLexer abstractJsonLexer, DecodeSequenceMode decodeSequenceMode) {
        int i = WhenMappings.$EnumSwitchMapping$0[decodeSequenceMode.ordinal()];
        if (i == 1) {
            return DecodeSequenceMode.WHITESPACE_SEPARATED;
        }
        if (i != 2) {
            if (i != 3) {
                throw new NoWhenBranchMatchedException();
            } else if (tryConsumeStartArray(abstractJsonLexer)) {
                return DecodeSequenceMode.ARRAY_WRAPPED;
            } else {
                return DecodeSequenceMode.WHITESPACE_SEPARATED;
            }
        } else if (tryConsumeStartArray(abstractJsonLexer)) {
            return DecodeSequenceMode.ARRAY_WRAPPED;
        } else {
            abstractJsonLexer.fail$kotlinx_serialization_json((byte) 8);
            throw new KotlinNothingValueException();
        }
    }

    private static final boolean tryConsumeStartArray(AbstractJsonLexer abstractJsonLexer) {
        if (abstractJsonLexer.peekNextToken() != 8) {
            return false;
        }
        abstractJsonLexer.consumeNextToken((byte) 8);
        return true;
    }
}
