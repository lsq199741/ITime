package com.itime.sign.service.impl;

import com.itime.sign.pojo.UserBasic;
import com.itime.sign.service.IUserBasicService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

@Service
public class UserBasicServiceImpl implements IUserBasicService {

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public ObjectId saveBasic(UserBasic basic) {
        mongoOperations.save(basic);
        UserBasic result = getBasicById(basic.get_id());
        if (result == null)
            return null;
        else
            return result.get_id();
    }

    @Override
    public boolean updateBasic(UserBasic basic) {
        return false;
    }

    @Override
    public UserBasic getBasicById(ObjectId _id) {
        return mongoOperations.findOne(new Query(Criteria.where("_id").is(_id)), UserBasic.class);
    }
}
