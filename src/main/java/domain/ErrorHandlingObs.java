package domain;

import io.reactivex.Observable;

public class ErrorHandlingObs {

    public static void main(String args[]) throws InterruptedException {

        Observable.just("TEST").buffer(10).
                doOnNext(System.out::println).
                map((a) -> {
                    throw new IllegalStateException("error mapping");}
                ).retry(5)
                .subscribe((s) -> System.out.println("subscribed: " + s));

    }

}
