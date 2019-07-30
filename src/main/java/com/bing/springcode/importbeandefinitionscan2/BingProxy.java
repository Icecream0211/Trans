package com.bing.springcode.importbeandefinitionscan2;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class BingProxy<T> implements InvocationHandler, Serializable {
    private final Class<T> bingInterface;

    public BingProxy(Class<T> bingInterface) {
        this.bingInterface = bingInterface;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        System.out.println("调用前，参数：{}" + args);
        //这里可以得到参数数组和方法等，可以通过反射，注解等，进行结果集的处理
        //mybatis就是在这里获取参数和相关注解，然后根据返回值类型，进行结果集的转换
        Object result = JSON.toJSONString(args);
        System.out.println("调用后，结果：{}" + result);
        return result;
    }
}

