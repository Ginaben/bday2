package happy.bday2.service;

import happy.bday2.dto.BDayDto;
import happy.bday2.dto.search.SearchCondition;
import happy.bday2.entity.BDay;
import happy.bday2.entity.Info;
import happy.bday2.repository.BDayRepository;
import happy.bday2.repository.InfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BDayServiceImpl implements BDayService{

    private final BDayRepository repository;
    private final InfoRepository infoRepository;


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
        return new BDayDto(day);
//        return new BDayDto(day.getId(), day.getName(), day.getMonth(), day.getDay());
    }

    //TMI 저장
    @Override
    public Long addTmi(String text, Long id) {
        BDay day = repository.getById(id);
        return infoRepository.save(new Info(text, day)).getId();
    }

    //tmi 페이징
    @Override
    public Slice<BDayDto> getTmi(Pageable pageable, Long id) {
        return infoRepository.getTmi(pageable, id);
    }


    //생일 검색 페이징
    @Override
    public Slice<BDayDto> searchDay(Pageable pageable, SearchCondition condition) {
        return repository.searchDay(pageable, condition);
    }

    //tmi 삭제
    @Override
    public void remove(Long tno) {
        infoRepository.deleteById(tno);
    }

    //전체 삭제
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


    //tmi 리스트
/*
    @Override
    public List<BDayDto> getTmiById(Long id) {
        return infoRepository.getTmiById(id);
    }
*/

    //테스트 생일 저장
    @Override
    @Transactional
    public Long saveTest(BDay bDay) {
        return repository.save(new BDay(bDay.getName(), bDay.getMonth(), bDay.getDay()))
                .getId();
    }

    //테스트 tmi 저장
    @Override
    public Long addTmiTest(Info info, Long id) {
        BDay day = repository.getById(id);
        return infoRepository.save(new Info(info.getText(), day))
                .getId();
    }


}
