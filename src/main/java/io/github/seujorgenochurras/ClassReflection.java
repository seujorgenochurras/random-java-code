package io.github.seujorgenochurras;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.stream.Collectors;

public class ClassReflection {
    public static void main(String[] args) {
        System.out.println(getClassesInsidePackage("io.github.seujorgenochurras"));
    }
    public static Set<Class<?>> getClassesInsidePackage(String packageName){
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replace('.', '/'));
        assert stream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

        return reader.lines()
                .filter(fileName -> fileName.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    public static Class<?> getClass(String className, String packageName){
        try {
            return Class.forName(packageName + "." + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
