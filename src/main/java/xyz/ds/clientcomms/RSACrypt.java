package xyz.ds.clientcomms;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSACrypt {
    private static final String b = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA6cIRRHt6FT6ujZCMVdJiT72I08J4o+VjQqsW13LW14cgsJCc3mOh0D4WoXDgD4smcAOwYHmE8s7BdcTxwRZB+t/sB65MP186AKuZZuuzZ6rxBkrQpnKGliTXYuvyE0pz2fhI4b2lrefLxN8R9RoASM+utjV4o8H0qOeuhRUlNPKqslLc1xTy4rjbUQLkh+ZdXX1e5NYkFn72s7QlcSkBwXdRN7p3rDKTau/7YCz+mvnv1/dfINVhz2GKrgOsgyhVxdLTcDIpVZ/b6Lc+2G0OIgQaNCSwRtsPxKLronpoDFcvlVpI6ibHx6QzEBIw4MhiU0XhXRzuV+Df+lDl62MnTQIDAQAB";
    private static PublicKey a;

    public static byte[] a(byte[] byArray) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, a);
        return cipher.doFinal(byArray);
    }

    private static PublicKey a(String string) throws Exception {
        byte[] byArray = Base64.getDecoder().decode(string);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byArray);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }

    static {
        try {
            a = RSACrypt.a(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}