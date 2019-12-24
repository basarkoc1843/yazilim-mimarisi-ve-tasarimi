package com.BasarKoc;

public class Singleton {
    private static Singleton nesne=new Singleton();
    private static int count;
    private String name;
    private Singleton() {
        count++;
        name=count+". Tasarım Objesi";
        System.out.println("Tasarım objesi oluşturuluyor");
    }
    public static Singleton getNesne() {
        return nesne;
    }
    public void ismiYazdir() {
        System.out.println(name);
    }



}
