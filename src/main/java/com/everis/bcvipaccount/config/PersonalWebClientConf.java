package com.everis.bcvipaccount.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PersonalWebClientConf {
    @Value("${personal.client.uri}")
    private String personalClientBaseUri;
    private String endPoint = "personals-customers/accounts/";
}
