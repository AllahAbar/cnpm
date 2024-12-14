package com.example.hello.Service;

import com.example.hello.Model.Staff;
import com.example.hello.Model.Restaurant;
import com.example.hello.Repository.StaffRepository;
import com.example.hello.Repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    // Lấy tất cả nhân viên
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // Lấy nhân viên theo ID
    public Optional<Staff> getStaffById(Long staffId) {
        return staffRepository.findById(staffId);
    }

    // Tạo nhân viên mới
    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    // Cập nhật nhân viên
    public Staff updateStaff(Long staffId, Staff staffDetails) {
        Optional<Staff> existingStaff = staffRepository.findById(staffId);
        if (existingStaff.isPresent()) {
            Staff staff = existingStaff.get();
            staff.setName(staffDetails.getName());
            staff.setPhoneNumber(staffDetails.getPhoneNumber());
            staff.setEmail(staffDetails.getEmail());
            staff.setPosition(staffDetails.getPosition());
            staff.setEmploymentStatus(staffDetails.getEmploymentStatus());
            staff.setHireDate(staffDetails.getHireDate());

            // Kiểm tra xem restaurantId có hợp lệ không
            if (staffDetails.getRestaurant() != null) {
                Optional<Restaurant> restaurant = restaurantRepository.findById(staffDetails.getRestaurant().getRestaurantId());
                restaurant.ifPresent(staff::setRestaurant);
            }

            return staffRepository.save(staff);
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // Xóa nhân viên
    public boolean deleteStaff(Long staffId) {
        Optional<Staff> existingStaff = staffRepository.findById(staffId);
        if (existingStaff.isPresent()) {
            staffRepository.deleteById(staffId);
            return true;
        }
        return false;
    }
}
