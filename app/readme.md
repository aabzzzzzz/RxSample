1. Rxjava有哪些优势?

具备响应式编程该有的特性
为异步而生, 无需手动创建线程, 并具备线程切换能力.
链式调用, 保证代码简洁
各种操作符, 功能非常强大, 满足各种业务需求.
简化异常处理.

2. Rxjava适用场景? 

网络请求, 数据库读写, 文件读写, 定时任务.. 等
各种耗时操作需要通过异步来完成的操作, 都可以使用Rxjava;

3. Rxjava 是一种特殊的观察者模式, 观察者, 被观察者, 订阅. 

操作符详细介绍:
// https://juejin.cn/post/6844903617124630535
// RxJava2 只看这一篇文章就够了

// https://www.cnblogs.com/coprince/p/8603492.html
// java 泛型详解-绝对是对泛型方法讲解最详细的，没有之一 

// https://blog.csdn.net/qq_38261312/article/details/89433899
// RxJava--一图胜千言

// https://blog.csdn.net/qq_38261312/article/details/89434557
// 为什么subscribeOn第一次执行生效，observeOn每次执行都生效？

// https://juejin.cn/post/6903053077486862344
// Rxjava2 compose

// https://juejin.cn/post/6893870636733890574
// Jetpack AAC完整解析（一）Lifecycle 完全掌握！

// https://blog.csdn.net/weixin_42814000/article/details/105956035
// Android RxJava2.0：4种Subject


4. 掌握3个流概念, 搞定Rxjava架构设计
    1. 链式构建流
        Rxjava使用了装饰器模式, 所以每次新的被观察的构建都是基于上一个被观察者的,
        也就是支持了上游的被观察者的引用.
        
        每次创建被观察者, 也会创建新的观察者, 而这个观察者同样的持有下游观察者的引用.
        
        source -> Observable1 -> Observable2 -> Observable3 -> ...
        
        Observable1 = Observable1(source);
        Observable2 = Observable2(Observable1);
        Observable3 = Observable3(Observable2);
        .....
    2. subscribeActual订阅流
        subscribe -> 
        Observer0 = Observer0();
        Observable3.subscribe(Observer0);
        ->
        Observer1 = Observer1(Observer0);
        Observable2.subscribe(Observer1);
        ->
        Observer2 = Observer2(Observer1);
        Observable1.subscribe(Observer2);
        ->
        Observer3 = Observer3(Observer2);
        Observable0.subscribe(Observer3);
        ....
    3. 观察者回调流
        emitter.onNext();//发射.
        -> Observer3.onNext();
        -> Observer2.onNext();
        -> Observer1.onNext();
        -> Observer0.onNext();// 自己实现的要处理业务的观察者.

5. rxjava的线程调度. 
    1. subscribeOn 只有一个生效 (最上游的生效)
    2. observerOn 只会影响下游的观察者处理事件? 
    
6. rxjava的内存泄漏.
    1. dispose;
    2. 