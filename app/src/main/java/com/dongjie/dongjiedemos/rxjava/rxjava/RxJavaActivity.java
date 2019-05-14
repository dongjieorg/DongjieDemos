package com.dongjie.dongjiedemos.rxjava.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.dongjie.dongjiedemos.R;
import com.dongjie.dongjiedemos.tools.LogUtils;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
public class RxJavaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
    }

    public void onClick1(View v) {
        /** 输出：
         2019-05-13 16:33:30.195 10924-10924/com.dongjie.dongjiedemos D/dongjiejie: onSubscribe
         2019-05-13 16:33:30.195 10924-10924/com.dongjie.dongjiedemos D/dongjiejie: Observable emit 1
         2019-05-13 16:33:30.195 10924-10924/com.dongjie.dongjiedemos D/dongjiejie: onNext1
         2019-05-13 16:33:30.195 10924-10924/com.dongjie.dongjiedemos D/dongjiejie: Observable emit 2
         2019-05-13 16:33:30.195 10924-10924/com.dongjie.dongjiedemos D/dongjiejie: onNext2
         2019-05-13 16:33:30.195 10924-10924/com.dongjie.dongjiedemos D/dongjiejie: Observable emit 3
         2019-05-13 16:33:30.195 10924-10924/com.dongjie.dongjiedemos D/dongjiejie: onNext3
         2019-05-13 16:33:30.195 10924-10924/com.dongjie.dongjiedemos D/dongjiejie: Observable emit 4
         2019-05-13 16:33:30.195 10924-10924/com.dongjie.dongjiedemos D/dongjiejie: onNext4
         019-05-13 16:33:30.195 10924-10924/com.dongjie.dongjiedemos D/dongjiejie: onComplete
         */
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                LogUtils.showLog("Observable emit 1");
                e.onNext(1); // 回调onNext()方法
                LogUtils.showLog("Observable emit 2");
                e.onNext(2); // 回调onNext()方法
                LogUtils.showLog("Observable emit 3");
                e.onNext(3); // 回调onNext()方法
                LogUtils.showLog("Observable emit 4");
                e.onNext(4); // 回调onNext()方法
                e.onComplete(); // 回调onComplete()方法，不写这一句onComplete不会自动执行
            }
        }).subscribe(new Observer<Integer>() {
            Disposable mDisposable;
            @Override
            public void onSubscribe(Disposable d) {
                mDisposable = d;
                LogUtils.showLog("onSubscribe");
            }

            @Override
            public void onNext(Integer integer) {
                int i = integer;
                if (i == 3) {
                    // 解除事件订阅， 解除后后面发送的事件都收不到了，包括完成和error也收不到了
//                    mDisposable.dispose();
                }
                LogUtils.showLog("onNext" + i);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.showLog("error:" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.showLog("onComplete");
            }
        });
    }

    public void onClick2(View v) {
        // .subscribeOn(Schedulers.newThread())  // subscribeOn指定被观察者（上面的subscribe方法）在哪个线程执行，指定多次第一次有效， 后面的没作用
        // .observeOn(AndroidSchedulers.mainThread()) // 指定下面被观察者接收事件的方法在哪个线程执行， 每次调用下面的回调方法就会切一次线程
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final ObservableEmitter<Integer> e) throws Exception {
                LogUtils.showLog("Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread()) // 上面subscribe方法在新线程中执行
                .observeOn(AndroidSchedulers.mainThread()) // 下面的回调事件在主线程中运行
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        LogUtils.showLog("Consumer thread is： " + Thread.currentThread().getName());
                    }
                });

        /**上面被观察者在子线程中运行， 下面观察者（消费者）在主线程中运行
         2019-05-14 11:37:37.172 22821-23661/com.dongjie.dongjiedemos D/dongjiejie: Observable thread is : RxNewThreadScheduler-1
         2019-05-14 11:37:37.182 22821-22821/com.dongjie.dongjiedemos D/dongjiejie: Consumer thread is： main
         */
    }

    public void onClick3(View v) {
        // .subscribeOn(Schedulers.newThread())  // subscribeOn指定被观察者（上面的subscribe方法）在哪个线程执行，指定多次第一次有效， 后面的没作用
        // .observeOn(AndroidSchedulers.mainThread()) // 指定下面接收事件的方法在哪个线程执行， 每次调用下面的回调方法就会切一次线程
        // doOnNext()的accept()方法仅仅只是在Observer的onXXX()方法被调用之前调用, 没有与Observer的调用之间没有任何关系, 所以doOnNext()这个方法可以用来在观察者Observer:onXXX()方法被调用之前进行一些初始化操作
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(final ObservableEmitter<Integer> e) throws Exception {
                LogUtils.showLog("Observable thread is : " + Thread.currentThread().getName());
                e.onNext(1);
                Thread.sleep(2000); // 两秒后执行下面两句代码
                e.onNext(2);// 每次发送事件的时候，doOnNext都要优先subscribe里的事件执行一遍，而且每次发送事件，下面设置事件（设置切换线程等）都要重新执行一遍
                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread()) // 上面subscribe方法在新线程中执行
          .subscribeOn(Schedulers.io()) // 这个没起作用，subscribeOn第一次调用起作用， 后面不起作用
          .observeOn(AndroidSchedulers.mainThread()) // 下面的回调事件在主线程中运行
          .doOnNext(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Exception {
                    LogUtils.showLog("After observeOn(mainThread)，Current thread is： " + Thread.currentThread().getName());
                }
           })
           .observeOn(Schedulers.io())
           .subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Exception {
                    LogUtils.showLog("After observeOn(io)，Current thread is： " + Thread.currentThread().getName());
                }
           });

        /**第一次发射事件后下面doOnNext和accept事件已经切到了io子线程中，第二次发射这些切换线程的代码重新执行了， 说明发射的时候代码从上到下重新执行了一遍
         2019-05-13 17:41:41.089 30465-31148/com.dongjie.dongjiedemos D/dongjiejie: Observable thread is : RxNewThreadScheduler-1
         2019-05-13 17:41:41.096 30465-30465/com.dongjie.dongjiedemos D/dongjiejie: After observeOn(mainThread)，Current thread is main
         2019-05-13 17:41:41.097 30465-31150/com.dongjie.dongjiedemos D/dongjiejie: After observeOn(io)，Current thread is RxCachedThreadScheduler-2
         两秒后输出：
         2019-05-13 17:41:43.092 30465-30465/com.dongjie.dongjiedemos D/dongjiejie: After observeOn(mainThread)，Current thread is main
         2019-05-13 17:41:43.092 30465-31150/com.dongjie.dongjiedemos D/dongjiejie: After observeOn(io)，Current thread is RxCachedThreadScheduler-2
         */
    }
}
