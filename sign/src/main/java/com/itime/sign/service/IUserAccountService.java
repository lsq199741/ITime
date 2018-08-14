package com.itime.sign.service;

import com.itime.sign.pojo.UserAccount;
import org.bson.types.ObjectId;

public interface IUserAccountService {

    boolean saveUser(UserAccount user);

    boolean removeUser(ObjectId id);

    boolean updateUser(UserAccount user);

    UserAccount findUserByMobile(String mobile);

    UserAccount findUserByEmail(String email);

    UserAccount findUserByWeChat(String openId);

}
