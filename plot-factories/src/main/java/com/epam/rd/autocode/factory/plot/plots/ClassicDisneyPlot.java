package com.epam.rd.autocode.factory.plot.plots;

import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.Plot;


public class ClassicDisneyPlot implements Plot {

    private final Character hero;
    private final Character beloved;
    private final Character villain;

    public ClassicDisneyPlot(Character hero, Character beloved, Character villain) {
        this.hero = hero;
        this.beloved = beloved;
        this.villain = villain;

        asText();
    }

    @Override
    public String asText() {
        return String.format("%s is young and adorable. " +
                             "%s and %s love each other. " +
                             "%s interferes with their happiness, but loses in the end.",
                hero.name(), hero.name(), beloved.name(), villain.name());
    }
}