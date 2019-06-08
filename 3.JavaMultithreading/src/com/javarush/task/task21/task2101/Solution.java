package com.javarush.task.task21.task2101;

/* 
Определяем адрес сети
*/
public class Solution {
    public static void main(String[] args) {
        byte[] ip = new byte[]{(byte) 192, (byte) 168, 1, 2};
        byte[] mask = new byte[]{(byte) 255, (byte) 255, (byte) 254, 0};
        byte[] netAddress = getNetAddress(ip, mask);
        print(ip);          //11000000 10101000 00000001 00000010
        print(mask);        //11111111 11111111 11111110 00000000
        print(netAddress);  //11000000 10101000 00000000 00000000
    }

    public static byte[] getNetAddress(byte[] ip, byte[] mask) {
        final int LEN = 4;

        if (ip.length != mask.length && ip.length != LEN) {
            System.out.println("Invalid parameters");
            return new byte[4];
        }

        byte[] result = new byte[LEN];
        for (int i = 0; i < LEN; i++) {
            result[i] = (byte) (ip[i] & mask[i]);
        }

        return result;
    }

    public static void print(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(
                    String.format("%8s",
                    Integer.toBinaryString(Byte.toUnsignedInt(b))
                    ).replace(' ', '0')
            ).append(' ');
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }
}
