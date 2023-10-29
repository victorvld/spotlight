package os.psy.research.spotlight.domain.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import os.psy.research.spotlight.core.entity.AbstractEntity;

@Entity
@Getter
@Setter
@Table(name = "focus_unit")
@NoArgsConstructor
@SuperBuilder
public class FocusUnit extends AbstractEntity {

    @Column
    private String userId;

    @Column(name = "linked_project_point_id")
    private String linkedTaskId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "working_time_id", referencedColumnName = "entity_id")
    private WorkingTime workingTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "break_time_id", referencedColumnName = "entity_id")
    private BreakTime breakTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_assessment_id", referencedColumnName = "entity_id")
    private UserAssessment userAssessment;
}
