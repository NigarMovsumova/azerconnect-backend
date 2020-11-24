package az.azerconnect.azerconnectbackend.service.impl;

import az.azerconnect.azerconnectbackend.exception.NullException;
import az.azerconnect.azerconnectbackend.model.MsisdnRequest;
import az.azerconnect.azerconnectbackend.service.EligibilityCheckService;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import static az.azerconnect.azerconnectbackend.util.MsisdnUtil.checkEligibility;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    private static String createRegexPattern(String listString) {
        if (listString == null) {
            listString = "$^";
        }
        return StringUtils.chop(
                listString
                        .replace("%,", "|")
                        .replace("_", "([0-9])")
                        .replace(",", "|"));
    }

    @Override
    public Map<String, String> isEligibleToSell(MsisdnRequest msisdnRequest) {
        if (msisdnRequest == null) {
            throw new NullException("Your request is empty");
        }

        if (msisdnRequest.getMsisdnList() == null || msisdnRequest.getMsisdnList().isEmpty()) {
            throw new NullException("Number List is not provided");
        }

        String whiteListRegexPattern = createRegexPattern(msisdnRequest.getWhiteListString());
        String blackListRegexPattern = createRegexPattern(msisdnRequest.getBlackListString());
        return checkEligibility(msisdnRequest.getMsisdnList(), whiteListRegexPattern, blackListRegexPattern);
    }
}
