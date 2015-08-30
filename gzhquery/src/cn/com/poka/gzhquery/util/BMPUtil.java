package cn.com.poka.gzhquery.util;

public class BMPUtil {

	//Header Data
	private byte[] data = new byte[14];
	
	public byte[] getData(){
		return this.data;
	}
	
	//BMP file size
	private int size;
	public int getSize(){
		return this.size;
	}
	
	private int offset;
	public int getOffset(){
		return this.offset;
	}
	
	BMPUtil( int size , int offset ){
		this.size = size;
		this.offset = offset;
		
		data[0] = 'B';
		data[1] = 'M';
		
		int value = size;
		data[2] = (byte)value;
		value = value >>>8;
		data[3] = (byte) value;
		value = value >>> 8;
		data[4] = (byte)value;
		value = value >>> 8;
		data[5] = (byte)value;
		
		value = offset;
		data[10] = (byte) value;
		value = value >>>8 ;
		data[11] = (byte)value;
		value = value >>> 8;
		data[12] = (byte)value;
		value = value >>> 8 ;
		data[13] = (byte) value;
	} 
	
}
