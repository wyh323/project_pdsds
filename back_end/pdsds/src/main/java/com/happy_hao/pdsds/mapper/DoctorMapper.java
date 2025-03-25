package com.happy_hao.pdsds.mapper;

import com.happy_hao.pdsds.entity.Doctor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DoctorMapper extends UserMapper<Doctor> {
    @Select("select * from doctor where username=#{username}")
    Doctor findByUsername(String username);

    @Insert("insert into doctor(username, password) values (#{username}, #{password})")
    void add(String username, String password);

    @Update("update doctor set password = #{newPassword} WHERE username = #{username}")
    void updatePwdByUserName(String username, String newPassword);
}
