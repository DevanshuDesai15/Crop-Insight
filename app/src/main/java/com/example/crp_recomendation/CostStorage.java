package com.example.crp_recomendation;

public class CostStorage {

    static String buy;
    static String srr;
    static String syr;

    public CostStorage() {
    }

    public static String getBuy() {
        return buy;
    }

    public static void setBuy(String buy) {
        CostStorage.buy = buy;
    }

    public static String getSrr() {
        return srr;
    }

    public static void setSrr(String srr) {
        CostStorage.srr = srr;
    }

    public static String getSyr() {
        return syr;
    }

    public static void setSyr(String syr) {
        CostStorage.syr = syr;
    }
}
