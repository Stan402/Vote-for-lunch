package ru.graduateproject;

public class AuthorizedUser {
    private static int id = 100000;

    public static int id(){
        return id;
    }
    public static void setId(int id) {
        AuthorizedUser.id = id;
    }
}
