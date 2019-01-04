package com.tb.bimo.service;

import com.iyzipay.Options;
import com.tb.bimo.configuration.IyzicoConfiguration;

public class IyzicoService {

    private final Options options = new Options();

    IyzicoService(IyzicoConfiguration iyzicoConfiguration) {
        this.options.setApiKey(iyzicoConfiguration.getApiKey());
        this.options.setSecretKey(iyzicoConfiguration.getSecretKey());
        this.options.setBaseUrl(iyzicoConfiguration.getBaseUrl());
    }
}
