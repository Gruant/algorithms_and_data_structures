package ru.antongrutsin;

import java.util.ArrayDeque;

public class Main {
    private static class Stack {
        private char[] stack;
        private int head;

        public Stack(int size) {
            this.stack = new char[size];
            this.head = -1;
        }

        public boolean isEmpty() {
            return head == -1;
        }

        public boolean isFull() {
            return head == stack.length - 1;
        }

        public boolean push(char i) {
            if (isFull()) return false;
            stack[++head] = i;
            return true;
        }

        public char pop() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head--];
        }

        public int peek() throws RuntimeException {
            if (isEmpty()) throw new RuntimeException("Stack is empty");
            return stack[head];
        }
    }

    private static int checkBrackets(String input) {
        int size = input.length();
        Stack st = new Stack(size);
        for (int i = 0; i < size; i++) {
            char ch = input.charAt(i);
            if (ch == '[' || ch == '(' || ch == '<' || ch == '{') {
                st.push(ch);
            } else if (ch == ']' || ch == ')' || ch == '>' || ch == '}') {
                if (st.isEmpty())
                    return i;

                char op = (char) st.pop();
                if (!((op == '[' && ch == ']') ||
                        (op == '{' && ch == '}') ||
                        (op == '(' && ch == ')') ||
                        (op == '<' && ch == '>'))) {
                    return i;
                }
            }
        }
        if (!st.isEmpty()) {
            return size;
        }
        return -1;
    }

    private static class Queue {
        private int[] queue;
        private int head;
        private int tail;
        private int capacity;

        public Queue(int initial) {
            queue = new int[initial];
            head = 0;
            tail = -1;
            capacity = 0;
        }

        public boolean isEmpty() {
            return capacity == 0;
        }

        public boolean isFull() {
            return capacity == queue.length;
        }

        public int length() {
            return capacity;
        }

        public void insert(int i) {
            if (isFull())
                throw new RuntimeException("Queue is full!");
            if (tail == queue.length -1)
                tail = -1;
            queue[++tail] = i;
            capacity++;
        }

        public int remove() {
            if (isEmpty()) throw new RuntimeException("Queue is empty");
            int temp = queue[head++];
            head %= queue.length; //if (head == queue.length) head = 0;
            capacity--;
            return temp;
        }

    }

    /*
    Создать программу, которая переворачивает вводимые строки (читает справа налево) при помощи стека.
     */
    public static String reverseString(String str){
        Stack stack = new Stack(str.length());
        String returnStr = "";
        char[] chars = str.toCharArray();
        for (char character : chars) {
            stack.push(character);
        }
        while (!stack.isEmpty()) {
            returnStr = returnStr + stack.pop();
        }
        return returnStr;
    }


    public static void main(String[] args) {
        System.out.println(checkBrackets("<>"));
        System.out.println(reverseString("Привет"));
        //Deque
        Deque deque = new Deque(5);
        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.addLast(5);
        System.out.println(deque.popFirst());
        System.out.println(deque.popLast());
        System.out.println(deque.popLast());
        System.out.println();
        //Priority Queue
        PriorityQueue pq = new PriorityQueue(4);
        pq.insert(3);
        pq.insert(2);
        pq.insert(100);
        pq.insert(1000);
        System.out.println(pq.peek());
        System.out.println();
        pq.remove();
        System.out.println(pq.peek());
    }
}
