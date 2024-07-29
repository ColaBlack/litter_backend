package edu.zafu.backgroud.Mapper;

import edu.zafu.backgroud.entity.type_num;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface typeMapper {
    @Select("SELECT * FROM type_count")
    public List<type_num> query();
}


