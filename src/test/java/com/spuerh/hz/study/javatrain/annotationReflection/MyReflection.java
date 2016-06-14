package com.spuerh.hz.study.javatrain.annotationReflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

//通过反射获取注解信息
public class MyReflection
{

    public static void main(String[] args) throws Exception
    {
    	MyAnnotationTest myTest = new MyAnnotationTest();

        // 获取Class对象
        Class<MyAnnotationTest> c = MyAnnotationTest.class;
        // 获取Method对象
        Method method = c.getMethod("outputvalue", new Class[] {});

        // 判断是否存在指定类型的注解
        if (method.isAnnotationPresent(MyAnnotation.class))
        {
            // 如果存在该类型注解，这执行这个方法
            method.invoke(myTest, new Object[] {});

          // 如果MyAnnotation前面是@Retention(RetentionPolicy.RUNTIME)，则执行
            // 如果MyAnnotation前面是@Retention(RetentionPolicy.CLASS)，或SOURCE,则括号内语句不执行
            // 因为只有为RUNTIME时，注解信息会被读取过来，其他两种情况注解不能被反射读取过来

            // 返回注解
            MyAnnotation myAnnotation = method
                    .getAnnotation(MyAnnotation.class);

            // 获取并打印注解中的信息
            String hello = myAnnotation.hello();
            String world = myAnnotation.world();
            System.out.println(hello + ", " + world);
        }

        // 获取方法的全部的Annotation
        Annotation[] annotations = method.getAnnotations();
        for (Annotation annotation : annotations)
        {
            System.out.println(annotation.annotationType().getName());
        }
        // 运行结果为：
        // com.learnjava.annotation.MyAnnotation
        // java.lang.Deprecated
        // 因为只能获取到RetentionPolicy为RUNTIME的注解

    }
}