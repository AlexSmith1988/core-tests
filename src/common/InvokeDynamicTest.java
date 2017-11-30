package common;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by AIKuznetsov on 23.11.2017.
 */
public class InvokeDynamicTest extends Rod
        implements Foo, Bar {

    public static void main(String[] args) throws Throwable {
        methodHandles();

        Double d = 10;

//        lambdaTest();
    }

    private static void lambdaTest() {
        final String param = "42".concat("42");
        Map<String, String> testMap = new HashMap<>();
        Function<String, String> func = key -> key + param;
        testMap.computeIfAbsent("TestKEy", func);
        testMap.computeIfAbsent("TestKEy2", func);
        System.out.println(testMap.get("TestKEy"));
    }

    private static void methodHandles() throws Throwable {
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        MethodHandle methodHandle = lookup.findStatic(InvokeDynamicTest.class, "hello", MethodType.methodType(void.class));

        methodHandle.invokeExact();
    }

    public static void hello() {
        System.out.println("Hello");
    }
}

interface Foo {

}

interface Bar {

}

class Rod {

}