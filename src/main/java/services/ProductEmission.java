package services;

import io.reactivex.Flowable;

import java.util.UUID;

public class ProductEmission {

    public Flowable<UUID> getProducts() {
        return Flowable.range(1, 100).map( i-> UUID.randomUUID());
    }
}
