package br.com.dio.util;

public final class BoardTemplate {

    private BoardTemplate() {}

    private static final String ROW_DIVIDER = 
            "*************************************************************************************";
    private static final String CELL_DIVIDER = 
            "*|-------||-------||-------|*|-------||-------||-------|*|-------||-------||-------|*";
    private static final String EMPTY_ROW = 
            "*|       ||       ||       |*|       ||       ||       |*|       ||       ||       |*";

    public static String generateBoard(String[][] board) {
        StringBuilder builder = new StringBuilder();
        builder.append(ROW_DIVIDER).append("\n");
        builder.append(generateHeader()).append("\n");

        for (int i = 0; i < 9; i++) {
            builder.append(EMPTY_ROW).append("\n");
            builder.append(generateRow(board[i], i)).append("\n");
            builder.append(EMPTY_ROW).append("\n");
            if (i % 3 == 2 && i != 8) {
                builder.append(CELL_DIVIDER).append("\n");
            }
        }

        builder.append(ROW_DIVIDER);
        return builder.toString();
    }

    private static String generateHeader() {
        return "*|---0---||---1---||---2---|*|---3---||---4---||---5---|*|---6---||---7---||---8---|*";
    }

    private static String generateRow(String[] row, int rowIndex) {
        StringBuilder builder = new StringBuilder();
        builder.append(rowIndex).append("|");
        for (int i = 0; i < 9; i++) {
            builder.append(String.format("  %s   |", row[i] == null ? " " : row[i]));
            if (i % 3 == 2 && i != 8) {
                builder.append("*|");
            } else if (i != 8) {
                builder.append("|");
            }
        }
        builder.append(rowIndex);
        return builder.toString();
    }
}
