package com.group8.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author acoffee
 * @create 2022-02-17 16:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadImg {
    int id;
    MultipartFile file;
}
