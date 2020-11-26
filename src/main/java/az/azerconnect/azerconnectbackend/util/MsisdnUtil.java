package az.azerconnect.azerconnectbackend.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static java.lang.String.format;

public class MsisdnUtil {

    private static final Logger logger = LogManager.getLogger(MsisdnUtil.class);


    public static Map<String, String> checkEligibility(List<String> msisdnList,
                                                       Pattern whiteListPattern,
                                                       Pattern blackListPattern) {
        logger.info("checkEligibility start for msisdnList:{} with whiteListPattern:{} and blackListPattern:{}",
                msisdnList, whiteListPattern, blackListPattern);
        Map<String, String> eligibilityMap = new HashMap<>();

        for (String msisdn : msisdnList) {
            msisdn = msisdn.substring(3);
            if (isMsisdnValid(msisdn)) {
                logger.info("{} is valid", msisdn);
                if (blackListPattern != null && blackListPattern.matcher(msisdn).lookingAt()) {
                    logger.info("{} has a match in blackList", msisdn);
                    eligibilityMap.put(msisdn, format("msisdn = %s is in blacklist", msisdn));
                } else if (whiteListPattern != null && !whiteListPattern.matcher(msisdn).lookingAt()) {
                    logger.info("{} does not have a match in whiteList", msisdn);
                    eligibilityMap.put(msisdn, format("msisdn = %s is not in whitelist", msisdn));
                } else if (whiteListPattern != null &&
                        blackListPattern != null &&
                        whiteListPattern.matcher(msisdn).lookingAt() &&
                        !blackListPattern.matcher(msisdn).lookingAt()) {
                    logger.info("{} can be sold", msisdn);
                    eligibilityMap.put(msisdn, "OK");
                }
            }
        }
        return eligibilityMap;
    }

    public static Pattern createRegexPattern(String listString) {
        logger.info("createRegexPattern start for listString:{}", listString);

        String regexString = listString.replace("%,", "|").replace("_", "([0-9])")
                .replace(",", "|");
        if (regexString.endsWith("|")) {
            regexString = StringUtils.chop(regexString);
        }
        logger.info("createRegexPattern end with regexString:{}", regexString);
        return Pattern.compile(regexString);
    }


    public static boolean isMsisdnValid(String msisdn) {
        logger.info("isMsisdnValid start for msisdn:{}", msisdn);
        if (msisdn == null) return false;
        return Pattern.compile("(70|77|55|99)[0-9]{7}").matcher(msisdn).matches();
    }
}