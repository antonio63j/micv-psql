package com.afl.micvback.util.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.afl.micvback.util.pdf.UtilPdf;

@Service
public class DownloadFileService implements IDownloadFileService {
	@Autowired 
	private UtilPdf utilPdf;
	
	public final static String DIRECTORIO_DOWNLOAD = "/downloads/";	
	public final static String PREFIJO_FILE_DOWNLOAD = "cv-AntonioFernandezLucena.pdf";	
	public final static String FILE_DONWLOAD = "donwloads";	

	@Override
	public String getFilenameGenerado() throws Exception{

		//String archivo = PREFIJO_FILE_DOWNLOAD + UUID.randomUUID().toString() + ".pdf";

	    String filename = DIRECTORIO_DOWNLOAD + PREFIJO_FILE_DOWNLOAD;
		//String filename = DIRECTORIO_DOWNLOAD + archivo;
	
//        File file = new File ();
//        file.getParentFile().mkdirs();
		//UtilPdf utilPdf = new UtilPdf();
		utilPdf.generarPdfCv(filename);
		
		//document.close();
		return PREFIJO_FILE_DOWNLOAD;
	}

	@Override
	public boolean deleteFilename(String archivo) {
		if (archivo != null && archivo.length() > 0) {
			Path pathRuta = getPath (DIRECTORIO_DOWNLOAD, archivo);
			File archivoToDelete = pathRuta.toFile();
			if (archivoToDelete.exists() && archivoToDelete.canRead()) {
				archivoToDelete.delete();
				return true;
			}
		}
		return false;
	}
	
	public Path getPath(String path, String archivo) {
		Path rutaArchivo = Paths.get(path).resolve(archivo).toAbsolutePath();
		return rutaArchivo;
	}

}
