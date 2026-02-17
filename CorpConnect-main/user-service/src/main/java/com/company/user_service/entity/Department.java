package com.company.user_service.entity;

public enum Department {
    IT,
    FINANCE,
    HR,
    ITS;

    public static Department from(String s) {
        if (s == null) return null;
        try {
            return Department.valueOf(s.toUpperCase());
        } catch (Exception ex) {
            return null;
        }
    }
}