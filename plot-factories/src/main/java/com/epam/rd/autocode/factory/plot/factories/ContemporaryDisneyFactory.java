package com.epam.rd.autocode.factory.plot.factories;

import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.EpicCrisis;
import com.epam.rd.autocode.factory.plot.Plot;
import com.epam.rd.autocode.factory.plot.PlotFactory;
import com.epam.rd.autocode.factory.plot.plots.ContemporaryDisneyPlot;


public class ContemporaryDisneyFactory implements PlotFactory {

    private final Character hero;
    private final EpicCrisis epicCrisis;
    private final Character funnyFriend;

    public ContemporaryDisneyFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        this.hero = hero;
        this.epicCrisis = epicCrisis;
        this.funnyFriend = funnyFriend;
    }

    @Override
    public Plot plot() {
        return new ContemporaryDisneyPlot(hero, epicCrisis, funnyFriend);
    }
}
