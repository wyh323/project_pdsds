package com.happy_hao.pdsds.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.happy_hao.pdsds.entity.Slide;

@Mapper
public interface HomeMapper {

    // 根据用户名查询用户
    @Select("SELECT * FROM slides")
    List<Slide> findAllSlides();

}
