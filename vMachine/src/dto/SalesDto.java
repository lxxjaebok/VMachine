package dto;

public record SalesDto(int id, int employee_id, int product_id, int sell_price) {
    public SalesDto(int employee_id, int product_id, int sell_price) {
        this(0,employee_id, product_id, sell_price);
    }
    public static SalesDto of(int employee_id, int product_id, int sell_price){
        return new SalesDto(employee_id, product_id, sell_price);
    }
}
