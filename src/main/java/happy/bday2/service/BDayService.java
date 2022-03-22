package happy.bday2.service;

import happy.bday2.dto.BDayDto;
import happy.bday2.dto.search.SearchCondition;
import happy.bday2.entity.BDay;
import happy.bday2.entity.Info;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface BDayService {
    //이름생일저장
    Long save(String name, String month, String day);
    //이름생일
    BDayDto getInfo(Long id);
    //tmi저장
    Long addTmi(String text, Long id);
    //tmi 리스트
    Slice<BDayDto> getTmi(Pageable pageable, Long id);
    //검색
    Slice<BDayDto> searchDay(Pageable pageable, SearchCondition condition);
    //tmi 삭제
    void remove(Long tno);
    //전체 삭제
    void delete(Long id);

    //tmi 리스트
//    List<BDayDto> getTmiById(Long id);

    //test데이터
    Long saveTest(BDay bDay);
    Long addTmiTest(Info info, Long id);

    }
