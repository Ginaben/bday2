package happy.bday2.controller;

import happy.bday2.dto.BDayDto;
import happy.bday2.service.BDayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class BDayController {

    private final BDayService service;

    //인덱스
    @GetMapping({"","/"})
    public String index (@ModelAttribute("day") BDayDto dto){
        return "index";
    }

    //생일자 저장
    @PostMapping("/add")
    public String addDay(@Validated @ModelAttribute("day") BDayDto dto, BindingResult bindingResult){
        Long id = service.save(dto.getName(), dto.getMonth(),dto.getDay());
        return "redirect:/detail/" + id;
    }

    //디테일
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("date",service.getInfo(id));
        return "detail";
    }

    @PostMapping("/addTmi/{id}")
    public String addTmi(@PathVariable("id") Long id, Model model, HttpServletRequest request){
        String text = request.getParameter("text");
        service.addTMI(text,id);

        return "detail :: #list-table";
    }
}
