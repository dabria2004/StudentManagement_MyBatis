package com.ppt.stu_mybatis.Model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserBean {

    private String id; 
    private String name;
    private String email;
    private String password;
    private String conpassword;
    private String role;
}
