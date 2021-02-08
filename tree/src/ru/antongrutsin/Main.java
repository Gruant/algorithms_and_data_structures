package ru.antongrutsin;

import java.util.Random;

public class Main {
    private static Random rand = new Random();

    private static class Tree {

        private static class TreeNode{
            private Integer integer;
            public TreeNode left;
            public TreeNode right;

            public TreeNode(Integer integer) {
                this.integer = integer;
            }

            @Override
            public String toString() {
                return String.format("TN(%s)", integer);
            }
        }

        TreeNode root;

        public void insert(Integer integer) {
            TreeNode node = new TreeNode(integer);
            if (root == null) {
                root = node;
            } else {
                TreeNode current = root;
                TreeNode parent;
                while (true) {
                    parent = current;
                    if (integer < current.integer) {
                        current = current.left;
                        if (current == null) {
                            parent.left = node;
                            return;
                        }
                    } else if (integer > current.integer) {
                        current = current.right;
                        if (current == null) {
                            parent.right = node;
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
        public Integer find(Integer integer) {
            TreeNode current = root;
            while (current.integer != integer) {
                current = (integer < current.integer) ? current.left : current.right;
                if (current == null) return null;
            }
            return current.integer;
        }
        public void preOrderTraverse(TreeNode currentNode) {
            if (currentNode != null) {
                System.out.println(currentNode);
                preOrderTraverse(currentNode.left);
                preOrderTraverse(currentNode.right);
            }
        }
        // LeftRootRight
        // RightLeftRoot
        public void displayTree() {
            preOrderTraverse(root);
        }
        public Integer delete(Integer integer)  {
            TreeNode current = root;
            TreeNode parent = root;
            boolean isLeftChild = true;
            while (current.integer != integer) {
                parent = current;
                if (integer < current.integer) {
                    current = current.left;
                    isLeftChild = true;
                } else  {
                    current = current.right;
                    isLeftChild = false;
                }
                if (current == null) {
                    return null;
                }
            }
            //leaf
            if (current.left == null && current.right == null) {
                if (current == root) {
                    root = null;
                } else if (isLeftChild) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            }
            // one ancestor
            else if (current.right == null) {
                if (isLeftChild)
                    parent.left = current.left;
                else
                    parent.right = current.left;
            }
            else if (current.left == null) {
                if (isLeftChild)
                    parent.left = current.right;
                else
                    parent.right = current.right;
            }
            // two ancestors
            else {
                TreeNode successor = getSuccessor(current);
                if (current == root) {
                    root = successor;
                } else if (isLeftChild) {
                    parent.left = successor;
                } else {
                    parent.right = successor;
                }
                successor.left = current.left;
            }
            return current.integer;
        }

        private TreeNode getSuccessor(TreeNode node) {
            TreeNode current = node.right;
            TreeNode parent = node;
            TreeNode successor = node;
            while (current != null) {
                parent = successor;
                successor = current;
                current = current.left;
            }

            if (successor != node.right) {
                parent.left = successor.right;
                successor.right = node.right;
            }
            return successor;
        }

        public boolean isBalanced(){
            return checkBalance(root) != -1;
        }

        private int checkBalance(TreeNode node){
            if(node == null) return 0;
            int left = checkBalance(node.left);

            if(left == -1) return -1;

            int right = checkBalance(node.right);

            if(right == -1) return -1;

            if(Math.abs(left - right) > 1){
                return -1;
            }else{
                return 1 + Math.max(left, right);
            }
        }

    }

    public static Tree makeTree(int height) {
        Tree tree = new Tree();
        int count = 0;
        while (count > height) {
            int value = rand.nextInt(200) - 100;
            tree.insert(value);
            count++;
        }
        return tree;
    }

    public static void main(String[] args) {
        Tree tree;
        int total = 20;
        int count = 0;
        for (int i = 0; i < total; i++) {
            tree = makeTree(7);
            if (tree.isBalanced()) {
                count++;
            }
        }
        System.out.printf("Процент сбалансированных деревьев: %f\n", (double) count / total * 100f);
    }
}
