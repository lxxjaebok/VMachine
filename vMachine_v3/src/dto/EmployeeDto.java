package dto;

public record EmployeeDto(int id, int password , String name, String tel, int charge_money, int on_off) {
    @Override
    public String toString() {
        return   id + "      " + password + "          " + name + "       " + tel + "     " + charge_money + "       " + on_off;
    }

    public static EmployeeDto allOf(int id, int password , String name, String tel, int charge_money, int on_off){
        return new EmployeeDto(id,password,name,tel,charge_money, on_off);
    }
    public EmployeeDto(int password, String name, String tel, int charge_money, int on_off) {
        this(0, password, name, tel, charge_money,on_off);
    }
    public static EmployeeDto Of(int password , String name, String tel, int charge_money, int on_off){
        return new EmployeeDto(password,name,tel,charge_money,on_off);
    }
}



