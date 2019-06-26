package encryptdecrypt;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        HashMap<String, String> params = new HashMap<>();
        if (args.length % 2 != 0) {
            System.out.println("Error! Wrong args!");
            return;
        }

        for( int i = 0; i < args.length; i+=2) {
            params.put(args[i], args[i+1]);
        }

        String inputString;
        try {
            inputString = reader(params);
        } catch (FileNotFoundException e) {
            return;
        }

        int key;

        try {
            key = Integer.parseInt(params.get("-key"));
        } catch (Exception e) {
            System.out.println("Wrong key! Key must be a number!");
            return;
        }



        Algorithm alg;
        if (!params.containsKey("-alg")) {
            System.out.println("Please specify algorithm with -alg flag");
            return;
        }
        switch (params.get("-alg")) {
            case "shift": {
                alg = new ShiftAlgorithm();
                break;
            }
            case "unicode": {
                alg = new UnicodeAlgorithm();
                break;
            }
            default: {
                System.out.println("Wrong algorithm!");
                return;
            }
        }

        boolean decrypt = params.containsKey("-mode") && "dec".equals(params.get("-mode"));

        writer(params, alg.transform(inputString, key, decrypt));
    }

    @NotNull
    static String reader(HashMap<String, String> params) throws FileNotFoundException {
        if(params.containsKey("-in")) {
            File inputFile = new File(params.get("-in"));
            try (Scanner sc = new Scanner(inputFile)) {
                return sc.nextLine();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
                throw e;
            }
        } else if(params.containsKey("-data")) {
            return params.get("-data");
        } else {
            Scanner sc = new Scanner(System.in);
            return sc.nextLine();
        }
    }

    static void writer(HashMap<String, String> params, String result) {
        if (params.containsKey("-out")) {
            File output = new File(params.get("-out"));
            try (FileWriter writer = new FileWriter(output)) {
                writer.write(result);
            } catch (IOException e) {
                System.out.println("Can't write to file: " + e.getMessage());
            }
        } else {
            System.out.println(result);
        }
    }

}
