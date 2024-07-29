package edu.zafu.backgroud.controller;

import edu.zafu.backgroud.Mapper.departmentMapper;
import edu.zafu.backgroud.entity.department_num;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class departmentController {

    private departmentMapper departmentMapper;

    @GetMapping("/department")
    public List<department_num> query() {
        return departmentMapper.query();
    }

    public departmentController(departmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }
}
