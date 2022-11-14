import java.util.Random;

public class BinarySearchTree {

    public class Branch {
        int number;
        Object data;
        int floor;

        Branch left, right;

        public Branch(int enter) {
            number = enter;
            floor = 0;
            left = null;
            right = null;
        }

        public Branch(int enter, Object data) {
            number = enter;
            this.data = data;
            floor = 0;
            left = null;
            right = null;
        }

        public Branch() {
        }

        public Branch(int enter, int floor) {
            number = enter;
            this.floor = floor;
            left = null;
            right = null;
        }

        public Branch(int enter, Object data, int floor) {
            number = enter;
            this.floor = floor;
            this.data = data;
            left = null;
            right = null;
        }

        int getNumber() {
            return number;
        }

        int getFloor() {
            return floor;
        }

        Object getData() {
            return data;
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

    BinarySearchTree() {
    }

    BinarySearchTree(int enter) {
        root = new Branch(enter);
    }

    BinarySearchTree(int enter, Object data) {
        root = new Branch(enter, data);
    }

    void insertValue(int enter) {
        root = insertInBranch(root, enter, root.floor);
    }

    void insertValue(int enter, Object data) {
        root = insertInBranch(root, enter, data, root.floor);
    }

    Branch insertInBranch(Branch root, int enter, int floor) {
        if (root == null) {
            root = new Branch(enter, floor + 1);
            return root;
        }
        if (enter < root.number) {
            root.left = insertInBranch(root.left, enter, root.floor);
        } else if (enter > root.number) {
            root.right = insertInBranch(root.right, enter, root.floor);
        }

        return root;
    }

    Branch insertInBranch(Branch root, int enter, Object data, int floor) {
        if (root == null) {
            root = new Branch(enter, data, floor + 1);
            return root;
        }
        if (enter < root.number) {
            root.left = insertInBranch(root.left, enter, data, root.floor);
        } else if (enter > root.number) {
            root.right = insertInBranch(root.right, enter, data, root.floor);
        }

        return root;
    }

    void viewTree() {
        viewBranch(root);
    }

    void viewBranch(Branch root) {
        Object[][] view = new Object[20][100];
        view[0][50] = root.getNumber();
        view[1][49] = "/";
        view[1][51] = "\\";

        if (root.getLeft() != null)
            viewBranchLeft(view, root.getLeft(), 45);

        if (root.getRight() != null)
            viewBranchRight(view, root.getRight(), 55);

        for (int i = 0; i < 4; i++) {
            for (int y = 0; y < 100; y++) {
                if (view[i][y] != null)
                    System.out.print(view[i][y]);
                else
                    System.out.print(" ");
            }
            System.out.println("");
        }
    }

    void viewBranchLeft(Object[][] view, Branch root, int center) {
        if (root.getFloor() < 6) {
            view[2 * root.getFloor()][center - 10] = root.getNumber();
            view[2 * root.getFloor() + 1][center - 11] = "/";
            view[2 * root.getFloor() + 1][center - 9] = "\\";

            if (root.getLeft() != null)
                viewBranchLeft(view, root.getLeft(), center - 10);
            if (root.getRight() != null)
                viewBranchRight(view, root.getRight(), center - 10);
        }
    }

    void viewBranchRight(Object[][] view, Branch root, int center) {
        if (root.floor < 4) {
            view[2 * root.getFloor()][center + 10] = root.getNumber();
            view[2 * root.getFloor() + 1][center + 9] = "/";
            view[2 * root.getFloor() + 1][center + 11] = "\\";

            if (root.getLeft() != null)
                viewBranchLeft(view, root.getLeft(), center + 10);
            if (root.getRight() != null)
                viewBranchRight(view, root.getRight(), center + 10);
        }
    }

    void deleteValue(int enter) {
        root = deleteValue(enter, root);
    }

    Branch deleteValue(int enter, Branch node) {
        if (node == null) {
            return null;
        }

        if (enter < node.getNumber()) {
            node.replaceLeft(deleteValue(enter, node.getLeft()));
        } else if (enter > node.getNumber()) {
            node.replaceRight(deleteValue(enter, node.getRight()));
        }

        else if (node.getLeft() == null && node.getRight() == null) {
            node = null;
            return node;
        }

        else if (node.getLeft() == null) {
            node = node.getRight();
        } else if (node.getRight() == null) {
            node = node.getLeft();
        }

        else {
            deleteValueWithTwoChildren(node);
        }

        return node;
    }

    private void deleteValueWithTwoChildren(Branch node) {

        Branch successor = findMinimum(node.getRight());

        node.replaceNumber(successor.getNumber());

        node.replaceRight(deleteValue(successor.getNumber(), node.getRight()));
    }

    private Branch findMinimum(Branch node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    boolean searchValue(int enter) {
        return searchValue(root, enter);
    }

    boolean searchValue(Branch node, int enter) {
        boolean find = false;
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
                find = true;
                turnWhile = false;
            }
        }
        return find;
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
        BinarySearchTree tree = new BinarySearchTree(12);
        try (java.util.Scanner keybord = new java.util.Scanner(System.in)) {
            for (int i = 0; i < 100; i++) {
                Random rand = new Random();
                int randNumber = rand.nextInt(1000);
                tree.insertValue(randNumber);
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

            tree.deleteValue(Integer.parseInt(number3));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        tree.viewTree();

    }
}