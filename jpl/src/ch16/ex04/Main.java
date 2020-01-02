package ch16.ex04;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("ch16.ex04.AppliedAnnotation");
            Field[] fields = clazz.getDeclaredFields();
            Method[] methods = clazz.getDeclaredMethods();
            Constructor[] constructors = clazz.getDeclaredConstructors();

            System.out.println("-----Runtime annotation applied field-----");
            for (Field field : fields) {
                for (Annotation annotation : field.getAnnotations()) {
                    System.out.println(annotation);
                }
            }
            System.out.println("-----Runtime annotation applied method-----");
            for (Method method  : methods) {
                for (Annotation annotation : method.getAnnotations()) {
                    System.out.println(annotation);
                }
            }
            System.out.println("-----Runtime annotation applied constructor-----");
            for (Constructor constructor : constructors) {
                for (Annotation annotation : constructor.getAnnotations()) {
                    System.out.println(annotation);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
