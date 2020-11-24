package az.azerconnect.hometask.service;

import az.azerconnect.hometask.model.MsisdnRequest;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public interface EligibilityCheckService {
    Map<String, String> isEligibleToSell(MsisdnRequest msisdnSaleRequest);
}
