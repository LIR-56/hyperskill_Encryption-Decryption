package encryptdecrypt;

public interface Algorithm {

    String transform(String input, int key, boolean decrypt);
}
