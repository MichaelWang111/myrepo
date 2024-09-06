package com.jackson0714.passjava.auth.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 悟空聊架构
 * @description TODO
 * @date 2024/7/4
 * @site www.passjava.cn
 * @github https://github.com/Jackson0714
 */
@Data
public class UserInfo {
    /**
    在Java中，声明一个集合时，可以使用接口类型作为变量的类型。这种做法有几个优点：
    面向接口编程：使用接口类型（如List<String>）声明变量，而不是具体实现类（如ArrayList<String>），可以使代码更灵活，更容易维护。如果以后需要更改集合的实现（例如从ArrayList更改为LinkedList），只需要修改实例化的部分，而不需要修改变量的声明和使用的地方。
    多态性：通过接口类型，可以赋值为任何实现该接口的对象。例如，List<String> roles可以赋值为一个ArrayList，也可以赋值为一个LinkedList，而不需要改变变量的声明。
    因此，List<String> roles = new ArrayList<>() 是一种更通用、更灵活的声明方式，符合面向接口编程的原则。而 ArrayList<String> roles = new ArrayList<>() 则将变量限制为只能使用 ArrayList 实现。
    */
    List<String > roles;
    String name;
    String avatar;
    String introduction;
}
