package ch22.ex01;

public class FloatValueFormatter {

    public static void formatValue(Float[] arrayFloat, int rowNum) {
        StringBuilder formatBuilder = new StringBuilder();
        for (int i = 0; i < rowNum; i++) {
            formatBuilder.append("%.80g%n");
        }
        System.out.printf(formatBuilder.toString(), arrayFloat);
    }

    public static void main(String[] args) {
        formatValue(new Float[]{6.89034f, 2.22229f, 843.895683f}, 3);
    }

}
