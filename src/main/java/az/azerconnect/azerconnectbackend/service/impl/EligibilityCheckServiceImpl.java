package az.azerconnect.azerconnectbackend.service.impl;

import az.azerconnect.azerconnectbackend.exception.EmptyRequestException;
import az.azerconnect.azerconnectbackend.exception.NullException;
import az.azerconnect.azerconnectbackend.model.MsisdnRequest;
import az.azerconnect.azerconnectbackend.service.EligibilityCheckService;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

import static az.azerconnect.azerconnectbackend.util.MsisdnUtil.checkEligibility;
import static az.azerconnect.azerconnectbackend.util.MsisdnUtil.createRegexPattern;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    @Override
    public Map<String, String> isEligibleToSell(MsisdnRequest msisdnRequest) {
        if (msisdnRequest == null) {
            throw new EmptyRequestException("Your request is empty");
        }

        if (msisdnRequest.getMsisdnList() == null || msisdnRequest.getMsisdnList().isEmpty()) {
            throw new NullException("Number list is not provided");
        }

        Pattern whiteListRegexPattern = createRegexPattern(msisdnRequest.getWhiteListString());
        Pattern blackListRegexPattern = createRegexPattern(msisdnRequest.getBlackListString());
        return checkEligibility(msisdnRequest.getMsisdnList(), whiteListRegexPattern, blackListRegexPattern);
    }
}
