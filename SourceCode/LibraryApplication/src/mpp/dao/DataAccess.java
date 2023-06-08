package mpp.dao;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class DataAccess {

	public static final String OUTPUT_DIR = System.getProperty("user.dir") + File.separator + "storage";

	public Object readFromFile(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			String filePathString = OUTPUT_DIR + File.separator + type.toString();
			File f = new File(filePathString);
			if (f.exists() && !f.isDirectory()) {
				in = new ObjectInputStream(Files.newInputStream(path));
				retVal = in.readObject();
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (Exception e) {
				}
			}
		}
		return retVal;
	}
	
	public  void saveToFile(StorageType type, Object obj) {
	    ObjectOutputStream out = null;
	    try {
	        Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
	        out = new ObjectOutputStream(Files.newOutputStream(path));
	        out.writeObject(obj);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (out != null) {
	            try {
	                out.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}


}
