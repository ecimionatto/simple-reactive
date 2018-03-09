package domain;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SampleObs {

    public static void main(String args[]) throws InterruptedException {

        //simpleObservable().filter(word -> word.equals("no")).map(word -> word.toString().toUpperCase()).subscribe(hello -> System.out.println(hello));
        Observable.concat(
                    slowObserver().observeOn(Schedulers.io()),
                    fastObservable().observeOn(Schedulers.io()),
                    simpleObservable()).
                doOnNext(System.out::println).
                map(String::toUpperCase).
                buffer(10).
                subscribe(
                        word -> saveData(word)
                );

        TimeUnit.SECONDS.sleep(30l);

    }

    private static Observable<String> slowObserver() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Observable.range(1, 50).map(i -> i + "slow");
    }

    private static Observable<String> fastObservable() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return Observable.range(1, 50).map(index -> index + "fast");
    }

    private static void saveData(final List<String> word) {
        word.forEach(System.out::println);
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private static Observable<String> simpleObservable() {
        return Observable.create(s -> {
            s.onNext("Hello World!");
            s.onNext("Test!");
            s.onNext("no");

            s.onComplete();
        });
    }

}
