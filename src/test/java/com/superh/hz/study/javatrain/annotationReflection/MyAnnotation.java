package com.superh.hz.study.javatrain.annotationReflection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation
{

    String value() default "hello";
    String hello() default "shengqishi";
    String world();
    //不加（）就出错了
    //属性名字叫作value时可以不加名字直接赋值
    //属性名字不叫作value时给属性赋值必须显式指定名字
    //可以通过default关键字设置默认值
}
