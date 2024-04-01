package dto;

public record SalesADto(String name, int count, int money) {

    @Override
    public String toString() {
        return  name  + "        " + count + "          " + money;
    }

    public SalesADto(String name, int count, int money) {
        this.name = name;
        this.count = count;
        this.money = money;
    }
    public static SalesADto all(String name, int count, int money){
        return new SalesADto(name, count, money);
    }
}
