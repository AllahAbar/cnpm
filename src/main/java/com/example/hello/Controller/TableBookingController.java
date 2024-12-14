package com.example.hello.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.hello.Model.TableBooking;
import com.example.hello.Service.TableBookingService;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/table-bookings")
public class TableBookingController {

    @Autowired
    private TableBookingService tableBookingService;

    // Lấy tất cả các bàn đã đặt
    @GetMapping
    public List<TableBooking> getAllBookings() {
        return tableBookingService.getAllBookings();
    }

    // Lấy thông tin đặt bàn theo ID
    @GetMapping("/{id}")
    public ResponseEntity<TableBooking> getBookingById(@PathVariable("id") Long idTable) {
        Optional<TableBooking> tableBooking = tableBookingService.getBookingById(idTable);
        return tableBooking.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Tạo mới một đặt bàn
    @PostMapping
    public ResponseEntity<TableBooking> createBooking(@RequestBody TableBooking tableBooking) {
        TableBooking newBooking = tableBookingService.createBooking(tableBooking);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
    }

    // Cập nhật thông tin đặt bàn
    @PutMapping("/{id}")
    public ResponseEntity<TableBooking> updateBooking(@PathVariable("id") Long idTable, @RequestBody TableBooking tableBookingDetails) {
        TableBooking updatedBooking = tableBookingService.updateBooking(idTable, tableBookingDetails);
        return updatedBooking != null ? ResponseEntity.ok(updatedBooking) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Xóa thông tin đặt bàn
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") Long idTable) {
        boolean isDeleted = tableBookingService.deleteBooking(idTable);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
