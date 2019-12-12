package ch13.ex04;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static final Map<String, Class<?>> PRIMITIVE_TYPE_MAP = new HashMap() {
        {
            put("Byte", byte.class);
            put("Short", short.class);
            put("Integer", int.class);
            put("Long", long.class);
            put("Boolean", boolean.class);
            put("Float", float.class);
            put("Double", double.class);
            put("Character", char.class);
        }
    };

    private static final Map<String, String> PARSE_METHOD_NAME_MAP = new HashMap() {
        {
            put("Byte", "parseByte");
            put("Short", "parseShort");
            put("Integer", "parseInt");
            put("Long", "parseLong");
            put("Boolean", "parseBoolean");
            put("Float", "parseFloat");
            put("Double", "parseDouble");
            put("Character", "toCharArray");
        }
    };


    public static void main(String[] args) {
        Object[] values = compile("Boolean true\nCharacter s\nInteger 90\nFloat 3.14");
        for (Object value : values) {
            System.out.println(value);
        }
    }

    private static Object[] compile (String readStr) {
        ArrayList<Object> outputList = new ArrayList<>();
        String[] divideByRow = readStr.split("\n", -1);
        for (String row : divideByRow) {
            String[] divideStr = row.split(" ", -1);
            if (divideStr.length > 2) {
                throw new IllegalArgumentException("Given the number of value is more than 1.");
            }
            try {
                Class<?> clazz = Class.forName("java.lang." + divideStr[0]);
                //コンストラクタの生成
                Constructor<?> constructor = clazz.getConstructor(PRIMITIVE_TYPE_MAP.get(divideStr[0]));
                //parse...メソッドの生成
                Method m;
                //Characterだけ文字列を自クラス型に変換するMethodがないため、別分岐
                if (divideStr[0].equals("Character")) {
                    if (divideStr[1].length() > 1) {
                        throw new IllegalArgumentException("Character value length is more than 1.");
                    }
                    outputList.add(constructor.newInstance(divideStr[1].toCharArray()[0]));
                } else {
                    m = clazz.getMethod(PARSE_METHOD_NAME_MAP.get(divideStr[0]), String.class);
                    //上で作ったparseメソッドで、文字列をプリミティブ型に変換し、コンストラクタでオブジェクト生成
                    outputList.add(constructor.newInstance(m.invoke(null, divideStr[1])));
                }
            } catch (ReflectiveOperationException e) {
                e.printStackTrace();
            }
        }
        return outputList.toArray();
    }
}
