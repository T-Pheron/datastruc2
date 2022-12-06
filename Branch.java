public class Branch {
    int number;
    int height;
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