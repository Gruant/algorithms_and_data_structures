package ru.antongrutsin;

import java.util.Objects;

public class Main {
    // class iteroCat
    // reset()
    // next(), prev(for dll)
    // getCurrent()
    // atEnd()
    // insertBefore();
    // insertAfter();
    // deleteCurrent();
    private static class Cat {
        int age;
        String name;

        public Cat(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Cat(a=%d,n=%s)", age, name);
        }
    }

    public static class DoubleLinkedList {
        public static class Node {
            Cat c;
            Node next;

            public Node(Cat c) {
                this.c = c;
            }

            @Override
            public String toString() {
                return String.format("Node(c=%s)", c);
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Node node = (Node) o;
                return Objects.equals(c, node.c) &&
                        Objects.equals(next, node.next);
            }
        }

        private Node first;
        private Node last;

        public DoubleLinkedList() {
            this.first = null;
            this.last = null;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public Node getFirst() {
            return first;
        }

        public void setFirst(Node first) {
            this.first = first;
        }


        public void push(Cat c) {
            Node n = new Node(c);
            if (this.isEmpty())
                last = n;
            n.next = first;
            first = n;
        }

        public void pushLast(Cat c) {
            Node n = new Node(c);
            if (this.isEmpty()) {
                first = n;
            } else {
                last.next = n;
            }
            last = n;
        }

        public Cat pop() {
            if (isEmpty()) return null;
            Cat temp = first.c;
            if (first.next == null)
                last = null;
            first = first.next;
            return temp;
        }

        public boolean contains(Cat c) {
            Node n = new Node(c);
            Node current = first;
            while (!current.equals(n)) {
                if (current.next == null) return false;
                else current = current.next;
            }
            return true;
        }

        public void delete(Cat c) {
            Node n = new Node(c);
            Node current = first;
            Node previous = first;
            while (!current.equals(n)) {
                if (current.next == null) return;
                else {
                    previous = current;
                    current = current.next;
                }
            }

            if (current == first) {
                first = first.next;
            } else {
                previous.next = current.next;
            }

            // return current.c

        }
    }

    private static class Iterator {
        private DoubleLinkedList.Node current;
        private DoubleLinkedList.Node previous;
        private final DoubleLinkedList list;

        public Iterator(DoubleLinkedList list) {
            this.list = list;
            this.reset();
        }

        public void reset() {
            current = list.getFirst();
            previous = null;
        }

        public boolean atEnd() {
            return (current.next == null);
        }

        public void nextLink() {
            previous = current;
            current = current.next;
        }

        public DoubleLinkedList.Node getCurrent() {
            return current;
        }

        public void insertAfter(Cat c) {
            DoubleLinkedList.Node n = new DoubleLinkedList.Node(c);
            if (list.isEmpty()) {
                list.setFirst(n);
                current = n;
            } else {
                n.next = current.next;
                current.next = n;
                nextLink();
            }
        }

        public void insertBefore(Cat c) {
            DoubleLinkedList.Node n = new DoubleLinkedList.Node(c);
            if (previous == null) {
                n.next = list.getFirst();
                list.setFirst(n);
                reset();
            } else {
                n.next = previous.next;
                previous.next = n;
                current = n;
            }
        }

        public void deleteCurrent() {
            if (previous == null) {
                list.setFirst(current.next);
                reset();
            } else {
                previous.next = current.next;
                if (atEnd()) {
                    reset();
                } else {
                    current = current.next;
                }
            }
        }
    }


    public static void main(String[] args) {

    }
}


