package domain;

import io.reactivex.Flowable;
import services.MerchService;
import services.ProductEmission;

public class TestableProcess {

    private ProductEmission productEmission;
    private MerchService merchService;

    public Flowable<Product> process() {
        return productEmission.
                getProducts().flatMap( sku -> merchService.findBySkuId(sku));
    }
}
