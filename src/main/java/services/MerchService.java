package services;

import domain.Product;
import io.reactivex.Flowable;
import io.reactivex.Observable;

import java.util.UUID;

public class MerchService {

    public Flowable<Product> findBySkuId(UUID id) {
        return Flowable.just(new Product(UUID.randomUUID(), UUID.randomUUID(), "mock prod"));
    }

}
