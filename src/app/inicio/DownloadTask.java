package app.inicio;

import java.util.concurrent.Callable;

public class DownloadTask implements Callable<String> {
	
	private String url;

	public DownloadTask(String url) {
		this.url = url;
	}

	@Override
	public String call() throws Exception {
		// Lógica para descargar la información desde la URL (simulada)
		// ...

		// Devuelve un mensaje de éxito (simulado)
		return "Contenido de " + url + " descargado exitosamente.";
	}
}