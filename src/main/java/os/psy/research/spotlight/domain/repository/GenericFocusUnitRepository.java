package os.psy.research.spotlight.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import os.psy.research.spotlight.domain.entity.AbstractEntity;
import os.psy.research.spotlight.domain.entity.FocusUnit;

public interface GenericFocusUnitRepository<T> extends JpaRepository<T, String> {

    }
