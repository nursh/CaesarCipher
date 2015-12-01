
public class Cipher {
    private static final int MIN_CAPITAL_ASCII = 65;
    private static final int MAX_CAPITAL_ASCII = 90;
    private static final int MIN_SMALL_ASCII = 97;
    private static final int MAX_SMALL_ASCII = 122;

    private int keyShift;
    private String message;
    private String encodedMessage;
    private String decodedMessage;

    public Cipher(int keyShift, String message) {
        this.keyShift = keyShift;
        this.message = message;
        this.decodedMessage = "";
        this.encodedMessage = "";
    }

    public String getEncodedMessage() {
        return encodedMessage;
    }

    public String getDecodedMessage() {
        return decodedMessage;
    }

    public void encodeMessage() {
        for (char c : message.toCharArray())
            if ((!(Character.isLetter(c)))){
                    encodedMessage += c;
            } else {
                if (Character.isUpperCase(c)) {
                    encodedMessage += encodeBoundCheckCapitalLetters(c);
                } else {
                    encodedMessage += encodeBoundCheckSmallLetters(c);
                }
            }
    }

    public char encodeBoundCheckCapitalLetters(char c ) {
        int check = c + keyShift;
        if (check > MAX_CAPITAL_ASCII){
            c += keyShift;
            c -= MAX_CAPITAL_ASCII;
            c += MIN_CAPITAL_ASCII - 1;
            return c;
        }
        return c += keyShift;
    }

    public char encodeBoundCheckSmallLetters(char c ) {
        int check = c + keyShift;
        if (check > MAX_SMALL_ASCII){
            c += keyShift;
            c -= MAX_SMALL_ASCII;
            c += MIN_SMALL_ASCII - 1;
            return c;
        }
        return c += keyShift;
    }

    public void decodeMessage() {
        for (char c : message.toCharArray())
            if (!(Character.isLetter(c))){
                decodedMessage += c;
            } else {
                if (Character.isUpperCase(c)) {
                    decodedMessage += decodeBoundCheckCapitalLetters(c);
                } else {
                    decodedMessage += decodeBoundCheckSmallLetters(c);
                }
            }
    }

    public char decodeBoundCheckCapitalLetters(char c) {
        int check = c - keyShift;
        if (check < MIN_CAPITAL_ASCII){
            c -= keyShift;
            c = (char) (MIN_CAPITAL_ASCII - c);
            c = (char) (MAX_CAPITAL_ASCII - c + 1);
            return c;
        }
        return c -= keyShift;
    }

    public char decodeBoundCheckSmallLetters(char c) {
        int check = c - keyShift;
        if (check < MIN_SMALL_ASCII){
            c -= keyShift;
            c = (char) (MIN_SMALL_ASCII - c);
            c = (char) (MAX_SMALL_ASCII - c + 1);
            return c;
        }
        return c -= keyShift;
    }

}
