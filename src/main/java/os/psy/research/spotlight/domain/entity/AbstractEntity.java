package os.psy.research.spotlight.domain.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractEntity implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    //@Setter(AccessLevel.NONE)
    private String entityId;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}