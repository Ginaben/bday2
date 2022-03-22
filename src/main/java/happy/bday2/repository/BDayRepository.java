package happy.bday2.repository;

import happy.bday2.entity.BDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BDayRepository extends JpaRepository<BDay, Long>, BDayRepositoryCustom {

}
