package com.happy_hao.pdsds.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.happy_hao.pdsds.entity.Patient;

@Mapper
public interface PatientMapper {

    // 根据用户名查询用户
    @Select("select * from patient where username=#{username}")
    Patient findByUsername(String username);

    // 添加
    @Insert("insert into patient(username, password)" + "values (#{username}, #{password})")
    void add(String username, String password);

    @Update("update patient set password = #{newPassword} WHERE username = #{username}")
    void updatePwdByUserName(String username, String newPassword);

}
