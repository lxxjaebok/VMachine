package dto;

public record ProductDto(int id, String product_name, int price, int stock,int on_off) {


    public static ProductDto allOf(int id, String product_name, int price, int stock,int on_off){
        return new ProductDto(id,product_name,price,stock,on_off);
    }
    public ProductDto(String product_name, int price, int stock,int on_off) {
        this(0, product_name, price, stock,on_off);
    }
    public static ProductDto Of(String product, int price, int stock,int on_off){
        return new ProductDto(product, price, stock,on_off);
    }

    @Override
    public String toString() {
        return  id + "       " + product_name + "          " + price + "      " + stock + "       " + on_off;
    }
}