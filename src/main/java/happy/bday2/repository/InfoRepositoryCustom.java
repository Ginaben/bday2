package happy.bday2.repository;

import happy.bday2.dto.BDayDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface InfoRepositoryCustom {

//    List<BDayDto> getTmiById(Long id);
    Slice<BDayDto> getTmi(Pageable pageable, Long id);


    }
