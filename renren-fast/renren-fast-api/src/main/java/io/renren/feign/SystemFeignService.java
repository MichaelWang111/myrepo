package io.renren.feign;

import com.baomidou.mybatisplus.extension.api.R;
import io.renren.modules.sys.form.SysLoginForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wukong
 */
@FeignClient("renren-fast")
public interface SystemFeignService {
    @RequestMapping("/sys/valid/captcha")
    R<Boolean> validCaptcha(@RequestBody SysLoginForm form);

    @PostMapping("/sys/valid/user")
    R<Boolean> validUser(@RequestBody SysLoginForm form);
}
