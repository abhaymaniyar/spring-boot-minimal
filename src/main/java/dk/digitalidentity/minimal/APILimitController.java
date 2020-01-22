package dk.digitalidentity.minimal;

import dk.digitalidentity.minimal.requestDto.*;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jooq.tools.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.configuration.ConfigurationException;

import java.util.List;

@RestController
@RequestMapping(value = "/v1")
public class APILimitController {
    @PostMapping("/updateConfig")
    public ResponseEntity<String> updateApiLimit(@RequestBody ApiLimitConfigList apiLimitConfigList) throws ConfigurationException {
        List<Config> configList = apiLimitConfigList.getConfig();
        for (Config config: configList) {
            String serviceName = config.getService();
            String configFilePath = StringUtils.replace("/home/abhay/spring-boot-minimal/src/main/java/dk/digitalidentity/minimal/configurationfiles/{serviceName}.properties", "{serviceName}", serviceName);
            PropertiesConfiguration configFile = new PropertiesConfiguration(configFilePath);

            configFile.setProperty("globalLimits.get.limit", config.getGlobalLimits().getGet().getLimit());
            configFile.setProperty("globalLimits.get.granularity", config.getGlobalLimits().getGet().getGranularity());
            for (ApiLimit apiConfig : config.getApiLimits()) {
                Methods methods = apiConfig.getMethods();
                String apiName = apiConfig.getApi();
                String getApiLimit = methods.getGet().getLimit();
                String getApiGranularity = methods.getGet().getGranularity();
                if (getApiGranularity.equals("minute")) {
                    getApiLimit = String.valueOf(Integer.parseInt(getApiLimit) * 60);
                }

                System.out.println("Updating API config for service : " + serviceName + ", API : " + apiName);
                System.out.println("Setting limit to : " + getApiLimit + ", Granularity to : " + getApiGranularity);

                // Update configuration file on Configuration server
                configFile.setProperty("apiLimits." + apiName + ".get.limit", getApiLimit);
                configFile.setProperty("apiLimits." + apiName + ".get.granularity", getApiGranularity);
            }
            configFile.save();
        }
        return new ResponseEntity<String>("OK", HttpStatus.OK);
    }
}
