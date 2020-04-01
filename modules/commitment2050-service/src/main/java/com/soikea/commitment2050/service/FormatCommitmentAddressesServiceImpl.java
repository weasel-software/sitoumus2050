package com.soikea.commitment2050.service;

import com.soikea.commitment2050.model.City;
import com.soikea.commitment2050.model.Commitment;
import org.osgi.service.component.annotations.Component;
import java.util.stream.Stream;
import java.util.List;

@Component(immediate = true, service = FormatCommitmentAddressesService.class)
public class FormatCommitmentAddressesServiceImpl  implements FormatCommitmentAddressesService {
    public Commitment formatCommitmentAddresses(Commitment commitment, List<City> cityList) {

        // find the current City in the address field and replace the whole field with city only
        if(!"".equals(commitment.getAddress())) {
            System.out.println("----------");
            System.out.println("COMMITMENT ADDRESS: " + commitment.getAddress());
            Stream<City> stream = cityList.stream();
            City matchingCity = stream.filter(city -> {
                return commitment.getAddress().contains(city.name_fi_FI);
            })
                    .findFirst()
                    .orElse(null);
            if (matchingCity != null) {
                System.out.println("FOUND MATCH: " + matchingCity.name_fi_FI);
                commitment.setAddress(matchingCity.name_fi_FI);
            } else {
                System.out.println("NO MATCH FOUND.");
            }
        } else {
            System.out.println("----------");
            System.out.println("Empty address field. Skipping...");
        }
        return commitment;
    }
}
