/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        int N = asciis.length;
        int maxLength = 0;
        String[] sorted = new String[N];
        {
            for (int i = 0; i < N; i++) {
                maxLength = asciis[i].length() > maxLength ? asciis[i].length() : maxLength;
                sorted[i] = asciis[i];
            }
        }
        for (int index = maxLength - 1; index >= 0; index--) {
            sortHelperLSD(sorted, index);
        }
        return sorted;
    }

    private static void swap(String[] str, int i1, int i2) {
        String temp = str[i1];
        str[i1] = str[i2];
        str[i2] = temp;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     *
     * @param asciis Input array of Strings
     * @param index  The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        int N = asciis.length;
        char[] charAtIndex = new char[N];
        for (int i = 0; i < N; i++) {
            charAtIndex[i] = index < asciis[i].length() ? asciis[i].charAt(index) : '0';
        }
        for (int i = 0; i < N; i++) {
            for (int j = i; j > 0; j--) {
                if (charAtIndex[j - 1] > charAtIndex[j]) {
                    char temp = charAtIndex[j - 1];
                    charAtIndex[j - 1] = charAtIndex[j];
                    charAtIndex[j] = temp;
                    swap(asciis, j, j - 1);
                } else {
                    break;
                }
            }
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start  int for where to start sorting in this method (includes String at start)
     * @param end    int for where to end sorting in this method (does not include String at end)
     * @param index  the index of the character the method is currently sorting on
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] str = {"356", "12", "904", "294", "209", "820", "394", "810"};
        for (String s : str) {
            System.out.println(s + " ");
        }
        String[] sorted = sort(str);
        System.out.println("After sorting");
        for (String s : sorted) {
            System.out.println(s + " ");
        }
    }
}
