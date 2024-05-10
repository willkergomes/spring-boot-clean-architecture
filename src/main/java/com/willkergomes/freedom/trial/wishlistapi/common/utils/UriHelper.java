package com.willkergomes.freedom.trial.wishlistapi.common.utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class UriHelper {

    private UriHelper() {
    }

    public static URI buildURI(UriComponentsBuilder uriComponentsBuilder, String path, String customerId, String productId) {
        UriComponents uriComponents =
                uriComponentsBuilder.path(path)
                        .buildAndExpand(customerId, productId);
        return uriComponents.toUri();
    }
}
