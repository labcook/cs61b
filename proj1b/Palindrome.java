public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            char now = word.charAt(i);
            deque.addLast(now);
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else {
            int front = 0;
            int rear = word.length() - 1;
            while (front < rear) {
                char f = word.charAt(front);
                char r = word.charAt(rear);
                if (f == r) {
                    front += 1;
                    rear -= 1;
                } else {
                    return false;
                }
            }
            //situation 1: front == rear
            //situation 2: front = rear + 1
            return true;
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() == 0 || word.length() == 1) {
            return true;
        } else {
            int front = 0;
            int rear = word.length() - 1;
            while (front < rear) {
                char f = word.charAt(front);
                char r = word.charAt(rear);
                if (cc.equalChars(f,r)) {
                    front += 1;
                    rear -= 1;
                } else {
                    return false;
                }
            }
            //situation 1: front == rear
            //situation 2: front = rear + 1
            return true;
        }
    }
}
