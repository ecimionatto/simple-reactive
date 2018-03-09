package domain;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import services.MerchService;
import services.ProductEmission;

import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class TestableProcessTest {

    @Mock
    private MerchService merchService;

    @Mock
    private ProductEmission productEmission;

    @InjectMocks
    private TestableProcess testableProcess;

    @Test
    public void whenObserverEmitsOneThenShouldContainOneProduct() {

        final Product productFixture = new Product(UUID.randomUUID(), UUID.randomUUID(), "test");

        final UUID productSku = UUID.randomUUID();
        given(productEmission.getProducts()).willReturn(Flowable.just(productSku));
        given(merchService.findBySkuId(productSku)).willReturn(Flowable.just(productFixture));

        final TestSubscriber<Product> testSubscriber = testableProcess.process().test();
        testSubscriber.assertComplete();
        testSubscriber.assertValue(productFixture);

        then(productEmission).should().getProducts();
        then(merchService).should().findBySkuId(productSku);

    }

    @Test
    public void whenObserverEmitsNoneThenSubscripberShouldReturnEmpty() {

        given(productEmission.getProducts()).willReturn(Flowable.empty());

        final TestSubscriber<Product> testSubscriber = testableProcess.process().test();
        testSubscriber.assertComplete();
        testSubscriber.assertNoValues();

        then(productEmission).should().getProducts();

    }



}