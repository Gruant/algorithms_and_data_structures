package ru.antongrutsin;

public class Main {

    public static void main(String[] args) {
        int number = 4;
        int extent = 4;
        int[] arr = {3, -2, 4};
        System.out.println("Возведение в степень: " + exponentiation(number,extent));
        System.out.println("Минимальное значение массива: " + minimumValue(arr));
        System.out.println("Нахождение среднего арифметического массива: " + average(arr));

    }

    /*
    Возведение в степень:
    Расчет производился по большему блоку for: перебора по величине степени
    Сложность алгоритма: O(n)
     */
    public static int exponentiation(int number, int extent){
        int result = 1;
        if (extent == 0){
            return 1;
        } else if (number == 0){
            return 0;
        } else {
            for (int i = 0; i < extent; i++) {
                result *= number;
            }
            return result;
        }
    }

    /*
    Поиск минимального элемента в массиве:
    Расчет производился по блоку for: происходит перебор каждого элемента массива
    Сложность алгоритма: O(n)
     */
    public static int minimumValue (int[] arr){
        int min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }
    /*
       Поиск минимального элемента в массиве:
       Расчет производился по блоку for: происходит перебор каждого элемента массива + поределение стреднего
       Сложность алгоритма: O(n + 1)
        */
    public static float average(int[] arr){
        float result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += arr[i];
        }
        return result/arr.length;
    }

}
