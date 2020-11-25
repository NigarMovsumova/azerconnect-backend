package az.azerconnect.azerconnectbackend

import az.azerconnect.azerconnectbackend.util.MsisdnUtil
import spock.lang.Specification

import java.util.regex.Pattern

class MsisdnUtilSpec extends Specification {

    def "check if msisdn valid"(String msisdn, boolean isValid) {
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

    def "check if numbers are eligible for sale by matching them with white/black list regex patterns"() {
        List<String> msisdnList = new ArrayList<>(Arrays.asList("994702000123", "994702131234", "994701131234"))
        Pattern whiteListPattern = Pattern.compile("7020|70213|70214|70215|70216|70217|70218|70219|7022|7023|7024|7025|7026|7027|7029|703|70571|70572|70573|70574|70575|70576|70577|70578|70579|7058|7059|706|707|709|77270|773|775|7760|7761|7762|7763|7771|7772|7773|7774|7775|7776|70406|70412|70413|70421|70422|70430|70431|70432|70434|70438|70439|70440|70441|70449|70477|70478|70479|70491|70493|70494|70495|70498|70436|70490|70437|70492|70497|70447|70448|70433|77271|77251|70500|70501|70502|70503|70504|70506|70507|70508|70509|7052|7053|7054|70550|70551|70552|70553|70554|70556|70557|70558|70559|7056|70402|70403")
        Pattern blackListPattern = Pattern.compile("702000|70201|702996|702997|702998|702999|703996|703997|703998|703999|706996|706997|706998|706999|7070|707996|707997|707998|707999|70900|709996|709997|709998|709999|77331111|773344444|773355555|773366666|773388888|773399999|7733([0-9])3333|773331111|773332222|773334444|773335555|773336666|773337777|773338888|773339999|77333([0-9])333|773333([0-9])33|7733333([0-9])3|70210|70211|70212|708|70505|70555|7720|7721|7722|7723|7724|77250|77252|77253|77254|77255|77256|77257|77258|77259|7726|77272|7728|7729|77210|77211|77212|774|778|779|70400|70401|70404|70405|70407|70408|70409|70410|70411|70414|70415|70416|70417|70418|70419|70420|70423|70424|70425|70426|70427|70428|70429|70435|70442|70443|70444|70445|70446|70450|70451|70452|70453|70454|70455|70456|70457|70458|70459|70460|70461|70462|70463|70464|70465|70466|70467|70468|70469|70470|70471|70472|70473|70474|70475|70476|70480|70481|70482|70483|70484|70485|70486|70487|70488|70489|70496|70499|77235|772354|776478|70512|77500|77550|77555")
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
        null                  | Pattern.compile("\$^")
        "772854774"           | Pattern.compile("772854774")
    }

}
