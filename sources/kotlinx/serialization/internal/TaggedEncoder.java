package kotlinx.serialization.internal;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.modules.SerializersModule;
import kotlinx.serialization.modules.SerializersModuleBuildersKt;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b'\b'\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u001cJ\u001e\u0010\u001d\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u001cJ\u000e\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u001fJ\u001e\u0010 \u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u001fJ\u000e\u0010!\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\"J\u001e\u0010#\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\"J\u0018\u0010$\u001a\u00020\u00172\u0006\u0010%\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\u0016\u0010&\u001a\u00020\u00152\u0006\u0010'\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010(\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020)J\u001e\u0010*\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020)J\u0010\u0010+\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0016\u0010,\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010-\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u001aJ\u001e\u0010.\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u001aJ\u000e\u0010/\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u000200J\u001e\u00101\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u000200J\b\u00102\u001a\u00020\u0015H\u0016J\b\u00103\u001a\u00020\u0015H\u0016J?\u00104\u001a\u00020\u0015\"\b\b\u0001\u00105*\u0002062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\f\u00107\u001a\b\u0012\u0004\u0012\u0002H5082\b\u0010\u0016\u001a\u0004\u0018\u0001H5H\u0016¢\u0006\u0002\u00109J9\u0010:\u001a\u00020\u0015\"\u0004\b\u0001\u001052\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\f\u00107\u001a\b\u0012\u0004\u0012\u0002H5082\u0006\u0010\u0016\u001a\u0002H5H\u0016¢\u0006\u0002\u00109J\u000e\u0010;\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020<J\u001e\u0010=\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020<J\u000e\u0010>\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020?J\u001e\u0010@\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020?J\u001d\u0010A\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\u0017H\u0014¢\u0006\u0002\u0010CJ\u001d\u0010D\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\u001cH\u0014¢\u0006\u0002\u0010EJ\u001d\u0010F\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\u001fH\u0014¢\u0006\u0002\u0010GJ\u001d\u0010H\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\"H\u0014¢\u0006\u0002\u0010IJ%\u0010J\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u00002\u0006\u0010'\u001a\u00020\u00132\u0006\u0010K\u001a\u00020\u001aH\u0014¢\u0006\u0002\u0010LJ\u001d\u0010M\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020)H\u0014¢\u0006\u0002\u0010NJ\u001d\u0010O\u001a\u00020\u00022\u0006\u0010B\u001a\u00028\u00002\u0006\u0010P\u001a\u00020\u0013H\u0014¢\u0006\u0002\u0010QJ\u001d\u0010R\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020\u001aH\u0014¢\u0006\u0002\u0010SJ\u001d\u0010T\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u000200H\u0014¢\u0006\u0002\u0010UJ\u0015\u0010V\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010WJ\u0015\u0010X\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u0000H\u0014¢\u0006\u0002\u0010WJ\u001d\u0010Y\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020<H\u0014¢\u0006\u0002\u0010ZJ\u001d\u0010[\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u00020?H\u0014¢\u0006\u0002\u0010\\J\u001d\u0010]\u001a\u00020\u00152\u0006\u0010B\u001a\u00028\u00002\u0006\u0010\u0016\u001a\u000206H\u0014¢\u0006\u0002\u0010^J\u0010\u0010_\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u000e\u0010`\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0013J\r\u0010a\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010\u0007J\u0015\u0010b\u001a\u00020\u00152\u0006\u0010c\u001a\u00028\u0000H\u0004¢\u0006\u0002\u0010WJ\u0019\u0010d\u001a\u00028\u0000*\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH$¢\u0006\u0002\u0010eR\u0014\u0010\u0005\u001a\u00028\u00008DX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00018\u00008DX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u0007R\u0014\u0010\n\u001a\u00020\u000b8VX\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u001e\u0010\u000e\u001a\u0012\u0012\u0004\u0012\u00028\u00000\u000fj\b\u0012\u0004\u0012\u00028\u0000`\u0010X\u0004¢\u0006\u0002\n\u0000¨\u0006f"}, d2 = {"Lkotlinx/serialization/internal/TaggedEncoder;", "Tag", "Lkotlinx/serialization/encoding/Encoder;", "Lkotlinx/serialization/encoding/CompositeEncoder;", "()V", "currentTag", "getCurrentTag", "()Ljava/lang/Object;", "currentTagOrNull", "getCurrentTagOrNull", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "tagStack", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "beginStructure", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "encodeBoolean", "", "value", "", "encodeBooleanElement", "index", "", "encodeByte", "", "encodeByteElement", "encodeChar", "", "encodeCharElement", "encodeDouble", "", "encodeDoubleElement", "encodeElement", "desc", "encodeEnum", "enumDescriptor", "encodeFloat", "", "encodeFloatElement", "encodeInline", "encodeInlineElement", "encodeInt", "encodeIntElement", "encodeLong", "", "encodeLongElement", "encodeNotNullMark", "encodeNull", "encodeNullableSerializableElement", "T", "", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeSerializableElement", "encodeShort", "", "encodeShortElement", "encodeString", "", "encodeStringElement", "encodeTaggedBoolean", "tag", "(Ljava/lang/Object;Z)V", "encodeTaggedByte", "(Ljava/lang/Object;B)V", "encodeTaggedChar", "(Ljava/lang/Object;C)V", "encodeTaggedDouble", "(Ljava/lang/Object;D)V", "encodeTaggedEnum", "ordinal", "(Ljava/lang/Object;Lkotlinx/serialization/descriptors/SerialDescriptor;I)V", "encodeTaggedFloat", "(Ljava/lang/Object;F)V", "encodeTaggedInline", "inlineDescriptor", "(Ljava/lang/Object;Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/encoding/Encoder;", "encodeTaggedInt", "(Ljava/lang/Object;I)V", "encodeTaggedLong", "(Ljava/lang/Object;J)V", "encodeTaggedNonNullMark", "(Ljava/lang/Object;)V", "encodeTaggedNull", "encodeTaggedShort", "(Ljava/lang/Object;S)V", "encodeTaggedString", "(Ljava/lang/Object;Ljava/lang/String;)V", "encodeTaggedValue", "(Ljava/lang/Object;Ljava/lang/Object;)V", "endEncode", "endStructure", "popTag", "pushTag", "name", "getTag", "(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Ljava/lang/Object;", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@InternalSerializationApi
/* compiled from: Tagged.kt */
public abstract class TaggedEncoder<Tag> implements Encoder, CompositeEncoder {
    private final ArrayList<Tag> tagStack = new ArrayList<>();

    /* access modifiers changed from: protected */
    public void encodeTaggedNonNullMark(Tag tag) {
    }

    /* access modifiers changed from: protected */
    public void endEncode(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
    }

    /* access modifiers changed from: protected */
    public abstract Tag getTag(SerialDescriptor serialDescriptor, int i);

    public CompositeEncoder beginCollection(SerialDescriptor serialDescriptor, int i) {
        return Encoder.DefaultImpls.beginCollection(this, serialDescriptor, i);
    }

    @ExperimentalSerializationApi
    public <T> void encodeNullableSerializableValue(SerializationStrategy<? super T> serializationStrategy, T t) {
        Encoder.DefaultImpls.encodeNullableSerializableValue(this, serializationStrategy, t);
    }

    public <T> void encodeSerializableValue(SerializationStrategy<? super T> serializationStrategy, T t) {
        Encoder.DefaultImpls.encodeSerializableValue(this, serializationStrategy, t);
    }

    @ExperimentalSerializationApi
    public boolean shouldEncodeElementDefault(SerialDescriptor serialDescriptor, int i) {
        return CompositeEncoder.DefaultImpls.shouldEncodeElementDefault(this, serialDescriptor, i);
    }

    public SerializersModule getSerializersModule() {
        return SerializersModuleBuildersKt.EmptySerializersModule();
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedValue(Tag tag, Object obj) {
        Intrinsics.checkNotNullParameter(obj, "value");
        throw new SerializationException("Non-serializable " + Reflection.getOrCreateKotlinClass(obj.getClass()) + " is not supported by " + Reflection.getOrCreateKotlinClass(getClass()) + " encoder");
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedNull(Tag tag) {
        throw new SerializationException("null is not supported");
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedInt(Tag tag, int i) {
        encodeTaggedValue(tag, Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedByte(Tag tag, byte b) {
        encodeTaggedValue(tag, Byte.valueOf(b));
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedShort(Tag tag, short s) {
        encodeTaggedValue(tag, Short.valueOf(s));
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedLong(Tag tag, long j) {
        encodeTaggedValue(tag, Long.valueOf(j));
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedFloat(Tag tag, float f) {
        encodeTaggedValue(tag, Float.valueOf(f));
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedDouble(Tag tag, double d) {
        encodeTaggedValue(tag, Double.valueOf(d));
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedBoolean(Tag tag, boolean z) {
        encodeTaggedValue(tag, Boolean.valueOf(z));
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedChar(Tag tag, char c) {
        encodeTaggedValue(tag, Character.valueOf(c));
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedString(Tag tag, String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        encodeTaggedValue(tag, str);
    }

    /* access modifiers changed from: protected */
    public void encodeTaggedEnum(Tag tag, SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "enumDescriptor");
        encodeTaggedValue(tag, Integer.valueOf(i));
    }

    /* access modifiers changed from: protected */
    public Encoder encodeTaggedInline(Tag tag, SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "inlineDescriptor");
        TaggedEncoder taggedEncoder = this;
        pushTag(tag);
        return this;
    }

    public Encoder encodeInline(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return encodeTaggedInline(popTag(), serialDescriptor);
    }

    private final boolean encodeElement(SerialDescriptor serialDescriptor, int i) {
        pushTag(getTag(serialDescriptor, i));
        return true;
    }

    public void encodeNotNullMark() {
        encodeTaggedNonNullMark(getCurrentTag());
    }

    public void encodeNull() {
        encodeTaggedNull(popTag());
    }

    public final void encodeBoolean(boolean z) {
        encodeTaggedBoolean(popTag(), z);
    }

    public final void encodeByte(byte b) {
        encodeTaggedByte(popTag(), b);
    }

    public final void encodeShort(short s) {
        encodeTaggedShort(popTag(), s);
    }

    public final void encodeInt(int i) {
        encodeTaggedInt(popTag(), i);
    }

    public final void encodeLong(long j) {
        encodeTaggedLong(popTag(), j);
    }

    public final void encodeFloat(float f) {
        encodeTaggedFloat(popTag(), f);
    }

    public final void encodeDouble(double d) {
        encodeTaggedDouble(popTag(), d);
    }

    public final void encodeChar(char c) {
        encodeTaggedChar(popTag(), c);
    }

    public final void encodeString(String str) {
        Intrinsics.checkNotNullParameter(str, "value");
        encodeTaggedString(popTag(), str);
    }

    public final void encodeEnum(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "enumDescriptor");
        encodeTaggedEnum(popTag(), serialDescriptor, i);
    }

    public CompositeEncoder beginStructure(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return this;
    }

    public final void endStructure(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        if (!this.tagStack.isEmpty()) {
            popTag();
        }
        endEncode(serialDescriptor);
    }

    public final void encodeBooleanElement(SerialDescriptor serialDescriptor, int i, boolean z) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        encodeTaggedBoolean(getTag(serialDescriptor, i), z);
    }

    public final void encodeByteElement(SerialDescriptor serialDescriptor, int i, byte b) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        encodeTaggedByte(getTag(serialDescriptor, i), b);
    }

    public final void encodeShortElement(SerialDescriptor serialDescriptor, int i, short s) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        encodeTaggedShort(getTag(serialDescriptor, i), s);
    }

    public final void encodeIntElement(SerialDescriptor serialDescriptor, int i, int i2) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        encodeTaggedInt(getTag(serialDescriptor, i), i2);
    }

    public final void encodeLongElement(SerialDescriptor serialDescriptor, int i, long j) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        encodeTaggedLong(getTag(serialDescriptor, i), j);
    }

    public final void encodeFloatElement(SerialDescriptor serialDescriptor, int i, float f) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        encodeTaggedFloat(getTag(serialDescriptor, i), f);
    }

    public final void encodeDoubleElement(SerialDescriptor serialDescriptor, int i, double d) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        encodeTaggedDouble(getTag(serialDescriptor, i), d);
    }

    public final void encodeCharElement(SerialDescriptor serialDescriptor, int i, char c) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        encodeTaggedChar(getTag(serialDescriptor, i), c);
    }

    public final void encodeStringElement(SerialDescriptor serialDescriptor, int i, String str) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.checkNotNullParameter(str, "value");
        encodeTaggedString(getTag(serialDescriptor, i), str);
    }

    public final Encoder encodeInlineElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return encodeTaggedInline(getTag(serialDescriptor, i), serialDescriptor.getElementDescriptor(i));
    }

    public <T> void encodeSerializableElement(SerialDescriptor serialDescriptor, int i, SerializationStrategy<? super T> serializationStrategy, T t) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serializationStrategy, "serializer");
        if (encodeElement(serialDescriptor, i)) {
            encodeSerializableValue(serializationStrategy, t);
        }
    }

    public <T> void encodeNullableSerializableElement(SerialDescriptor serialDescriptor, int i, SerializationStrategy<? super T> serializationStrategy, T t) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.checkNotNullParameter(serializationStrategy, "serializer");
        if (encodeElement(serialDescriptor, i)) {
            encodeNullableSerializableValue(serializationStrategy, t);
        }
    }

    /* access modifiers changed from: protected */
    public final Tag getCurrentTag() {
        return CollectionsKt.last(this.tagStack);
    }

    /* access modifiers changed from: protected */
    public final Tag getCurrentTagOrNull() {
        return CollectionsKt.lastOrNull(this.tagStack);
    }

    /* access modifiers changed from: protected */
    public final void pushTag(Tag tag) {
        this.tagStack.add(tag);
    }

    /* access modifiers changed from: protected */
    public final Tag popTag() {
        if (!this.tagStack.isEmpty()) {
            ArrayList<Tag> arrayList = this.tagStack;
            return arrayList.remove(CollectionsKt.getLastIndex(arrayList));
        }
        throw new SerializationException("No tag in stack for requested element");
    }
}
