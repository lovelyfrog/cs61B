public class OffByN implements CharacterComparator {

    private int offN;
    public OffByN(int N) {
        offN = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == -offN || diff == offN) {
            return true;
        }
        return false;
    }

//    public static void main(String[] args) {
//        CharacterComparator cc = new OffByN(5);
//        System.out.println(cc.equalChars('a', 'f'));
//        System.out.println(cc.equalChars('f', 'h'));
//    }
}
