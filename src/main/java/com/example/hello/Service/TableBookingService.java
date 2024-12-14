package com.example.hello.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.example.hello.Repository.TableBookingRepository;
import com.example.hello.Model.TableBooking;

@Service
public class TableBookingService {

    @Autowired
    private TableBookingRepository tableBookingRepository;

    public List<TableBooking> getAllBookings() {
        return tableBookingRepository.findAll();
    }

    public Optional<TableBooking> getBookingById(Long idTable) {
        return tableBookingRepository.findById(idTable);
    }

    public TableBooking createBooking(TableBooking tableBooking) {
        return tableBookingRepository.save(tableBooking);
    }

    public TableBooking updateBooking(Long idTable, TableBooking tableBookingDetails) {
        Optional<TableBooking> existingBooking = tableBookingRepository.findById(idTable);
        if (existingBooking.isPresent()) {
            TableBooking booking = existingBooking.get();
            booking.setBookingDate(tableBookingDetails.getBookingDate());
            booking.setGuest(tableBookingDetails.getGuest());
            booking.setPlaced(tableBookingDetails.isPlaced());
            return tableBookingRepository.save(booking);
        }
        return null; // Trả về null nếu không tìm thấy
    }

    public boolean deleteBooking(Long idTable) {
        Optional<TableBooking> existingBooking = tableBookingRepository.findById(idTable);
        if (existingBooking.isPresent()) {
            tableBookingRepository.deleteById(idTable);
            return true;
        }
        return false;
    }
}