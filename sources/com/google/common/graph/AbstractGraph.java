package com.google.common.graph;

import java.util.Set;
import javax.annotation.CheckForNull;

@ElementTypesAreNonnullByDefault
public abstract class AbstractGraph<N> extends AbstractBaseGraph<N> implements Graph<N> {
    public /* bridge */ /* synthetic */ Iterable predecessors(Object obj) {
        return predecessors(obj);
    }

    public /* bridge */ /* synthetic */ Iterable successors(Object obj) {
        return successors(obj);
    }

    public /* bridge */ /* synthetic */ int degree(Object obj) {
        return super.degree(obj);
    }

    public /* bridge */ /* synthetic */ Set edges() {
        return super.edges();
    }

    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(EndpointPair endpointPair) {
        return super.hasEdgeConnecting(endpointPair);
    }

    public /* bridge */ /* synthetic */ boolean hasEdgeConnecting(Object obj, Object obj2) {
        return super.hasEdgeConnecting(obj, obj2);
    }

    public /* bridge */ /* synthetic */ int inDegree(Object obj) {
        return super.inDegree(obj);
    }

    public /* bridge */ /* synthetic */ ElementOrder incidentEdgeOrder() {
        return super.incidentEdgeOrder();
    }

    public /* bridge */ /* synthetic */ Set incidentEdges(Object obj) {
        return super.incidentEdges(obj);
    }

    public /* bridge */ /* synthetic */ int outDegree(Object obj) {
        return super.outDegree(obj);
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Graph)) {
            return false;
        }
        Graph graph = (Graph) obj;
        if (isDirected() != graph.isDirected() || !nodes().equals(graph.nodes()) || !edges().equals(graph.edges())) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return edges().hashCode();
    }

    public String toString() {
        boolean isDirected = isDirected();
        boolean allowsSelfLoops = allowsSelfLoops();
        String valueOf = String.valueOf(nodes());
        String valueOf2 = String.valueOf(edges());
        StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 59 + String.valueOf(valueOf2).length());
        sb.append("isDirected: ");
        sb.append(isDirected);
        sb.append(", allowsSelfLoops: ");
        sb.append(allowsSelfLoops);
        sb.append(", nodes: ");
        sb.append(valueOf);
        sb.append(", edges: ");
        sb.append(valueOf2);
        return sb.toString();
    }
}
