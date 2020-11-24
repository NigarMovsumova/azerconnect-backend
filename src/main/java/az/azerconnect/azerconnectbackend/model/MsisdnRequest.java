package az.azerconnect.azerconnectbackend.model;

import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@ApiModel("Msisdn Request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MsisdnRequest {
    private List<String> msisdnList;

    private String blackListString;

    private String whiteListString;


}
