package ch16.ex05;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class ClassContents {

    private static Set<String> memberSet = new HashSet<>();

    public static void main(String[] args) {
        try{
            Class<?> c = Class.forName(args[0]);
            System.out.println(c);
            //Baseのクラスは、privateメンバも含め、すべて表示
            printMembers(c.getDeclaredFields());
            printMembers(c.getDeclaredConstructors());
            printMembers(c.getDeclaredMethods());
            Class<?> superClazz = (Class<?>) c.getGenericSuperclass();
            while (superClazz != null) {
                //superクラスはpublicメンバのみ表示
                printMembers(superClazz.getFields());
                printMembers(superClazz.getConstructors());
                printMembers(superClazz.getMethods());
                superClazz = (Class<?>) superClazz.getGenericSuperclass();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /*System.out.println("---------------------------------------");
        printMemberSet();*/
    }

    private static void printMembers (Member[] mems) {
        for (Member m : mems) {
            Class<?> declaringClass = m.getDeclaringClass();
            if (declaringClass == Object.class) {
                continue;
            }
            String decl = m.toString();
            //コンストラクタは修飾子+パッケージ名も含めて、既に子クラスで出現したかの判定する
            //(オーバーライドができないため)
            //フィールドやメソッドは、単純なメソッド名+引数名で既に子クラスで出現したかの判定する
            //(private -> publicのようなオーバーライドが可能なため、
            //そのようなときはオーバーライドした子クラスのメソッドのみ表示する)
            String simpleMemberName = strip(decl, ".*" + declaringClass.getCanonicalName() + "\\.");
            if (!memberSet.contains(simpleMemberName)) {
                memberSet.add(simpleMemberName);
                System.out.print(" ");
                System.out.println(strip(decl, "java\\.lang\\."));
                if (m instanceof Field) {
                    for (Annotation annotation : ((Field) m).getAnnotations()) {
                        System.out.print(" ->");
                        System.out.println("Applied annotations are " + annotation);
                    }
                }
                if (m instanceof Constructor) {
                    for (Annotation annotation : ((Constructor) m).getAnnotations()) {
                        System.out.print(" ->");
                        System.out.println("Applied annotations are " + annotation);
                    }
                }
                if (m instanceof Method) {
                    for (Annotation annotation : ((Method) m).getAnnotations()) {
                        System.out.print(" ->");
                        System.out.println("Applied annotations are " + annotation);
                    }
                }
            }
        }
    }

    private static String strip(String inputStr, String stripedStrReg) {
        return inputStr.replaceAll(stripedStrReg, "");
    }

    private static void printMemberSet () {
        for (String str : memberSet) {
            System.out.println(str);
        }
    }

}
