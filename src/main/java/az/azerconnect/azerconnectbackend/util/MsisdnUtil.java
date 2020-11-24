package az.azerconnect.azerconnectbackend.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class MsisdnUtil {


    public static Map<String, String> checkEligibility(List<String> msisdnList,
                                                       String whiteListPattern,
                                                       String blackListPattern) {
        Map<String, String> eligibilityMap = new HashMap<>();

        for (String msisdn : msisdnList) {
            msisdn = msisdn.substring(3);
            if (isMsisdnValid(msisdn)) {
                if (!Pattern.compile(whiteListPattern).matcher(msisdn).lookingAt()) {
                    eligibilityMap.put(msisdn, format("msisdn = %s is not in whitelist", msisdn));
                }
                if (Pattern.compile(blackListPattern).matcher(msisdn).lookingAt()) {
                    eligibilityMap.put(msisdn, format("msisdn = %s is in blacklist", msisdn));
                }
                if (Pattern.compile(whiteListPattern).matcher(msisdn).lookingAt() &&
                        !Pattern.compile(blackListPattern).matcher(msisdn).lookingAt()) {
                    eligibilityMap.put(msisdn, "OK");
                }
            }
        }
        return eligibilityMap;
    }


    public static boolean isMsisdnValid(String msisdn) {
        if (msisdn == null) {
            return false;
        }
        return Pattern.compile("(70|77|55|99)[0-9]{7}").matcher(msisdn).matches();
    }
}