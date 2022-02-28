package uz.pdp.finaltask.entity;

import lombok.*;
import uz.pdp.finaltask.entity.template.AbsLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Input extends AbsLong {

    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Currency currency;

    @Column(nullable = false)
    private String factureNumber;

    @ManyToOne
    private Supplier supplier;

    @Column(nullable = false)
    private String code;


    public Input(Long id, Warehouse warehouse, Currency currency, String factureNumber, Supplier supplier, String code) {
        super(id);
        this.warehouse = warehouse;
        this.currency = currency;
        this.factureNumber = factureNumber;
        this.supplier = supplier;
        this.code = code;
    }

    public Input(Warehouse warehouse, Currency currency, String factureNumber, Supplier supplier, String code) {
        this.warehouse = warehouse;
        this.currency = currency;
        this.factureNumber = factureNumber;
        this.supplier = supplier;
        this.code = code;
    }
}
