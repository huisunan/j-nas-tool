package io.github.hsn.jnastool.torrent.bencoding.types;

import java.util.*;

//TODO we don't need this..?

/**
 * Created by christophe on 15.01.15.
 */
public class BDictionary implements IBencodable
{
    private final Map<BByteString, IBencodable> dictionary;
    public byte[] blob;

    public BDictionary()
    {
        // LinkedHashMap to preserve order.
        this.dictionary = new LinkedHashMap<BByteString, IBencodable>();
    }

    ////////////////////////////////////////////////////////////////////////////
    //// LOGIC METHODS /////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public void add(BByteString key, IBencodable value)
    {
        this.dictionary.put(key, value);
    }

    public Object find(BByteString key)
    {
        return dictionary.get(key);
    }

    ////////////////////////////////////////////////////////////////////////////
    //// ENCODING /////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    public String encodedString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("d");
        for (Map.Entry<BByteString, IBencodable> entry : this.dictionary.entrySet())
        {
            sb.append(entry.getKey().encodedString());
            sb.append(entry.getValue().encodedString());
        }
        sb.append("e");
        return sb.toString();
    }

    public byte[] encode()
    {
        // Get the total size of the keys and values.
        ArrayList<Byte> bytes = new ArrayList<>();
        bytes.add((byte) 'd');

        for (Map.Entry<BByteString, IBencodable> entry : this.dictionary.entrySet())
        {
            byte[] keyEncoded = entry.getKey().encode();
            byte[] valEncoded = entry.getValue().encode();
            for (byte b : keyEncoded)
                bytes.add(b);
            for (byte b : valEncoded)
                bytes.add(b);
        }
        bytes.add((byte) 'e');
        byte[] encoded = new byte[bytes.size()];

        for (int i = 0; i < bytes.size(); i++)
            encoded[i] = bytes.get(i);

        return encoded;
    }

    ////////////////////////////////////////////////////////////////////////////
    //// OVERRIDDEN METHODS ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[\n");
        for (Map.Entry<BByteString, IBencodable> entry : this.dictionary.entrySet())
        {
            sb.append(entry.getKey()).append(" :: ").append(entry.getValue()).append("\n");
        }
        sb.append("]");

        return sb.toString();
    }
}
