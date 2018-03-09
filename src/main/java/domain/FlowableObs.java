package domain;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import services.InventoryService;
import services.MerchService;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class FlowableObs {

    private InventoryService inventoryService = new InventoryService();
    private MerchService merchService = new MerchService();
    private CheckoutRepository checkoutRepository = new CheckoutRepository();

    public Disposable process() {
        return receiveOrders().
                observeOn(Schedulers.io()).
                doOnNext(i -> System.out.println(Thread.currentThread().getName())).
                flatMap(id -> Flowable.zip(Flowable.just(id),
                        inventoryService.getReserationId(id),
                        merchService.findBySkuId(id),
                        (i, r, p) -> buildCheckout(i, r, p))).
                doOnNext(i -> System.out.println(Thread.currentThread().getName())).
                subscribe(checkout -> checkoutRepository.save(checkout));

    }

    private Flowable<Object> getZip(final UUID id) {
        return Flowable.zip(Flowable.just(id),
                inventoryService.getReserationId(id),
                merchService.findBySkuId(id),
                (i, r, p) -> buildCheckout(i, r, p));
    }

    private Checkout buildCheckout(final UUID i, final UUID r, final Product p) {
        System.out.println("building checkout object: " + i);
        return new Checkout(i, r, p);
    }

    private Flowable<UUID> receiveOrders() {
        System.out.println("receiving orders");
        return Flowable.range(1, 1_000).
                doOnNext(i -> System.out.print(i + ":")).
                map(i -> UUID.randomUUID()).
                doOnNext(System.out::println);
    }

    private Observable<Checkout> handleError(final Throwable throwable) {
        System.out.println("error: " + throwable.getMessage());
        return Observable.empty();
    }

    public static void main(String args[]) throws InterruptedException {
        new FlowableObs().process();
        TimeUnit.MINUTES.sleep(1_0);
    }

}
