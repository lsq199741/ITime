package com.itime.sign.service.impl;

import com.itime.sign.pojo.UserAccount;
import com.itime.sign.service.IUserAccountService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component("userAccountService")
public class UserAccountServiceImpl implements IUserAccountService {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public boolean saveUser(UserAccount user) {

        boolean saveByMobile = user.getMobile() != null ? true : false;

        mongoOperations.save(user);

        UserAccount check = null;

        if (saveByMobile) {
            check = findUserByMobile(user.getMobile());
        } else {
            check = findUserByEmail(user.getEmail());
        }
        return check != null ? true : false;
    }

    @Override
    public boolean removeUser(ObjectId id) {


        return false;
    }

    @Override
    public boolean updateUser(UserAccount user) {
        return false;
    }

    @Override
    public UserAccount findUserByMobile(String mobile) {
        return null;
    }

    @Override
    public UserAccount findUserByEmail(String email) {
        return mongoOperations.findOne(new Query(Criteria.where("email").is(email)), UserAccount.class);
    }

    @Override
    public UserAccount findUserByItCode(String itCode) {
        return mongoOperations.findOne(new Query(Criteria.where("it_code").is(itCode)), UserAccount.class);
    }

}
