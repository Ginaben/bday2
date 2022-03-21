package happy.bday2.service;

import happy.bday2.dto.BDayDto;
import happy.bday2.entity.BDay;

public interface BDayService {
    Long save(String name, String month, String day);

    Long saveTest(BDay bDay);

    BDayDto getInfo(Long id);

    }
