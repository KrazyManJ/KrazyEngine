package me.KrazyManJ.KrazyEngine.NMS.NBTEditor;

import java.lang.reflect.InvocationTargetException;

/**
 * A class for holding NBTTagCompounds
 */
@SuppressWarnings("unused")
public final class NBTCompound {
    final Object tag;

    NBTCompound(Object tag) {
        this.tag = tag;
    }

    public void set( Object value, Object... keys ) {
        try {
            NBTEditor.setTag( tag, value, keys );
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    /**
     * The exact same as the toString method
     *
     * @return
     * Convert the compound to a string.
     */
    public String toJson() {
        return tag != null ? tag.toString() : "{}";
    }

    public static NBTCompound fromJson( String json ) {
        try {
            return new NBTCompound( NBTEditor.getMethod( "loadNBTTagCompound" ).invoke( null, json ) );
        } catch ( IllegalAccessException | IllegalArgumentException | InvocationTargetException e ) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return tag != null ? tag.toString() : "{}";
    }

    @Override
    public int hashCode() {
        return tag.hashCode();
    }

    @Override
    public boolean equals( Object obj ) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        NBTCompound other = (NBTCompound) obj;
        if (tag == null) { return other.tag == null; } else return tag.equals(other.tag);
    }
}
