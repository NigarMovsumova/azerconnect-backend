package az.azerconnect.azerconnectbackend

import az.azerconnect.azerconnectbackend.exception.EmptyMsisdnListException
import az.azerconnect.azerconnectbackend.exception.EmptyRequestException
import az.azerconnect.azerconnectbackend.model.MsisdnRequest
import az.azerconnect.azerconnectbackend.service.impl.EligibilityCheckServiceImpl
import spock.lang.Specification

class EligibilityCheckServiceImplTest extends Specification {

    private EligibilityCheckServiceImpl eligibilityCheckService

    def setup() {
        eligibilityCheckService = new EligibilityCheckServiceImpl()
    }

    def "check if isEligibleToSell method throws correct exception when msisdnRequest is null"() {
        when:
        eligibilityCheckService.isEligibleToSell(null)
        then:
        thrown EmptyRequestException
    }

    def "check if isEligibleToSell method throws correct exception when msisdnList is empty"() {
        when:
        eligibilityCheckService.isEligibleToSell(new MsisdnRequest())
        then:
        thrown EmptyMsisdnListException
    }

    def "check if isEligibleToSell method returns correct map with correct request"() {
        setup:
        List<String> msisdnList = new ArrayList<>(Arrays.asList("994702000123", "994702131234", "994701131234"))
        String whiteListString = TestUtility.mockWhiteList
        String blackListString = TestUtility.mockBlackList
        MsisdnRequest msisdnRequest = new MsisdnRequest()
        msisdnRequest.setMsisdnList(msisdnList)
        msisdnRequest.setWhiteListString(whiteListString)
        msisdnRequest.setBlackListString(blackListString)
        Map<String, String> resultMap = new HashMap<>()
        resultMap.put("702131234", "OK")
        resultMap.put("702000123", "msisdn = 702000123 is in blacklist")
        resultMap.put("701131234", "msisdn = 701131234 is not in whitelist")
        expect:
        eligibilityCheckService.isEligibleToSell(msisdnRequest) == resultMap
    }
}
