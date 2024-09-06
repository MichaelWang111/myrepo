package com.jackson0714.passjava.member.api.feign;

import com.baomidou.mybatisplus.extension.api.R;
import com.jackson0714.passjava.member.api.entity.MemberEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wukong
 */
@FeignClient("passjava-member")
public interface MemberFeignService {
    @RequestMapping("member/member/save")
    R<MemberEntity> createMember(@RequestBody MemberEntity member);
}
