package com.example.hello.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.hello.Model.TableBooking;

@Repository
public interface TableBookingRepository extends JpaRepository<TableBooking, Long> {
    
}