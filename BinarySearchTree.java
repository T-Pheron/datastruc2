import java.util.Random;

public class BinarySearchTree {

    Branch root;

    BinarySearchTree() {
        root = new Branch();
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

        for (int i = 0; i < 10; i++) {
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
            System.out.println("Delete 1");
            return null;
        }
        if (enter < node.getNumber()) {
            System.out.println("Delete 3");
            Branch nodeDefault = node.getRight();
            if (nodeDefault.getLeft() == null && nodeDefault.getRight() == null) {
                node.replaceLeft(null);
            } else
                node.replaceLeft(deleteValue(enter, node.getLeft()));
        } else if (enter > node.getNumber()) {
            System.out.println("Delete 4");
            Branch nodeDefault = node.getLeft();
            if (nodeDefault.getLeft() == null && nodeDefault.getRight() == null) {
                node.replaceRight(null);
            } else
                node.replaceRight(deleteValue(enter, node.getRight()));
        } else if (node.getLeft() == null) {
            System.out.println("Delete 5");
            node = node.getRight();
        } else if (node.getRight() == null) {
            System.out.println("Delete 6");
            node = node.getLeft();
        } else {
            System.out.println("Delete 7");
            deleteValueWithTwoChildren(node);
        }

        System.out.println("Delete 8");

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
        BinarySearchTree tree = new BinarySearchTree(12);
        try (java.util.Scanner keybord = new java.util.Scanner(System.in)) {
            for (int i = 0; i < 5; i++) {
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