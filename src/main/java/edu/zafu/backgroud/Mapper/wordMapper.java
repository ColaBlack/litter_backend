package edu.zafu.backgroud.Mapper;

import edu.zafu.backgroud.entity.word_num;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface wordMapper {
    @Select("SELECT * FROM word_count ORDER BY num DESC LIMIT 100")
    public List<word_num> query();
}
