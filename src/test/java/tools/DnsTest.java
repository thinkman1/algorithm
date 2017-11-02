package tools;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.junit.Test;

public class DnsTest {

    @Test
    public void test() {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            displayStuff("local host", inetAddress);
            System.out.print("--------------------------");

            inetAddress = InetAddress.getByName("www.google.com");
            displayStuff("www.google.com", inetAddress);
            System.out.print("--------------------------");

            InetAddress[] inetAddressArray = InetAddress.getAllByName("www.google.com");
            for (int i = 0; i < inetAddressArray.length; i++) {
                displayStuff("www.google.com #" + (i + 1), inetAddressArray[i]);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static void displayStuff(String whichHost, InetAddress inetAddress) {
        System.out.println("--------------------------");
        System.out.println("Which Host:" + whichHost);
        System.out.println("Canonical Host Name:" + inetAddress.getCanonicalHostName());
        System.out.println("Host Name:" + inetAddress.getHostName());
        System.out.println("Host Address:" + inetAddress.getHostAddress());
    }
}
