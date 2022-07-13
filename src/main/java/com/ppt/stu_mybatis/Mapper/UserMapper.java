package com.ppt.stu_mybatis.Mapper;

import java.util.List;
import com.ppt.stu_mybatis.Model.UserBean;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

final String getAll = "select * from student";

final String getUserListByIdOrName = "select * from user where user_id like #{id} or user_name like #{name}";

final String getUserById = "select * form user where user_id = #{id}";

final String delete = "delete from user WHERE user_id = #{id}";

final String insert = "insert into user (user_id, user_name, user_email, user_password, user_conpassword, user_role) values (#{id}, #{name}, #{email}, #{password}, #{conpassword}, #{role})";

final String update = "update user set user_id = #{id}, user_name = #{name},user_email = #{email},user_password=#{password}, user_conpassword=#{conpassword}, user_role=#{role} where user_id = #{id}";

@Select(getAll)
@Results(value = { 
    @Result(property = "id", column = "user_id"),
    @Result(property = "name", column = "user_name"),
    @Result(property = "email", column = "user_email"),
    @Result(property = "password", column = "user_password"),
    @Result(property = "conpassword", column = "user_conpassword"),
    @Result(property = "role", column = "user_role")})
List<UserBean> getAll();

@Select(getUserListByIdOrName)
@Results(value = { 
    @Result(property = "id", column = "user_id"),
    @Result(property = "name", column = "user_name"),
    @Result(property = "email", column = "user_email"),
    @Result(property = "password", column = "user_password"),
    @Result(property = "conpassword", column = "user_conpassword"),
    @Result(property = "role", column = "user_role")})
List<UserBean> selectUserByIdOrName();

@Select(getUserById)
@Results(value = { 
    @Result(property = "id", column = "user_id"),
    @Result(property = "name", column = "user_name"),
    @Result(property = "email", column = "user_email"),
    @Result(property = "password", column = "user_password"),
    @Result(property = "conpassword", column = "user_conpassword"),
    @Result(property = "role", column = "user_role")})
UserBean selectUserById(String userId);

@Update(update)
void updateUserById(UserBean user);

@Delete(delete)
void deleteUser(String userId);

@Insert(insert)
void insert(UserBean student);
}
