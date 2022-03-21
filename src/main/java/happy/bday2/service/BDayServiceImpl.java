package happy.bday2.service;

import happy.bday2.dto.BDayDto;
import happy.bday2.entity.BDay;
import happy.bday2.repository.BDayRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class BDayServiceImpl implements BDayService{

    private final BDayRepository repository;

    @Override
    @Transactional
    public Long save(String name, String month, String day) {
        BDay bDay = repository.save(new BDay(name, month, day));
        return bDay.getId();
    }

    @Override
    public BDayDto getInfo(Long id) {
        BDay day = repository.getById(id);
        return new BDayDto(day.getId(), day.getName(), day.getMonth(), day.getDay());
    }

    @Override
    @Transactional
    public Long saveTest(BDay bDay) {
        return repository.save(new BDay(bDay.getName(), bDay.getMonth(), bDay.getDay()))
                .getId();
    }

}
