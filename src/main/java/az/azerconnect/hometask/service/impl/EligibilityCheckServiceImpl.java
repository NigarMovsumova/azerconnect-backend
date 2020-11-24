package az.azerconnect.hometask.service.impl;

import az.azerconnect.hometask.exception.NullException;
import az.azerconnect.hometask.model.MsisdnRequest;
import az.azerconnect.hometask.service.EligibilityCheckService;

import az.azerconnect.hometask.util.ParsingUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.springframework.stereotype.Service;

import static az.azerconnect.hometask.util.ParsingUtil.checkEligibility;
import static az.azerconnect.hometask.util.ParsingUtil.createRegexPattern;
import static az.azerconnect.hometask.util.ParsingUtil.parseStringToSet;

@Service
public class EligibilityCheckServiceImpl implements EligibilityCheckService {

    @Override
    public Map<String, String> isEligibleToSell(MsisdnRequest msisdnRequest) {
        if (msisdnRequest == null || msisdnRequest.getBlackListString() == null){
            throw new NullException("Request is empty");
        }

        if (msisdnRequest.getMsisdnList() == null){
            throw new NullException("Msisdn List is not provided");
        }

        Set<String> whiteList = parseStringToSet(msisdnRequest.getWhiteListString());
        Set<String> blackList = parseStringToSet(msisdnRequest.getBlackListString());
        List<HashSet<String>> separatedWhiteListsMasks = ParsingUtil.separateMasksByType(whiteList);
        List<HashSet<String>> separatedBlackListMasks = ParsingUtil.separateMasksByType(blackList);
        Pattern whiteListRegex = createRegexPattern(separatedWhiteListsMasks);
        //Pattern blackListRegex = createRegexPattern(separatedBlackListMasks);
        return checkEligibility(msisdnRequest.getMsisdnList(), whiteListRegex/*, blackListRegex*/);
    }
}
