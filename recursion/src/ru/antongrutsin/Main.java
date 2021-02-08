package ru.antongrutsin;

public class Main {

    public static void main(String[] args) {
        System.out.println(exponentiation(2,4));
    }

    static int exponentiation(int base, int power)
    {
        int temp;
        if(power == 0) {
            return 1;
        }

        temp = exponentiation(base, power/2);

        if (power%2 == 0)
            return temp * temp;
        else
            return base * temp * temp;
    }
}
