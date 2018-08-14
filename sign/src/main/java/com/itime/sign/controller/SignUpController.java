package com.itime.sign.controller;


import com.itime.sign.pojo.JsonResult;
import com.itime.sign.pojo.UserAccount;
import com.itime.sign.service.IUserAccountService;
import com.itime.sign.service.IUserBasicService;
import com.itime.sign.utils.PassUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/sign_up", method = RequestMethod.POST)
public class SignUpController {

    private final IUserAccountService accountService;

    private final IUserBasicService basicService;

    @Autowired
    public SignUpController(IUserAccountService accountService, IUserBasicService basicService) {
        this.accountService = accountService;
        this.basicService = basicService;
    }

    @RequestMapping(value = "/email")
    public JsonResult email(@Valid String nickname, @Valid String email, @Valid String password) {

        JsonResult result;
        UserAccount account = accountService.findUserByEmail(email);
        if (account == null) {
            account = new UserAccount();
            account.setEmail(email);
            account.setEmail_confirmed(true);
            account.setPassword(PassUtils.getMD5(password));
            boolean r = accountService.saveUser(account);
            if (r) {
                account.setPassword(null);
                result = JsonResult.ok("注册成功！", account);
            } else {
                result = JsonResult.errorMsg("注册失败!");
            }

        } else {
            result = JsonResult.errorMsg("该邮箱已被注册!");
        }

        return result;
    }




}
