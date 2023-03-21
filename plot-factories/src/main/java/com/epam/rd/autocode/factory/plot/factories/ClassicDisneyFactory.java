package com.epam.rd.autocode.factory.plot.factories;


import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.Plot;
import com.epam.rd.autocode.factory.plot.PlotFactory;
import com.epam.rd.autocode.factory.plot.plots.ClassicDisneyPlot;

public class ClassicDisneyFactory implements PlotFactory {

    private final Character hero;
    private final Character beloved;
    private final Character villain;

    public ClassicDisneyFactory(Character hero, Character beloved, Character villain) {
        this.hero = hero;
        this.beloved = beloved;
        this.villain = villain;
    }

    @Override
    public Plot plot() {
        return new ClassicDisneyPlot(hero, beloved, villain);
    }
}
