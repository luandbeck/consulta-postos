package com.faculdade.consultapostos.providers;

import com.faculdade.consultapostos.providers.dtos.NominatimResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "nominatimClient", url = "https://nominatim.openstreetmap.org/")
public interface NominatimClient {

    @GetMapping
    List<NominatimResponse> getCoordenadas(@RequestParam("q") final String q,
                                           @RequestParam("addressdetails") final Integer addressDetails,
                                           @RequestParam("countrycodes") final String countryCodes,
                                           @RequestParam("format") final String format,
                                           @RequestParam("limit") final Integer limit);
}
