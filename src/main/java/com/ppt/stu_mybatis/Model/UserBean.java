package com.ppt.stu_mybatis.Model;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserBean {

    private String user_id; 
    @NotEmpty(message = "Name cannot be blank!")
    private String user_name;
    @NotEmpty(message = "Email cannot be blank!")
    private String user_email;
    @NotEmpty(message = "Password cannot be blank!")
    private String user_password;
    @NotEmpty(message = "Confirm password cannot be blank!")
    private String user_conpassword;
    @NotEmpty(message = "Role cannot be blank!")
    private String user_role;
}
