package com.epam.rd.autocode.factory.plot;

import com.epam.rd.autocode.factory.plot.factories.ClassicDisneyFactory;
import com.epam.rd.autocode.factory.plot.factories.ContemporaryDisneyFactory;
import com.epam.rd.autocode.factory.plot.factories.MarvelFactory;

class PlotFactories {

    public PlotFactory classicDisneyPlotFactory(Character hero, Character beloved, Character villain) {
        return new ClassicDisneyFactory(hero, beloved, villain);
    }

    public PlotFactory contemporaryDisneyPlotFactory(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        return new ContemporaryDisneyFactory(hero, epicCrisis, funnyFriend);
    }

    public PlotFactory marvelPlotFactory(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        return new MarvelFactory(heroes, epicCrisis, villain);
    }
}
