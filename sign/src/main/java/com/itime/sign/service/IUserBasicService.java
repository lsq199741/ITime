package com.itime.sign.service;

import com.itime.sign.pojo.UserBasic;
import org.bson.types.ObjectId;

public interface IUserBasicService {

    ObjectId saveBasic(UserBasic basic);

    boolean updateBasic(UserBasic basic);

    UserBasic getBasicById(ObjectId _id);


}
