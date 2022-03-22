package happy.bday2.service;

import happy.bday2.dto.BDayDto;
import happy.bday2.entity.BDay;
import happy.bday2.entity.Info;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface BDayService {
    Long save(String name, String month, String day);

    BDayDto getInfo(Long id);

    Long addTmi(String text, Long id);

//    List<BDayDto> getTmiById(Long id);
    Slice<BDayDto> getTmi(Pageable pageable, Long id);

    Long saveTest(BDay bDay);
    Long addTmiTest(Info info, Long id);

    }
