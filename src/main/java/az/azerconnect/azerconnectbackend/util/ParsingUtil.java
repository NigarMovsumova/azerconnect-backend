package az.azerconnect.azerconnectbackend.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.String.format;

public class ParsingUtil {

    public static List<String> parseStringToSet(String parsedString) {
        return Arrays.stream(parsedString.split(","))
                .collect(Collectors.toList());
    }

    public static List<List<String>> separateMasksByType(List<String> maskList) {
        List<String> rangeMasks = new LinkedList<>();
        List<String> wildCardMasks = new LinkedList<>();
        List<String> exactMasks = new LinkedList<>();
        for (String mask : maskList) {
            if (mask.endsWith("%")) {
                rangeMasks.add(mask);
            } else if (mask.contains("_")) {
                wildCardMasks.add(mask);
            } else {
                exactMasks.add(mask);
            }
        }
        return new ArrayList<>(Arrays.asList(rangeMasks, wildCardMasks, exactMasks));
    }

    public static Pattern createRegexPattern(List<List<String>> categorizedMasksLists) {
        StringBuilder rangeMaskRegex = new StringBuilder();
        List<String> rangeMaskSet = categorizedMasksLists.get(0);
        for (String rangeMask : rangeMaskSet) {
            rangeMask = rangeMask.substring(0, rangeMask.indexOf("%"));
            rangeMaskRegex.append(rangeMask);
            rangeMaskRegex.append("|");
        }

        /*StringBuilder wildCardRegex = new StringBuilder();
        HashSet<String> wildCardMaskSet = categorizedMasksLists.get(1);
        for (String wildCard:wildCardMaskSet) {
            wildCard = wildCard.replace("_", "([0-9])");
            wildCardRegex.append(wildCard);
        }*/

        StringBuilder exactMaskRegex = new StringBuilder();
        List<String> exactMaskSet = categorizedMasksLists.get(2);
        for (String exactMask : exactMaskSet) {
            exactMaskRegex.append(exactMask);
            exactMaskRegex.append("|");
        }

        /*System.out.println(rangeMaskRegex.toString());
        return Pattern.compile(rangeMaskRegex.substring(0, rangeMaskRegex.toString().length()));*/

        /*System.out.println(wildCardRegex.toString());
        return Pattern.compile(wildCardRegex.substring(0, wildCardRegex.toString().length()));*/
        System.out.println(exactMaskRegex.toString());
        return Pattern.compile(exactMaskRegex.substring(0, exactMaskRegex.toString().length()));
    }

    public static Map<String, String> checkEligibility(List<String> msisdnList,
                                                       Pattern whiteListPattern
            /*Pattern blackListPattern*/) {
        Map<String, String> eligibilityMap = new HashMap<>();

        for (String msisdn : msisdnList) {
            if (whiteListPattern.matcher(msisdn).matches()) {
                /*if (!blackListPattern.matcher(msisdn).matches()) {
                    eligibilityMap.put(msisdn, "OK");
                } else {
                    eligibilityMap.put(msisdn, format("msisdn = %s is in blacklist", msisdn));
                }
            } else {
                eligibilityMap.put(msisdn, format("msisdn = %s is in whitelist", msisdn));
            }
        }*/
                eligibilityMap.put(msisdn, format("msisdn = %s is in whitelist", msisdn));
            }
        }
        return eligibilityMap;
    }
}
