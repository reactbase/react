package com.shvid.react.packedobject;

import com.shvid.react.RC;
import com.shvid.react.Swapper;
import com.shvid.react.UnsafeHolder;

public final class PackedChar implements PackedObject {
	
	final long offset;
	final char defaultValue;

	public PackedChar(long offset) {
		this(offset, PackedConstants.DEFAULT_CHAR);
	}

	public PackedChar(long offset, char defaultValue) {
		this.offset = offset;
		this.defaultValue = defaultValue;
	}
	
	public void format(byte[] blob) {
		setChar(blob, 0, defaultValue);
	}
	
	public void format(long address) {
		setChar(address, 0, defaultValue);
	}
	
	public char getChar(byte[] blob, long ptr) {
		char charValue = UnsafeHolder.UNSAFE.getChar(blob, offset + ptr + UnsafeHolder.byteArrayBaseOffset);
		return RC.getInstance().isLittleEndian ? charValue : Swapper.swapChar(charValue);
	}
	
	public char getChar(long address, long ptr) {
		char charValue = UnsafeHolder.UNSAFE.getChar(address + offset + ptr);
		return RC.getInstance().isLittleEndian ? charValue : Swapper.swapChar(charValue);
	}
	
	public void setChar(byte[] blob, long ptr, char value) {
		char converted = RC.getInstance().isLittleEndian ? value : Swapper.swapChar(value);
		UnsafeHolder.UNSAFE.putChar(blob, offset + ptr + UnsafeHolder.byteArrayBaseOffset, converted);
	}	
	
	public void setChar(long address, long ptr, char value) {
		char converted = RC.getInstance().isLittleEndian ? value : Swapper.swapChar(value);
		UnsafeHolder.UNSAFE.putChar(address + offset + ptr, converted);
	}
	
	public int getFixedSize() {
		return PackedConstants.CHAR_SIZE;
	}
	
	public int getInitCapacity() {
		return 0;
	}
}