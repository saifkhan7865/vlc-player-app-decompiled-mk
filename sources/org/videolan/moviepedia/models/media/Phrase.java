package org.videolan.moviepedia.models.media;

import com.squareup.moshi.Json;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, d2 = {"Lorg/videolan/moviepedia/models/media/Phrase;", "", "highlighted", "", "text", "(Ljava/lang/String;Ljava/lang/String;)V", "getHighlighted", "()Ljava/lang/String;", "getText", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: MediaResult.kt */
public final class Phrase {
    @Json(name = "highlighted")
    private final String highlighted;
    @Json(name = "text")
    private final String text;

    public static /* synthetic */ Phrase copy$default(Phrase phrase, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = phrase.highlighted;
        }
        if ((i & 2) != 0) {
            str2 = phrase.text;
        }
        return phrase.copy(str, str2);
    }

    public final String component1() {
        return this.highlighted;
    }

    public final String component2() {
        return this.text;
    }

    public final Phrase copy(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "highlighted");
        Intrinsics.checkNotNullParameter(str2, "text");
        return new Phrase(str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Phrase)) {
            return false;
        }
        Phrase phrase = (Phrase) obj;
        return Intrinsics.areEqual((Object) this.highlighted, (Object) phrase.highlighted) && Intrinsics.areEqual((Object) this.text, (Object) phrase.text);
    }

    public int hashCode() {
        return (this.highlighted.hashCode() * 31) + this.text.hashCode();
    }

    public String toString() {
        return "Phrase(highlighted=" + this.highlighted + ", text=" + this.text + ')';
    }

    public Phrase(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "highlighted");
        Intrinsics.checkNotNullParameter(str2, "text");
        this.highlighted = str;
        this.text = str2;
    }

    public final String getHighlighted() {
        return this.highlighted;
    }

    public final String getText() {
        return this.text;
    }
}
