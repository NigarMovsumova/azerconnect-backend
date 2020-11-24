package az.azerconnect.hometask.model;

import java.util.Set;

public class MsisdnRequest {
    private Set<String> msisdnList;

    private String blackListString;

    private String whiteListString;


    public MsisdnRequest() {
    }

    public MsisdnRequest(String blackListString,
                         String whiteListString) {
        this.blackListString = blackListString;
        this.whiteListString = whiteListString;
    }

    public Set<String> getMsisdnList() {
        return msisdnList;
    }

    public void setMsisdnList(Set<String> msisdnList) {
        this.msisdnList = msisdnList;
    }

    public String getBlackListString() {
        return blackListString;
    }

    public void setBlackListString(String blackListString) {
        this.blackListString = blackListString;
    }

    public String getWhiteListString() {
        return whiteListString;
    }

    public void setWhiteListString(String whiteListString) {
        this.whiteListString = whiteListString;
    }

    @Override
    public String toString() {
        return "MsisdnRequest{" +
                "msisdnList=" + msisdnList +
                ", blackListString='" + blackListString + '\'' +
                ", whiteListString='" + whiteListString + '\'' +
                '}';
    }
}
