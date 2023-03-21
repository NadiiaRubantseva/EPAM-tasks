package com.epam.rd.autocode.factory.plot.plots;

import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.EpicCrisis;
import com.epam.rd.autocode.factory.plot.Plot;

public class ContemporaryDisneyPlot implements Plot {


    private final Character hero;
    private final EpicCrisis epicCrisis;
    private final Character funnyFriend;

    public ContemporaryDisneyPlot(Character hero, EpicCrisis epicCrisis, Character funnyFriend) {
        this.hero = hero;
        this.epicCrisis = epicCrisis;
        this.funnyFriend = funnyFriend;

        asText();
    }

    @Override
    public String asText() {
        return String.format("In the beginning, %s feels a bit awkward and uncomfortable. " +
                "But personal issue fades, when a big trouble comes - %s. " +
                "%s stands up against it, but with no success at first. " +
                "But then, by putting self together and with the help of friends, including spectacular, funny %s, " +
                "%s restores the spirit, overcomes the crisis and gains gratitude and respect.",
                hero.name(), epicCrisis.name(), hero.name(), funnyFriend.name(), hero.name());
    }
}
