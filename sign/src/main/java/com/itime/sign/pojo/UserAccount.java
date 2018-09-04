package com.itime.sign.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/*
 * @Author shuqiang
 * @Desc 用户实体类
 * @Date 2018/8/6 下午3:24
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_account")
public class UserAccount {

    @Id
    private ObjectId _id;

    //闲时号 好友添加，查询
    @Indexed(unique = true, background = true)
    private String it_code;

    //用户手机号
    @Indexed(unique = true, background = true, sparse = true)
    private String mobile;

    //用户邮箱
    @Indexed(unique = true, background = true, sparse = true)
    private String email;

    //用户手机号是否已验证
    private boolean mobile_confirmed = false;

    //用户邮箱是否已验证
    private boolean email_confirmed = false;

    //用户密码
    @JsonIgnore
    private String password;

    //是否首次登陆
    private boolean is_first_login = false;

    //创建时间
    @JsonIgnore
    private Date create_time = new Date();

    //上次登陆时间
    private Date last_login_time = new Date();

    //用户基本信息
    @JsonIgnore
    private UserBasic userBasic;

    public String get_id() {
        return _id.toHexString();
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "_id=" + _id.toHexString() +
                ", it_code='" + it_code + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", mobile_confirmed=" + mobile_confirmed +
                ", email_confirmed=" + email_confirmed +
                ", password='" + password + '\'' +
                ", is_first_login=" + is_first_login +
                ", create_time=" + create_time +
                ", last_login_time=" + last_login_time +
                '}';
    }
}
