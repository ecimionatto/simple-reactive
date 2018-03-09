package domain;

import io.reactivex.Completable;

import java.util.concurrent.TimeUnit;

public class CheckoutRepository {

    public Completable save(Checkout checkout) {
        //checkout.delay(3, TimeUnit.SECONDS).forEach(c -> System.out.println("saving: " + c.getOrderId()));
        System.out.println("saving: " + checkout.getOrderId());
        //try {
        //    TimeUnit.SECONDS.sleep(1l);
        //} catch (InterruptedException e) {
        //    throw new RuntimeException(e);
        //}
        return Completable.never();
    }
}
