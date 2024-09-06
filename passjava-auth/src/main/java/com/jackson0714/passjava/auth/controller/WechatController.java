package com.jackson0714.passjava.auth.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.jackson0714.passjava.auth.entity.WxUserOpenInfo;
import com.jackson0714.passjava.auth.jpa.SysUserRepository;
import com.baomidou.mybatisplus.extension.api.R;
import com.jackson0714.passjava.jwt.common.ResponseResult;
import com.jackson0714.passjava.jwt.config.PassJavaJwtProperties;
import com.jackson0714.passjava.jwt.utils.PassJavaJwtTokenUtil;
import com.jackson0714.passjava.member.api.entity.MemberEntity;
import com.jackson0714.passjava.member.api.feign.MemberFeignService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 获取 JWT 令牌和刷新 JWT 令牌接口
 *
 * @author 悟空聊架构
 * site www.passjava.cn
 * @date 2023-06-05
 *
 *  @startuml
 *  @enduml
 */
@RestController
@RequestMapping("/auth/app")
//@ConditionalOnProperty(name = "passjava.gateway.jwt.useDefaultController", havingValue = "true")
public class WechatController {

    private final String CODE_OPENID_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Resource
    private PassJavaJwtProperties jwtProperties;
    @Resource
    private SysUserRepository sysUserRepository;
    @Resource
    private PassJavaJwtTokenUtil jwtTokenUtil;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private MemberFeignService memberFeignService;

    /**
     * 通过 code 换取 openid
     *
     * 小程序缓存 token，如果 token 存在，则请求携带 token，网关对 token 做验证。
     * 如果小程序本地没有 token，则用微信 wx.login 获取的 code 换取 openid，如果用户表存在这个 openid，则颁发 token。
     * 如果不存在这个 openid，则创建用户，用户信息：openid，id
     */
    @GetMapping("/code2openid")
    public R<?> code2openid(@RequestParam Map<String, Object> params) throws Exception {

        // 1.通过 code 换取 openid
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", "wx08bacac66e424d72");
        paramMap.put("secret", "1381dd40a5c27ba509b65bc3f592137e");
        paramMap.put("js_code", params.get("code"));
        paramMap.put("grant_type", "authorization_code");
        String result = HttpUtil.get(CODE_OPENID_URL, paramMap);
        WxUserOpenInfo wxUserOpenInfo = JSONUtil.toBean(result, WxUserOpenInfo.class);

        // 2.创建用户
        MemberEntity member = new MemberEntity();
        member.setMpOpenid(wxUserOpenInfo.getOpenid());
        member.setUnionid(wxUserOpenInfo.getUnionid());
        R<MemberEntity> memberResult = memberFeignService.createMember(member);

        if(memberResult.getData() == null || memberResult.getData().getId() == null) {
            return R.failed("创建微信用户失败");
        }

        // 3.颁发 token
        // 通过 jwtTokenUtil 生成 JWT 令牌和刷新令牌
        Map<String, Object> tokenMap = jwtTokenUtil.generateTokenAndRefreshToken(member.getMpOpenid(), "");
        return R.ok(tokenMap);
    }



}
