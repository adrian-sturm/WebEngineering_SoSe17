package com.micromata.webengineering.myforum.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;


/**
 * Helper functions to retrieve the server's hostname, ip and port for the running application.
 * <p>
 * See https://stackoverflow.com/questions/29929896/how-to-get-local-server-host-and-port-in-spring-boot as found by
 * a google search for "spring-boot get host and ip".
 */
@Service
@Configuration
public class AddressService {

    @Value("${addressService.address}")
    private String serverURL;

    /**
     * Return server URL with http:// prefix.
     *
     * @return server URL.
     */
    public String getServerURL() {
        return serverURL;
    }
}
