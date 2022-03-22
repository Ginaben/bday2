package happy.bday2.repository;

import happy.bday2.dto.BDayDto;
import happy.bday2.dto.search.SearchCondition;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface BDayRepositoryCustom {

    Slice<BDayDto> searchDay(Pageable pageable, SearchCondition condition);

    }
