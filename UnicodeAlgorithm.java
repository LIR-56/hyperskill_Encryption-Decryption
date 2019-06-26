package encryptdecrypt;

public class UnicodeAlgorithm implements Algorithm {

    @Override
    public String transform(String input, int key, boolean decrypt) {
        if (decrypt) {
            key *= -1;
        }
        StringBuilder result = new StringBuilder();
        for(char c : input.toCharArray()) {
            result.append((char)(c+key));
        }
        return result.toString();
    }
}
