package cryptosystem;

public class Caesar
{
    public String encrypt(String plaintext,int key) 
    {
        String ciphertext="";
        for (int i = 0; i < plaintext.length(); i++)
        {   
            char ch = plaintext.charAt(i);
            if (Character.isLetter(ch))
            {  
                if (Character.isLowerCase(ch)) 
                {    
                   char c = (char)(ch + key);
                    if (c > 'z') 
                        ciphertext+=(char)(c-26);
                    else 
                        ciphertext+=c;
                }
                else if (Character.isUpperCase(ch)) 
                {
                    char c = (char)(ch + key);
                    if (c > 'Z') 
                        ciphertext+=(char)(c-26);
                    else 
                        ciphertext+=c;
                }
            } 
            else 
               ciphertext+=ch;
        }
        return ciphertext;
    }
    
    public String decrypt(String ciphertext,int key) 
    {
        String plaintext="";
        for (int i = 0; i < ciphertext.length(); i++)
        {   
            char ch = ciphertext.charAt(i);
            if (Character.isLetter(ch))
            {  
                if (Character.isLowerCase(ch)) 
                {    
                   char c = (char)(ch - key);
                    if (c < 'a') 
                        plaintext+=(char)(c+26);
                    else 
                        plaintext+=c;
                }
                else if (Character.isUpperCase(ch)) 
                {
                    char c = (char)(ch - key);
                    if (c < 'A') 
                        plaintext+=(char)(c+26);
                    else 
                        plaintext+=c;
                }
            } 
            else 
               plaintext+=ch;
        }
        return plaintext;
    }
}
