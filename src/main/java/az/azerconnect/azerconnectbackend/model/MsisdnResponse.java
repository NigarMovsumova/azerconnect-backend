package az.azerconnect.azerconnectbackend.model;

import java.util.Map;

public class MsisdnResponse {

    private Map<String, String> msisdnListResponse;

    public MsisdnResponse() {
    }

    public MsisdnResponse(Map<String, String> msisdnListResponse) {
        this.msisdnListResponse = msisdnListResponse;
    }

    public Map<String, String> getMsisdnListResponse() {
        return msisdnListResponse;
    }

    @Override
    public String toString() {
        return "MsisdnResponse{" +
                "msisdnListResponse=" + msisdnListResponse +
                '}';
    }
}
