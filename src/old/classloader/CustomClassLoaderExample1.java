package old.classloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.Class.forName;

/**
 * Created by ilya on 13.01.2017.
 */
public class CustomClassLoaderExample1 {
    public static void main(String[] args) throws Exception {
        final Object customLoadedObject1 = CustomClassLoader.testLoadClass();
        final Object customLoadedObject2 = CustomClassLoader.testLoadClass();

        System.out.println("diff classloaders:" + customLoadedObject1.equals(customLoadedObject2));

        ObjectToBeLoadedByCustomClassLoader l1 = new ObjectToBeLoadedByCustomClassLoader();
        ObjectToBeLoadedByCustomClassLoader l2 = new ObjectToBeLoadedByCustomClassLoader();
        System.out.println("same class:" + l1.equals(l2));

        l1 = new ObjectToBeLoadedByCustomClassLoader();
        l2 = new ObjectToBeLoadedByChildClassLoaderCustom();
        System.out.println("parent and child:" + l1.equals(l2));

        l1 = new ObjectToBeLoadedByChildClassLoaderCustom();
        l2 = new ObjectToBeLoadedByChildClassLoaderCustom();
        System.out.println("both childs:" + l1.equals(l2));
    }

    static final class CustomClassLoader extends ClassLoader {
        static Object testLoadClass() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
            ClassLoader customClassLoader = new CustomClassLoader();
            Class<?> clazz = forName(ObjectToBeLoadedByCustomClassLoader.class.getName(), true, customClassLoader);

            return clazz.newInstance();
        }

        CustomClassLoader() {
            super(CustomClassLoaderExample1.class.getClassLoader());
        }

        @Override
        protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
            if (!ObjectToBeLoadedByCustomClassLoader.class.getName().equals(name)) {
                return super.loadClass(name, resolve);
            }
            System.out.println();

            try {

                Path path = Paths.get("\\CustomClassLoaderExample1$ObjectToBeLoadedByCustomClassLoader.class");
                byte[] classBytes = Files.readAllBytes(path);
                Class<?> c = defineClass(name, classBytes, 0, classBytes.length);
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            } catch (IOException ex) {
                throw new ClassNotFoundException("Could not load " + name, ex);
            }
        }
    }

    public static class ObjectToBeLoadedByCustomClassLoader {

        private String testField = "testField";

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            ObjectToBeLoadedByCustomClassLoader loader = (ObjectToBeLoadedByCustomClassLoader) o;

            return testField != null ? testField.equals(loader.testField) : loader.testField == null;
        }

        @Override
        public int hashCode() {
            return testField != null ? testField.hashCode() : 0;
        }
    }

    public static class ObjectToBeLoadedByChildClassLoaderCustom extends ObjectToBeLoadedByCustomClassLoader {
    }


}
