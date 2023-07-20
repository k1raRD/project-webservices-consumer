/**
 * 
 */
package com.k1rard.projectwebserviceconsumer.client;

import java.time.LocalDateTime;
import java.util.List;

import org.glassfish.jersey.client.ClientConfig;

import com.k1rard.projectwebserviceconsumer.dto.EmpleadoDTO;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * @author k1rard Clase cliente que permite consultar el webservice de
 *         Empleados.
 */
public class EmpleadoWSClient {

	public static void main(String[] args) {
		// :::::::::::::::::::::: GET ::::::::::::::

		String endPoint = "http://localhost:8080/project-webservices/k1rard/empleadoWS";

//		Client cliente = ClientBuilder.newClient();
//		WebTarget webTarget = cliente.target(endPoint).path("empleados");
//
//		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
//		Response response = invocationBuilder.get();
//
//		if (response.getStatus() == 204) {
//			System.out.println("No se encontraron los datos de los empleados.");
//		}
//
//		if (response.getStatus() == 200) {
//			List<EmpleadoDTO> empleados = response.readEntity(new GenericType<List<EmpleadoDTO>>() {
//			});
//
//			empleados.forEach(e -> System.out.println(e.getNombre() + " " + e.getPrimerApellido()));
//		}
		
		// :::::::::::::::::::::: POST ::::::::::::::

		Client cliente = ClientBuilder.newClient();
		WebTarget webTarget = cliente.target(endPoint).path("empleados");
		
		EmpleadoDTO empleado = new EmpleadoDTO();
		empleado.setId(3L);
		empleado.setNombre("Narciso");
		empleado.setPrimerApellido("rodriguez");
		empleado.setSegundoApellido("perez");
		empleado.setEdad(24);
		empleado.setNumeroEmpleado("234233");
		empleado.setFechaCreacion(LocalDateTime.now());

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(empleado, MediaType.APPLICATION_JSON));

		if (response.getStatus() == 204) {
			System.out.println("No se se pudo guardar el empleado.");
		}

		if (response.getStatus() == 200) {
			EmpleadoDTO responseEmpleado = response.readEntity(new GenericType<EmpleadoDTO>(){}); 
			
			System.out.println("El empleado " + responseEmpleado.getNombre() + " " + responseEmpleado.getPrimerApellido()  + " fue guardado con exito!");
		}
		

	}
}
