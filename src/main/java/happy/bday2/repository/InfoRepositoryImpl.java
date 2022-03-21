package happy.bday2.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import happy.bday2.dto.BDayDto;


import javax.persistence.EntityManager;
import java.util.List;

import static happy.bday2.entity.QBDay.bDay;
import static happy.bday2.entity.QInfo.info;


public class InfoRepositoryImpl implements InfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public InfoRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

//    private final QInfo info = info;

    @Override
    public List<BDayDto> getTmiById(Long id) {
        List<BDayDto> content = queryFactory
                .select(Projections.constructor(BDayDto.class,
                        info.id,
                        info.text,
                        bDay
                        ))
                .from(info, info)
                .innerJoin(info.day, bDay)
                .where(info.day.id.eq(id))
                .fetch();
        return content;
    }
}
