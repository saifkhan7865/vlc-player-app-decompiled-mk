package com.typesafe.config;

import java.net.URL;
import java.util.List;

public interface ConfigOrigin {
    List<String> comments();

    String description();

    String filename();

    int lineNumber();

    String resource();

    URL url();

    ConfigOrigin withComments(List<String> list);

    ConfigOrigin withLineNumber(int i);
}
