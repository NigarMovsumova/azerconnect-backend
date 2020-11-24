package az.azerconnect.azerconnectbackend.controller;

import az.azerconnect.azerconnectbackend.model.MsisdnRequest;
import az.azerconnect.azerconnectbackend.service.EligibilityCheckService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EligibilityController {

    private final EligibilityCheckService eligibilityCheckService;

    public EligibilityController(EligibilityCheckService eligibilityCheckService) {
        this.eligibilityCheckService = eligibilityCheckService;
    }

    @GetMapping("masklist/iseligible")
    public Map<String, String> isEligibleToSell(MsisdnRequest msisdnRequest) {
        return eligibilityCheckService.isEligibleToSell(msisdnRequest);
    }
}
