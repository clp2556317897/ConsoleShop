import java.util.Date;
import java.util.Map;

public class Order {
  private User user;
    private Product products[];
    private Map<Integer,Integer> ammount;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product[] getProducts() {
        return products;
    }

    public void setProducts(Product[] products) {
        this.products = products;
    }

    public Map<Integer, Integer> getAmmount() {
        return ammount;
    }

    public void setAmmount(Map<Integer, Integer> ammount){
        this.ammount=ammount;
    }



    public float getActualPay() {
        return actualPay;
    }

    public Map<Integer, Float> getTotalAmountPerProduct() {
        return totalAmountPerProduct;
    }

    public void setTotalAmountPerProduct(Map<Integer, Float> totalAmountPerProduct) {
        this.totalAmountPerProduct = totalAmountPerProduct;
    }

    public void setActualPay(float actualPay) {
        this.actualPay = actualPay;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    private Map<Integer,Float> totalAmountPerProduct;//应付款
    private float actualPay;
    private Date orderDate;
}
