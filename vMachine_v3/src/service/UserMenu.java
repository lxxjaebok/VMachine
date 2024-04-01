package service;

import dto.TelDto;
import view.AdminView;
import view.UserView;

import java.util.Scanner;

public class UserMenu {
    public static void Menu(String dto) {

        Scanner sc = new Scanner(System.in);
        int ch;
        while (true) {
            do {
                System.out.println("===============================================================================================");
                System.out.println("반갑습니다 " + dto + "님");
                System.out.println("1.금액충전  2.잔돈반환  3.제품선택  4.관리자 메뉴  5.로그아웃");
                ch = sc.nextInt();
            } while (ch < 1 || ch > 5);
            //1부터5까지의 값이 빠져나간다
            if (ch == 5) {
                System.out.println("로그아웃되었습니다.");
                break;
            }
            switch (ch) {
                case 1:
                    //동전투입
                    System.out.println("==============================");
                    UserLogin.insertCoin();
                    break;
                case 2:
                    //잔돈반환
                    System.out.println("==============================");
                    UserLogin.setCoin();

                    break;
                case 3:
                    System.out.println("=====================< Menu >====================");

                    System.out.println("---------------------------------------------------");
                    UserView.product();
                    UserView.choice();


                    break;
                case 4:
                    int choice;
                    AdminMenu menu = new AdminMenu();
                    while (true) {
                        do {
                            System.out.println("=======================< 관리자 Page >=============================");
                            System.out.println("1.제품관리  2.회원관리  3.판매관리  4.이전");
                            choice = sc.nextInt();
                        } while (choice < 1 || choice > 4);
                        if(choice == 4){
                            break;
                        }
                        switch (choice){
                            case 1:
                                menu.MachineMenu();
                                break;
                            case 2:
                                menu.UserMenu();
                                break;
                            case 3:
                                menu.SalesMenu();
                                break;
                        }
                    }
            }
        }
    }
}