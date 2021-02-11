package ru.antongrutsin;

import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static class Item<K, V> {
        private final K key;
        private V value;
        Item<K, V> next;

        public Item(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Item<?, ?> item = (Item<?, ?>) o;
            return Objects.equals(key, item.key) &&
                    Objects.equals(value, item.value) &&
                    Objects.equals(next, item.next);
        }

        @Override
        public String toString() {
            return "Item{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    public static class HashCat<K, V> {
        private ArrayList<Item<K, V>> arr;
        private int arraySize;
        private int llSize;

        public HashCat(int arraySize) {
            this.arr = new ArrayList<>();
            this.arraySize = arraySize;
            this.llSize = 0;

            for (int i = 0; i < arraySize; i++) {
                arr.add(null);
            }
        }

        @Override
        public String toString() {
            return "HashCat{" +
                    "arr=" + arr +
                    '}';
        }

        public boolean isFull(){
            return arraySize == llSize;
        }

        public void increaseArr(){
            ArrayList<Item<K, V>> temp = arr;
            arr = new ArrayList<>();
            arraySize *= 2;
            llSize = 0;

            for (int i = 0; i < arraySize; i++) {
                arr.add(null);
            }

            for (Item<K, V> head : temp) {
                while(head != null){
                    add(head.key, head.value);
                    head = head.next;
                }
            }
        }


        public int size() { return llSize; }

        public boolean isEmpty() { return size() == 0; }

        private int hashFunc(K key) {
            return (Integer)key % arraySize;
        }

        public V remove(K key) {
            int arrIndex = hashFunc(key);
            Item<K, V> head = arr.get(arrIndex);
            Item<K, V> prev = null;
            while (head != null) {
                if (head.key.equals(key)) {
                    break;
                } else {
                    prev = head;
                    head = head.next;
                }
            }

            if (head == null) {
                return null;
            }

            llSize--;
            if (prev != null) {
                prev.next = head.next;
            } else {
                arr.set(arrIndex, head.next);
            }

            return head.value;
        }

        public void add(K key, V value){
            int index = hashFunc(key);
            Item<K, V> head = arr.get(index);

            while(head != null){
                if (head.key.equals(key)){
                    head.value = value;
                    return;
                }
                head = head.next;
            }
            llSize++;
            head = arr.get(index);
            Item<K, V> newNode = new Item<K, V>(key, value);
            newNode.next = head;
            arr.set(index, newNode);

            if (isFull()){
                increaseArr();
            }
        }

        public V get(K key){
            int index = hashFunc(key);
            Item<K, V> head = arr.get(index);
            while(head != null) {
                if(head.key.equals(key)){
                    return head.value;
                } else {
                    head = head.next;
                }
            }
            return null;
        }
    }
    public static void main(String[] args) {
        HashCat<Integer, String> cat = new HashCat<Integer, String>(5);
        cat.add(10, "Рысь");
        cat.add(20, "Батон");
        cat.add(40, "Радуга");
        cat.add(15, "Неопознан");
        System.out.println(cat);
        System.out.println(cat.get(15));
        System.out.println(cat.get(20));
    }
}
