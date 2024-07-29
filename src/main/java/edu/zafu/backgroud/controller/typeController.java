package edu.zafu.backgroud.controller;

import edu.zafu.backgroud.Mapper.typeMapper;
import edu.zafu.backgroud.entity.type_num;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class typeController {

    private typeMapper typeMapper;

    @GetMapping("/type")
    public List<type_num> query() {
        return typeMapper.query();
    }

    public typeController(typeMapper typeMapper) {
        this.typeMapper = typeMapper;
    }
}
