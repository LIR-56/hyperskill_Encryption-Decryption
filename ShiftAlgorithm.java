package encryptdecrypt;

import org.jetbrains.annotations.NotNull;

public class ShiftAlgorithm implements Algorithm {

    @Override
    public String transform(@NotNull String input, int key, boolean decrypt) {
        if(decrypt) {
            key = 26 - key;
        }

        String abc = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder result = new StringBuilder();
        for(char c : input.toCharArray()) {
            if (abc.indexOf(c) == -1) {
                result.append(c);
                continue;
            }
            char cTransformed = abc.charAt((Character.toLowerCase(c)-'a'+key)%26);
            result.append(cTransformed);
        }
        return result.toString();
    }
}
