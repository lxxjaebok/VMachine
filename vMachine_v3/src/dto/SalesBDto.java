package dto;

public record SalesBDto(int id, String name, int count, int money) {
    @Override
    public String toString() {
        return id + "          " + name + "        " + count + "        " + money;
    }
    public static SalesBDto all(int id, String name, int count, int money){
        return new SalesBDto (id, name, count, money);
    }
}
