package az.azerconnect.azerconnectbackend.controller;

import az.azerconnect.azerconnectbackend.model.MsisdnRequest;
import az.azerconnect.azerconnectbackend.service.EligibilityCheckService;
import java.util.Map;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("${api.root.url}")
public class EligibilityController {

    private final EligibilityCheckService eligibilityCheckService;

    public EligibilityController(EligibilityCheckService eligibilityCheckService) {
        this.eligibilityCheckService = eligibilityCheckService;
    }

    @PostMapping("masklist/isEligible")
    public Map<String, String> isEligibleToSell(MsisdnRequest msisdnRequest) {
        return eligibilityCheckService.isEligibleToSell(msisdnRequest);
    }
}
