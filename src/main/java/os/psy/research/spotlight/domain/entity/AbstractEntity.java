package os.psy.research.spotlight.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@Setter(AccessLevel.NONE)
    private String id;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}