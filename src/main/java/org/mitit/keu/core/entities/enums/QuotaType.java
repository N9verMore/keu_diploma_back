package org.mitit.keu.core.entities.enums;

import java.util.Arrays;

public enum QuotaType {

    NONE("без пільг"),
    OUTOFQUEUE("позачерговий"),
    FIRSTINPRIORITY("першочерговий");

    private String name;

    QuotaType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static QuotaType getByValue(String mean) {
        return Arrays.stream(QuotaType.values()).filter(f -> f.getName().equals(mean)).findAny().orElse(null);
    }
}