package uz.pdp.finaltask.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.finaltask.entity.template.AbsLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Output extends AbsLong{


    @ManyToOne
    private Warehouse warehouse;

    @ManyToOne
    private Currency currency;

    @Column(nullable = false)
    private String factureNumber;

    @Column(nullable = false)
    private String code;

    @ManyToOne
    private Client client;

}
