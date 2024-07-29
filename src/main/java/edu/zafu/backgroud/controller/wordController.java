package edu.zafu.backgroud.controller;

import edu.zafu.backgroud.Mapper.wordMapper;
import edu.zafu.backgroud.entity.word_num;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class wordController {

    private wordMapper wordMapper;

    @GetMapping("/word")
    public List<word_num> query(){
        return this.wordMapper.query();
    }

    public wordController(wordMapper wordMapper) {
        this.wordMapper = wordMapper;
    }
}
