package com.everysesac.backend.domain;

import lombok.Getter;

@Getter
public enum SnsDiv {

    GOOGLE("google"), KAKAO("kakao"), NAVER("naver");


    private final String div;

    SnsDiv(String div) {
        this.div = div;
    }
}
