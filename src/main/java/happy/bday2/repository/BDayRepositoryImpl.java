package happy.bday2.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import happy.bday2.dto.BDayDto;
import happy.bday2.dto.search.SearchCondition;
import happy.bday2.dto.search.SearchType;
import happy.bday2.entity.BDay;
import happy.bday2.entity.Info;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;

import java.util.List;

import static happy.bday2.entity.QBDay.bDay;
import static happy.bday2.entity.QInfo.info;

public class BDayRepositoryImpl implements BDayRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public BDayRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Slice<BDayDto> searchDay(Pageable pageable, SearchCondition condition) {
        List<BDayDto> content = queryFactory
                .select(Projections.constructor(BDayDto.class,
                        bDay.id,
                        bDay.name,
                        bDay.month,
                        bDay.day
                ))
                .from(bDay, bDay)
                .where(isSearch(condition.getSearchType(), condition.getContent()))
                .orderBy(bDay.month.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<BDay> countQuery = queryFactory
                .selectFrom(bDay)
                .fetch();

        return new PageImpl<>(content, pageable, countQuery.size());

    }

    //검색조건
    private BooleanExpression eqName(String name) {
        return StringUtils.hasText(name) ? bDay.name.containsIgnoreCase(name) : null;
    }

    private BooleanExpression eqMonth(String month) {
        return StringUtils.hasText(month) ? bDay.month.eq(month) : null;
    }

    private BooleanExpression isSearch(SearchType searchType, String searchText) {
        if (searchType.equals(SearchType.NAME)) {
            return eqName(searchText);
        } else if (searchType.equals(SearchType.MONTH)) {
            return eqMonth(searchText);
        } else {
            return eqName(searchText).or(eqMonth(searchText));
        }
    }


}
