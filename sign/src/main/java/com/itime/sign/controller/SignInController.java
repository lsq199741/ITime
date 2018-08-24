package com.itime.sign.controller;

import com.itime.sign.pojo.UserAccount;
import com.itime.sign.service.IUserAccountService;
import com.itime.sign.pojo.JsonResult;
import com.itime.sign.utils.PassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/sign_in")
public class SignInController {

    private final IUserAccountService userAccountService;

    @Autowired
    public SignInController(IUserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @RequestMapping(value = "/email")
    public JsonResult loginByEmail(@Valid String email, @Valid String password) {
        UserAccount account = null;
        try {
            account = userAccountService.findUserByEmail(email);
        } catch (Exception e) {
            return JsonResult.errorMsg("系统异常");
        }

        if (account != null) {
            //获取MD5密码
            password = PassUtils.getMD5(password);
            //判断密码是否相同
            boolean r = password.equals(account.getPassword());
            if (!r) {
                return JsonResult.errorMsg("兄弟你输入的东西好像有些问题,要不再试试～");
            } else {
                account.setPassword("");
                return JsonResult.ok(account);
            }
        } else {
            return JsonResult.errorMsg("小伙子你在干啥,有没有注册你心里没点嗯..数嘛～");
        }
    }




}
