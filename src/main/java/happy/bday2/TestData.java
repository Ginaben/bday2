package happy.bday2;

import happy.bday2.entity.BDay;
import happy.bday2.repository.BDayRepository;
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
        }
    }

}
