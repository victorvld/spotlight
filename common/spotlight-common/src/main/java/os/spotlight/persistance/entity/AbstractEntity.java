package os.spotlight.persistance.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AbstractEntity implements Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "entity_id")
    private String entityId;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
