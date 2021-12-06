package sortingTool;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;

public class Main {
    private final static byte DATA_TYPE_WORDS = 0;
    private final static byte DATA_TYPE_LINES = 1;
    private final static byte DATA_TYPE_LONGS = 2;

    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter outputStream = new PrintWriter(System.out);
        InputStream inputStream = System.in;
        boolean isSortingTypeNatural = true;
        byte sortingDataType = DATA_TYPE_WORDS;
        Iterator<String> iterator = Arrays.stream(args).iterator();
        String nextArgument = iterator.next();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            switch (nextArgument) {
                case "-sortingType":
                    switch (temp) {
                        case "natural" -> isSortingTypeNatural = true;
                        case "byCount" -> isSortingTypeNatural = false;
                        default -> {
                            System.out.println("No sorting type defined!");
                            return;
                        }
                    }
                    break;
                case "-dataType":
                    switch (temp) {
                        case "long" -> sortingDataType = DATA_TYPE_LONGS;
                        case "line" -> sortingDataType = DATA_TYPE_LINES;
                        case "word" -> sortingDataType = DATA_TYPE_WORDS;
                        default -> {
                            System.out.println("No data type defined!");
                            return;
                        }
                    }
                    break;
                case "-inputFile": {
                    inputStream = new FileInputStream(temp);
                    break;
                }
                case "-outputFile": {
                    outputStream = new PrintWriter(temp);
                    break;
                }
                default: {
                    System.out.printf("%s is not a valid parameter. It will be skipped.", nextArgument);
                }
            }
            if (iterator.hasNext()) {
                nextArgument = iterator.next();
            } else {
                break;
            }
        }
        SortingTool sortingTool = new SortingTool(inputStream, outputStream);
        switch (sortingDataType) {
            case DATA_TYPE_WORDS -> sortingTool.sortWords(isSortingTypeNatural);
            case DATA_TYPE_LINES -> sortingTool.sortLines(isSortingTypeNatural);
            case DATA_TYPE_LONGS -> sortingTool.sortLongs(isSortingTypeNatural);
        }
        outputStream.flush();
    }
}
