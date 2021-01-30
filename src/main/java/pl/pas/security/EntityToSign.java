package pl.pas.security;

import java.util.Map;

public interface EntityToSign {
    Map<String, String> takePayload();
}
