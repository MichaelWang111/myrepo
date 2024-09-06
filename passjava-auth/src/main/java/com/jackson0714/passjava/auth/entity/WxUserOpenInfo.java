package com.jackson0714.passjava.auth.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author 悟空聊架构
 * @description TODO
 * @date 2023/6/14 06:58
 * @site www.passjava.cn
 * @github https://github.com/Jackson0714
 */
@Data
public class WxUserOpenInfo {
    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("expires_in")
    private int expiresIn;

    @JsonProperty("openid")
    private String openid;

    @JsonProperty("unionid")
    private String unionid;
}
