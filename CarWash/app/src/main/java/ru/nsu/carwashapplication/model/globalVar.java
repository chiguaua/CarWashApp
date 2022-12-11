package ru.nsu.carwashapplication.model;

public class globalVar {
    public static String  ServerUrl = "http://192.168.0.101:8080";
    private static String userMail;
    private static String userPass;

    public static String getUserPass() {
        return userPass;
    }

    public static void setUserPass(String userPas) {
        userPass = userPas;
    }

    public static void setUserMail(String userMa) {
        userMail = userMa;
    }

    public static String getUserMail() {
        return userMail;
    }
}
