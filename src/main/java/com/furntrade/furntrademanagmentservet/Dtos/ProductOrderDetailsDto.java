package com.furntrade.furntrademanagmentservet.Dtos;
import com.furntrade.furntrademanagmentservet.Models.Product;
import org.springframework.hateoas.RepresentationModel;

public class ProductOrderDetailsDto extends RepresentationModel<ProductOrderDetailsDto>{

    private Product product;
    private int quantity;

    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
