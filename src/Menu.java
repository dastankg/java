import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Pars.main();
        main();
    }


    public static void main() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Welcome to my program");
        System.out.println();
        System.out.println("Что вы хотите сделать");
        System.out.println("1 - Увидеть курс валют");
        System.out.println("2 - Купить валюту");
        System.out.println("3 - Продать валюту");
        System.out.println("4 - Оформить микро взайм");
        System.out.println("0 - Завершить программу");
        System.out.println();
        int first = sc.nextInt();
        if (first == 1) {
            System.out.println();
            CSVLibrary.main();
            menu();
        }
        else if (first == 2){
            System.out.println(Buy.main());
            menu();
        }

    }


    public static void menu() {

        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("1 - EXIT");
        System.out.println("2 - Menu");
        int n = sc.nextInt();
        if (n == 2) {
            Menu.main();
        }
    }
}
