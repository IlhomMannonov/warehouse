package uz.pdp.finaltask.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
public class OutputProduct extends AbsLong {

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Double price;


    @ManyToOne
    private Output output;
}
