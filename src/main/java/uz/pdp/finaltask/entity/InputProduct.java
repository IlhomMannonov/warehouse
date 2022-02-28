package uz.pdp.finaltask.entity;

import lombok.*;
import uz.pdp.finaltask.entity.template.AbsLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class InputProduct extends AbsLong {
    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double price;

    private Date expireDate;

    @ManyToOne
    private Input input;

    public InputProduct(Product product, Double amount) {
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.expireDate = expireDate;
        this.input = input;
    }

    public InputProduct(Product product, Double amount, Double price, Date expireDate, Input input) {
        this.product = product;
        this.amount = amount;
        this.price = price;
        this.expireDate = expireDate;
        this.input = input;
    }
}
