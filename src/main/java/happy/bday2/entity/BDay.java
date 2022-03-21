package happy.bday2.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BDay extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String month;
    private String day;

//    @OneToMany(mappedBy = "bDay", cascade = {PERSIST, REMOVE}, orphanRemoval = true)
//    private List<Info> infoList = new ArrayList<>();


    public BDay(String name, String month, String day) {
        this.name = name;
        this.month = month;
        this.day = day;
    }
}
