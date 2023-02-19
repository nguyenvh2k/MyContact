package com.myconcat.controller;

import com.myconcat.entity.Contact;
import com.myconcat.service.ContactService;
import com.myconcat.service.ContactServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api/v3")
@Tag(name = "Contact")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class RestContactController {

    @Autowired
    private ContactService contactService;

    @Operation(summary = "Tạo ngẫu nhiên thông tin liên hệ !", description = "Nhập số liên hệ cần tạo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực",content = @Content),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm",content = @Content),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy",content = @Content)
    })
    @PostMapping("/contact/generate")
    public List<Contact> generateInfo(Integer n) {
        List<Contact> contactList = new ArrayList<>();
        Random rd = new Random();
        String[] arr1 = {"Long", "Nguyên", "Nhi", "Loan", "Lâm", "Vinh",
                "Loan", "Hà", "Trâm", "Ngọc", "Linh", "Hương","Mạnh",
                "Châu","Ánh","Hoàng","Nga","Thảo","Nhung","Trung","Thương"
                ,"Thủy","Vy","Ngân","Khánh","Huy","Nhi","Diệp","Phương",
                "Bình","Thúy","An","Hân","Chi","Lệ","Chi","Thanh",
                "Liên","Oanh","Quỳnh","Ly","Ánh","Nguyệt","Mai","Hoa","Duyên"};
        String[] arr2 = {"Hoàng", "Nguyễn", "Lê", "Trịnh", "Trần", "Vũ", "Đinh", "Võ", "Lý","Phạm","Phan","Huỳnh","Đặng","Đỗ","Bùi","Hồ"};
        String[] arr3 = {"Thị","Văn","Hoàng","Đình","Đạt"};
        for (int i = 0;i<n;i++){
            String firstname = arr1[rd.nextInt(arr1.length-1)];
            String lastname = arr2[rd.nextInt(arr2.length-1)];
            String middlename = arr3[rd.nextInt(arr3.length-1)];
            String email = firstname.toLowerCase() + rd.nextInt(10000) + "@gmail.com";
            String fullname = lastname+" "+middlename+" "+firstname;
            Contact contact = new Contact(fullname,email,rd.nextInt(10000000)+"");
            contactService.save(contact);
            contactList.add(contact);
        }
        return contactList;
    }
    @Operation(summary = "Lấy tất cả thông tin liên hệ")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực",content = @Content),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm",content = @Content),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy",content = @Content)
    })
    @GetMapping("/contact")
    public List<Contact> listContact(){
        return (List<Contact>) contactService.findAll();
    }

    @Operation(summary = "Tìm liên hệ theo id", description = "Nhập id vào để tìm thông tin liên hệ")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực",content = @Content),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm",content = @Content),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy",content = @Content)
    })
    @GetMapping("/contact/{id}")
    public Optional<Contact> findContactById(@PathVariable(value = "id") Integer id){
        contactService.findOne(id);
        return contactService.findOne(id);
    }

    @Operation(summary = "Tìm liên hệ theo tên",description = "Nhập tên liên hệ cần tìm")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực",content = @Content),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm",content = @Content),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy",content = @Content)
    })
    @GetMapping("/contact/search")
    public List<Contact> findContactByName(String name){
        return contactService.search(name);
    }

    @Operation(summary = "Thêm mới liên hệ",description = "Nhập các thông tin như tên, email, số điện thoại.")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực",content = @Content),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm",content = @Content),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy",content = @Content)
    })
    @PostMapping("/contact")
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact){
        contactService.save(contact);
        return ResponseEntity.ok().body(contact);
    }

    @Operation(summary = "Xoá liên hệ",description = "Nhập id tên liên hệ cần xoá")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực",content = @Content),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm",content = @Content),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy",content = @Content)
    })
    @DeleteMapping("/contact/{id}")
    public ResponseEntity<Contact> deleteContact(@PathVariable(value = "id") Integer id){
        contactService.delete(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Sửa tên liên hệ",description = "Nhập id tên liên hệ cần sửa")
    @ApiResponses(value = {
            @ApiResponse(responseCode  = "200", description = "Thành công"),
            @ApiResponse(responseCode  = "401", description = "Chưa xác thực",content = @Content),
            @ApiResponse(responseCode  = "403", description = "Truy cập bị cấm",content = @Content),
            @ApiResponse(responseCode  = "404", description = "Không tìm thấy",content = @Content)
    })
    @PutMapping("/contact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable(value = "id") Integer id ,@RequestBody Contact contact){
        contact.setId(id);
        contactService.save(contact);
        return ResponseEntity.ok().body(contact);
    }

}
