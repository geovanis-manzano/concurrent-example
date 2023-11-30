package app.inicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentExample {
	public static void main(String[] args) {
		// Lista de URLs simuladas
		List<String> urls = Arrays.asList("https://example.com", "https://example.org", "https://example.net");

		// Crear un ExecutorService con un número fijo de hilos
		int numHilos = 3; // Puedes ajustar este número según tu necesidad
		ExecutorService executorService = Executors.newFixedThreadPool(numHilos);

		// Lista para almacenar los resultados
		List<Future<String>> futures = new ArrayList<>();

		// Iterar sobre las URLs
		for (String url : urls) {
			// Crear una instancia de DownloadTask para cada URL
			Callable<String> downloadTask = new DownloadTask(url);

			// Enviar la tarea al ExecutorService y almacenar el Future en la lista
			Future<String> future = executorService.submit(downloadTask);
			futures.add(future);
		}

		// Cerrar el ExecutorService
		executorService.shutdown();

		// Recorrer la lista de Future y obtener los resultados
		for (Future<String> future : futures) {
			try {
				// Obtener el resultado (esperando si es necesario)
				String resultado = future.get();
				System.out.println("Descargado: " + resultado);
			} catch (InterruptedException | ExecutionException e) {
				// Manejar excepciones si es necesario
				e.printStackTrace();
			}
		}
	}
}