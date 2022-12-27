public class Main {
    public static void main(String[] args) {
        // & - bitwise multiplication
        int a1 = 2; //010
        int b1 = 5;//101
        System.out.println(" 2 & 5" + (a1&b1));

        // | - bitwise sum
        int a2 = 2; //010
        int b2 = 5;//101
        System.out.println(a2 | b2); // результат 7 - 111
        int a3 = 4; //100
        int b3 = 5;//101
        System.out.println(a3 | b3); // результат 5 - 101

        // ^ = bitwise or (xor)
        int number = 45; // 0010 1101
        int key = 102; // 0110 0110
        int encrypt = number ^ key; //0100 1011
        System.out.println("encrypt number: " +encrypt);

        int decrypt = encrypt ^ key;
        System.out.println("unencrypt number: " + decrypt);

        //logical negation
        byte a = 12;                 // 0000 1100
        System.out.println(~a);     //  1111 0011 or -13
    }
}
