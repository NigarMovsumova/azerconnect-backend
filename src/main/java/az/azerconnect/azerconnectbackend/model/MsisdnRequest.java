package az.azerconnect.azerconnectbackend.model;

import io.swagger.annotations.ApiModel;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@ApiModel("Request to Rest service")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class MsisdnRequest {
    private List<String> msisdnList;

    private String blackListString;

    private String whiteListString;


}
