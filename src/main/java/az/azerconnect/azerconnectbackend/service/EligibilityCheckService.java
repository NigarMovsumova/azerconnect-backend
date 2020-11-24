package az.azerconnect.azerconnectbackend.service;

import az.azerconnect.azerconnectbackend.model.MsisdnRequest;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public interface EligibilityCheckService {
    Map<String, String> isEligibleToSell(MsisdnRequest msisdnSaleRequest);
}
