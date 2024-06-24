package cryptosystem;

public class Transposition
{
    public String encrypt(String message, String key)
    {
        int rows = message.length() / key.length();
        if (message.length() % key.length() != 0) 
            rows++;
        char[][] matrix = new char[rows][key.length()];
        int index = 0;
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < key.length(); j++) 
            {
                if (index < message.length()) 
                    matrix[i][j] = message.charAt(index++);
                else
                    matrix[i][j] = 'z';
            }
        StringBuilder encryptedText = new StringBuilder();
        for (int k = 0; k < key.length(); k++)
            for (int i = 0; i < key.length(); i++)
        {
            int col = key.charAt(i)-49;
            if(col==k)
                for (int j = 0; j < rows; j++)    
                    encryptedText.append(matrix[j][i]);
        }
        return encryptedText.toString();
    }
    
    public String decrypt(String message, String key) 
    {
        int rows = message.length() / key.length();
        char[][] matrix = new char[rows][key.length()];
        int index = 0;
        for (int k = 0; k < key.length(); k++)
            for (int i = 0; i < key.length(); i++) 
            {
                int col = key.charAt(i)-49;
                if(col==k)
                    for (int j = 0; j < rows; j++) 
                        matrix[j][i] = message.charAt(index++);
            }
        StringBuilder decryptedText = new StringBuilder();
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < key.length(); j++) 
            {
                decryptedText.append(matrix[i][j]);
            }
        for(int i=0;i<decryptedText.length();i++)
            if(decryptedText.charAt(i)=='z')
                decryptedText=decryptedText.deleteCharAt(i--);
        return decryptedText.toString().trim();
    }
}
