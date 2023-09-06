package com.derezhenko.net;

import java.util.Map;

public interface HttpClient {
    String get(String url, Map<String, String> map);
}
