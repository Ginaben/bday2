package happy.bday2.dto;

import happy.bday2.entity.BDay;
import happy.bday2.entity.Info;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class BDayDto {
    private Long id;

    private String name;
    private String month;
    private String day;

    private BDay bday;

    private Long tid;
    @NotBlank
    private String text;

    private List<Info> tmiList = new ArrayList<>();

    public BDayDto(){}

    public BDayDto(Long id, String name, String month, String day){
        this.id = id;
        this.name = name;
        this.month = month;
        this.day = day;
    }

//    public BDayDto(Info info) {
//        this.tid = info.getId();
//        this.text = info.getText();
//        this.bday = info.getDay();
//    }

    public BDayDto(Long tid, String text, BDay bDay) {
        this.tid = tid;
        this.text = text;
        this.bday = bDay;
    }

}
