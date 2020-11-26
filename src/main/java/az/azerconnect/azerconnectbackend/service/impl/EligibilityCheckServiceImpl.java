package az.azerconnect.azerconnectbackend.service.impl;

import az.azerconnect.azerconnectbackend.exception.EmptyMsisdnListException;
import az.azerconnect.azerconnectbackend.exception.EmptyRequestException;
import az.azerconnect.azerconnectbackend.model.MsisdnRequest;
import az.azerconnect.azerconnectbackend.service.EligibilityCheckService;
import java.util.Map;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import static az.azerconnect.azerconnectbackend.util.MsisdnUtil.checkEligibility;
import static az.azerconnect.azerconnectbackend.util.MsisdnUtil.createRegexPattern;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {
    private static final Logger logger = LogManager.getLogger(EligibilityCheckServiceImpl.class);


    @Override
    public Map<String, String> isEligibleToSell(MsisdnRequest msisdnRequest) {
        logger.info("isEligibleToSell for msisdnList:{}", msisdnRequest);
        if (msisdnRequest == null) {
            throw new EmptyRequestException("Your request is empty");
        }

        if (msisdnRequest.getMsisdnList() == null || msisdnRequest.getMsisdnList().isEmpty()) {
            throw new EmptyMsisdnListException("Number list is not provided");
        }

        Pattern whiteListRegexPattern = null;
        if (msisdnRequest.getWhiteListString() != null && msisdnRequest.getWhiteListString().length() != 0) {
            whiteListRegexPattern = createRegexPattern(msisdnRequest.getWhiteListString());
        }

        Pattern blackListRegexPattern = null;
        if (msisdnRequest.getBlackListString() != null && msisdnRequest.getBlackListString().length() != 0) {
            blackListRegexPattern = createRegexPattern(msisdnRequest.getBlackListString());
        }

        return checkEligibility(msisdnRequest.getMsisdnList(), whiteListRegexPattern, blackListRegexPattern);
    }
}
