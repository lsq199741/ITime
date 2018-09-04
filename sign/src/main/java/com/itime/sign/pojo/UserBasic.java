package com.itime.sign.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user_basic")
public class UserBasic {

    //用户昵称
    private String nickname;

    //用户性别，1:男性 2:女性 0:未知(厉害了大兄弟)
    private Integer gender;

    //用户头像
    private String head_img_url;

    //用户个人简介
    private String intro;

    //用户个人主页
    private String homepage;

    //用户微信二维码
    private String wechat_img;

    @Override
    public String toString() {
        return "UserBasic{" +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", headImgUrl='" + head_img_url + '\'' +
                ", intro='" + intro + '\'' +
                ", homepage='" + homepage + '\'' +
                ", wechat_img='" + wechat_img + '\'' +
                '}';
    }
}
