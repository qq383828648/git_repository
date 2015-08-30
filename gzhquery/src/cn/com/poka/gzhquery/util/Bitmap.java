/*
 * Copyright (C) 2013 United States Government as represented by the Administrator of the
 * National Aeronautics and Space Administration.
 * All Rights Reserved.
 */

package cn.com.poka.gzhquery.util;

import java.io.ByteArrayInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: admin
 * Date: 2013-10-21 
 * Time: ����10:45
 * To change this template use File | Settings | File Templates.
 */
public class Bitmap {
    public enum Format {
        Format8bit,Format24bit
    }
    int width;
    int height;
    Format format;
    byte[] Palette = new byte[1024];
    byte[] bitmapValue = new byte[1078];

    /**
     * dddddddd
     */
    public Bitmap(int width, int height, Format format) {
        this.width = width;
        this.height = height;
        this.format = format;
        initBitmap();
    }
    void initBitmap()
    {
        byte [] buffHeader = new byte[14];
        byte [] buffInfoHeader = new byte[40];
        BitmapHeader bitmapHeader = new BitmapHeader();
        bitmapHeader.getByteArray(buffHeader);
        //BitmapInfoHeader bih = new BitmapInfoHeader(1024,819,Format.Format8bit);
        BitmapInfoHeader bih = new BitmapInfoHeader(width,height,format);
        bih.getByteArray(buffInfoHeader);
        for (int i=0;i<buffHeader.length;i++)
        {
            bitmapValue[i] = buffHeader[i];
        }
        for (int i=0;i<buffInfoHeader.length;i++)
        {
            bitmapValue[i+14] = buffInfoHeader[i];
        }
        for (int i=0;i<Palette.length;i++)
        {
            bitmapValue[i+54] = Palette[i];
        }
    }
    public void setPalette(byte[] paletteValue)
    {
        this.Palette = paletteValue;
        for (int i=0;i<Palette.length;i++)
        {
            bitmapValue[i+54] = Palette[i];
        }
    }
    public byte[] getPalette()
    {
        return Palette;
    }
    public Bitmap setOffset(int offsetValue)
    {
        Bitmap renBitmap = new Bitmap(this.width,this.height,this.format);
        renBitmap.setPalette(this.Palette);
        byte[] originalBitmapColor = this.getBitmapColor();
        byte[] offsetBitmapColor = new byte[originalBitmapColor.length + offsetValue];
        for (int i=0;i<originalBitmapColor.length;i++)
        {
            offsetBitmapColor[i+offsetValue] = originalBitmapColor[i];
        }
        renBitmap.setBitmapColor(offsetBitmapColor);
        return renBitmap;
    }
    public void setBitmapColor(byte[] bitmapColorValue)
    {
        byte[] headAndPal;
        if(bitmapValue.length==1078)
        {
            headAndPal = new byte[1078];
            headAndPal = bitmapValue.clone();
            bitmapValue = new byte[1078 + this.width * this.height];
            for (int i=0;i<headAndPal.length;i++)
            {
                bitmapValue[i] = headAndPal[i];
            }
        }
        byte [][] tempBitmapColorValue = new byte[this.height][this.width];
        for (int i=0;i<this.height;i++)
        {
            for (int j=0;j<this.width;j++)
            {
                tempBitmapColorValue[this.height -1 -i][j] = bitmapColorValue[i*this.width+j];
            }
        }
        for (int i=0;i<bitmapValue.length-1078;i++)
        {
            int row = i/this.width;
            int col = i%this.width;
            bitmapValue[i+1078] = tempBitmapColorValue[row][col];
        }
    }
    public byte[] getBitmapColor()
    {
        byte[] bitmapColorValue = new byte[this.bitmapValue.length - this.Palette.length];
        for (int i=0;i<bitmapColorValue.length;i++)
        {
            bitmapColorValue[i] = bitmapValue[i + this.Palette.length];
        }
        return bitmapColorValue;
    }
    /**
     * 位图水平翻转
    */
    public Bitmap HorizontalFlip()
    {
        Bitmap renBitmap = new Bitmap(this.width,this.height,this.format);
        renBitmap.setPalette(this.Palette);
        byte[] originalBitmapColor = this.getBitmapColor();
        byte[] flipBitmapColor = new byte[originalBitmapColor.length];
        for(int i = 0;i<this.height;i++)
        {
            byte [] tempByte = new byte[this.width];
            for (int j = 0 ;j< tempByte.length;j++)
            {
                tempByte[j] = originalBitmapColor[i*this.width + j];
            }
            tempByte = FormatTransfer.bytesReverseOrder(tempByte);
            for (int j = 0 ;j< tempByte.length;j++)
            {
                flipBitmapColor[i*this.width + j] = tempByte[j];
            }
        }
        renBitmap.setBitmapColor(flipBitmapColor);
        return renBitmap;
    }

    /**
     * 位图垂直翻转
     * @return
     */
    public Bitmap VerticalFlip()
    {
        Bitmap renBitmap = new Bitmap(this.width,this.height,this.format);
        renBitmap.setPalette(this.Palette);
        byte[] originalBitmapColor = this.getBitmapColor();
        originalBitmapColor = FormatTransfer.bytesReverseOrder(originalBitmapColor);
        renBitmap.setBitmapColor(originalBitmapColor);
        return renBitmap;
    }
    public ByteArrayInputStream toStream()
    {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bitmapValue);
        return  byteArrayInputStream;
    }
}

class BitmapHeader
{
	String bhType;//文件的类型,为0x4d42,即BM
	int bhSize;//文件的大小,可为0
	short bhReserved1;//保留字段，应为0
	short bhReserved2;//保留字段，应为0
	int bhOffBits;//像素数据的偏移地址,通常文件头为54，8位256色图需加上调色板1024

	BitmapHeader()
	{
		bhType = "BM";
		bhSize = 0;
		bhReserved1 = 0;
		bhReserved2 = 0;
        bhOffBits = 54+1024;
	}

	BitmapHeader(byte bf[])
	{
        /*
		if(bf.length!=14)
		{
			System.out.println("The size of the argument is not correct.");
			return;
		}
		//this.bfType=(short)((((int)bf[1]&0xff)<<8) | ((int)bf[0]&0xff));
		this.bfSize=(((int)bf[5]&0xff)<<24) | (((int)bf[4]&0xff)<<16) | (((int)bf[3]&0xff)<<8) | ((int)bf[2]&0xff);
		this.bfReserved1=(short)((((int)bf[7]&0xff)<<8) | ((int)bf[6]&0xff));
		this.bfReserved2=(short)((((int)bf[9]&0xff)<<8) | ((int)bf[8]&0xff));
		this.bfOffBits=(((int)bf[13]&0xff)<<24) | (((int)bf[12]&0xff)<<16) | (((int)bf[11]&0xff)<<8) | ((int)bf[10]&0xff);
		*/
	}
	public void getByteArray(byte bf[])
	{
		if(bf.length!=14)
		{
			System.out.println("The size of the argument is not correct.");
			return;
		}
        int index = 0;
        byte[] temp = FormatTransfer.stringToBytes(bhType);
        bf[0] = temp[0];
        index++;
        bf[1] = temp[1];
        index++;
        //temp = FormatTransfer.toLH(839734);
        index = setByteArray(bf,index,bhSize);
        index = setByteArray(bf,index,bhReserved1);
        index = setByteArray(bf,index,bhReserved2);
        index = setByteArray(bf,index,bhOffBits);
	}
    int setByteArray(byte[] b,int startIndex,int value)
    {
        byte[] temp = FormatTransfer.toLH(value);
        for(int i=0;i<temp.length;i++)
        {
            b[i+startIndex]=temp[i];
        }
        return startIndex + temp.length;
    }
    int setByteArray(byte[] b,int startIndex,short value)
    {
        byte[] temp = FormatTransfer.toLH(value);
        for(int i=0;i<temp.length;i++)
        {
            b[i+startIndex]=temp[i];
        }
        return startIndex + temp.length;
    }
}

class BitmapInfoHeader
{
	int bihSize;//本结构体所占的字节数，为40
	int bihWidth;//位图的宽度
	int bihHeight;//位图的高度
	short bihPlanes;//位图的平面数，必须为1
	short bihBitCount;//每个像素所需的位数，必须是1(双色),(28-29字节), 4(16色)，8(256色)或24(真彩色)之一
	int bihCompression;// 位图压缩类型，必须是 0(不压缩),(30-33字节), 1(BI_RLE8压缩类型)或2(BI_RLE4压缩类型)之一
	int bihSizeImage;//位图的大小,可为0
	int bihXPelsPerMeter;//水平方向的分辨率，以米为单位,可为0
	int bihYPelsPerMeter;//垂直方向的分辨率，以米为单位,可为0
	int bihClrUsed;//位图用到的颜色数,可为0
	int bihClrImportant;//位图用到的重要的颜色数,可为0

	BitmapInfoHeader(int width,int height, Bitmap.Format format)
	{
		bihSize=40;
		bihWidth=width;
		bihHeight=height;
		bihPlanes=1;
        switch(format)
        {
            case Format8bit:
                bihBitCount=8;
                break;
            case Format24bit:
                bihBitCount=24;
                break;
        }
		bihCompression=0;
		bihSizeImage=0;
		bihXPelsPerMeter=0;
		bihYPelsPerMeter=0;
		bihClrUsed=0;
		bihClrImportant=0;
	}

	BitmapInfoHeader(byte bi[])
	{
        /*
		if(bi.length!=40)
		{
			System.out.println("The size of the input argument is not correct.");
			return;
		}
		bihSize=(((int)bi[3]&0xff)<<24) | (((int)bi[2]&0xff)<<16) | (((int)bi[1]&0xff)<<8) | ((int)bi[0]&0xff);
		bihWidth=(((int)bi[7]&0xff)<<24) | (((int)bi[6]&0xff)<<16) | (((int)bi[5]&0xff)<<8) | ((int)bi[4]&0xff);
		bihHeight=(((int)bi[11]&0xff)<<24) | (((int)bi[10]&0xff)<<16) | (((int)bi[9]&0xff)<<8) | ((int)bi[8]&0xff);
		bihPlanes=(short)((((int)bi[13]&0xff)<<8)|((int)bi[12]&0xff));
		bihBitCount=(short)((((int)bi[15]&0xff)<<8)|((int)bi[14]&0xff));
		bihCompression=(((int)bi[19]&0xff)<<24) | (((int)bi[18]&0xff)<<16) | (((int)bi[17]&0xff)<<8) | ((int)bi[16]&0xff);
		bihSizeImage=(((int)bi[23]&0xff)<<24) | (((int)bi[22]&0xff)<<16) | (((int)bi[21]&0xff)<<8) | ((int)bi[20]&0xff);
		bihXPelsPerMeter=(((int)bi[27]&0xff)<<24) | (((int)bi[26]&0xff)<<16) | (((int)bi[25]&0xff)<<8) | ((int)bi[24]&0xff);
		bihYPelsPerMeter=(((int)bi[31]&0xff)<<24) | (((int)bi[30]&0xff)<<16) | (((int)bi[29]&0xff)<<8) | ((int)bi[28]&0xff);
		bihClrUsed=(((int)bi[35]&0xff)<<24) | (((int)bi[34]&0xff)<<16) | (((int)bi[33]&0xff)<<8) | ((int)bi[32]&0xff);
		bihClrImportant=(((int)bi[36]&0xff)<<24) | (((int)bi[37]&0xff)<<16) | (((int)bi[38]&0xff)<<8) | ((int)bi[39]&0xff);
		*/
	}
	public void getByteArray(byte bf[])
	{
		if(bf.length!=40)
		{
			System.out.println("The size of the argument is not correct.");
			return;
		}
        int index = 0;
        index = setByteArray(bf,index,bihSize);
        index = setByteArray(bf,index,bihWidth);
        index = setByteArray(bf,index,bihHeight);
        index = setByteArray(bf,index,bihPlanes);
        index = setByteArray(bf,index,bihBitCount);
        index = setByteArray(bf,index,bihCompression);
        index = setByteArray(bf,index,bihSizeImage);
        index = setByteArray(bf,index,bihXPelsPerMeter);
        index = setByteArray(bf,index,bihYPelsPerMeter);
        index = setByteArray(bf,index,bihClrUsed);
        index = setByteArray(bf,index,bihClrImportant);
	}
    int setByteArray(byte[] b,int startIndex,int value)
    {
        byte[] temp = FormatTransfer.toLH(value);
        for(int i=0;i<temp.length;i++)
        {
            b[i+startIndex]=temp[i];
        }
        return startIndex + temp.length;
    }
    int setByteArray(byte[] b,int startIndex,short value)
    {
        byte[] temp = FormatTransfer.toLH(value);
        for(int i=0;i<temp.length;i++)
        {
            b[i+startIndex]=temp[i];
        }
        return startIndex + temp.length;
    }
}
