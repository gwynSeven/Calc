public class hasNextRoman {

    void hasNextRoman() {

    }

    boolean hasNextRoman(String[] romanArray, String vi) {
        for (int i = 0; i < romanArray.length; i++) {
            if (romanArray[i].equals(vi)) {
                return true;
            }
        }
        return false;
    }


    int hasRomanNumberInt(String[] romanArray, String counter) {
        for (int i = 0; i < romanArray.length; i++) {
            if (romanArray[i].equals(counter)) {
                return i;
            }
        }
        return 0;
    }
}




