package io.renren.modules.sys.controller;

import com.baomidou.mybatisplus.extension.api.R;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.form.SysLoginForm;
import io.renren.modules.sys.service.SysCaptchaService;
import io.renren.modules.sys.service.SysUserService;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 悟空聊架构
 * @description TODO
 * @date 2023/6/17 16:16
 * @site www.passjava.cn
 * @github https://github.com/Jackson0714
 */
public class LoginUserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysCaptchaService sysCaptchaService;

    @PostMapping("/sys/valid/captcha")
    public R<Boolean> validCaptcha(@RequestBody SysLoginForm form) {
        boolean captcha = sysCaptchaService.validate(form.getUuid(), form.getCaptcha());
		if(!captcha){
			return R.failed("验证码不正确");
		}
        return R.ok(true);
    }

    @PostMapping("/sys/valid/user")
    public R<Boolean> validUser(@RequestBody SysLoginForm form) {
        //用户信息
		SysUserEntity user = sysUserService.queryByUserName(form.getUsername());

		//账号不存在、密码错误
		if(user == null || !user.getPassword().equals(new Sha256Hash(form.getPassword(), user.getSalt()).toHex())) {
			return R.failed("账号或密码不正确");
		}

		//账号锁定
		if(user.getStatus() == 0){
			return R.failed("账号已被锁定,请联系管理员");
		}

        return R.ok(true);
    }

}
