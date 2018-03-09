package domain;

import com.sun.java.swing.plaf.windows.WindowsTreeUI;
import io.reactivex.Observable;

import java.util.stream.Stream;

public class SimpleObs {

    public static void main(String args[]) throws InterruptedException {

        Observable.range(1, 10).
                doOnNext(s -> System.out.println("mapping: " + s)).
                map(a -> a * 10)
                .subscribe(s -> System.out.println("subscribe: " + s));



    }

}
