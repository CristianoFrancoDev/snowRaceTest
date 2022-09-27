package it.contrader.utils;

import java.util.Base64;

public class CryptoHelper
{
    public static String encode(String toEncode)
    {
        // Getting encoder
        Base64.Encoder encoder = Base64.getEncoder();

        // Creating byte array
        byte byteArr[] = {1,2};

        // encoding byte array
        byte byteArr2[] = encoder.encode(byteArr);

        byte byteArr3[] = new byte[5];                // Make sure it has enough size to store copied bytes
        int x = encoder.encode(byteArr,byteArr3);    // Returns number of bytes written

        // Encoding string
        String str = encoder.encodeToString(toEncode.getBytes());

        return str;
        }

        public static String decode(String encodedString)
        {
            // Getting decoder
            Base64.Decoder decoder = Base64.getDecoder();

            // Decoding string
            String dStr = new String(decoder.decode(encodedString));

            return dStr;
        }
}
