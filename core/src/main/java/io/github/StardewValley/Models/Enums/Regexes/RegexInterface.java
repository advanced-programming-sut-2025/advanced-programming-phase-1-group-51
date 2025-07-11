package io.github.StardewValley.Models.Enums.Regexes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface RegexInterface {



    String getPattern();

    default Matcher getMatcher(String input) {
        Matcher matcher = Pattern.compile(this.getPattern()).matcher(input);
        return matcher.matches() ? matcher : null;
    }
}
