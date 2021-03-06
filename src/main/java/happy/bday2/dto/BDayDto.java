package happy.bday2.dto;

import happy.bday2.entity.BDay;
import happy.bday2.entity.Info;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class BDayDto {
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String month;
    @NotNull
    private String day;

    private BDay bDay;

    private Long tno;
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
//        this.day.css = info.getId();
//        this.text = info.getText();
//        this.bDay = info.getBDay();
//    }

    //tmi 리스트
    public BDayDto(Long tno, String text, BDay bDay) {
        this.tno = tno;
        this.text = text;
        this.bDay = bDay;
    }


}
