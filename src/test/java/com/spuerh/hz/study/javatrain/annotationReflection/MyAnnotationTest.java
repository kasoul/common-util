package com.spuerh.hz.study.javatrain.annotationReflection;


@MyAnnotation(hello="beijing",world="tianjin")
public class MyAnnotationTest
{

    @MyAnnotation(hello="shanghai",world="guangzhou")
    @Deprecated
    public void outputvalue()
    {
        System.out.println("Output something.");
        System.out.println(Color.GREEN);
    }
    
}