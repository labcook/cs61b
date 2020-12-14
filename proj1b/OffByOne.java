public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        if (x == y) {
            return true;
        }
        return false;
    }

}
