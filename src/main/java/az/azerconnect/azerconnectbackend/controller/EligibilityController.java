package az.azerconnect.azerconnectbackend.controller;

import az.azerconnect.azerconnectbackend.model.MsisdnRequest;
import az.azerconnect.azerconnectbackend.service.EligibilityCheckService;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("${api.root.url}")
public class EligibilityController {

    private static final Logger logger = LogManager.getLogger(EligibilityController.class);

    private final EligibilityCheckService eligibilityCheckService;

    public EligibilityController(EligibilityCheckService eligibilityCheckService) {
        this.eligibilityCheckService = eligibilityCheckService;
    }

    @PostMapping("masklist/isEligible")
    public Map<String, String> isEligibleToSell(@RequestBody MsisdnRequest msisdnRequest) {
        logger.debug("isEligibleToSell start for msisdnRequest:{}", msisdnRequest);
        return eligibilityCheckService.isEligibleToSell(msisdnRequest);
    }
}
