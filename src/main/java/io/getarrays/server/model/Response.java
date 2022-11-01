package io.getarrays.server.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder

public class Response {

   protected LocalDateTime timeStamp;
   protected int statusCode;
   protected HttpStatus status;
   protected String reason;
   protected String message;
   protected String developerMessage;
   protected Map<?,?> data;
}
