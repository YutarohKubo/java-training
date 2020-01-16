package ch16.ex02;

import java.io.PrintStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TypeDesc {
    public static void main(String[] args) {
        TypeDesc desc = new TypeDesc();
        for (String name : args) {
            try {
                Class<?> startClass = Class.forName(name);
                desc.printType(startClass, 0, basic);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private PrintStream out = System.out;

    private static String[] basic = {"class", "interface", "enum", "annotation"},
            supercl = {"extends", "implements"},
            iFace = {null, "extends"};

    private void printType(Type type, int depth, String[] labels) {
        if (type == null || type.getTypeName().equals("java.lang.Object")) {
            return;
        }
        Class<?> cls = null;
        if (type instanceof Class<?>) {
            cls = (Class<?>) type;
        } else if (type instanceof ParameterizedType) {
            cls = (Class<?>) ((ParameterizedType) type).getRawType();
        } else {
            throw new Error("Unexpected non-class type");
        }
        for (int i = 0; i < depth; i++) {
            out.print(" ");
        }
        int kind = cls.isAnnotation() ? 3 :
                cls.isEnum() ? 2 :
                        cls.isInterface() ? 1 : 0;
        out.print(labels[kind] + " ");
        out.print(cls.getCanonicalName());

        TypeVariable<?>[] params = cls.getTypeParameters();
        if (params.length > 0) {
            out.print('<');
            for (TypeVariable<?> param : params) {
                out.print(param.getName());
                out.print(", ");
            }
            out.println("/b/b>");
        } else {
            out.println();
        }

        Class<?> enclosingClass = cls.getEnclosingClass();
        while (enclosingClass != null) {
            for (int i = 0; i < depth; i++) {
                out.print(" ");
            }
            out.print("is nested by " + enclosingClass.getCanonicalName());
            out.println();
            enclosingClass = enclosingClass.getEnclosingClass();
        }
        Type[] interfaces = cls.getGenericInterfaces();
        for (Type iface : interfaces) {
            printType(iface, depth + 1, cls.isInterface() ? iFace : supercl);
        }
        //superクラスは1つのみなので、for文内に記載する必要はない
        printType(cls.getGenericSuperclass(), depth + 1, supercl);
    }
}