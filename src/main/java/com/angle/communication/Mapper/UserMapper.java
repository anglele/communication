package com.angle.communication.Mapper;

import com.angle.communication.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url)values(#{name},#{accountId},#{gmtCreate},#{gmtModified},#{token},#{avatorUrl})")
    void insert(User user);

@Select("select * from user where token = #{token} ")
//如果token是类就可以自动加入select中，不是就要加@param注解
    User findByToken(@Param("token") String token);
@Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);

}