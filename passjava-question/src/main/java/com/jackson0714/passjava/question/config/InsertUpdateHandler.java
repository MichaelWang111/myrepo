package com.jackson0714.passjava.question.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 悟空聊架构
 * description TODO
 * date 2023/6/4 23:19
 * site www.passjava.cn
 * github https://github.com/Jackson0714
 */
@Component
public class InsertUpdateHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //根据名称设置属性值
        this.setFieldValByName("createTime",new Date(),metaObject);
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
