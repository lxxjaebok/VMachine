package view;

import dto.ProductDto;
import dto.SalesDto;
import dto.SalesADto;
import dto.TelDto;
//import service.MenuList;
import service.UserMenu;
import service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserView {
    static int nowId;
    static int voId;

    static Scanner sc = new Scanner(System.in);
    static UserService userService = new UserService();
    //private static List<ProductVO> MenuList;

    public static void insertView() {
        int password = 0;
        String name = null;
        String tel = null;
        int charg_money = 0;
        int on_off = 0;
        //Scanner 입력 받을 값 변수 선언
        System.out.println("회원가입을 진행합니다.");

        System.out.println("비밀번호 : ");
        password = sc.nextInt();

        System.out.println("이름 : ");
        name = sc.next();

        System.out.println("전화번호 : ");
        tel = sc.next();

        System.out.println("충전금액 : ");
        charg_money = sc.nextInt();
        if (charg_money % 1000 == 0 && charg_money > 0){
            TelDto dto = TelDto.Of(password,name,tel,charg_money,on_off);
            int result = userService.insertData(dto);
            if (result != 0){
                System.out.println("회원가입이 완료되었습니다.");
                TelDto ch = userService.selectemp(password);
                System.out.println(dto.name() + "님의 ID는 "+ch.id() + "입니다.");

            }else{
                System.out.println("회원가입에 실패하였습니다.");
            }
        }else {
            System.out.println("1000원 단위로 충전할 수 있습니다. / 회원가입에 실패하였습니다.");
        }
    }
    public static void login() {
        int id;
        System.out.println("아이디 입력 :");
        id = sc.nextInt();

        String password;
        System.out.println("비밀번호 입력 : ");
        password =sc.next();

        TelDto dto = userService.match(id,password);
        if (dto == null) {
            System.out.println("로그인에 실패하였습니다.");
        } else if (dto.on_off() == 0){
            System.out.println("삭제된 회원입니다.");
        }else {
            System.out.println("로그인 되었습니다.");
            nowId=id;
            UserMenu.Menu(dto.name());
        }
    }

    public static void insertCoin(int money) {
        TelDto nowMoney = userService.selectOne(nowId);
        int sum = nowMoney.charge_money() + money;
        int result = userService.addmoney(nowId, sum);
        if (result != 0) {
            System.out.println("금액 충전이 완료 되었습니다.");
            System.out.println("사용 가능 금액 : " + sum + "원");
        } else {
            System.out.println("금액 충전에 실패하였습니다.");
        }
    }

    public static void setCoin() {
            TelDto nowMoney = userService.selectOne(nowId);
            int money = nowMoney.charge_money();
            //userService.setCoin(nowId,money);
            int result = userService.setCoin(nowId, money);
            if (result != 0) {
                System.out.println("잔돈반환이 완료되었습니다.");
                System.out.println("사용 가능 금액 : 0원 ");
            } else {
                System.out.println("잔돈반환 실패하였습니다.");
            }
        }
    public static void product() {
        List<ProductDto> product = new ArrayList<>();
        product = userService.allList();
        if(product == null){
            System.out.println("제품이 없습니다");
        }else {
            System.out.println("ID  / product_name / price / stock / on_off");
            for (ProductDto d : product){
                System.out.println(d);
            }
        }
    }

    public static void user(){
        List<TelDto> user = new ArrayList<>();
        user = userService.selectAll();
        if(user == null){
            System.out.println("등록된 유저가 없습니다.");
        }
        else {
            System.out.println("ID  /  password   /   name   /      tel      /   money  /  on_off ");
            for (TelDto dto : user){
                System.out.println(dto);
            }
        }
    }

    public static void choice() {
        List<ProductDto> product = userService.allList();

        if (product.isEmpty()){
            System.out.println("제품이 없습니다.");
        } else {
            ProductDto p;
            System.out.println("선택할 제품 ID 입력 : ");
            voId = sc.nextInt();
            if (userService.selectvo(voId) != null) {
                TelDto nowMoney = userService.selectOne(nowId);
                p = userService.selectvo(voId);
                if (p.on_off() == 0) {
                    System.out.println("삭제된 제품입니다.");
                } else {
                    if (nowMoney.charge_money() - p.price() < 0) {
                        System.out.println("잔액이 부족합니다.");
                    } else {
                        if (p.stock() == 0) {
                            System.out.println("재고가 없습니다.");
                        } else {
                            afterBuy();
                            product();
                        }
                    }
                }
            } else System.out.println("존재하지 않는 상품입니다.");
        }
    }
    public static void afterBuy(){
        TelDto user = userService.selectOne(nowId);
        ProductDto p = userService.selectvo(voId);
        int money =user.charge_money()-p.price();
        int r = userService.setmoney(nowId,money);

        if (r != 0){
            System.out.println("결제가 완료되었습니다.");
            System.out.println("잔액 : " + money + "원");

            int stock = p.stock() - 1;
            int result = userService.setStock(voId,stock);
            if (result != 0){
                System.out.println(p.product_name() + "가(이) 1개 나왔습니다.");

            }else {
                System.out.println("상품 출고가 되지 않았습니다.");
                return;
            }

            int eid = nowId;
            int vid = voId;
            SalesDto dto = SalesDto.of(eid,vid,p.price());
            int rs = userService.eId(dto);
            if (rs != 0){
                System.out.println("판매가 완료되었습니다.");
            } else {
                System.out.println("판매에 실패하였습니다.");
            }
        }else {
            System.out.println("결제가 완료되지 않았습니다.");
            System.out.println(money);
        }
    }
    public static void aChoice(){
        List<SalesADto> dto = new ArrayList<>();
        dto = userService.aChoice();
        if (dto == null){
            System.out.println("오류가 발생하였습니다.");
        }else {
            System.out.println("제품별 판매 현황을 보여드리겠습니다.");
            System.out.println("제품명  /  판매수량  /  판매금액");
            for (SalesADto salesUDto : dto){
                System.out.println(salesUDto);
            }
        }

    }
}
