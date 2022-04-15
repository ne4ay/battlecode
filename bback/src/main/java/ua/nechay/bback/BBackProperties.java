package ua.nechay.bback;

import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;

/**
 * @author anechaev
 * @since 15.04.2022
 */
public class BBackProperties {

    @Value("${server.port}")
    private int port;

    @PostConstruct
    private void initialize() {
        System.out.println(this); //TODO: clean
    }

    @Override
    public String toString() {
        return "BBackProperties{" +
            "port=" + port +
            '}';
    }
}
