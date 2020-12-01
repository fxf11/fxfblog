package com.fxf.blogbs.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private int userId;
    private String userName;
    private String userPassword;
    private String userProfilePhoto;
    private String userAdmin;

}
