package happy.bday2.repository;

import happy.bday2.entity.Info;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InfoRepository extends JpaRepository<Info, Long>, InfoRepositoryCustom {

    @Query("select tmi from Info tmi where tmi.day.id=:id")
    List<Info> getTmi(@Param("id") Long id);
}
