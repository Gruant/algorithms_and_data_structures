package ru.antongrutsin;

public class Main {

    private static class Queue {
        private final int[] queue;
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

//        public int length() {
//            return capacity;
//        }

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

    private static class Graph {
        private static class Vertex {
            char label;
            boolean wasVisited;

            public Vertex(char label) {
                this.label = label;
                this.wasVisited = false;
            }

            @Override
            public String toString() {
                return String.format("V=%c", label);
            }
        }

        private final int MAX_VERTICES = 16;
        private final Vertex[] vertexList;
        private final int[][] adjacencyMatrix;
        private int currentSize;

        public Graph() {
            vertexList = new Vertex[MAX_VERTICES];
            adjacencyMatrix = new int[MAX_VERTICES][MAX_VERTICES];
            currentSize = 0;
        }
        public void addVertex(char label) {
            vertexList[currentSize++] = new Vertex(label);
        }
        public void addEdge(int start, int end) {
            adjacencyMatrix[start][end] = 1; // change 1 to weight for weight
            adjacencyMatrix[end][start] = 1; // delete this for direction
        }
        public void displayVertex(int v) {
            System.out.print(vertexList[v] + " ");
        }
        private int getUnvisitedVertex(int current) {
            for (int i = 0; i < currentSize; i++) {
                if (adjacencyMatrix[current][i] == 1 &&
                        !vertexList[i].wasVisited) {
                    return i;
                }
            }
            return -1;
        }


//        public void depthTraverse() {
//            Stack stack = new Stack(MAX_VERTICES);
//            vertexList[0].wasVisited = true;
//            displayVertex(0);
//            stack.push(0);
//            while (!stack.isEmpty()) {
//                int v = getUnvisitedVertex(stack.peek());
//                if (v == -1) {
//                    stack.pop();
//                } else {
//                    vertexList[v].wasVisited = true;
//                    displayVertex(v);
//                    stack.push(v);
//                }
//            }
//            resetFlags();
//        }


        public void widthTraverse() {
            Queue queue = new Queue(MAX_VERTICES);
            vertexList[0].wasVisited = true;
            queue.insert(0);
            while (!queue.isEmpty()) {
                int current = queue.remove();
                displayVertex(current);
                int next;
                while ((next = getUnvisitedVertex(current)) != -1) {
                    vertexList[next].wasVisited = true;
                    queue.insert(next);
                }
            }
            resetFlags();
        }
        private void resetFlags() {
            for (int i = 0; i < currentSize; i++) {
                vertexList[i].wasVisited = false;
            }
        }
    }

    public static void main(String[] args) {
        Graph g = new Graph();
        int MAXVERTEX = 10;
        int charA = 97;
        for (int i = 0; i < MAXVERTEX; i++) {
            g.addVertex((char)charA++);
        }
        g.addEdge(1, 2);
        g.addEdge(2, 5);
        g.addEdge(3, 4);
        g.addEdge(0, 4);
        g.addEdge(1, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(3, 4);
        g.addEdge(0, 4);
        g.addEdge(1, 1);
        System.out.println();
        g.widthTraverse();
    }
}
