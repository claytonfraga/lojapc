package com.ufes.lojapc.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class Util {

    private Util() {

    }

    public static String removeAcentos(String str) {
        String nfdNormalizedString = Normalizer.normalize(str, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

}
