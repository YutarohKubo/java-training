package ch08.ex12;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Class<?> targetClazz = TestClass.class;

        Arrays.stream(targetClazz.getDeclaredMethods())
                .forEach(method -> Arrays.stream(method.getAnnotationsByType(TestCase.class)).forEach(testCase -> {
                    Type[] types = method.getGenericParameterTypes();
                    if (types.length != 1 || !types[0].getTypeName().matches(("int|java.lang.Integer"))) {
                        // TestCaseが適用されている関数の引数がintまたはInteger型1つでなければ例外スロー
                        throw new IllegalStateException("test function (" + method.getName() + ") args is not one of Integer");
                    }
                    Type returnType = method.getGenericReturnType();
                    if (!returnType.getTypeName().matches(("int|java.lang.Integer"))) {
                        // TestCaseが適用されている関数の返値がintまたはInteger型でなければ例外スロー
                        throw new IllegalStateException("test function (" + method.getName() + ") return type is not Integer");
                    }
                    try {
                        String param = testCase.params();
                        String expected = testCase.expected();
                        int returnObject = (int) method.invoke(targetClazz.newInstance(), Integer.parseInt(param));
                        if (Integer.toString(returnObject).equals(expected)) {
                            System.out.println("param = " + param + " , expected = " + expected + " is success " + "(method : " + method.getName() + ")");
                        } else {
                            System.out.println("param = " + param + " , expected = " + expected + " is failed " + "(method : " + method.getName() + ")");
                        }
                    } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }));
    }

}
