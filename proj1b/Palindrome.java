public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> tmp = new ArrayDeque<>();

        for (int i=0; i<word.length(); i++) {
            tmp.addLast(word.charAt(i));
        }

        return tmp;
    }

    public boolean isPalindrome(String word) {
        Deque wordDeque = wordToDeque(word);
        while (wordDeque.size() != 0) {
            if (wordDeque.size() == 1) {
                return true;
            } else {
                if (wordDeque.removeFirst() != wordDeque.removeLast()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque wordDeque = wordToDeque(word);
        while (wordDeque.size() != 0) {
            if (wordDeque.size() == 1) {
                return true;
            } else {
                if (!cc.equalChars((char)wordDeque.removeFirst(), (char)wordDeque.removeLast())) {
                    return false;
                }
            }
        }
        return true;
    }
}
