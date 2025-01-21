package com.p8labs.reactive.entity;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberCommon
{
    private boolean isBanned = false;
    protected Integer reward;

    public void updateIsBanned(boolean isBanned) {
        this.isBanned = isBanned;
    }
}
