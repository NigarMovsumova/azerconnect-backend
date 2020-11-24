package az.azerconnect.azerconnectbackend

import az.azerconnect.azerconnectbackend.util.MsisdnUtil
import spock.lang.Specification

class MsisdnUtilSpec extends Specification {

    def "is msisdn valid"(String msisdn, boolean isValid) {
        expect:
        MsisdnUtil.isMsisdnValid(msisdn) == isValid
        where:
        msisdn          | isValid
        "1"             | false
        "994702769481"  | false
        "702769481"     | true
        "+994702769481" | false
        null            | false
        "55fdf8888"     | false
        "test msisdn"   | false
        "990000000"     | true
        ""              | false
        "772854774"     | true
    }
}
