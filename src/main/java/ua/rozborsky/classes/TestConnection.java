package ua.rozborsky.classes;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by roman on 15.01.2017.
 */
public class TestConnection {
    public boolean connected() {
        int timeout = 2000;
        InetAddress[] addresses = new InetAddress[0];
        try {
            addresses = InetAddress.getAllByName("www.google.com");
        } catch (UnknownHostException e) {
        }
        for (InetAddress address : addresses) {
            try {
                if (address.isReachable(timeout)) {
                    return true;
                } else {
                    return false;
                }
            } catch (IOException e) {

            }
        }

        return false;
    }
}
