package com.reactbase.packedobject;

import java.nio.ByteBuffer;

public final class PackedFloat extends PackedObject {

	final float defaultValue;
	
	public PackedFloat(long offset) {
		this(offset, TypeDefaults.FLOAT);
	}
	
	public PackedFloat(long offset, float defaultValue) {
		super(offset);
		this.defaultValue = defaultValue;
	}

	public void format(Object address, long ptr) {
		setFloat(address, ptr, defaultValue);
	}

	public float getFloat(Object address, long ptr) {
		if (address instanceof byte[]) {
			return getFloatA((byte[]) address, ptr);
		}
		else if (address instanceof Long) {
			return getFloatL((Long) address, ptr);
		}
		else if (address instanceof ByteBuffer) {
			return getFloatB((ByteBuffer) address, ptr);
		}
		else {
			throw new IllegalArgumentException("unknown object " + address);
		}
	}

	public float getFloatA(byte[] blob, long ptr) {
		float value = UnsafeUtil.UNSAFE.getFloat(blob, offset + ptr + UnsafeUtil.byteArrayBaseOffset);
		return isLittleEndian ? value : Swapper.swapFloat(value);
	}
	
	public float getFloatL(long address, long ptr) {
		float value = UnsafeUtil.UNSAFE.getFloat(address + offset + ptr);
		return isLittleEndian ? value : Swapper.swapFloat(value);
	}

	public float getFloatB(ByteBuffer bb, long ptr) {
		return bb.getFloat((int)(offset + ptr));
	}
	
	public void setFloat(Object address, long ptr, float value) {
		if (address instanceof byte[]) {
			setFloatA((byte[]) address, ptr, value);
		}
		else if (address instanceof Long) {
			setFloatL((Long) address, ptr, value);
		}
		else if (address instanceof ByteBuffer) {
			setFloatB((ByteBuffer) address, ptr, value);
		}
		else {
			throw new IllegalArgumentException("unknown object " + address);
		}
	}
	
	public void setFloatA(byte[] blob, long ptr, float value) {
		value = isLittleEndian ? value : Swapper.swapFloat(value);
		UnsafeUtil.UNSAFE.putFloat(blob, offset + ptr + UnsafeUtil.byteArrayBaseOffset, value);
	}
	
	public void setFloatL(long address, long ptr, float value) {
		value = isLittleEndian ? value : Swapper.swapFloat(value);
		UnsafeUtil.UNSAFE.putFloat(address + offset + ptr, value);
	}
	
	public void setFloatB(ByteBuffer bb, long ptr, float value) {
		bb.putFloat((int)(offset + ptr), value);
	}	
	
	public int getTypeId() {
		return TypeRegistry.FLOAT_ID;
	}
	
	public int sizeOf() {
		return TypeSizes.FLOAT.sizeOf();
	}
	
}
