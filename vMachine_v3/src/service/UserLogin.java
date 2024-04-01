package service;

import dto.TelDto;
import view.UserView;

import java.util.Scanner;

public class UserLogin {

    public static void insertCoin() {
        int insert;
        Scanner sc = new Scanner(System.in);
        int money = 0;
        System.out.println("회원메뉴 - 금액충전");
        System.out.println("1:1000원, 2:2000원, 3.3000원, 4:4000원, 5:5000원");
        insert = sc.nextInt();
        if (insert == 1){
            money =1000;
            System.out.println("금액충전 : 1000원");
            UserView.insertCoin(money);

        }else if (insert == 2) {
            money =2000;
            System.out.println("금액충전 : 2000원");
            UserView.insertCoin(money);

        }else if (insert == 3) {
            money =3000;
            System.out.println("금액충전 : 3000원");
            UserView.insertCoin(money);

        }else if (insert == 4) {
            money =4000;
            System.out.println("금액충전 : 4000원");
            UserView.insertCoin(money);

        }else if (insert == 5) {
            money =5000;
            System.out.println("금액충전 : 5000원");
            UserView.insertCoin(money);

        }else {
            System.out.println("다시 입력해주세요");
            return;
        }

    }

    public static void setCoin() {
        System.out.println();
        UserView.setCoin();

    }
}
