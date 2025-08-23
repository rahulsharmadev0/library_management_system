package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * A generic table formatter for console output that eliminates table creation duplication.
 * Inspired by the ConsoleTable library for flexible and reusable table formatting.
 */
public class TableFormatter<T> {
    
    public enum Alignment { LEFT, CENTER, RIGHT }
    
    public enum Style {
        BASIC(new String[][] {
            /* TOP    */ {"+", "-", "+", "+"},
            /* HEADER */ {"|", "H", "|", "|"},
            /* HLINE  */ {"+", "-", "+", "+"},
            /* ROW    */ {"|", "D", "|", "|"},
            /* RLINE  */ {"+", "-", "+", "+"},
            /* BOTTOM */ {"+", "-", "+", "+"}
        }),
        
        LIGHT(new String[][] {
            /* TOP    */ {"┌", "─", "┬", "┐"},
            /* HEADER */ {"│", "H", "│", "│"},
            /* HLINE  */ {"├", "─", "┼", "┤"},
            /* ROW    */ {"│", "D", "│", "│"},
            /* RLINE  */ {"├", "─", "┼", "┤"},
            /* BOTTOM */ {"└", "─", "┴", "┘"}
        }),
        
        MINIMAL(new String[][] {
            /* TOP    */ {null, null, null, null},
            /* HEADER */ {"", "H", " ", ""},
            /* HLINE  */ {null, null, null, null},
            /* ROW    */ {"", "D", " ", ""},
            /* RLINE  */ {null, null, null, null},
            /* BOTTOM */ {null, null, null, null}
        });
        
        private final String[][] pattern;
        
        Style(String[][] pattern) {
            this.pattern = pattern;
        }
        
        public String getPattern(int row, int col) {
            if (pattern[row] == null) return null;
            return pattern[row][col];
        }
    }
    
    private static class ColumnConfig<T> {
        final String header;
        final int width;
        final Function<T, String> valueExtractor;
        final Alignment alignment;
        
        ColumnConfig(String header, int width, Function<T, String> valueExtractor, Alignment alignment) {
            this.header = header;
            this.width = width;
            this.valueExtractor = valueExtractor;
            this.alignment = alignment;
        }
    }
    
    private final List<ColumnConfig<T>> columns = new ArrayList<>();
    private Style style = Style.LIGHT;
    private boolean showIndex = true;
    private String title;
    
    public TableFormatter() {
        this("");
    }
    
    public TableFormatter(String title) {
        this.title = title;
    }
    
    public TableFormatter<T> withStyle(Style style) {
        this.style = style;
        return this;
    }
    
    public TableFormatter<T> withIndex(boolean showIndex) {
        this.showIndex = showIndex;
        return this;
    }
    
    public TableFormatter<T> addColumn(String header, int width, Function<T, String> valueExtractor) {
        return addColumn(header, width, valueExtractor, Alignment.LEFT);
    }
    
    public TableFormatter<T> addColumn(String header, int width, Function<T, String> valueExtractor, Alignment alignment) {
        columns.add(new ColumnConfig<>(header, width, valueExtractor, alignment));
        return this;
    }
    
    public void display(List<T> data) {
        if (title != null && !title.isEmpty()) {
            System.out.println("\n" + title);
        }
        
        if (data.isEmpty()) {
            System.out.println("No data to display.");
            return;
        }
        
        System.out.printf("Total: %d%n%n", data.size());
        
        // Calculate column widths
        int[] widths = calculateColumnWidths();
        
        // Render table
        renderTopBorder(widths);
        renderHeaderRow(widths);
        renderHeaderSeparator(widths);
        renderDataRows(data, widths);
        renderBottomBorder(widths);
    }
    
    private int[] calculateColumnWidths() {
        int columnCount = columns.size() + (showIndex ? 1 : 0);
        int[] widths = new int[columnCount];
        
        int index = 0;
        if (showIndex) {
            widths[index++] = 4; // "No." column width
        }
        
        for (ColumnConfig<T> column : columns) {
            widths[index++] = Math.max(column.width, column.header.length());
        }
        
        return widths;
    }
    
    private void renderTopBorder(int[] widths) {
        renderBorder(widths, 0); // TOP row
    }
    
    private void renderBottomBorder(int[] widths) {
        renderBorder(widths, 5); // BOTTOM row
    }
    
    private void renderHeaderSeparator(int[] widths) {
        renderBorder(widths, 2); // HLINE row
    }
    
    private void renderBorder(int[] widths, int styleRow) {
        if (style.getPattern(styleRow, 0) == null) return;
        
        StringBuilder line = new StringBuilder();
        
        // Left border
        line.append(style.getPattern(styleRow, 0));
        
        for (int i = 0; i < widths.length; i++) {
            // Column content (fill with horizontal line character)
            String fillChar = style.getPattern(styleRow, 1);
            line.append(fillChar.repeat(widths[i] + 2)); // +2 for padding
            
            // Column separator (except for last column)
            if (i < widths.length - 1) {
                line.append(style.getPattern(styleRow, 2));
            }
        }
        
        // Right border
        line.append(style.getPattern(styleRow, 3));
        
        System.out.println(line);
    }
    
    private void renderHeaderRow(int[] widths) {
        if (style.getPattern(1, 0) == null) return;
        
        StringBuilder line = new StringBuilder();
        
        // Left border
        line.append(style.getPattern(1, 0));
        
        int columnIndex = 0;
        
        // Index column header
        if (showIndex) {
            line.append(" ");
            line.append(formatCell("No.", widths[columnIndex], Alignment.CENTER));
            line.append(" ");
            if (columnIndex < widths.length - 1) {
                line.append(style.getPattern(1, 2));
            }
            columnIndex++;
        }
        
        // Data column headers
        for (ColumnConfig<T> column : columns) {
            line.append(" ");
            line.append(formatCell(column.header, widths[columnIndex], Alignment.CENTER));
            line.append(" ");
            if (columnIndex < widths.length - 1) {
                line.append(style.getPattern(1, 2));
            }
            columnIndex++;
        }
        
        // Right border
        line.append(style.getPattern(1, 3));
        
        System.out.println(line);
    }
    
    private void renderDataRows(List<T> data, int[] widths) {
        for (int rowIndex = 0; rowIndex < data.size(); rowIndex++) {
            T item = data.get(rowIndex);
            renderDataRow(item, rowIndex + 1, widths);
        }
    }
    
    private void renderDataRow(T item, int rowNumber, int[] widths) {
        if (style.getPattern(3, 0) == null) return;
        
        StringBuilder line = new StringBuilder();
        
        // Left border
        line.append(style.getPattern(3, 0));
        
        int columnIndex = 0;
        
        // Index column
        if (showIndex) {
            line.append(" ");
            line.append(formatCell(String.valueOf(rowNumber), widths[columnIndex], Alignment.RIGHT));
            line.append(" ");
            if (columnIndex < widths.length - 1) {
                line.append(style.getPattern(3, 2));
            }
            columnIndex++;
        }
        
        // Data columns
        for (ColumnConfig<T> column : columns) {
            String value = column.valueExtractor.apply(item);
            line.append(" ");
            line.append(formatCell(value, widths[columnIndex], column.alignment));
            line.append(" ");
            if (columnIndex < widths.length - 1) {
                line.append(style.getPattern(3, 2));
            }
            columnIndex++;
        }
        
        // Right border
        line.append(style.getPattern(3, 3));
        
        System.out.println(line);
    }
    
    private String formatCell(String content, int width, Alignment alignment) {
        if (content == null) content = "";
        
        // Truncate if too long
        if (content.length() > width) {
            content = content.substring(0, width - 3) + "...";
        }
        
        // Pad according to alignment
        return switch (alignment) {
            case LEFT -> String.format("%-" + width + "s", content);
            case RIGHT -> String.format("%" + width + "s", content);
            case CENTER -> {
                int totalPadding = width - content.length();
                int leftPadding = totalPadding / 2;
                int rightPadding = totalPadding - leftPadding;
                yield " ".repeat(leftPadding) + content + " ".repeat(rightPadding);
            }
        };
    }
    
    // Static convenience methods for quick table creation
    public static <T> TableFormatter<T> create() {
        return new TableFormatter<>();
    }
    
    public static <T> TableFormatter<T> create(String title) {
        return new TableFormatter<>(title);
    }
    
    public static <T> TableFormatter<T> builder(String title) {
        return new TableFormatter<>(title);
    }
}