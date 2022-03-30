package happy.bday2.controller;

import happy.bday2.dto.BDayDto;
import happy.bday2.dto.search.SearchCondition;
import happy.bday2.dto.search.SearchType;
import happy.bday2.paging.RequestPageSortDto;
import happy.bday2.service.BDayService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public String index (@ModelAttribute("day") BDayDto dto,
                         RequestPageSortDto requestPageDto, Model model,
                         SearchType searchType, String keyword){

        searchDayList(requestPageDto, model, searchType, keyword);
        return "index";
    }

    //생일 저장
    @PostMapping("/add")
    public String addDay(@Validated @ModelAttribute("day") BDayDto dto, BindingResult bindingResult){
        Long id = service.save(dto.getName(), dto.getMonth(),dto.getDay());
        return "redirect:/detail/" + id;
    }

    //전체 삭제
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> remove(@PathVariable("id") Long id,
                                       @ModelAttribute("day") BDayDto dto,
                                       RequestPageSortDto requestPageDto, Model model,
                                       SearchType searchType, String keyword) {
        service.delete(id);

        searchDayList(requestPageDto, model, searchType, keyword);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    //디테일
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model,
                         RequestPageSortDto requestPageDto){
        model.addAttribute("date",service.getInfo(id));

        //리스트
//        model.addAttribute("tmi",service.getTmiById(id));

        //페이징
        Pageable pageable = requestPageDto.getPageableSort();
        model.addAttribute("tmi",service.getTmi(pageable, id));

        return "detail";
    }

    //tmi저장
    @PostMapping("/addTmi/{id}")
    public String addTmi(@PathVariable("id") Long id, Model model, HttpServletRequest request){
        String text = request.getParameter("text");
        service.addTmi(text,id);

        return "detail :: #list-table";
    }

    //tmi 삭제
    @DeleteMapping("/deleteTmi/{tno}")
    public String remove(@PathVariable("tno") Long tno, Model model,
                         HttpServletRequest request, RequestPageSortDto requestPageDto) {
        service.remove(tno);

        Long id = Long.valueOf(request.getParameter("id"));

        Pageable pageable = requestPageDto.getPageableSort();
        model.addAttribute("tmi",service.getTmi(pageable, id));

        return "detail :: #list-table";
    }

    //검색
    @PostMapping({"","/"})
    public String searchList(@RequestParam(value = "searchType", required = false) SearchType searchType,
                             @RequestParam(value = "keyword", required = false) String keyword,
                             RequestPageSortDto requestPageDto, Model model) {

        searchDayList(requestPageDto, model, searchType, keyword);
        return "redirect:/list";
    }

    //검색 후 리스트
    @GetMapping("/list")
    public String list(RequestPageSortDto requestPageDto, Model model,
                       SearchType searchType, String keyword) {

        searchDayList(requestPageDto, model, searchType, keyword);
        return "list";

    }

    //검색 페이징
    private void searchDayList(RequestPageSortDto requestPageDto, Model model, SearchType searchType, String keyword) {

        Pageable pageable = requestPageDto.getPageableSort(Sort.by("month").descending());

        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);

            if (searchType != null) {
                model.addAttribute("search", service.searchDay(pageable, new SearchCondition(keyword, searchType)));
            }
    }

    /*
    //전체 삭제
    @DeleteMapping("/delete/{id}")
    public String remove(@PathVariable("id") Long id, @ModelAttribute("day") BDayDto dto,
                            RequestPageSortDto requestPageDto, Model model,
                            SearchType searchType, String keyword) {
        service.delete(id);

        searchDayList(requestPageDto, model, searchType, keyword);
        return "index";
    }
*/

    /*
    @ResponseBody
    @DeleteMapping("/deleteTmi/{tno}")
    public ResponseEntity<Long> remove(@PathVariable("tno") Long tno, Model model,
                                       @RequestBody BDayDto dto, RequestPageSortDto requestPageDto) {
        service.remove(tno);

        Pageable pageable = requestPageDto.getPageableSort();
        model.addAttribute("tmi",service.getTmi(pageable, dto.getId()));

        return new ResponseEntity<>(tno, HttpStatus.OK);
    }
*/


}
