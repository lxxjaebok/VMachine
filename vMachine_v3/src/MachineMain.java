import db.DBConn;
import view.UserView;

import java.util.Scanner;

public class MachineMain {
    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        int ch;
        while (true) {
            do {
                System.out.println("==============================");
                System.out.println("환영합니다 무엇을 도와드릴까요?");
                System.out.println("1.회원가입 / 2.로그인 / 3.종료");
                ch = sc.nextInt();
            } while (ch < 1 || ch >3);
            switch (ch){
                case 1:
                    UserView.insertView();
                    break;
                case 2:
                    UserView.login();

                    break;
                case 3:
                    DBConn.close();
                    return;
            }
        }
    }
}
