package ru.antongrutsin;

import sun.lwawt.macosx.CSystemTray;

import java.util.Arrays;

public class Main {

    private static class Array {
        private int[] arr;
        private int size;
        private boolean isSorted;

        private Array() {
            isSorted = false;
        }

        public Array(int size) {
            this();
            this.size = size;
            this.arr = new int[size];
        }

        public Array(int... args) {
            this();
            this.size = args.length;
            this.arr = args;
        }

        public Array(boolean isSorted, int... args) {
            this(args);
            this.isSorted = isSorted;
        }

        public int get(int index) {
            if (index >= size || index < 0)
                throw new ArrayIndexOutOfBoundsException("Your index is not correct: " + index);
            return arr[index];
        }

        public void set(int index, int value) {
            arr[index] = value;
            isSorted = false;
        }

        public boolean delete() { // last
            if (size == 0) return false;
            size--;
            return true;
        }

        public boolean delete(int index) {
            if (index >= size || index < 0)
                throw new ArrayIndexOutOfBoundsException("Your index is not correct: " + index);

            System.arraycopy(arr, index + 1, arr, index, size - index - 1);
            size--;
            return true;
        }

        public void append(int value) {
            if (size >= arr.length - 1) {
                int[] temp = arr;
                arr = new int[size * 2];
                System.arraycopy(temp, 0, arr, 0, size);
            }
            arr[size++] = value;
            isSorted = false;
        }

        public boolean isInArray(int value) {
            for (int i = 0; i < this.size; i++) {
                if (this.arr[i] == value) {
                    return true;
                }
            }
            return false;
        }

        public int hasValue(int value) {
            if (!isSorted)
                throw new RuntimeException("Trying to search in unsorted array");

            int l = 0;
            int r = size;
            int m;
            while (l < r) {
                m = (l + r) >> 1;
                if (value == arr[m]) {
                    return m;
                } else {
                    if (value < arr[m]) {
                        r = m;
                    } else {
                        l = m + 1;
                    }
                }
            }

            return -1;
        }

        private void swap(int a, int b) {
            int tmp = this.arr[a];
            this.arr[a] = this.arr[b];
            this.arr[b] = tmp;
        }

        public void sortBubble() {
            int count = 0;
            for (int out = size - 1; out > 0; out--) {
                for (int in = 0; in < out; in++) {
                    count++;
                    if (this.arr[in] > arr[in + 1]) {
                        swap(in, in + 1);
                    }
                }
            }
            isSorted = true;
            System.out.println(count);
        }

        public void sortSelect() {
            int count = 0;
            for (int i = 0; i < size; i++) {
                int flag = i;
                for (int j = i + 1; j < size; j++) {
                    count++;
                    if (arr[j] < arr[flag])
                        flag = j;
                }
                swap(i, flag);
            }
            isSorted = true;
            System.out.println(count);
        }

        public void sortInsert() {
            int count = 0;
            for (int out = 1; out < size; out++) {
                int temp = arr[out];
                int in = out;
                while (in > 0 && arr[in - 1] >= temp) {
                    arr[in] = arr[in - 1];
                    in--;
                    count++;
                }
                arr[in] = temp;
            }
            isSorted = true;
            System.out.println(count);
        }

//        homework
//        boolean deleteAll(int value) { } // by value
        public boolean deleteAll(int value){
            for (int i = 0; i < size; i++) {
                if(arr[i] == value){
                    System.arraycopy(arr, i + 1, arr, i, size - i - 1);
                    size--;
                }
            } return true;
        }

//        boolean deleteAll() { } // clear
        public boolean deleteAll(){
            arr = new int[size];
            return true;
        }

//        void insert(int index, int value) { } // shift the tail
        public void insert(int index, int value){
            System.out.println(arr.length);
            if (size >= arr.length - 1) {
                int[] temp = arr;
                arr = new int[size+1];
                System.arraycopy(temp, 0, arr, 0, size);
                size++;
            }
            System.arraycopy(arr, index, arr, index+1, size-1-index);
            arr[index] = value;
        }


        /*
        improve bubble sort
        Если в каком то проходе не было обмена, то массив уже отсортирован
         */
        public void improvedSortBubble() {
            boolean hasChanges = true;
            for (int out = size - 1; out > 0; out--) {
                if (hasChanges) {
                    for (int in = 0; in < out; in++) {
                        if (this.arr[in] > arr[in + 1]) {
                            swap(in, in + 1);
                            hasChanges = true;
                        } else {
                            hasChanges = false;
                        }
                    }
                }
                isSorted = true;
            }
        }



        @Override
        public String toString() {
            if (arr == null)
                return "null";
            int iMax = size - 1;
            if (iMax == -1)
                return "[]";

            StringBuilder b = new StringBuilder();
            b.append('[');
            for (int i = 0; ; i++) {
                b.append(arr[i]);
                if (i == iMax)
                    return b.append(']').toString();
                b.append(", ");
            }
        }
    }

    public static void main(String[] args) {
        Array array = new Array(9,2,3,7,4,5,6,7,1,8,0);
        System.out.println(array);
//        System.out.println(array);
//        array.deleteAll(7);
//        System.out.println(array);
//        array.deleteAll();
//        System.out.println(array);
//        array.insert(2, 100);
//        System.out.println(array);
        array.sortSelect();

    }

    private static void standardArrayThings() {
        int[] arr;
        int ar2[];
        ar2 = new int[5];
        arr = new int[]{1,2,3,'_',5,6,7};
        System.out.println(arr.length);
        System.out.println(Arrays.toString(arr));
    }
}
