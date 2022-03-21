package happy.bday2.service;

import happy.bday2.dto.BDayDto;
import happy.bday2.entity.BDay;
import happy.bday2.entity.Info;
import happy.bday2.repository.BDayRepository;
import happy.bday2.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BDayServiceImpl implements BDayService{

    private final BDayRepository repository;
    private final InfoRepository infoRepository;

//    @Override
//    public List<BDayDto> getTmiById(Long id){
//        Info info = infoRepository.getById(id);
//        return new BDayDto(info);
//    }

    //TMI 저장
    @Override
    public Long addTMI(String text, Long id) {
        BDay day = repository.getById(id);
        return infoRepository.save(new Info(text, day)).getId();
    }

    //이름 생일 저장
    @Override
    @Transactional
    public Long save(String name, String month, String day) {
        BDay bDay = repository.save(new BDay(name, month, day));
        return bDay.getId();
    }

    //디테일에 정보 불러오기
    @Override
    public BDayDto getInfo(Long id) {
        BDay day = repository.getById(id);
        return new BDayDto(day.getId(), day.getName(), day.getMonth(), day.getDay());
    }

    //테스트 저장
    @Override
    @Transactional
    public Long saveTest(BDay bDay) {
        return repository.save(new BDay(bDay.getName(), bDay.getMonth(), bDay.getDay()))
                .getId();
    }

}
