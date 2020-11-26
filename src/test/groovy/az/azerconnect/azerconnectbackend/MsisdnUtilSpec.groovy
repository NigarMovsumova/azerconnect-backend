package az.azerconnect.azerconnectbackend

import az.azerconnect.azerconnectbackend.util.MsisdnUtil
import spock.lang.Specification

import java.util.regex.Pattern

class MsisdnUtilSpec extends Specification {

    def "check if isMsisdnValid method recognizes correct/incorrect msisdns"(String msisdn, boolean isValid) {
        expect:
        MsisdnUtil.isMsisdnValid(msisdn) == isValid
        where:
        msisdn          | isValid
        "1"             | false
        "994702769481"  | false
        "702769481"     | true
        "505532280"     | false
        "512299204"     | false
        "+994702769481" | false
        null            | false
        "55fdf8888"     | false
        "test msisdn"   | false
        "990000000"     | true
        ""              | false
        "772854774"     | true
        "7027694810"    | false
        "70"            | false
        ""              | false
        "772769481"     | true
        "99477"         | false
        "702aer9481"    | false
        "070276948"     | false
        "0702769481"    | false
    }

    def "check if numbers are eligible for sale by matching them with white/black list regex patterns"() {
        List<String> msisdnList = new ArrayList<>(Arrays.asList("994702000123", "994702131234", "994701131234"))
        Pattern whiteListPattern = TestUtility.mockWhiteListPattern
        Pattern blackListPattern = TestUtility.mockBlackListPattern
        Map<String, String> resultMap = new HashMap<>()
        resultMap.put("702131234", "OK")
        resultMap.put("702000123", "msisdn = 702000123 is in blacklist")
        resultMap.put("701131234", "msisdn = 701131234 is not in whitelist")
        expect:
        MsisdnUtil.checkEligibility(msisdnList, whiteListPattern, blackListPattern) == resultMap
    }

    def "create correct regex pattern for both types of lists(whitelist, blacklist)"(String listString,
                                                                                     Pattern regexPattern) {

        expect:
        MsisdnUtil.createRegexPattern(listString).pattern() == regexPattern.pattern()
        where:
        listString            | regexPattern
        "1"                   | Pattern.compile("1")
        "702769481"           | Pattern.compile("702769481")
        "702769481,702558585" | Pattern.compile("702769481|702558585")
        "772854774"           | Pattern.compile("772854774")
    }

}
