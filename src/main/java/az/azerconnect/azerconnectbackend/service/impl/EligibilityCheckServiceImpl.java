package az.azerconnect.azerconnectbackend.service.impl;

import az.azerconnect.azerconnectbackend.exception.NullException;
import az.azerconnect.azerconnectbackend.model.MsisdnRequest;
import az.azerconnect.azerconnectbackend.service.EligibilityCheckService;
import az.azerconnect.azerconnectbackend.util.ParsingUtil;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

import static az.azerconnect.azerconnectbackend.util.ParsingUtil.checkEligibility;
import static az.azerconnect.azerconnectbackend.util.ParsingUtil.createRegexPattern;
import static az.azerconnect.azerconnectbackend.util.ParsingUtil.parseStringToSet;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    @Override
    public Map<String, String> isEligibleToSell(MsisdnRequest msisdnRequest) {
        if (msisdnRequest == null || msisdnRequest.getBlackListString() == null) {
            throw new NullException("Request is empty");
        }

        if (msisdnRequest.getMsisdnList() == null) {
            throw new NullException("Msisdn List is not provided");
        }

        List<String> whiteList = parseStringToSet(msisdnRequest.getWhiteListString());
        List<String> blackList = parseStringToSet(msisdnRequest.getBlackListString());
        List<List<String>> separatedWhiteListsMasks = ParsingUtil.separateMasksByType(whiteList);
        List<List<String>> separatedBlackListMasks = ParsingUtil.separateMasksByType(blackList);
        Pattern whiteListRegex = createRegexPattern(separatedWhiteListsMasks);
        //Pattern blackListRegex = createRegexPattern(separatedBlackListMasks);
        return checkEligibility(msisdnRequest.getMsisdnList(), whiteListRegex/*, blackListRegex*/);
    }
}
