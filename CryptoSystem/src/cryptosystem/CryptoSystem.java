package cryptosystem;

public class CryptoSystem
{

    public static void main(String[] args)
    {
        new GUI();
        
        Caesar caesar=new Caesar();
        System.out.println("hello: "+caesar.encrypt("hello", 15));
        System.out.println("");
        
        String plaintext = "Hollywood";
        String key = "Ballon";
        Playfair playfair=new Playfair(key);
        String ciphertext = playfair.encrypt(plaintext);
        System.out.println("Plaintext: " + plaintext);
        System.out.println("Ciphertext: " + ciphertext);
        System.out.println("Decrypted text: " + playfair.decrypt(ciphertext));
        System.out.println("");
        
        Vigenere vigenere=new Vigenere();
        String encryptedText1 = vigenere.encrypt(plaintext, key);
        System.out.println("Encrypted Text: " + encryptedText1);
        String decryptedText1 = vigenere.decrypt(encryptedText1, key);
        System.out.println("Decrypted Text: " + decryptedText1);
        System.out.println("");
        
        String plaintext2 = "attackpostponeduntiltwoam";
        String key2 = "4312567";
        Transposition transposition=new Transposition();
        String encryptedText2 = transposition.encrypt(plaintext2, key2);
        String decryptedText2 = transposition.decrypt(encryptedText2, key2);
        System.out.println("Encrypted Text: " + encryptedText2);
        System.out.println("Decrypted Text: " + decryptedText2);
    }
    
}
