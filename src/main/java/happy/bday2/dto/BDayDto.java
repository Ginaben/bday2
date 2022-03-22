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

    private BDay bDay;

    private Long tid;
    @NotBlank
    private String text;

//    private List<Info> tmiList = new ArrayList<>();


    public BDayDto(){}


    //day db (detail) or 검색
    public BDayDto(Long id, String name, String month, String day){
        this.id = id;
        this.name = name;
        this.month = month;
        this.day = day;
    }

    //day db (detail)
    public BDayDto(BDay bDay){
        this.id = bDay.getId();
        this.name = bDay.getName();
        this.month = bDay.getMonth();
        this.day = bDay.getDay();
    }

    //tmi 리스트
//    public BDayDto(Info info) {
//        this.tid = info.getId();
//        this.text = info.getText();
//        this.bDay = info.getBDay();
//    }

    //tmi 리스트
    public BDayDto(Long tid, String text, BDay bDay) {
        this.tid = tid;
        this.text = text;
        this.bDay = bDay;
    }


}
