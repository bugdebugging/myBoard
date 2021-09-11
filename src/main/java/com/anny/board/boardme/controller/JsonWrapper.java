package com.anny.board.boardme.controller;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class JsonWrapper {
    private Object data;
    private Object error;

    @Builder(builderMethodName = "of")
    public JsonWrapper(Object data, Object error) {
        this.data = data;
        this.error = error;
    }
}
