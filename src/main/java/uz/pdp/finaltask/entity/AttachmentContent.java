package uz.pdp.finaltask.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import uz.pdp.finaltask.entity.template.AbsLong;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class AttachmentContent extends AbsLong {

    @Column(nullable = false)
    public byte[] bytes;

    @OneToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Attachment attachment;

    public AttachmentContent(Long id, byte[] bytes, Attachment attachment) {
        super(id);
        this.bytes = bytes;
        this.attachment = attachment;
    }

    public AttachmentContent(byte[] bytes, Attachment attachment) {
        this.bytes = bytes;
        this.attachment = attachment;
    }
}
