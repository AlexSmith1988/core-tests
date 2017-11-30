package optionals;

import static java.util.Optional.ofNullable;

/**
 * Created by AIKuznetsov on 13.11.2017.
 */
public class OptionalsTest {

    public static void main(String[] args) {

        class T {
            int doCalculation() {
                return 42;
            }
        }
        class D {
            private final T t;

            D(T t) {
                this.t = t;
            }

            public T getT() {
                return t;
            }
        }
        class B {
            private final D d;

            B(D d) {
                this.d = d;
            }

            public D getD() {
                return d;
            }
        }

        B b = new B(new D(new T()));
        ofNullable(b)
                .map(B::getD)
                .map(D::getT)
                .map(T::doCalculation)
                .ifPresent(System.out::println);
    }
}
