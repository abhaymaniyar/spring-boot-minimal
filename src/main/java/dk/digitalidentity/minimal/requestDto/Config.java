
package dk.digitalidentity.minimal.requestDto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SuppressWarnings("unused")
public class Config {

    private List<ApiLimit> apiLimits;
    private GlobalLimits globalLimits;
    private String host;
    private String port;
    private String service;

}
