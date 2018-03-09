package domain;

import java.util.UUID;

public class Checkout {

    private UUID orderId;
    private UUID reservationId;
    private Product product;

    public UUID getOrderId() {
        return orderId;
    }

    public UUID getReservationId() {
        return reservationId;
    }

    public Product getProduct() {
        return product;
    }

    public Checkout(final UUID orderId, final UUID reservationId, final Product product) {
        this.orderId = orderId;
        this.reservationId = reservationId;
        this.product = product;
    }
}
