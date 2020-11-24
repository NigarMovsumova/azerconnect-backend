package az.azerconnect.azerconnectbackend.controller;

import az.azerconnect.azerconnectbackend.model.MsisdnRequest;
import az.azerconnect.azerconnectbackend.service.EligibilityCheckService;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.root.url}")
public class EligibilityController {

    private final EligibilityCheckService eligibilityCheckService;

    public EligibilityController(EligibilityCheckService eligibilityCheckService) {
        this.eligibilityCheckService = eligibilityCheckService;
    }

    @GetMapping("masklist/isEligible")
    public Map<String, String> isEligibleToSell(MsisdnRequest msisdnRequest) {
        return eligibilityCheckService.isEligibleToSell(msisdnRequest);
    }
}
