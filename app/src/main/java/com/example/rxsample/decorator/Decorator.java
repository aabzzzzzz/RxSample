package com.example.rxsample.decorator;

/**
 * 抽象装饰者
 */
public abstract class Decorator extends Component{
    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    // 委托给被修饰者执行
    @Override
    public void operator() {
        this.component.operator();
    }
}
