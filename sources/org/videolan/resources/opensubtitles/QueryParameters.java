package org.videolan.resources.opensubtitles;

import com.squareup.moshi.Json;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0016\u0010\u0005\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lorg/videolan/resources/opensubtitles/QueryParameters;", "", "query", "", "episode", "season", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getEpisode", "()Ljava/lang/String;", "getQuery", "getSeason", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "resources_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: Models.kt */
public final class QueryParameters {
    @Json(name = "episode")
    private final String episode;
    @Json(name = "query")
    private final String query;
    @Json(name = "season")
    private final String season;

    public static /* synthetic */ QueryParameters copy$default(QueryParameters queryParameters, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = queryParameters.query;
        }
        if ((i & 2) != 0) {
            str2 = queryParameters.episode;
        }
        if ((i & 4) != 0) {
            str3 = queryParameters.season;
        }
        return queryParameters.copy(str, str2, str3);
    }

    public final String component1() {
        return this.query;
    }

    public final String component2() {
        return this.episode;
    }

    public final String component3() {
        return this.season;
    }

    public final QueryParameters copy(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "query");
        Intrinsics.checkNotNullParameter(str2, "episode");
        Intrinsics.checkNotNullParameter(str3, "season");
        return new QueryParameters(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof QueryParameters)) {
            return false;
        }
        QueryParameters queryParameters = (QueryParameters) obj;
        return Intrinsics.areEqual((Object) this.query, (Object) queryParameters.query) && Intrinsics.areEqual((Object) this.episode, (Object) queryParameters.episode) && Intrinsics.areEqual((Object) this.season, (Object) queryParameters.season);
    }

    public int hashCode() {
        return (((this.query.hashCode() * 31) + this.episode.hashCode()) * 31) + this.season.hashCode();
    }

    public String toString() {
        return "QueryParameters(query=" + this.query + ", episode=" + this.episode + ", season=" + this.season + ')';
    }

    public QueryParameters(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "query");
        Intrinsics.checkNotNullParameter(str2, "episode");
        Intrinsics.checkNotNullParameter(str3, "season");
        this.query = str;
        this.episode = str2;
        this.season = str3;
    }

    public final String getQuery() {
        return this.query;
    }

    public final String getEpisode() {
        return this.episode;
    }

    public final String getSeason() {
        return this.season;
    }
}
