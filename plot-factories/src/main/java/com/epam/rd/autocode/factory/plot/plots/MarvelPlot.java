package com.epam.rd.autocode.factory.plot.plots;

import com.epam.rd.autocode.factory.plot.Character;
import com.epam.rd.autocode.factory.plot.EpicCrisis;
import com.epam.rd.autocode.factory.plot.Plot;

public class MarvelPlot implements Plot {

    private final Character[] heroes;
    private final EpicCrisis epicCrisis;
    private final Character villain;

    public MarvelPlot(Character[] heroes, EpicCrisis epicCrisis, Character villain) {
        this.heroes = heroes;
        this.epicCrisis = epicCrisis;
        this.villain = villain;

        asText();
    }

    @Override
    public String asText() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Character c : heroes) {
            stringBuilder.append("the brave ").append(c.name());
            if (c != heroes[heroes.length - 1]) {
                stringBuilder.append(", ");
                if (heroes.length >= 2 && c == heroes[heroes.length - 2]) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    stringBuilder.append(" and ");
                }
            }
        }
        return String.format("%s threatens the world." +
                " But %s %s on guard." +
                " So, no way that intrigues of %s will bend the willpower of%s inflexible hero%s",
                epicCrisis.name(), stringBuilder, heroes.length == 1 ? "is" : "are", villain.name(), heroes.length == 1 ? " the" : "", heroes.length == 1 ? "." : "es.");
    }
}
