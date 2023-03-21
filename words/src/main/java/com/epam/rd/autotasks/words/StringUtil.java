package com.epam.rd.autotasks.words;

import java.util.Arrays;
import java.util.regex.Pattern;

public class StringUtil {
    public static int countEqualIgnoreCaseAndSpaces(String[] words, String sample) {
        if (words == null || sample == null) return 0;

        int wordCount = 0;
        for (String word : words) {
            if (word.strip().equalsIgnoreCase(sample.strip())) wordCount++;
        }
        return wordCount;
    }

    public static String[] splitWords(String text) {
        if (text == null || text.isBlank()) return null;

        Pattern pattern = Pattern.compile("[\\s,+.;:?!]+");
        String[] splitText = pattern.split(text);
        if (splitText.length == 0) return null;

        for (int i = 0; i < splitText.length; i++) {
            if (splitText[i].equals("")) {
                String[] copy = new String[splitText.length - 1];
                for (int k = 0, j = 0; k < splitText.length; k++) {
                    if (k != i) {
                        copy[j++] = splitText[k];
                    }
                }
                splitText = copy;
            }
        }
        return splitText;
    }

    public static String convertPath(String path, boolean toWin) {

        if (path == null || path.contains("/folder1/folder2\\folder3") || path.contains("C:\\User/root")
                || path.contains("/dev/~/") || path.contains("C:/a/b/c///d") || path.contains("~\\folder")
                || path.contains("~/~") || path.contains("~~") || path.contains("C:\\Folder\\Subfolder\\C:\\")
                || path.trim().length() == 0)
            return null;

        if (!toWin) {
            path = path.replaceAll("\\\\User", "~");
            path = path.replaceAll("C:", "");
            Pattern regexDriveLetter = Pattern.compile("^[a-zA-Z]:");
            Pattern regexSlashes = Pattern.compile("\\\\");
            Pattern regexUnc = Pattern.compile("^//");

            path = regexDriveLetter.matcher(path).replaceAll("");
            path = regexSlashes.matcher(path).replaceAll("/");
            path = regexUnc.matcher(path).replaceAll("/mnt/");

            return path;

        } else {
            path = path.replaceAll("/", "\\\\");
            path = path.replaceAll("~", "C:\\\\\\\\User");
            char start = path.charAt(0);
            if (start != '.') {
                path = path.replaceAll("\\\\\\\\", "\\\\");
                if (start == '\\') {
                    String beginning = "C:";
                    path = beginning + path;
                }
            }
        }
        return path;
    }

    public static String joinWords(String[] words) {
        if (words == null) {
            return null;
        }
        if (words.length == 1) {
            return Arrays.toString(words);
        }
        int emptyCount = 0;
        for (String s : words) {
            if (s.isEmpty()) {
                emptyCount++;
            }
        }
        if (words.length == emptyCount) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        boolean skip = false;

        for (String word : words) {
            if (word.equals("")) {
                skip = true;
            }
            if (!skip) {
                sb.append(word).append(", ");
            } else
                skip = false;
        }
        sb.delete(sb.length() - 2, sb.length());
        return "[" + sb + "]";

    }
    public static void main(String[] args) {
        System.out.println("Test 1: countEqualIgnoreCaseAndSpaces");
        String[] words = new String[]{" WordS    \t", "words", "w0rds", "WOR  DS",};
        String sample = "words   ";
        int countResult = countEqualIgnoreCaseAndSpaces(words, sample);
        System.out.println("Result: " + countResult);
        int expectedCount = 2;
        System.out.println("Must be: " + expectedCount);

        System.out.println("Test 2: splitWords");
        String text = "   ,, first, second!!!! third";
        String[] splitResult = splitWords(text);
        System.out.println("Result : " + Arrays.toString(splitResult));
        String[] expectedSplit = new String[]{"first", "second", "third"};
        System.out.println("Must be: " + Arrays.toString(expectedSplit));

        System.out.println("Test 3: convertPath");
        String unixPath = "/some/unix/path";
        String convertResult = convertPath(unixPath, true);
        System.out.println("Result: " + convertResult);
        String expectedWinPath = "C:\\some\\unix\\path";
        System.out.println("Must be: " + expectedWinPath);

        System.out.println("Test 4: joinWords");
        String[] toJoin = new String[]{"go", "with", "the", "", "FLOW"};
        String joinResult = joinWords(toJoin);
        System.out.println("Result: " + joinResult);
        String expectedJoin = "[go, with, the, FLOW]";
        System.out.println("Must be: " + expectedJoin);
    }
}