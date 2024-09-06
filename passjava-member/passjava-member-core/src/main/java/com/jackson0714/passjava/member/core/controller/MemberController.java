package com.jackson0714.passjava.member.core.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.jackson0714.passjava.common.utils.PageUtils;
import com.jackson0714.passjava.common.utils.SecurityUtils;
import com.jackson0714.passjava.common.utils.ServletUtils;
import com.jackson0714.passjava.jwt.utils.PassJavaJwtTokenUtil;
import com.jackson0714.passjava.member.api.entity.MemberEntity;
import com.jackson0714.passjava.member.api.feign.StudyTimeFeignService;
import com.jackson0714.passjava.member.core.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Map;


/**
 * 会员-会员表
 *
 * @author 悟空聊架构
 * site www.passjava.cn
 * @date 2020-04-15 17:42:00
 */
@RestController
@RequestMapping("member/member")
public class MemberController {

    @Autowired
    private PassJavaJwtTokenUtil jwtTokenUtil;

    @Autowired
    private MemberService memberService;

    @Autowired
    private StudyTimeFeignService studyTimeFeignService;

    /**
     * 通过网关拿到 token 中的 userId，然后根据 userId 查询用户信息
     * @return
     */
    @RequestMapping("/userinfo")
    public R<MemberEntity> info(){
        // 方式一: 从 request 里面，推荐方式二
        HttpServletRequest request = ServletUtils.getRequest();
        String userId = jwtTokenUtil.getUserIdFromRequest(request);

        // 方式二：从线程里面拿，依赖自定义拦截器
        String userId2 = SecurityUtils.getUserId();
        MemberEntity member = memberService.getMemberByUserId(userId2);

        return R.ok(member);
    }

    @RequestMapping("/studytime/list/test/{id}")
    public R<String> getMemberStudyTimeListTest(@PathVariable("id") Long id) {
        // mock数据库查到的会员信息
        MemberEntity memberEntity = new MemberEntity();
        // 学习时长：100分钟
        memberEntity.setId(id);
        memberEntity.setNickname("悟空聊架构");

        //远程调用拿到该用户的学习时长（学习时长是mock数据）
        //R memberStudyTimeList = studyTimeFeignService.getMemberStudyTimeListTest(id);
        return R.ok("");
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("member:member:list")
    public R<PageUtils> list(@RequestParam Map<String, Object> params){
        PageUtils page = memberService.queryPage(params);

        return R.ok(page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("member:member:info")
    public R<MemberEntity> info(@PathVariable("id") Long id){
		MemberEntity member = memberService.getById(id);

        return R.ok(member);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("member:member:save")
    public R<MemberEntity> save(@RequestBody MemberEntity member){
		memberService.save(member);

        return R.ok(member);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("member:member:update")
    public R<Boolean> update(@RequestBody MemberEntity member){
		memberService.updateById(member);

        return R.ok(true);
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("member:member:delete")
    public R<Boolean> delete(@RequestBody Long[] ids){
		memberService.removeByIds(Arrays.asList(ids));

        return R.ok(true);
    }

    @RequestMapping("/createMember")
    public R<Boolean> createMember(@RequestBody MemberEntity member) throws Exception {
        // 执行create 逻辑

        // 调用营销系统，发放优惠券
        memberService.sendCoupon(1);

        return R.ok(true);
    }
}
