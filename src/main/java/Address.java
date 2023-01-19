public class Address {
    private final int x;
    private final int y;

    public Address(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean equals(Object other) {
        if (other instanceof Address) {
            Address compare = (Address)other;
            return compare.getX() == getX()
                    && compare.getY() == getY();
        }
        return false;
    }

    public int hashCode() {
        return 31*x + 11*y;
    }

    public String toString() {
        return " [" + x + "," + y + "] ";
    }
}
