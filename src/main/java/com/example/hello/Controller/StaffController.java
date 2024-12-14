package com.example.hello.Controller;

import com.example.hello.Model.Staff;
import com.example.hello.Service.StaffService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    // Get all staff members
    @GetMapping
    public ResponseEntity<List<Staff>> getAllStaff() {
        List<Staff> staffList = staffService.getAllStaff();
        return new ResponseEntity<>(staffList, HttpStatus.OK);
    }

    // Get a staff member by ID
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable Long id) {
        Optional<Staff> staff = staffService.getStaffById(id);
        if (staff.isPresent()) {
            return ResponseEntity.ok(staff.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Trả về 404 nếu không tìm thấy
        }
    }

    // Create a new staff member
    @PostMapping
    public ResponseEntity<Staff> createStaff(@RequestBody Staff staff) {
        Staff newStaff = staffService.createStaff(staff);
        return new ResponseEntity<>(newStaff, HttpStatus.CREATED); // Trả về 201 khi tạo thành công
    }

    // Update an existing staff member
    @PutMapping("/{id}")
    public ResponseEntity<Staff> updateStaff(@PathVariable Long id, @RequestBody Staff staffDetails) {
        Staff updatedStaff = staffService.updateStaff(id, staffDetails);
        if (updatedStaff != null) {
            return ResponseEntity.ok(updatedStaff); // Trả về 200 nếu cập nhật thành công
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Trả về 404 nếu không tìm thấy
        }
    }

    // Delete a staff member
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteStaff(@PathVariable Long id) {
        boolean deleted = staffService.deleteStaff(id);
        if (deleted) {
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Trả về 404 nếu không tìm thấy
        }
    }
}
