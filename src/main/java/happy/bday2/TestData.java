package happy.bday2;

import happy.bday2.entity.BDay;
import happy.bday2.entity.Info;
import happy.bday2.repository.BDayRepository;
import happy.bday2.repository.InfoRepository;
import happy.bday2.service.BDayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class TestData {

    private final InitService initService;

    @PostConstruct
    public void initService() {
        initService.init();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    @Slf4j
    static class InitService {
        private final EntityManager em;

        private final BDayService service;
        private final BDayRepository repository;
        private final InfoRepository infoRepository;

        public void init() {

            BDay day1 = new BDay("일","12","3");
            service.saveTest(day1);
            BDay day2 = new BDay("이","1","5");
            service.saveTest(day2);
            BDay day3 = new BDay("삼","5","20");
            service.saveTest(day3);
            BDay day4 = new BDay("사","8","7");
            service.saveTest(day4);
            BDay day5 = new BDay("오","2","7");
            service.saveTest(day5);
            BDay day6 = new BDay("육","11","13");
            service.saveTest(day6);
            BDay day7 = new BDay("밍","6","28");
            service.saveTest(day7);
            BDay day8 = new BDay("젼","2","12");
            service.saveTest(day8);

            em.flush();
            em.clear();

            Long d1 = repository.findById(1L).get().getId();
            Long d2 = repository.findById(2L).get().getId();
            Long d3 = repository.findById(3L).get().getId();
            Long d4 = repository.findById(4L).get().getId();
            Long d5 = repository.findById(5L).get().getId();
            Long d6 = repository.findById(6L).get().getId();
            Long d7 = repository.findById(7L).get().getId();
            Long d8 = repository.findById(8L).get().getId();

            Info info7 = new Info("INTP", day7);
            service.addTmiTest(info7, d7);
            Info info77 = new Info("INTP", day7);
            service.addTmiTest(info77, d7);
            Info info777 = new Info("INTP", day7);
            service.addTmiTest(info777, d7);

            Info info8 = new Info("ISFJ", day8);
            service.addTmiTest(info8, d8);
            Info info88 = new Info("ISFJ", day8);
            service.addTmiTest(info88, d8);
            Info info888 = new Info("ISFJ", day8);
            service.addTmiTest(info888, d8);

        }
    }

}
