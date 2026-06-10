package com.mukesh.shoppingresearchagent.provider;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EcommerceProviderRegistry {

    private final List<EcommerceProvider>
            providers;

    public EcommerceProviderRegistry(
            List<EcommerceProvider> providers
    ) {

        this.providers = providers;
    }

    public List<EcommerceProvider>
    getProviders() {

        return providers;
    }
}