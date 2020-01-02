package ch16;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class SimpleClassDesc {
    public static void main(String[] args) {
        Class type = null;
        try {
            type = Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("class" + type.getSimpleName());
        Class superClass = type.getSuperclass();
        if (superClass != null) {
            System.out.println("extends " + superClass.getCanonicalName());
        } else {
            System.out.println();
        }
        Method[] methods = type.getDeclaredMethods();
        for (Method m : methods) {
            if (Modifier.isPublic(m.getModifiers())) {
                System.out.println(" " + m);
            }
        }
    }
}
