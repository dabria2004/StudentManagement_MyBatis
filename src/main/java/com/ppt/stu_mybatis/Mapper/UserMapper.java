package com.ppt.stu_mybatis.Mapper;

import java.util.List;
import com.ppt.stu_mybatis.Model.UserBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

@Update("update user set user_name = #{user_name},user_email = #{user_email}, user_password = #{user_password}, user_conpassword = #{user_conpassword}, user_role = #{user_role} where user_id = #{user_id}")
void updateUserById(UserBean user);

@Delete("delete from user WHERE user_id = #{user_id}")
void deleteUser(String user_id);

@Insert("insert into user (user_id, user_name, user_email, user_password, user_conpassword, user_role) values (#{user_id}, #{user_name}, #{user_email}, #{user_password}, #{user_conpassword}, #{user_role})")
void insertUser(UserBean student);

@Select("select * from user where user_id = #{user_id}")
UserBean selectUserById(String user_id);

@Select("select * from user where user_id like #{user_id} or user_name like #{user_name}")
List<UserBean> selectUserListByIdOrName(String user_id, String user_name);

// @Select("SELECT EXISTS(SELECT 1 FROM user WHERE userMail=#{userMail} AND userPassword=#{userPassword})")
//     boolean existsByEmailAndPassword(String userMail,String userPassword);
@Select("SELECT EXISTS(SELECT 1 FROM user WHERE user_email=#{user_email})")
Boolean existsByEmail(String user_email);

@Select("select * from user")
List<UserBean> selectAll();

@Select("select exists(select 1 from user where user_email = #{user_email} and user_password = #{user_password})")
Boolean existsByEmailAndPassword(String user_email, String user_password);

@Select("select * from user where user_email = #{user_email}")
UserBean selectUserByEmail(String user_email);
}

//existsByEmailAndPassword
//findByEmail