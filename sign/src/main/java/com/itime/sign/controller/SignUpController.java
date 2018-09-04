package com.itime.sign.controller;


import com.itime.sign.pojo.JsonResult;
import com.itime.sign.pojo.UserAccount;
import com.itime.sign.pojo.UserBasic;
import com.itime.sign.service.IUserAccountService;
import com.itime.sign.service.IUserBasicService;
import com.itime.sign.utils.PassUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

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

    /*
     * @Author shuqiang
     * @Desc 判断闲时号是否存在
     * @Date 2018/8/24 下午3:47
     */
    @RequestMapping("/isItCode")
    public JsonResult isItCode(@Valid String itCode, HttpServletRequest request) {
        JsonResult result = null;
        UserAccount account = null;
        Map<String, Object> rMap = new HashMap<>();
        rMap.put("r", 1);

        if (!itCode.equals("") && !itCode.equals(null)) {
            account = accountService.findUserByItCode(itCode);
            if (account != null) {
                result = JsonResult.ok("闲时号已被注册", java.util.Optional.of(0));
            } else {
                result = JsonResult.ok(1);
            }
        } else {
            result = JsonResult.ok("请输入闲时号", java.util.Optional.of(0));
        }
        return result;
    }

    /*
     * @Author shuqiang
     * @Desc 注册
     * @Params type: 0邮箱，1手机；account：邮箱/手机号；
     * @Date 2018/8/27 下午6:36
     */
    @RequestMapping(value = "/registered")
    public JsonResult email(@Valid Integer type, @Valid String account, @Valid String password, @Valid String it_code, HttpServletRequest request) {

        JsonResult result = null;

        if (type != null) {

            //通过闲时号 it_code 获取用户
            UserAccount user = accountService.findUserByItCode(it_code);

            //判断用户是否已存在
            if (user == null) {
                //通过email或mobile 获取用户
                user = (type == 0) ? accountService.findUserByEmail(account) : accountService.findUserByMobile(account);
                //判断用户是否已存在
                if (user == null) {
                    //初始化userAccount
                    user = new UserAccount();
                    if (type == 0) {
                        //绑定Email
                        user.setEmail(account);
                        user.setEmail_confirmed(true);
                    } else {
                        //绑定Mobile
                        user.setMobile(account);
                        user.setMobile_confirmed(true);
                    }
                    //设置密码，闲时号并保存
                    user.setPassword(PassUtils.getMD5(password));
                    user.setIt_code(it_code);
                    boolean r = accountService.saveUser(user);
                    if (r) {
                        //添加一条用户基本信息
                        UserBasic basic = new UserBasic();
                        basic.setIt_code(it_code);
                        r = basicService.saveBasic(basic);
                        if (r) {
                            result = JsonResult.ok("注册成功！", user);
                            HttpSession session = request.getSession();
                            session.setAttribute("user", account);
                        } else
                            result = JsonResult.errorMsg("添加基本信息失败!");
                    } else {
                        result = JsonResult.errorMsg("注册失败!");
                    }
                } else {
                    result = type == 0 ? JsonResult.errorMsg("该邮箱已被注册!") : JsonResult.errorMsg("该手机号已被注册!");
                }
            } else {
                result = JsonResult.errorMsg("该闲时号已被注册!");
            }
        } else {
            result = JsonResult.errorMsg("系统参数异常!");
        }
        return result;
    }


}
