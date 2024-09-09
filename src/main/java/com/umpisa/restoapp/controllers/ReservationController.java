package com.umpisa.restoapp.controllers;

import com.umpisa.restoapp.models.AddReservation;
import com.umpisa.restoapp.models.UpdateReservation;
import com.umpisa.restoapp.models.ViewCustomer;
import com.umpisa.restoapp.models.mapper.CustomerMapper;
import com.umpisa.restoapp.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@RequestMapping("/api/reservations")
public class ReservationController {

    CustomerMapper customerMapper;
    CustomerService customerService;

    @Autowired
    public ReservationController(
                                 CustomerMapper customerMapper,
                                 CustomerService customerService) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    @Operation(summary = "retrieve reservations", description = "retrieve reservations",  responses = {
            @ApiResponse(responseCode = "200", description = "ok", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "no content", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "page not found", content = @Content),
            @ApiResponse(responseCode = "405", description = "method not allowed", content = @Content),
            @ApiResponse(responseCode = "415", description = "unsupported media type", content = @Content),
            @ApiResponse(responseCode = "500", description = "general processing error", content = @Content)
    })
    public ResponseEntity<?> getReservations() {
        Collection<ViewCustomer> reservations = customerMapper.list(customerService.viewAllReservations());
        if (reservations.isEmpty())
            return ResponseEntity.ok(new com.umpisa.restoapp.utils.ApiResponse("No reservations found.",
                    HttpStatus.NO_CONTENT));
        return ResponseEntity.ok(reservations);
    }

    @GetMapping("/{customer-id}")
    @Operation(summary = "retrieve reservation by id", description = "retrieve reservation by id",  responses = {
            @ApiResponse(responseCode = "200", description = "ok", content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ViewCustomer.class))}),
            @ApiResponse(responseCode = "204", description = "no content", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "page not found", content = @Content),
            @ApiResponse(responseCode = "405", description = "method not allowed", content = @Content),
            @ApiResponse(responseCode = "415", description = "unsupported media type", content = @Content),
            @ApiResponse(responseCode = "500", description = "general processing error", content = @Content)
    })
    public ResponseEntity<ViewCustomer> getReservationById(@PathVariable("customer-id") Long id) {
        return ResponseEntity.ok(customerMapper.toEntity(customerService.viewCustomerById(id)));
    }

    @PostMapping
    @Operation(summary = "add reservation", description = "add reservation",  responses = {
            @ApiResponse(responseCode = "200", description = "ok", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "no content", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "page not found", content = @Content),
            @ApiResponse(responseCode = "405", description = "method not allowed", content = @Content),
            @ApiResponse(responseCode = "415", description = "unsupported media type", content = @Content),
            @ApiResponse(responseCode = "500", description = "general processing error", content = @Content)
    })
    public ResponseEntity<com.umpisa.restoapp.utils.ApiResponse> createReservation(@RequestBody @Valid AddReservation addReservation) {
        customerService.addReservation(addReservation);
        return ResponseEntity.ok(new com.umpisa.restoapp.utils.ApiResponse("Reservation created successfully!. Please check your email/sms for confirmation",
                HttpStatus.OK));
    }

    @PutMapping("/{reservation-id}")
    @Operation(summary = "update reservation by id", description = "update reservation by id",  responses = {
            @ApiResponse(responseCode = "200", description = "ok", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "no content", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "page not found", content = @Content),
            @ApiResponse(responseCode = "405", description = "method not allowed", content = @Content),
            @ApiResponse(responseCode = "415", description = "unsupported media type", content = @Content),
            @ApiResponse(responseCode = "500", description = "general processing error", content = @Content)
    })
    public ResponseEntity<com.umpisa.restoapp.utils.ApiResponse> modifyReservation(@PathVariable("reservation-id") Long id, @RequestBody @Valid UpdateReservation entity) {
        customerService.updateReservation(entity, id);
        return ResponseEntity.ok(new com.umpisa.restoapp.utils.ApiResponse("Reservation updated successfully!. Please check your email/sms for confirmation",
                HttpStatus.OK));
    }

    @DeleteMapping("/{reservation-id}")
    @Operation(summary = "cancel reservation by id", description = "cancel reservation by id",  responses = {
            @ApiResponse(responseCode = "200", description = "ok", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "204", description = "no content", content = @Content),
            @ApiResponse(responseCode = "400", description = "bad request", content = @Content),
            @ApiResponse(responseCode = "404", description = "page not found", content = @Content),
            @ApiResponse(responseCode = "405", description = "method not allowed", content = @Content),
            @ApiResponse(responseCode = "415", description = "unsupported media type", content = @Content),
            @ApiResponse(responseCode = "500", description = "general processing error", content = @Content)
    })
    public ResponseEntity<com.umpisa.restoapp.utils.ApiResponse> cancelReservation(@PathVariable("reservation-id") Long id) {
        customerService.cancelReservation(id);
        return ResponseEntity.ok(new com.umpisa.restoapp.utils.ApiResponse("Reservation canceled successfully!. Please check your email/sms for confirmation",
                HttpStatus.OK));
    }

}
