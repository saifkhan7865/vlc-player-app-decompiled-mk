package org.bouncycastle.pkix.util.filter;

public interface Filter {
    String doFilter(String str);

    String doFilterUrl(String str);
}
