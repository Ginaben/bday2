package happy.bday2.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import happy.bday2.dto.BDayDto;
import happy.bday2.entity.Info;
import jdk.jfr.Category;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;


import javax.persistence.EntityManager;
import java.util.List;

import static happy.bday2.entity.QBDay.bDay;
import static happy.bday2.entity.QInfo.info;


public class InfoRepositoryImpl implements InfoRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public InfoRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Slice<BDayDto> getTmi(Pageable pageable, Long id) {
        List<BDayDto> content = queryFactory
                .select(Projections.constructor(BDayDto.class,
                info.id,
                info.text,
                bDay
        ))
                .from(info, info)
                .innerJoin(info.bDay, bDay).on(bDay.id.eq(info.bDay.id))
                .where(info.bDay.id.eq(id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<Info> countQuery = queryFactory
                .selectFrom(info)
                .where(info.bDay.id.eq(id))
                .fetch();

        return new PageImpl<>(content, pageable, countQuery.size());

    }

/*
    @Override
    public List<BDayDto> getTmiById(Long id) {
        List<BDayDto> content = queryFactory
                .select(Projections.constructor(BDayDto.class,
                        info.id,
                        info.text,
                        bDay
                        ))
                .from(info, info)
                .innerJoin(info.bDay, bDay).on(bDay.id.eq(info.bDay.id))
                .where(info.bDay.id.eq(id))
                .fetch();
        return content;
    }
*/

}
