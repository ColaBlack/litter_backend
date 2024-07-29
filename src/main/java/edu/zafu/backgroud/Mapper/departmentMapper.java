package edu.zafu.backgroud.Mapper;

import edu.zafu.backgroud.entity.department_num;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface departmentMapper {
    @Select("SELECT * FROM dapartment_count")
    public List<department_num> query();
}
