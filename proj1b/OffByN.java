public class OffByN implements CharacterComparator {
    private  int num;

    public OffByN(int n) {
        num = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == num || y - x == num) {
            return true;
        }
        return false;
    }
}
