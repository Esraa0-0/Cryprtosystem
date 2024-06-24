package cryptosystem;
import java.util.Arrays;

public class Playfair 
{
    private static final char PADDING_CHAR = 'X';
    private char[][] keyTable;

    public Playfair(String key) 
    {
        generateKeyTable(key);
    }

    private void generateKeyTable(String key) 
    {
        key = key.replaceAll("\\s+", "").toUpperCase();
        key = key.replaceAll("J", "I");
        boolean[] visited = new boolean[26];
        keyTable = new char[5][5];
        int row = 0;
        int col = 0;
        for (char c : key.toCharArray())
            if (!visited[c - 'A'])
            {
                keyTable[row][col] = c;
                visited[c - 'A'] = true;
                col++;
                if (col == 5) 
                {
                    col = 0;
                    row++;
                }
            }
        for (char c = 'A'; c <= 'Z'; c++) 
            if (c != 'J' && !visited[c - 'A']) 
            {
                keyTable[row][col] = c;
                col++;
                if (col == 5) 
                {
                    col = 0;
                    row++;
                }
            }
    }

    private String prepareText(String text) 
    {
        text = text.replaceAll("\\s+", "").toUpperCase();
        text = text.replaceAll("J", "I");
        StringBuilder sb = new StringBuilder(text);
        for (int i = 1; i < sb.length(); i += 2) 
            if (sb.charAt(i) == sb.charAt(i - 1)) 
                sb.insert(i, PADDING_CHAR);
        if (sb.length() % 2 != 0) 
            sb.append(PADDING_CHAR);
        return sb.toString();
    }

    private String encryptPair(char a, char b) 
    {
        int row1 = -1, col1 = -1, row2 = -1, col2 = -1;
        for (int i = 0; i < 5; i++) 
            for (int j = 0; j < 5; j++) 
                if (keyTable[i][j] == a)
                {
                    row1 = i;
                    col1 = j;
                }
                else if (keyTable[i][j] == b)
                {
                    row2 = i;
                    col2 = j;
                }
        if (row1 == row2) 
        {
            col1 = (col1 + 1) % 5;
            col2 = (col2 + 1) % 5;
        }
        else if (col1 == col2)
        {
            row1 = (row1 + 1) % 5;
            row2 = (row2 + 1) % 5;
        }
        else 
        {
            int temp = col1;
            col1 = col2;
            col2 = temp;
        }
        return String.valueOf(keyTable[row1][col1]) + keyTable[row2][col2];
    }

    private String decryptPair(char a, char b) 
    {
        int row1 = -1, col1 = -1, row2 = -1, col2 = -1;
        for (int i = 0; i < 5; i++) 
            for (int j = 0; j < 5; j++) 
                if (keyTable[i][j] == a) 
                {
                    row1 = i;
                    col1 = j;
                }
                else if (keyTable[i][j] == b)
                {
                    row2 = i;
                    col2 = j;
                }
        if (row1 == row2) 
        {
            col1 = (col1 - 1 + 5) % 5;
            col2 = (col2 - 1 + 5) % 5;
        }
        else if (col1 == col2) 
        {
            row1 = (row1 - 1 + 5) % 5;
            row2 = (row2 - 1 + 5) % 5;
        }
        else 
        {
            int temp = col1;
            col1 = col2;
            col2 = temp;
        }
        return String.valueOf(keyTable[row1][col1]) + keyTable[row2][col2];
    }

    public String encrypt(String plaintext) 
    {
        String preparedText = prepareText(plaintext);
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < preparedText.length(); i += 2) 
        {
            char a = preparedText.charAt(i);
            char b = preparedText.charAt(i + 1);
            ciphertext.append(encryptPair(a, b));
        }
        return ciphertext.toString();
    }

    public String decrypt(String ciphertext) 
    {
        StringBuilder plaintext = new StringBuilder();

        for (int i = 0; i < ciphertext.length(); i += 2)
        {
            char a = ciphertext.charAt(i);
            char b = ciphertext.charAt(i + 1);
            plaintext.append(decryptPair(a, b));
        }
        for (int i = 1; i < plaintext.length(); i += 2) 
        {
            if (plaintext.charAt(i)=='I') 
            {
                plaintext.deleteCharAt(i);
                plaintext.insert(i,'J');
            }
            if (plaintext.charAt(i)=='X' && plaintext.charAt(i+1) == plaintext.charAt(i - 1)) 
                plaintext.deleteCharAt(i);   
        }
        return plaintext.toString();
    }
}
