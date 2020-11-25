package az.azerconnect.azerconnectbackend.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

import static java.lang.String.format;

public class MsisdnUtil {


    public static Map<String, String> checkEligibility(List<String> msisdnList,
                                                       Pattern whiteListPattern,
                                                       Pattern blackListPattern) {
        Map<String, String> eligibilityMap = new HashMap<>();

        for (String msisdn : msisdnList) {
            msisdn = msisdn.substring(3);
            if (isMsisdnValid(msisdn)) {
                if (blackListPattern.matcher(msisdn).lookingAt()) {
                    eligibilityMap.put(msisdn, format("msisdn = %s is in blacklist", msisdn));
                } else if (!whiteListPattern.matcher(msisdn).lookingAt()) {
                    eligibilityMap.put(msisdn, format("msisdn = %s is not in whitelist", msisdn));
                } else if (whiteListPattern.matcher(msisdn).lookingAt() &&
                        !blackListPattern.matcher(msisdn).lookingAt()) {
                    eligibilityMap.put(msisdn, "OK");
                }
            }
        }
        return eligibilityMap;
    }

    public static Pattern createRegexPattern(String listString) {
        if (listString == null) {
            listString = "$^";
        }

        String regexString = listString.replace("%,", "|").replace("_", "([0-9])")
                .replace(",", "|");
        if (regexString.endsWith("|")) {
            regexString = StringUtils.chop(regexString);
        }
        return Pattern.compile(regexString);
    }


    public static boolean isMsisdnValid(String msisdn) {
        if (msisdn == null) return false;
        return Pattern.compile("(70|77|55|99)[0-9]{7}").matcher(msisdn).matches();
    }
}