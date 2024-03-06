package io.github.hsn.jnastool.torrent.bencoding.types;

import io.github.hsn.jnastool.torrent.bencoding.Utils;
import lombok.Getter;

import java.util.ArrayList;

/**
 * Created by christophe on 15.01.15.
 */
@Getter
public class BInt implements IBencodable
{
    private final Long value;

    public BInt(Long value)
    {
        this.value = value;
    }

    ////////////////////////////////////////////////////////////////////////////
    //// ENCODING /////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    public String encodedString()
    {
        return "i" + value + "e";
    }

    public byte[] encode()
    {
        byte[] sizeInAsciiBytes = Utils.stringToAsciiBytes(value.toString());

        ArrayList<Byte> bytes = new ArrayList<>();

        bytes.add((byte) 'i');

        for (byte sizeByte : sizeInAsciiBytes)
            bytes.add(sizeByte);

        bytes.add((byte) 'e');

        byte[] encoded = new byte[bytes.size()];

        for (int i = 0; i < bytes.size(); i++)
            encoded[i] = bytes.get(i);

        return encoded;
    }
    ////////////////////////////////////////////////////////////////////////////
    //// GETTERS AND SETTERS ///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////
    //// OVERRIDDEN METHODS ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BInt bInt = (BInt) o;

        return value.equals(bInt.value);
    }

    @Override
    public int hashCode()
    {
        return value.hashCode();
    }

    @Override
    public String toString()
    {
        return String.valueOf(value);
    }
}
