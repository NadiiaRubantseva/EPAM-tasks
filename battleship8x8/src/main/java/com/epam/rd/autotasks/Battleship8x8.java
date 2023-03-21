package com.epam.rd.autotasks;

public class Battleship8x8 {
    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {
        long s = Long.MIN_VALUE;
        switch (shot.charAt(0)) {
            case 'A': break;
            case 'B': { s >>>= 1; break; }
            case 'C': { s >>>= 2; break; }
            case 'D': { s >>>= 3; break; }
            case 'E': { s >>>= 4; break; }
            case 'F': { s >>>= 5; break; }
            case 'G': { s >>>= 6; break; }
            case 'H': { s >>>= 7; break; }
        }
        s >>>= (8 * (shot.charAt(1) - 1));
        shots |= s;
        return ships == (ships | s);
    }

    public String state() {

        char empty = '.';         // an empty cell
        char emptyShot = '×';     // an empty cell that has been shot
        char ship = '☐';          // a cell seized by a ship
        char hit = '☒';           // a cell seized by a ship that has been shot

        long check = 1L << 63;

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 64; i++) {

            if (i % 8 == 0) {
                result.append("\n");
            }

            if (Long.bitCount(ships & check) > 0) {

                if (Long.bitCount(shots & check) > 0) result.append(hit);
                else result.append(ship);

            } else {

                if (Long.bitCount(shots & check) > 0) result.append(emptyShot);
                else result.append(empty);

            }

            check = check >>> 1;

        }
        return result.toString();
    }
}

