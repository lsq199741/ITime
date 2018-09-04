package com.itime.sign.service;

import com.itime.sign.pojo.UserBasic;
import org.bson.types.ObjectId;

public interface IUserBasicService {

    boolean saveBasic(UserBasic basic);

    boolean updateBasic(UserBasic basic);

    UserBasic getBasicById(ObjectId _id);


}
