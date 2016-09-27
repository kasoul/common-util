package com.superh.hz.study.javatrain.annotationReflection;

public enum Color {  
	//必须先定义enum实例序列，再定义成员变量和成员方法
    RED("红色", 1), GREEN("绿色", 2), BLACK("白色", 3), YELLOW("黄色", 4);  
    // 成员变量  
    private String name;  
    private int index;  
    // 构造方法  
    private Color(String name, int index) {  
        this.name = name;  
        this.index = index;  
    }  
    //覆盖方法  
    @Override  
    public String toString() {  
        return this.index+"_"+this.name;  
    }  
}  