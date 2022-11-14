import java.util.Random;

public class AVLTree extends BinarySearchTree {

    public class Branch extends BinarySearchTree.Branch {
        int height;

        Branch left, right;

        private Branch() {
            super();
        }

        private Branch(int enter) {
            super(enter);
        }

        private Branch(int enter, Object data) {
            super(enter, data);
        }

        private Branch(int enter, int floor) {
            super(enter, floor);
        }

        private Branch(int enter, Object data, int floor) {
            super(enter, data, floor);
        }

        int getNumber() {
            return number;
        }

        Object getData() {
            return data;
        }

        int getFloor() {
            return floor;
        }

        int getHeight() {
            return height;
        }

        Branch getRight() {
            return right;
        }

        Branch getLeft() {
            return left;
        }

        void replaceNumber(int replace) {
            number = replace;
        }

        void replaceData(Object replace) {
            this.data = replace;
        }

        void replaceRight(Branch replace) {
            right = replace;
        }

        void replaceLeft(Branch replace) {
            left = replace;
        }
    }

    Branch root;

    public AVLTree(int enter) {
        super();
        root = new Branch(enter);
    }

    public AVLTree() {
        super();
        root = new Branch();
    }

    private int height(Branch node) {
        if (node == null)
            return -1;
        else
            return node.getHeight();
    }

    private void updateHeight(Branch node) {
        int leftChildHeight = height(node.getLeft());
        int rightChildHeight = height(node.getRight());
        node.height = max(leftChildHeight, rightChildHeight) + 1;
    }

    private int max(int a, int b) {
        if (a < b)
            return b;
        else
            return a;
    }

    private int balanceFactor(Branch node) {
        return height(node.getRight()) - height(node.getLeft());
    }

    private Branch rotateRight(Branch node) {
        Branch leftChild = node.getLeft();
        if (node.getLeft() != null) {
            leftChild.floor = node.getLeft().floor;
            leftChild.data = node.getLeft().data;
        }

        node.replaceLeft(leftChild.getRight());
        if (leftChild.getRight() != null) {
            node.getLeft().floor = leftChild.getRight().floor;
            node.getLeft().data = leftChild.getRight().data;
        }
        leftChild.replaceRight(node);
        if (node != null) {
            leftChild.getRight().floor = node.getFloor();
            leftChild.getRight().data = node.getData();
        }

        updateHeight(node);
        updateHeight(leftChild);

        return leftChild;
    }

    private Branch rotateLeft(Branch node) {
        Branch rightChild = node.getRight();
        if (node.getRight() != null) {
            rightChild.floor = node.getRight().floor;
            rightChild.data = node.getRight().data;
        }

        node.replaceRight(rightChild.getLeft());
        if (rightChild.getLeft() != null) {
            node.getRight().floor = rightChild.getLeft().floor;
            node.getRight().data = rightChild.getLeft().data;
        }
        rightChild.replaceLeft(node);
        if (node != null) {
            rightChild.getLeft().floor = node.getFloor();
            rightChild.getLeft().data = node.getData();
        }

        updateHeight(node);
        updateHeight(rightChild);

        return rightChild;
    }

    private Branch rebalance(Branch node) {
        int balanceFactor = balanceFactor(node);

        if (balanceFactor < -1) {
            if (balanceFactor(node.getLeft()) <= 0) {
                node = rotateRight(node);
            } else {
                node.replaceLeft(rotateLeft(node.getLeft()));
                node = rotateRight(node);
            }
        }

        if (balanceFactor > 1) {
            if (balanceFactor(node.getRight()) >= 0) {
                node = rotateLeft(node);
            } else {
                node.replaceRight(rotateRight(node.getRight()));
                node = rotateLeft(node);
            }
        }

        return node;
    }

    @Override
    void insertValue(int enter) {
        root = insertInBranch(root, enter, root.floor);
    }

    @Override
    void insertValue(int enter, Object data) {
        root = insertInBranch(root, enter, data, root.floor);
    }

    Branch insertInBranch(Branch root, int enter, int floor) {

        if (root == null) {
            root = new Branch(enter, floor + 1);
            updateHeight(root);
            return rebalance(root);
        }
        if (enter < root.getNumber()) {
            root.replaceLeft(insertInBranch(root.left, enter, root.floor));
        } else if (enter > root.getNumber()) {
            root.replaceRight(insertInBranch(root.right, enter, root.floor));
        }

        updateHeight(root);
        return rebalance(root);
    }

    Branch insertInBranch(Branch root, int enter, Object data, int floor) {

        if (root == null) {
            root = new Branch(enter, data, floor + 1);
            updateHeight(root);
            return rebalance(root);
        }
        if (enter < root.getNumber()) {
            root.replaceLeft(insertInBranch(root.left, enter, data, root.floor));
        } else if (enter > root.getNumber()) {
            root.replaceRight(insertInBranch(root.right, enter, data, root.floor));
        }

        updateHeight(root);
        return rebalance(root);
    }

    @Override
    void deleteValue(int enter) {
        root = deleteValue(enter, root);
    }

    Branch deleteValue(int enter, Branch node) {
        super.deleteValue(enter, node);
        updateHeight(node);
        return rebalance(node);
    }

    @Override
    void viewTree() {
        viewBranch(root);
    }

    void viewBranch(Branch root) {
        super.viewBranch(root);
    }

    @Override
    boolean searchValue(int enter) {
        return searchValue(root, enter);
    }

    boolean searchValue(Branch node, int enter) {
        return super.searchValue(node, enter);
    }

    void updateData(int number, Object data) {
        updateData(root, number, data);
    }

    void updateData(Branch node, int number, Object data) {
        boolean turnWhile = true;
        while (turnWhile != false) {
            if (node.getNumber() != number) {
                if (number < node.getNumber() && node.getLeft() != null)
                    node = node.getLeft();
                else if (number > node.getNumber() && node.getRight() != null)
                    node = node.getRight();
                else
                    turnWhile = false;
            } else {
                node.replaceData(data);
                turnWhile = false;
            }

        }
    }

    Object getData(int enter) {
        return getData(root, enter);
    }

    Object getData(Branch node, int enter) {
        boolean turnWhile = true;
        while (turnWhile != false) {
            if (node.getNumber() != enter) {
                if (enter < node.getNumber() && node.getLeft() != null)
                    node = node.getLeft();
                else if (enter > node.getNumber() && node.getRight() != null)
                    node = node.getRight();
                else
                    turnWhile = false;
            } else {
                return node.getData();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        AVLTree tree = new AVLTree(12);
        try (java.util.Scanner keybord = new java.util.Scanner(System.in)) {
            for (int i = 0; i < 10; i++) {
                Random rand = new Random();
                int randNumber = rand.nextInt(1000);
                tree.insertValue(randNumber);

                tree.viewTree();
                System.out.print(randNumber + "   ");
            }

            System.out.println("Give me a number for search");
            String number = keybord.next();

            System.out.println(tree.searchValue(Integer.parseInt(number)));

            System.out.println("Give me a number for search");
            String number1 = keybord.next();

            System.out.println(tree.searchValue(Integer.parseInt(number1)));

            tree.viewTree();
            System.out.println("Give me a number for delete");
            String number2 = keybord.next();

            tree.deleteValue(Integer.parseInt(number2));
            tree.viewTree();

            System.out.println("Give me a number for add");
            String number3 = keybord.next();

            tree.insertValue(Integer.parseInt(number3));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        tree.viewTree();

    }
}