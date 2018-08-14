package com.itime.sign.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
    @Indexed(unique = true, background = true)
    private String mobile;

    //用户邮箱
    @Indexed(unique = true, background = true)
    private String email;

    //用户手机号是否已验证
    private boolean mobile_confirmed;

    //用户邮箱是否已验证
    private boolean email_confirmed;

    //用户密码
    private String password;

    //创建时间
    private Date create_time;

    //上次登陆时间
    private Date last_login_time;

    @Override
    public String toString() {
        return "UserAccount{" +
                "_id=" + _id.toString() +
                ", it_code='" + it_code + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", mobile_confirmed=" + mobile_confirmed +
                ", email_confirmed=" + email_confirmed +
                ", password='" + password + '\'' +
                ", create_time=" + create_time +
                ", last_login_time=" + last_login_time +
                '}';
    }
}
