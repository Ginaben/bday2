package happy.bday2.repository;

import happy.bday2.dto.BDayDto;

import java.util.List;

public interface InfoRepositoryCustom {

    List<BDayDto> getTmiById(Long id);

    }
