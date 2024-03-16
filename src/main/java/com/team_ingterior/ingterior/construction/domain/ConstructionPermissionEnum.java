package com.team_ingterior.ingterior.construction.domain;

public enum ConstructionPermissionEnum {
    ADMIN(1, "관리자"),
    WORKER(2, "작업자"),
    OBSERVER(3, "일반사용자");

    private final int level;
    private final String name;

    ConstructionPermissionEnum(int level, String name) {
        this.level = level;
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    
}