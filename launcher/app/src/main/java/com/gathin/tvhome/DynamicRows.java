package com.gathin.tvhome;

/**
 * How many {@code rows_below_dock} the home grid needs for {@code gridApps} at the current column
 * count. Caps at the Launcher settings maximum (0–3). Used when {@code dynamic_rows} is on so new
 * Home shortcuts grow the visible rows and the saved settings choice follows.
 */
final class DynamicRows {
    static final int MAX = 3;

    private DynamicRows() { }

    /** Rows needed so every grid app fits without scrolling; 0 when there are none. */
    static int needed(int gridApps, int columns) {
        if (gridApps <= 0 || columns <= 0) return 0;
        int rows = (gridApps + columns - 1) / columns;
        return Math.min(MAX, Math.max(0, rows));
    }

    /**
     * Whether the saved {@code rows_below_dock} should move to {@code needed}. Always updates when
     * the needed count differs, so adding apps grows the setting and removing them shrinks it.
     */
    static boolean shouldUpdate(int currentRows, int neededRows) {
        return currentRows != neededRows;
    }
}
