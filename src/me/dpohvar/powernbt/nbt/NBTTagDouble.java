package me.dpohvar.powernbt.nbt;

import me.dpohvar.powernbt.utils.StaticValues;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 14.01.13 17:54
 *
 * @author DPOH-VAR
 */
public class NBTTagDouble extends NBTTagNumeric {
    private static Class clazz = StaticValues.getClass("NBTTagDouble");
    private static Field fieldData;
    private static Method methodRead;
    private static Method methodWrite;
    private static Method methodClone;

    static {
        try {
            methodRead = StaticValues.getMethodByTypeTypes(clazz, void.class, java.io.DataInput.class);
            methodWrite = StaticValues.getMethodByTypeTypes(clazz, void.class, java.io.DataOutput.class);
            methodClone = StaticValues.getMethodByTypeTypes(class_NBTBase, NBTBase.class_NBTBase);
            fieldData = StaticValues.getFieldByType(clazz, double.class);
            methodRead.setAccessible(true);
            methodWrite.setAccessible(true);
            methodClone.setAccessible(true);
            fieldData.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NBTTagDouble() {
        this("", (double) 0);
    }

    public NBTTagDouble(String s) {
        this(s, (double) 0);
    }

    public NBTTagDouble(double b) {
        this("", b);
    }

    public NBTTagDouble(String s, double b) {
        super(getNew(s, b));
    }

    private static Object getNew(String s, double b) {
        try{
            return clazz.getConstructor(String.class,double.class).newInstance(s,b);
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public NBTTagDouble(boolean ignored, Object tag) {
        super(tag);
        if (!clazz.isInstance(tag)) throw new IllegalArgumentException();
    }

    public void write(java.io.DataOutput output) {
        try {
            methodWrite.invoke(handle, output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void read(java.io.DataInput input) {
        try {
            methodRead.invoke(handle, input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public NBTTagDouble clone() {
        try {
            Object h = methodClone.invoke(handle);
            return new NBTTagDouble(true, h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean equals(Object o) {
        if (o instanceof NBTBase) o = ((NBTBase) o).getHandle();
        return handle.equals(o);
    }

    public int hashCode() {
        return handle.hashCode();
    }

    public Double get() {
        try {
            return (Double) fieldData.get(handle);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void set(Number value) {
        try {
            fieldData.set(handle, value.doubleValue());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte getTypeId() {
        return 6;
    }

}
