package com.example.rxsample.decorator;

/**
 * 抽象构件
 */
public class ConcreteComponent extends Component{
    @Override
    public void operator() {
        System.out.println("doSomething");
    }
}