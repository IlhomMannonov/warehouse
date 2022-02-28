package uz.pdp.finaltask.entity;

import lombok.*;
import uz.pdp.finaltask.entity.template.AbsNameEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Attachment extends AbsNameEntity {

    @Column(nullable = false)
    private Long size;

    @Column(nullable = false)
    private String contentType;

    public Attachment(String name, Long size, String contentType) {
        super(name);
        this.size = size;
        this.contentType = contentType;
    }

    public Attachment(Long size, String contentType) {
        this.size = size;
        this.contentType = contentType;
    }

    public Attachment(Long id, String name, Long size, String contentType) {
        super(id, name);
        this.size = size;
        this.contentType = contentType;
    }
}
