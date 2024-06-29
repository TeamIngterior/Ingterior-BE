package com.team_ingterior.ingterior.construction.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConstructionPermissionEnum {
    ADMIN(1, "관리자"),
    WORKER(2, "작업자"),
    OBSERVER(3, "일반사용자");

    private final int level;
    private final String name;

}