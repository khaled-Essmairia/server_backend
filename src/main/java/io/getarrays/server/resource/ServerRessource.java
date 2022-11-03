package io.getarrays.server.resource;

import static io.getarrays.server.enumeration.Status.SERVER_UP;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Timestamp;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.Server;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.getarrays.server.model.Response;
import io.getarrays.server.server.implementation.ServerServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ServerRessource {
	
	private final ServerServiceImpl serverService;
    
	@GetMapping("/list")
	public ResponseEntity<Response> getServer() throws InterruptedException{
        TimeUnit.SECONDS.sleep(3);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(of("servers", serverService.list(30)))
                        .message("Servers retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
				);
	}
	/* @GetMapping("/ping/{ipAddress}")
	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String address) throws InterruptedException{
		Server server = (Server) serverService.ping(address);
		return ResponseEntity.ok(
				Response.builder()
                .timeStamp(now())
                .data(of("server", server))
                .message(server.getStatus() == SERVER_UP ? "Ping success" : "Ping failed")
                .status(OK)
                .statusCode(OK.value())
                .build()
				);
	}*/
	 
	    @GetMapping("/get/{id}")
	    public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
	        return ResponseEntity.ok(
	                Response.builder()
	                        .timeStamp(now())
	                        .data(of("server", serverService.get(id)))
	                        .message("Server retrieved")
	                        .status(OK)
	                        .statusCode(OK.value())
	                        .build()
	        );
	    }

	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id) {
	        return ResponseEntity.ok(
	                Response.builder()
	                        .timeStamp(now())
	                        .data(of("deleted", serverService.delete(id)))
	                        .message("Server deleted")
	                        .status(OK)
	                        .statusCode(OK.value())
	                        .build()
	        );
	    }

	    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
	    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
	        return Files.readAllBytes(Paths.get(System.getProperty("user.home") + "/Downloads/images/" + fileName));
	    }
	
}
