package com.tank.springcloud.springbootclient.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Immutablepair;

import java.net.URI;

public class URIUtil {
    public static ImmutablePair<String, String> formtUri(String uri) {
        URI uri1 = URI.create(uri);
        String host = uri1.getHost();
        if (uri1.getPort() > 0 && uri1.getPort() != 80) {
            host = host + ":80";
        }
        String path = uri1.getPath();
        if (uri1.getFragment() != null) {
            path = path + "#" + uri1.getFragment();
        }
        if (StringUtils.isNotBlank(path)) {
            path = host + path;
        } else {
            path = host;
        }
        return ImmutablePair.of(host, path);
    }
}
