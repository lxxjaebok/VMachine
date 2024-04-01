package dto;

public record TelDto(int id,int password , String name, String tel, int charge_money,int on_off) {
    @Override
    public String toString() {
        return id + "      " + password + "          " + name + "       " + tel + "     " + charge_money + "       " + on_off;
    }

    public static TelDto allOf(int id, int password, String name, String tel, int charge_money, int on_off) {
        return new TelDto(id, password, name, tel, charge_money, on_off);
    }

    public TelDto(int password, String name, String tel, int charge_money, int on_off) {
        this(0, password, name, tel, charge_money, on_off);
    }

    public static TelDto Of(int password, String name, String tel, int charge_money, int on_off) {
        return new TelDto(password, name, tel, charge_money, on_off);
    }
}

