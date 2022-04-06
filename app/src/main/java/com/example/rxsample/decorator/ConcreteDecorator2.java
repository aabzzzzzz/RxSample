package com.example.rxsample.decorator;

public class ConcreteDecorator2 extends Decorator{
    // 定义被修饰者
    public ConcreteDecorator2(Component component){
        super(component);
    }

    // 定义自己的修饰方法
    public void method2(){
        System.out.println("修饰方法 method2");
    }
    @Override
    public void operator() {
        this.method2();
        super.operator();
    }
}
