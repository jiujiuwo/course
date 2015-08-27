package com.mathtop.course.domain;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class zip {
	private ByteArrayOutputStream bos ;
	private ZipOutputStream zipOut;     //压缩Zip   
    private static int      bufSize;    //size of bytes   
    private byte[]          buf;   
    private int             readedBytes;   
    
    private String zipFileName;
       
    public zip(String zipfilename){   
    	
        this(512);
        bos = new ByteArrayOutputStream();
        setZipFileName(zipfilename+".zip");//压缩后生成的zip文件名   
    }   
  
    public zip(int bufSize){   
        zip.bufSize = bufSize;   
        buf = new byte[zip.bufSize];   
    }  
    
    
	/**
	 * 压缩
	 * strZipFile:压缩后的文件
	 * strFilePaths：要压缩的文件
	 * */
	public void doCompress(String strZipFile,List<ZipFileName> strFilePaths) {
		FileInputStream fileIn;  
  
          
       
  
        try{   
            zipOut = new ZipOutputStream(bos);   

            for(ZipFileName fileName:strFilePaths){
            	fileIn = new FileInputStream(fileName.getRealFilePath());  
            	
            	 //生成的压缩包存放在原目录下  
                zipOut.putNextEntry(new ZipEntry(strZipFile+"/"+fileName.getZipFileName()));  
                  
                //此方法存放在该项目目录下  
                //this.zipOut.putNextEntry(new ZipEntry(fileName.toString()));  
                while((readedBytes=fileIn.read(buf))>0){  
                    zipOut.write(this.buf , 0 , readedBytes);  
                }  
                this.zipOut.closeEntry();   
                
            }
            
            zipOut.close();   
        }catch(Exception ioe){   
            ioe.printStackTrace();   
        }   
	}

	public String getZipFileName() {
		return zipFileName;
	}

	public void setZipFileName(String zipFileName) {
		this.zipFileName = zipFileName;
	}

	public ByteArrayOutputStream getBos() {
		return bos;
	}

}
