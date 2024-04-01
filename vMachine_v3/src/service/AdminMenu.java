package service;

import view.AdminView;
import view.UserView;

import java.util.Scanner;

public class AdminMenu {
    Scanner sc = new Scanner(System.in);
    int choice;
    AdminView adminView = new AdminView();
    public void MachineMenu(){
        while (true){
            do{
                System.out.println("=====================< 제품 Page >=============================");
                System.out.println("1.제품등록  2.제품수정  3.제품삭제  4.재고수정  5.제품조회  6.뒤로가기");
                choice = sc.nextInt();
            }while (choice < 1 ||  choice > 6);
            if (choice == 6) break;
            switch (choice) {
                case 1:
                    adminView.insertMenu();
                    break;
                case 2:
                    adminView.updateMenu();
                    break;
                case 3:
                    adminView.deleteMenu();
                    break;
                case 4:
                    adminView.updateStock();
                    break;
                case 5:
                    UserView.product();
            }
        }
    }

    public void UserMenu(){
        while (true){
            do {
                System.out.println("=====================< 회원관리 Page >=============================");
                System.out.println("1.회원생성  2.회원수정  3.회원삭제  4.회원조회  5.뒤로가기");
                choice = sc.nextInt();
            }while (choice < 1 ||  choice > 5);
            if(choice == 5) break;

            switch (choice){
                case 1:
                    UserView.insertView();
                    break;
                case 2:
                    adminView.updateUser();
                    break;
                case 3:
                    adminView.deleteUser();
                    break;
                case 4:
                    UserView.user();
                    break;
            }
        }
    }

    public void SalesMenu(){
        while (true){
            do {
                System.out.println("=====================< 판매관리 Page >=============================");
                System.out.println("1.제품별 판매현황  2.회원별 판매현황  3.뒤로가기");
                choice = sc.nextInt();
            }while (choice < 1 ||  choice > 5);
            if(choice == 3) break;
            switch (choice){
                case 1:
                    UserView.aChoice();
                    break;
                case 2:
                    adminView.showlistUser();
                    break;
            }
        }
    }
}