package com.faculdade.consultapostos.configurations;


import com.faculdade.consultapostos.exceptions.domain.ErrorMap;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;


@Getter
@Setter
@Component
@RefreshScope
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "error-config")
public class ErrorConfig {

    private List<ErrorMap> errorMapping;
}
