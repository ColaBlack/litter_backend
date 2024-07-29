package edu.zafu.backgroud.controller;

import edu.zafu.backgroud.Mapper.yearMapper;
import edu.zafu.backgroud.entity.year_num;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class yearController {

    private yearMapper yearMapper;

    @GetMapping("/year")
    public List<year_num> query() {
        return yearMapper.query();
    }

    public yearController(yearMapper yearMapper) {
        this.yearMapper = yearMapper;
    }
}
