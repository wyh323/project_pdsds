package com.happy_hao.pdsds.mapper;

import java.time.LocalDateTime;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.happy_hao.pdsds.entity.Mail;

@Mapper
public interface MailMapper {
    @Select("SELECT * FROM mail WHERE email = #{email} ORDER BY createTime DESC LIMIT 1")
    Mail findByEmail(String email);

    @Insert("insert into mail(email,token,createTime)" + "values (#{email},#{token},now())")
    void add(String email, String token);

    @Update("UPDATE mail SET createTime = #{createTime} WHERE token = #{token}")
    void updateCreateTimeByToken(String token, LocalDateTime createTime);
}
