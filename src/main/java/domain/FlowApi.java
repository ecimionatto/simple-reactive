package domain;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

public class FlowApi {

    public void main(String[] args) {
        Flowable.fromCallable(() -> {
            Thread.sleep(1000); //  imitate expensive computation
            return "Done";
        })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.single())
                .subscribe(System.out::println, Throwable::printStackTrace);
    }
}
