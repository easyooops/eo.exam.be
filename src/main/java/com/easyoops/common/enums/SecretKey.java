package com.easyoops.common.enums;

import lombok.Getter;


@Getter
public enum SecretKey {

    CHANNEL_SECRET_KEY("common.secret");

    private final String name;

    SecretKey(String name) {
        this.name = name;
    }

}