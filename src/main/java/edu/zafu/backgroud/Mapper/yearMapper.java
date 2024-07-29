package edu.zafu.backgroud.Mapper;

import edu.zafu.backgroud.entity.year_num;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface yearMapper {
    @Select("SELECT * FROM year_count ORDER BY year")
    public List<year_num> query();
}
