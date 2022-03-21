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

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping
public class BDayController {

    private final BDayService service;

    @GetMapping({"","/"})
    public String index (@ModelAttribute("day") BDayDto dto){
        return "index";
    }

    @PostMapping("/add")
    public String addDay(@Validated @ModelAttribute("day") BDayDto dto, BindingResult bindingResult){
        Long id = service.save(dto.getName(), dto.getMonth(),dto.getDay());
        return "redirect:/detail/" + id;
    }

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        model.addAttribute("date",service.getInfo(id));
        return "detail";
    }

}
