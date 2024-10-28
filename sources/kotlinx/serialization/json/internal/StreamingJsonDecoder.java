package kotlinx.serialization.json.internal;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.ChunkedDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000¾\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0010\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001TB/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u0012H\u0002J\b\u0010\"\u001a\u00020 H\u0016J\b\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020&H\u0016J\b\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010*\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u000bH\u0016J\b\u0010,\u001a\u00020-H\u0016J\u0010\u0010.\u001a\u00020/2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u00100\u001a\u00020\u0012H\u0016J\b\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u00020\u0012H\u0002J\b\u00104\u001a\u000205H\u0016J\b\u00106\u001a\u00020\u0012H\u0002J\b\u00107\u001a\u00020 H\u0016J\n\u00108\u001a\u0004\u0018\u000109H\u0016J\u0010\u0010:\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000bH\u0002J;\u0010;\u001a\u0002H<\"\u0004\b\u0000\u0010<2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010!\u001a\u00020\u00122\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H<0>2\b\u0010?\u001a\u0004\u0018\u0001H<H\u0016¢\u0006\u0002\u0010@J!\u0010A\u001a\u0002H<\"\u0004\b\u0000\u0010<2\f\u0010=\u001a\b\u0012\u0004\u0012\u0002H<0>H\u0016¢\u0006\u0002\u0010BJ\b\u0010C\u001a\u00020DH\u0016J\b\u0010E\u001a\u00020FH\u0016J+\u0010G\u001a\u00020\u001e2!\u0010H\u001a\u001d\u0012\u0013\u0012\u00110F¢\u0006\f\bJ\u0012\b\bK\u0012\u0004\b\b(L\u0012\u0004\u0012\u00020\u001e0IH\u0016J\b\u0010M\u001a\u00020FH\u0002J\u0010\u0010N\u001a\u00020\u001e2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010O\u001a\u00020 2\u0006\u0010P\u001a\u00020FH\u0002J\u0010\u0010Q\u001a\u00020\u001e2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0016\u0010R\u001a\u00020 *\u0004\u0018\u00010\r2\u0006\u0010S\u001a\u00020FH\u0002R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0010\u0010\b\u001a\u00020\t8\u0000X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0017\u001a\u00020\u0018X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006U"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonDecoder;", "Lkotlinx/serialization/json/JsonDecoder;", "Lkotlinx/serialization/encoding/ChunkedDecoder;", "Lkotlinx/serialization/encoding/AbstractDecoder;", "json", "Lkotlinx/serialization/json/Json;", "mode", "Lkotlinx/serialization/json/internal/WriteMode;", "lexer", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "discriminatorHolder", "Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/WriteMode;Lkotlinx/serialization/json/internal/AbstractJsonLexer;Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;)V", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "currentIndex", "", "elementMarker", "Lkotlinx/serialization/json/internal/JsonElementMarker;", "getJson", "()Lkotlinx/serialization/json/Json;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "checkLeadingComma", "", "coerceInputValue", "", "index", "decodeBoolean", "decodeByte", "", "decodeChar", "", "decodeDouble", "", "decodeElementIndex", "decodeEnum", "enumDescriptor", "decodeFloat", "", "decodeInline", "Lkotlinx/serialization/encoding/Decoder;", "decodeInt", "decodeJsonElement", "Lkotlinx/serialization/json/JsonElement;", "decodeListIndex", "decodeLong", "", "decodeMapIndex", "decodeNotNullMark", "decodeNull", "", "decodeObjectIndex", "decodeSerializableElement", "T", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "previousValue", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "decodeSerializableValue", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "decodeShort", "", "decodeString", "", "decodeStringChunked", "consumeChunk", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "chunk", "decodeStringKey", "endStructure", "handleUnknown", "key", "skipLeftoverElements", "trySkip", "unknownKey", "DiscriminatorHolder", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: StreamingJsonDecoder.kt */
public class StreamingJsonDecoder extends AbstractDecoder implements JsonDecoder, ChunkedDecoder {
    private final JsonConfiguration configuration;
    private int currentIndex = -1;
    private DiscriminatorHolder discriminatorHolder;
    private final JsonElementMarker elementMarker;
    private final Json json;
    public final AbstractJsonLexer lexer;
    private final WriteMode mode;
    private final SerializersModule serializersModule;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: StreamingJsonDecoder.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                kotlinx.serialization.json.internal.WriteMode[] r0 = kotlinx.serialization.json.internal.WriteMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                kotlinx.serialization.json.internal.WriteMode r1 = kotlinx.serialization.json.internal.WriteMode.LIST     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                kotlinx.serialization.json.internal.WriteMode r1 = kotlinx.serialization.json.internal.WriteMode.MAP     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                kotlinx.serialization.json.internal.WriteMode r1 = kotlinx.serialization.json.internal.WriteMode.POLY_OBJ     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                kotlinx.serialization.json.internal.WriteMode r1 = kotlinx.serialization.json.internal.WriteMode.OBJ     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.StreamingJsonDecoder.WhenMappings.<clinit>():void");
        }
    }

    public Void decodeNull() {
        return null;
    }

    public final Json getJson() {
        return this.json;
    }

    public StreamingJsonDecoder(Json json2, WriteMode writeMode, AbstractJsonLexer abstractJsonLexer, SerialDescriptor serialDescriptor, DiscriminatorHolder discriminatorHolder2) {
        Intrinsics.checkNotNullParameter(json2, "json");
        Intrinsics.checkNotNullParameter(writeMode, RtspHeaders.Values.MODE);
        Intrinsics.checkNotNullParameter(abstractJsonLexer, "lexer");
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        this.json = json2;
        this.mode = writeMode;
        this.lexer = abstractJsonLexer;
        this.serializersModule = json2.getSerializersModule();
        this.discriminatorHolder = discriminatorHolder2;
        JsonConfiguration configuration2 = json2.getConfiguration();
        this.configuration = configuration2;
        this.elementMarker = configuration2.getExplicitNulls() ? null : new JsonElementMarker(serialDescriptor);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u000e¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;", "", "discriminatorToSkip", "", "(Ljava/lang/String;)V", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: StreamingJsonDecoder.kt */
    public static final class DiscriminatorHolder {
        public String discriminatorToSkip;

        public DiscriminatorHolder(String str) {
            this.discriminatorToSkip = str;
        }
    }

    private final boolean trySkip(DiscriminatorHolder discriminatorHolder2, String str) {
        if (discriminatorHolder2 == null || !Intrinsics.areEqual((Object) discriminatorHolder2.discriminatorToSkip, (Object) str)) {
            return false;
        }
        discriminatorHolder2.discriminatorToSkip = null;
        return true;
    }

    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public JsonElement decodeJsonElement() {
        return new JsonTreeReader(this.json.getConfiguration(), this.lexer).read();
    }

    public <T> T decodeSerializableValue(DeserializationStrategy<? extends T> deserializationStrategy) {
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        try {
            if (deserializationStrategy instanceof AbstractPolymorphicSerializer) {
                if (!this.json.getConfiguration().getUseArrayPolymorphism()) {
                    String classDiscriminator = PolymorphicKt.classDiscriminator(deserializationStrategy.getDescriptor(), this.json);
                    String consumeLeadingMatchingValue = this.lexer.consumeLeadingMatchingValue(classDiscriminator, this.configuration.isLenient());
                    DeserializationStrategy findPolymorphicSerializerOrNull = consumeLeadingMatchingValue != null ? ((AbstractPolymorphicSerializer) deserializationStrategy).findPolymorphicSerializerOrNull((CompositeDecoder) this, consumeLeadingMatchingValue) : null;
                    if (findPolymorphicSerializerOrNull == null) {
                        return PolymorphicKt.decodeSerializableValuePolymorphic(this, deserializationStrategy);
                    }
                    this.discriminatorHolder = new DiscriminatorHolder(classDiscriminator);
                    return findPolymorphicSerializerOrNull.deserialize(this);
                }
            }
            return deserializationStrategy.deserialize(this);
        } catch (MissingFieldException e) {
            String message = e.getMessage();
            Intrinsics.checkNotNull(message);
            if (StringsKt.contains$default((CharSequence) message, (CharSequence) "at path", false, 2, (Object) null)) {
                throw e;
            }
            List<String> missingFields = e.getMissingFields();
            throw new MissingFieldException(missingFields, e.getMessage() + " at path: " + this.lexer.path.getPath(), e);
        }
    }

    public CompositeDecoder beginStructure(SerialDescriptor serialDescriptor) {
        StreamingJsonDecoder streamingJsonDecoder;
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        WriteMode switchMode = WriteModeKt.switchMode(this.json, serialDescriptor);
        this.lexer.path.pushDescriptor(serialDescriptor);
        this.lexer.consumeNextToken(switchMode.begin);
        checkLeadingComma();
        int i = WhenMappings.$EnumSwitchMapping$0[switchMode.ordinal()];
        if (i == 1 || i == 2 || i == 3) {
            return new StreamingJsonDecoder(this.json, switchMode, this.lexer, serialDescriptor, this.discriminatorHolder);
        }
        if (this.mode != switchMode || !this.json.getConfiguration().getExplicitNulls()) {
            streamingJsonDecoder = new StreamingJsonDecoder(this.json, switchMode, this.lexer, serialDescriptor, this.discriminatorHolder);
        } else {
            streamingJsonDecoder = this;
        }
        return streamingJsonDecoder;
    }

    public void endStructure(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        if (this.json.getConfiguration().getIgnoreUnknownKeys() && serialDescriptor.getElementsCount() == 0) {
            skipLeftoverElements(serialDescriptor);
        }
        this.lexer.consumeNextToken(this.mode.end);
        this.lexer.path.popDescriptor();
    }

    private final void skipLeftoverElements(SerialDescriptor serialDescriptor) {
        do {
        } while (decodeElementIndex(serialDescriptor) != -1);
    }

    public boolean decodeNotNullMark() {
        JsonElementMarker jsonElementMarker = this.elementMarker;
        return (jsonElementMarker == null || !jsonElementMarker.isUnmarkedNull$kotlinx_serialization_json()) && !AbstractJsonLexer.tryConsumeNull$default(this.lexer, false, 1, (Object) null);
    }

    private final void checkLeadingComma() {
        if (this.lexer.peekNextToken() == 4) {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected leading comma", 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
    }

    public <T> T decodeSerializableElement(SerialDescriptor serialDescriptor, int i, DeserializationStrategy<? extends T> deserializationStrategy, T t) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        boolean z = this.mode == WriteMode.MAP && (i & 1) == 0;
        if (z) {
            this.lexer.path.resetCurrentMapKey();
        }
        T decodeSerializableElement = super.decodeSerializableElement(serialDescriptor, i, deserializationStrategy, t);
        if (z) {
            this.lexer.path.updateCurrentMapKey(decodeSerializableElement);
        }
        return decodeSerializableElement;
    }

    public int decodeElementIndex(SerialDescriptor serialDescriptor) {
        int i;
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        int i2 = WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()];
        if (i2 == 2) {
            i = decodeMapIndex();
        } else if (i2 != 4) {
            i = decodeListIndex();
        } else {
            i = decodeObjectIndex(serialDescriptor);
        }
        if (this.mode != WriteMode.MAP) {
            this.lexer.path.updateDescriptorIndex(i);
        }
        return i;
    }

    private final int decodeMapIndex() {
        int i = this.currentIndex;
        boolean z = false;
        boolean z2 = i % 2 != 0;
        if (!z2) {
            this.lexer.consumeNextToken((char) AbstractJsonLexerKt.COLON);
        } else if (i != -1) {
            z = this.lexer.tryConsumeComma();
        }
        if (this.lexer.canConsumeValue()) {
            if (z2) {
                if (this.currentIndex == -1) {
                    AbstractJsonLexer abstractJsonLexer = this.lexer;
                    boolean z3 = !z;
                    int access$getCurrentPosition$p = abstractJsonLexer.currentPosition;
                    if (!z3) {
                        AbstractJsonLexer.fail$default(abstractJsonLexer, "Unexpected trailing comma", access$getCurrentPosition$p, (String) null, 4, (Object) null);
                        throw new KotlinNothingValueException();
                    }
                } else {
                    AbstractJsonLexer abstractJsonLexer2 = this.lexer;
                    int access$getCurrentPosition$p2 = abstractJsonLexer2.currentPosition;
                    if (!z) {
                        AbstractJsonLexer.fail$default(abstractJsonLexer2, "Expected comma after the key-value pair", access$getCurrentPosition$p2, (String) null, 4, (Object) null);
                        throw new KotlinNothingValueException();
                    }
                }
            }
            int i2 = this.currentIndex + 1;
            this.currentIndex = i2;
            return i2;
        } else if (!z) {
            return -1;
        } else {
            AbstractJsonLexer.fail$default(this.lexer, "Expected '}', but had ',' instead", 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
    }

    private final boolean coerceInputValue(SerialDescriptor serialDescriptor, int i) {
        String peekString;
        Json json2 = this.json;
        SerialDescriptor elementDescriptor = serialDescriptor.getElementDescriptor(i);
        if (!elementDescriptor.isNullable() && this.lexer.tryConsumeNull(true)) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) elementDescriptor.getKind(), (Object) SerialKind.ENUM.INSTANCE) || ((elementDescriptor.isNullable() && this.lexer.tryConsumeNull(false)) || (peekString = this.lexer.peekString(this.configuration.isLenient())) == null || JsonNamesMapKt.getJsonNameIndex(elementDescriptor, json2, peekString) != -3)) {
            return false;
        }
        this.lexer.consumeString();
        return true;
    }

    private final int decodeObjectIndex(SerialDescriptor serialDescriptor) {
        boolean z;
        boolean tryConsumeComma = this.lexer.tryConsumeComma();
        while (this.lexer.canConsumeValue()) {
            String decodeStringKey = decodeStringKey();
            this.lexer.consumeNextToken((char) AbstractJsonLexerKt.COLON);
            int jsonNameIndex = JsonNamesMapKt.getJsonNameIndex(serialDescriptor, this.json, decodeStringKey);
            boolean z2 = false;
            if (jsonNameIndex == -3) {
                z = false;
                z2 = true;
            } else if (!this.configuration.getCoerceInputValues() || !coerceInputValue(serialDescriptor, jsonNameIndex)) {
                JsonElementMarker jsonElementMarker = this.elementMarker;
                if (jsonElementMarker != null) {
                    jsonElementMarker.mark$kotlinx_serialization_json(jsonNameIndex);
                }
                return jsonNameIndex;
            } else {
                z = this.lexer.tryConsumeComma();
            }
            tryConsumeComma = z2 ? handleUnknown(decodeStringKey) : z;
        }
        if (!tryConsumeComma) {
            JsonElementMarker jsonElementMarker2 = this.elementMarker;
            if (jsonElementMarker2 != null) {
                return jsonElementMarker2.nextUnmarkedIndex$kotlinx_serialization_json();
            }
            return -1;
        }
        AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    private final boolean handleUnknown(String str) {
        if (this.configuration.getIgnoreUnknownKeys() || trySkip(this.discriminatorHolder, str)) {
            this.lexer.skipElement(this.configuration.isLenient());
        } else {
            this.lexer.failOnUnknownKey(str);
        }
        return this.lexer.tryConsumeComma();
    }

    private final int decodeListIndex() {
        boolean tryConsumeComma = this.lexer.tryConsumeComma();
        if (this.lexer.canConsumeValue()) {
            int i = this.currentIndex;
            if (i == -1 || tryConsumeComma) {
                int i2 = i + 1;
                this.currentIndex = i2;
                return i2;
            }
            AbstractJsonLexer.fail$default(this.lexer, "Expected end of the array or comma", 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        } else if (!tryConsumeComma) {
            return -1;
        } else {
            AbstractJsonLexer.fail$default(this.lexer, "Unexpected trailing comma", 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
    }

    public boolean decodeBoolean() {
        if (this.configuration.isLenient()) {
            return this.lexer.consumeBooleanLenient();
        }
        return this.lexer.consumeBoolean();
    }

    public byte decodeByte() {
        long consumeNumericLiteral = this.lexer.consumeNumericLiteral();
        byte b = (byte) ((int) consumeNumericLiteral);
        if (consumeNumericLiteral == ((long) b)) {
            return b;
        }
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse byte for input '" + consumeNumericLiteral + '\'', 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    public short decodeShort() {
        long consumeNumericLiteral = this.lexer.consumeNumericLiteral();
        short s = (short) ((int) consumeNumericLiteral);
        if (consumeNumericLiteral == ((long) s)) {
            return s;
        }
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse short for input '" + consumeNumericLiteral + '\'', 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    public int decodeInt() {
        long consumeNumericLiteral = this.lexer.consumeNumericLiteral();
        int i = (int) consumeNumericLiteral;
        if (consumeNumericLiteral == ((long) i)) {
            return i;
        }
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse int for input '" + consumeNumericLiteral + '\'', 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    public long decodeLong() {
        return this.lexer.consumeNumericLiteral();
    }

    public float decodeFloat() {
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        String consumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            float parseFloat = Float.parseFloat(consumeStringLenient);
            if (this.json.getConfiguration().getAllowSpecialFloatingPointValues() || (!Float.isInfinite(parseFloat) && !Float.isNaN(parseFloat))) {
                return parseFloat;
            }
            JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Float.valueOf(parseFloat));
            throw new KotlinNothingValueException();
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type 'float' for input '" + consumeStringLenient + '\'', 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
    }

    public double decodeDouble() {
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        String consumeStringLenient = abstractJsonLexer.consumeStringLenient();
        try {
            double parseDouble = Double.parseDouble(consumeStringLenient);
            if (this.json.getConfiguration().getAllowSpecialFloatingPointValues() || (!Double.isInfinite(parseDouble) && !Double.isNaN(parseDouble))) {
                return parseDouble;
            }
            JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Double.valueOf(parseDouble));
            throw new KotlinNothingValueException();
        } catch (IllegalArgumentException unused) {
            AbstractJsonLexer.fail$default(abstractJsonLexer, "Failed to parse type 'double' for input '" + consumeStringLenient + '\'', 0, (String) null, 6, (Object) null);
            throw new KotlinNothingValueException();
        }
    }

    public char decodeChar() {
        String consumeStringLenient = this.lexer.consumeStringLenient();
        if (consumeStringLenient.length() == 1) {
            return consumeStringLenient.charAt(0);
        }
        AbstractJsonLexer abstractJsonLexer = this.lexer;
        AbstractJsonLexer.fail$default(abstractJsonLexer, "Expected single char, but got '" + consumeStringLenient + '\'', 0, (String) null, 6, (Object) null);
        throw new KotlinNothingValueException();
    }

    private final String decodeStringKey() {
        if (this.configuration.isLenient()) {
            return this.lexer.consumeStringLenientNotNull();
        }
        return this.lexer.consumeKeyString();
    }

    public String decodeString() {
        if (this.configuration.isLenient()) {
            return this.lexer.consumeStringLenientNotNull();
        }
        return this.lexer.consumeString();
    }

    public void decodeStringChunked(Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "consumeChunk");
        this.lexer.consumeStringChunked(this.configuration.isLenient(), function1);
    }

    public Decoder decodeInline(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        if (StreamingJsonEncoderKt.isUnsignedNumber(serialDescriptor)) {
            return new JsonDecoderForUnsignedTypes(this.lexer, this.json);
        }
        return super.decodeInline(serialDescriptor);
    }

    public int decodeEnum(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "enumDescriptor");
        Json json2 = this.json;
        String decodeString = decodeString();
        return JsonNamesMapKt.getJsonNameIndexOrThrow(serialDescriptor, json2, decodeString, " at path " + this.lexer.path.getPath());
    }
}
