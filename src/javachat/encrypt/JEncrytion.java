package javachat.encrypt;

/**
 *
 * @author matheus
 */
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.spec.KeySpec;
import javachat.network.message.MsgPacket;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

public class JEncrytion {
    public static byte[] encrypt(String key, Object object) {
        try {
            if (key.length() < 24) {
                do {
                    key = key + key;
                } while (key.length() < 24);
                key = key.substring(0, 24);
            }
            Cipher cipher = Cipher.getInstance("DESede");
            byte[] encryptKey = key.getBytes();
            KeySpec keySpec = new DESedeKeySpec(encryptKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherText = cipher.doFinal(toBytes(object));
            return cipherText;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static Object dencrypt(String key,byte[] b) {
        try {
            if (key.length() < 24) {
                do {
                    key = key + key;
                } while (key.length() < 24);
                key = key.substring(0, 24);
            }
            Cipher cipher = Cipher.getInstance("DESede");
            byte[] encryptKey = key.getBytes();
            KeySpec keySpec = new DESedeKeySpec(encryptKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] decipherObject = cipher.doFinal(b);
            return toObject(decipherObject);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static byte[] toBytes(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }

    public static Object toObject(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }

    public static void main(String[] argv) {
            MsgPacket mp = new MsgPacket();
            mp.setMsg("oi, funciona");
            
            byte[] cry = encrypt("Matheus", mp);
            
            MsgPacket tmp;
            tmp = (MsgPacket) dencrypt("Matheus", cry);
            
            System.out.println(tmp.getMsg());
    }
}
