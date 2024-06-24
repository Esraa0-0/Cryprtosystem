package cryptosystem;

public class Vigenere 
{
    public String encrypt(String plaintext, String key) 
    {
        String ciphertext = "";
        int j = 0;
        for (int i = 0; i < plaintext.length(); i++) 
        {
            char c = plaintext.charAt(i);
            if (c >= 'a' && c <= 'z') 
            {
                key=key.toLowerCase();
                c = (char) (c + key.charAt(j) - 'a');
                if (c > 'z') 
                    c = (char) (c - 26);
                j = ++j % key.length();
            }
            else if (c >= 'A' && c <= 'Z') 
            {
                key=key.toUpperCase();
                c = (char) (c + key.charAt(j) - 'A');
                if (c > 'Z') 
                    c = (char) (c - 26);
                j = ++j % key.length();
            }
            ciphertext += c;
        }
        return ciphertext;
    }
    
    public String decrypt(String ciphertext, String key)
    {            
        String plaintext = "";
        int j = 0;
        for (int i = 0; i < ciphertext.length(); i++) 
        {
            char c = ciphertext.charAt(i);
            if (c >= 'a' && c <= 'z') 
            {
                key=key.toLowerCase();
                c = (char) (c - key.charAt(j) + 'a');
                if (c < 'a') 
                    c = (char) (c + 26);
                j = ++j % key.length();
            } 
            else if (c >= 'A' && c <= 'Z') 
            {
                key=key.toUpperCase();
                c = (char) (c - key.charAt(j) + 'A');
                if (c < 'A') 
                    c = (char) (c + 26);
                j = ++j % key.length();
            }
            plaintext += c;
        }
        return plaintext;
    }
}
