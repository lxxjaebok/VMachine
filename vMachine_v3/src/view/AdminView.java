package view;

import dto.ProductDto;
import dto.SalesBDto;
import dto.TelDto;
import service.AdminService;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static view.UserView.userService;

public class AdminView {
    Scanner sc = new Scanner(System.in);

    public void insertMenu() {
        String menu_name = null;
        int price = 0;
        int stock = 0;
        int on_off = 1;
        List<ProductDto> product = new ArrayList<>();
        product = userService.allList();
        System.out.println();
        System.out.println("제품 추가를 진행합니다.");
        System.out.println("제품명 : ");
        menu_name = sc.next();
        boolean flag = true;
        for (ProductDto vo : product) {
            if (menu_name.equals(vo.product_name())) {
                System.out.println("이미 등록된 상품입니다");
                flag = false;
                return;
            }
        }
        if (flag == true) {
            System.out.println("제품가격 : ");
            price = sc.nextInt();

            System.out.println("재고 : ");
            stock = sc.nextInt();

            ProductDto dto = ProductDto.Of(menu_name, price, stock,on_off);
            int result = AdminService.insertMenu(dto);
            if (result == 0){
                System.out.println("제품등록이 완료되었습니다.");
            } else {
                System.out.println("제품등록에 실패했습니다.");
            }
            //product.add(ProductDto.Of(menu_name, price, stock,on_off));
            UserView.product();
        }
    }

    public void deleteMenu() {
        List<ProductDto> product = new ArrayList<>();
        product = userService.allList();
        int menu_id = 0;
        boolean flag = true;
        System.out.println("삭제할 아이디 입력 : ");
        menu_id = sc.nextInt();
        for (ProductDto dto : product) {
            if (menu_id == (dto.id())) {
                //product.remove(product);
                if (dto.on_off() == 0){
                    System.out.println("이미 삭제된 제품입니다.");
                    return;
                }else {
                    int result = AdminService.deleteMenu(menu_id);
                    if (result != 0){
                        System.out.println(dto.product_name() + "(이)가 삭제됐습니다");
                    }else {
                        System.out.println("제품 삭제를 실패했습니다.");
                    }
                    UserView.product();
                    flag = false;
                    return;
                }
            }
        }
        if (flag == true) {
            System.out.println("해당 상품이 존재하지 않습니다.");
        }
    }

    public void updateMenu() {
        ProductDto dto = null;
        int menu_id = 0;
        String name = null;
        int price = 0;
        int stock = 0;
        int on_off = 0;
        String yesOrNo = null;
        UserView.product();
        System.out.println("수정 할 제품 ID 입력 : ");
        menu_id = sc.nextInt();

        dto = userService.selectvo(menu_id);
        if (dto == null){
            System.out.println("입력하신 상품이 조회되지 않습니다.");
        } else {
                System.out.println("수정 전 제품 이름 : " + dto.product_name());
                System.out.println("(수정) 제품 이름? ");
                name =sc.next();

                System.out.println("수정 전 제품 가격 : " + dto.price());
                System.out.println("(수정) 제품 가격 ?(숫자만 입력)");
                price = sc.nextInt();

                System.out.println("수정 전 제품 재고 : " + dto.stock());
                System.out.println("(수정) 제품 재고?(숫자만 입력)");
                stock = sc.nextInt();
                if (dto.on_off() == 0){
                    do{
                        System.out.println("제품을 재등록하시겠습니까(Y/N)");
                        yesOrNo = sc.next();
                    }while (!(yesOrNo.toUpperCase().equals("Y")||yesOrNo.toUpperCase().equals("N")));
                    if (yesOrNo.toUpperCase().equals("Y")){
                        on_off = 1;
                    }
                }
                int result = AdminService.updateMenu(menu_id, name, price, stock,1);
                if (result != 0){
                    System.out.println("수정이 완료되었습니다.");
                }else {
                    System.out.println("수정에 실패하였습니다.");
                }
                UserView.product();
        }
    }

    public void updateStock() {
        int menu_id;
        ProductDto dto = null;
        UserView.product();

        System.out.println("재고를 수정 할 제품 ID 입력 : ");
        menu_id = sc.nextInt();
        dto = userService.selectvo(menu_id);
        if (dto == null) {
            System.out.println("현재 등록된 상품이 없습니다.");
        } else {
            if (dto.on_off() == 0){
                System.out.println("삭제된 제품입니다.");
                return;
            }
            System.out.println("수정 전 재고 : " + dto.stock());
            System.out.println("(수정) 재고 수량?(숫자만 입력) : ");
            int updateStock = sc.nextInt();
            int result = AdminService.updateStock(menu_id, updateStock);
            if (result != 0){
                System.out.println("재고 수정이 완료되었습니다.");
                UserView.product();
            }else {
                System.out.println("재고 수정에 실패하였습니다.");
            }
        }
    }

    public void updateUser() {
        TelDto dto = null;
        int password = 0;
        String name = null;
        String tel = null;
        int charge_money = 0;
        int  on_off = 0;
        String yesOrNo = null;
        UserView.user();
        System.out.println("수정 할 아이디 입력");
        int user_id = sc.nextInt();
        dto = userService.selectOne(user_id);
        if (dto == null){
            System.out.println("현재 등록된 아이디가 없습니다.");
            return;
        }
        System.out.println("수정 전 비밀번호 : " + dto.password());
        System.out.println("(수정) 비밀번호?");
        password = sc.nextInt();

        System.out.println("수정 전 이름 : " + dto.name());
        System.out.println("(수정) 유저 이름?");
        name = sc.next();

        System.out.println("수정 전 전화번호 : " + dto.tel());
        System.out.println("(수정) 전화번호?");
        tel = sc.next();

        System.out.println("수정 전 잔액 : " + dto.charge_money());
        System.out.println("(수정) 잔액?");
        charge_money = sc.nextInt();
        if (charge_money % 1000 == 0 && charge_money > 0) {
            if (dto.on_off() == 0){
                do{
                    System.out.println("회원님을 재등록하시겠습니까(Y/N)");
                    yesOrNo = sc.next();
                }while (!(yesOrNo.toUpperCase().equals("Y")||yesOrNo.toUpperCase().equals("N")));
                if (yesOrNo.toUpperCase().equals("Y")){
                    on_off = 1;
                }
            }
            int result = AdminService.updateUser(user_id, password, name, tel, charge_money, 1);
            if (result != 0){
                System.out.println("회원님 수정이 완료되었습니다.");
                UserView.user();
            }else {
                System.out.println("회원님 수정에 실패하였습니다.");
            }
        }else {
            System.out.println("금액 수정은 1000원 단위로 가능합니다.");
        }
    }

    public void deleteUser() {
        int user_id;
        TelDto dto = null;
        System.out.println("삭제할 아이디 입력 : ");
        user_id = sc.nextInt();
        dto = userService.selectOne(user_id);
        if (dto == null){
            System.out.println("조회되는 ID가 없습니다.");
            return;
        }else if (dto.on_off() == 0){
            System.out.println("이미 삭제된 회원입니다.");
            return;
        }
        int result = AdminService.deleteUser(user_id);
        if (result != 0) {
            System.out.println("계정이 삭제 됐습니다.");
            UserView.user();
        } else {
            System.out.println("계정 삭제를 실패하였습니다.");
        }
    }

    public void showlistUser() {
        List<SalesBDto> user = new ArrayList<>();
        user = userService.salesUser();
        if (user == null) {
            System.out.println("ERROR !!!");
        }
        System.out.println("회원별 판매 현황을 보여드리겠습니다.");
        System.out.println("회원ID  /  회원이름  /  사용금액  /  충전금액");
        for (SalesBDto dto : user) {
            System.out.println(dto);
        }
    }
}