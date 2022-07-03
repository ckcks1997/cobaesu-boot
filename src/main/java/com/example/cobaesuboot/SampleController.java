package com.example.cobaesuboot;

import com.example.cobaesuboot.dto.SampleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class SampleController {

    @GetMapping("/sample/ex1/{id}")
    public String exModel(Model model, @PathVariable("id") int id, @RequestParam(value = "sno", defaultValue="0") int sno){
        System.out.println(sno+"===");
        System.out.println(id+"===");
        List<SampleDTO> list = IntStream.rangeClosed(1, 20).asLongStream()
                .mapToObj(i -> {
                    SampleDTO dto = SampleDTO.builder()
                            .sno(i)
                            .first("first" + i)
                            .last("last" + i)
                            .regTime(LocalDateTime.now())
                            .build();
                    return dto;
                }).collect(Collectors.toList());

        model.addAttribute("list", list);
        return "sample/ex1";
    }

    @GetMapping("sample/ex2")
    public String exLayout1(){

        return "sample/exLayout1";
    }
    @GetMapping("sample/ex3")
    public String exLayout2(){

        return "sample/exTemplate";
    }
    @GetMapping("sample/ex4")
    public String exLayout3(){

        return "sample/exSidebar";
    }
}
