package com.efimchick.ifmo.io.filetree;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class FileTreeImpl implements FileTree {
    private static final String EMPTY_STRING = "";
    private static final String SPACE = " ";
    private static final String INDENT = "  ";
    private static final String VERTICAL_BRANCH = "│";
    private static final String CLOSING_BRANCH = "└";
    private static final String BRANCH = "─ ";
    private static final String OPENING_BRANCH = "├";
    private static final String BYTES = "bytes";
    private static final String NEWLINE = "\n";

    @Override
    public Optional<String> tree(Path path) {
        if (path == null || !Files.exists(path)) {
            return Optional.empty();
        }
        File file = path.toFile();
        if (file.isFile()) {
            return Optional.of(printFileNameAndLength(file));
        }
        return Optional.of(getTree(file, EMPTY_STRING).getTree());
    }

    private String printFileNameAndLength(File file) {
        return printNameAndLength(file.getName(), file.length());
    }

    private String printNameAndLength(String name, long size) {
        return name + SPACE + size + SPACE + BYTES;
    }

    private TreeResult getTree(File directory, String indent) {
        List<File> files = Arrays.asList(Objects.requireNonNull(directory.listFiles()));
        files.sort((File a, File b) -> {
            int result = Boolean.compare(a.isFile(), b.isFile());
            if (result == 0) {
                result = a.getName().compareToIgnoreCase(b.getName());
            }
            return result;
        });

        long size = 0;
        StringBuilder treeStringBuilder = new StringBuilder();

        for (File file : files) {
            treeStringBuilder.append(NEWLINE);
            treeStringBuilder.append(indent);

            StringBuilder indentStringBuilder = new StringBuilder(indent);
            boolean isLast = file.equals(files.get(files.size() - 1));
            indentStringBuilder.append(isLast ? SPACE : VERTICAL_BRANCH);
            indentStringBuilder.append(INDENT);
            treeStringBuilder.append(isLast ? CLOSING_BRANCH : OPENING_BRANCH);
            treeStringBuilder.append(BRANCH);
            if (file.isFile()) {
                size += file.length();
                treeStringBuilder.append(printFileNameAndLength(file));
            } else {
                TreeResult treeResult = getTree(file, indentStringBuilder.toString());
                size += treeResult.getSize();
                treeStringBuilder.append(treeResult.getTree());
            }
        }
        String name = directory.getName();
        return new TreeResult(printNameAndLength(name, size) + treeStringBuilder, size);
    }

    private static class TreeResult {
        private final String tree;
        private final long size;

        public TreeResult(String tree, long size) {
            this.tree = tree;
            this.size = size;
        }

        public String getTree() {
            return tree;
        }

        public long getSize() {
            return size;
        }
    }
}
