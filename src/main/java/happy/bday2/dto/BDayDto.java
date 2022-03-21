package happy.bday2.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class BDayDto {
    private Long id;

    private String name;
    private String month;
    private String day;

    @NotBlank
    private String text;

    public BDayDto(){}

    public BDayDto(Long id, String name, String month, String day){
        this.id = id;
        this.name = name;
        this.month = month;
        this.day = day;
    }


}
