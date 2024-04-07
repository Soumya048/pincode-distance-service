package com.freightfox.pds.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition(info = @Info(
            title = "PinCode Distance Service [PDS]"
            , version = "1.0"
            , description = "PinCode Distance API endpoints"
            , termsOfService = ""
            , contact = @Contact(email = "soumyakantswain5@gmail.com")
            , license = @License(url = "")
        )
)
public class OpenApiSwagger {
}
