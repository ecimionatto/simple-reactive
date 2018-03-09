package services;

import io.reactivex.Flowable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class InventoryService {

    public Flowable<UUID> getReserationId(UUID skuId) {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Flowable.just(UUID.randomUUID());
    }
}
