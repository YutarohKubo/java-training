package ch22.ex03;

import java.util.*;

public class Main {

    private static Map<Character, BitSet> bitSetMap = new HashMap<>();

    public static void main(String[] args) {
        String inputStr = "Hello World";
        for (int i = 0; i < inputStr.length(); i++) {
            char ch = inputStr.charAt(i);
            bitSetMap.put(ch, convertToBinary(ch));
        }
        for (Map.Entry<Character, BitSet> entry : bitSetMap.entrySet()) {
            System.out.println("char = " + entry.getKey() + " , " + "bitset = " + printBitSet(entry.getValue()));
        }
    }

    private static BitSet convertToBinary(int num) {
        String binaryStr = Integer.toBinaryString(num);
        BitSet resultBitSet = new BitSet(binaryStr.length());
        for (int i = 0; i < binaryStr.length(); i++) {
            int bit = (int) binaryStr.charAt(binaryStr.length() - i - 1);
            if (bit == 49) {
                resultBitSet.set(i);
            }
        }
        return resultBitSet;
    }

    private static String printBitSet(BitSet bitSet) {
        List<Integer> bitList = new ArrayList<>();
        for (int i = bitSet.nextSetBit(0); i >= 0; i = bitSet.nextSetBit(i + 1)) {
            bitList.add(i);
        }

        StringBuilder resultStr = new StringBuilder();
        for (int i = 0; i <= bitList.get(bitList.size() - 1); i++) {
            String bitStr = "0";
            if (bitList.contains(i)) {
                bitStr = "1";
            }
            resultStr.append(bitStr);
        }

        return resultStr.reverse().toString();
    }

}
