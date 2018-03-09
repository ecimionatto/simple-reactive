package domain;

import java.util.UUID;

public class Product {

    private UUID id;
    private UUID skuId;
    private String  name;

    public Product(final UUID id, final UUID skuId, final String name) {
        this.id = id;
        this.skuId = skuId;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public UUID getSkuId() {
        return skuId;
    }

    public String getName() {
        return name;
    }


}
