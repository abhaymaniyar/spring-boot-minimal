This app contains API relevant to configuration server and updates configuration files residing on the server.

The specific configurations it updates are API limit configurations. 

API Endpoint: localhost:9992/app/v1/updateConfig

Request Body Sample: 
```{
    "config": [
        {
            "service": "pixelService",
            "host": "lifesight.pixel.io",
            "port": "3000",
            "globalLimits": {
                "get": {
                    "limit": "20",
                    "granularity": "seconds"
                }
            },
            "apiLimits": [
                {
                    "api": "createPixel",
                    "methods": {
                        "get": {
                            "limit": "15",
                            "granularity": "seconds"
                        }
                    }
                },
                {
                    "api": "getPixel",
                    "methods": {
                        "get": {
                            "limit": "15",
                            "granularity": "seconds"
                        }
                    }
                }
            ]
        }
    ]
}
```

