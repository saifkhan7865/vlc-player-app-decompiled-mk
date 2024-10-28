package org.videolan.moviepedia.models.body;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lorg/videolan/moviepedia/models/body/ScrobbleBodyBatch;", "", "id", "", "metadata", "Lorg/videolan/moviepedia/models/body/ScrobbleBody;", "(Ljava/lang/String;Lorg/videolan/moviepedia/models/body/ScrobbleBody;)V", "getId", "()Ljava/lang/String;", "getMetadata", "()Lorg/videolan/moviepedia/models/body/ScrobbleBody;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "moviepedia_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: ScrobbleBody.kt */
public final class ScrobbleBodyBatch {
    private final String id;
    private final ScrobbleBody metadata;

    public static /* synthetic */ ScrobbleBodyBatch copy$default(ScrobbleBodyBatch scrobbleBodyBatch, String str, ScrobbleBody scrobbleBody, int i, Object obj) {
        if ((i & 1) != 0) {
            str = scrobbleBodyBatch.id;
        }
        if ((i & 2) != 0) {
            scrobbleBody = scrobbleBodyBatch.metadata;
        }
        return scrobbleBodyBatch.copy(str, scrobbleBody);
    }

    public final String component1() {
        return this.id;
    }

    public final ScrobbleBody component2() {
        return this.metadata;
    }

    public final ScrobbleBodyBatch copy(String str, ScrobbleBody scrobbleBody) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(scrobbleBody, "metadata");
        return new ScrobbleBodyBatch(str, scrobbleBody);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ScrobbleBodyBatch)) {
            return false;
        }
        ScrobbleBodyBatch scrobbleBodyBatch = (ScrobbleBodyBatch) obj;
        return Intrinsics.areEqual((Object) this.id, (Object) scrobbleBodyBatch.id) && Intrinsics.areEqual((Object) this.metadata, (Object) scrobbleBodyBatch.metadata);
    }

    public int hashCode() {
        return (this.id.hashCode() * 31) + this.metadata.hashCode();
    }

    public String toString() {
        return "ScrobbleBodyBatch(id=" + this.id + ", metadata=" + this.metadata + ')';
    }

    public ScrobbleBodyBatch(String str, ScrobbleBody scrobbleBody) {
        Intrinsics.checkNotNullParameter(str, "id");
        Intrinsics.checkNotNullParameter(scrobbleBody, "metadata");
        this.id = str;
        this.metadata = scrobbleBody;
    }

    public final String getId() {
        return this.id;
    }

    public final ScrobbleBody getMetadata() {
        return this.metadata;
    }
}
