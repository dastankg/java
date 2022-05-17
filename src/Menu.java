import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Pars.main();
        main();
    }


    public static void main() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println();
            System.out.println("Welcome to my program");
            System.out.println();
            System.out.println("Что вы хотите сделать?");
            System.out.println("1 - Увидеть курс валют");
            System.out.println("2 - Купить валюту");
            System.out.println("3 - Продать валюту");
            System.out.println("4 - Оформить микро взайм");
            System.out.println("5 - Оплатить микро взайм");
            System.out.println("6 - Оформить Депозит");
            System.out.println("7 - Забрать деньги из депозита");
            System.out.println("8 - Вход");
            System.out.println("0 - Завершить программу");
            System.out.println();
            System.out.print("--> ");
            int first = sc.nextInt();
            if (first == 1) {
                System.out.println();
                CSVLibrary.main();
                menu();
            } else if (first == 2) {
                System.out.println(Buy.main());
                menu();
            } else if (first == 3) {
                System.out.println(Sell.main());
                menu();
            } else if (first == 4) {
                Credit.main();
                menu();
            } else if (first == 5) {
                Pay.pay();
            } else if (first == 8) {
                Auth.main();
                menu();
            } else if (first == 6){
                Deposits.start();
                menu();
            } else if (first == 7){
                Deposits.get();
            }
            else if (first == 0) {
                System.out.println("До свидания!");
                break;
            } else {
                System.out.println("Не верные данные");
            }

        }
    }

    public static void main_admin() {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Что вы хотите сделать?");
        System.out.println();
        System.out.println("1 - посмотреть счет Банка");
        System.out.println("2 - попольнить счет Банка");
        System.out.println("3 - взять деньги");
        System.out.println("4 - посмотреть историю продажи валют");
        System.out.println("5 - посмотреть историю покупки валют");
        System.out.println("6 - выдача кредита");
        System.out.println("7 - просмотр клиентов");
        System.out.println("8 - просмотр плохих клиентов");
        System.out.println("9 - просмотр хороших клиентов");
        System.out.println("10 - просмотр клиентов которые взяли депозит");
        System.out.println();
        System.out.print("--> ");
        int n = sc.nextInt();
        if (n == 1) {
            Admin.show_currency();
            menu_admin();
        } else if (n == 2) {
            Admin.add_currency();
            menu_admin();
        } else if (n == 3) {
            Admin.get_currency();
            menu_admin();
        } else if (n == 4) {
            Admin.sell_history();
            menu_admin();
        } else if (n == 5) {
            Admin.buy_history();
            menu_admin();
        } else if (n == 6) {
            Admin.credit();
            menu_admin();
        } else if (n == 7) {
            Admin.client();
            menu_admin();
        } else if (n == 8) {
            Admin.show_bad_client();
            menu_admin();
        } else if (n == 9) {
            Admin.show_good_client();
            menu_admin();
        } else if (n == 10) {
            Deposits.print();
            menu_admin();
        }

    }


    public static void menu_admin() {

        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("1 - EXIT");
        System.out.println("2 - Menu");
        System.out.println("3 - Main_Menu");
        System.out.println();
        System.out.print("--> ");
        int n = sc.nextInt();
        if (n == 3) {
            Menu.main();
        } else if (n == 2) {
            main_admin();
        }
    }

    public static void menu() {

        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("1 - EXIT");
        System.out.println("2 - Menu");
        System.out.println();
        System.out.print("--> ");
        int n = sc.nextInt();
        if (n == 2) {
            Menu.main();
        }
    }
}
