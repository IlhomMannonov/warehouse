package uz.pdp.finaltask.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import uz.pdp.finaltask.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Product extends AbsNameEntity {
    @ManyToOne(optional = false)
    private Category category;

    @OneToOne
    private Attachment photo;

    @Column(nullable = false)
    private String code;

    @ManyToOne(optional = false)
    private Measurment measurment;

    private boolean active;

}
