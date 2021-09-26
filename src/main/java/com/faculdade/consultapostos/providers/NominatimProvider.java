package com.faculdade.consultapostos.providers;

import com.faculdade.consultapostos.providers.dtos.NominatimResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NominatimProvider {

    @Autowired
    private NominatimClient nominatimClient;

    public NominatimResponse getCoordenadas(final String endereco, final Long numeroEndereco) {
        final String enderecoCompleto = numeroEndereco.toString() + ", " + endereco;

        final List<NominatimResponse> response = nominatimClient.getCoordenadas(enderecoCompleto, 0, "br", "json", 1);

        System.out.println("bla");

        return response.get(0);
    }
}