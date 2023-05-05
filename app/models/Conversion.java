package models;

public class Conversion {

    public Conversion(){

    }

    public static String convertCodeToText(int number) {

        switch (number) {
            case 100:
                return "Clear";
            case 200:
                return "Clear1";
            case 300:
                return "Clear2";
            case 400:
                return "Clear3";
            case 500:
                return "Clear4";
            case 600:
                return"Clear5";
            case 700:
                return "Clear6";
            case 800:
                return "Clear7";
            default:
                return "Clearx";


        }

    }
}
