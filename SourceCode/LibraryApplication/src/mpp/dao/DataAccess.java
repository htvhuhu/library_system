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

	static Object readFromFile(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
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
	
	static void saveToFile(StorageType type, Object obj) {
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
