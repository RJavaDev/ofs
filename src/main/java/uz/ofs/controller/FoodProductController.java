package uz.ofs.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.ofs.constants.ResponseCode;
import uz.ofs.constants.ResponseMessage;
import uz.ofs.controller.convert.FoodProductConvert;
import uz.ofs.dto.FoodProductDto;
import uz.ofs.dto.dtoUtil.ApiResponse;
import uz.ofs.dto.dtoUtil.FilterForm;
import uz.ofs.dto.request.FoodProductCreateDto;
import uz.ofs.dto.request.FoodProductUpdateDto;
import uz.ofs.entity.FoodProductEntity;
import uz.ofs.service.FoodProductService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/food-product")
public class FoodProductController {

    private final FoodProductService service;

    @PostMapping("/add")
    public ApiResponse<Object> add(@RequestBody FoodProductCreateDto dto){
        FoodProductEntity entity = FoodProductConvert.convertToEntity(dto);
        boolean isAdd = service.add(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isAdd)
                .message(ResponseMessage.OK);
    }

    @GetMapping("/get/{id}")
    public ApiResponse<Object> getById(@PathVariable("id") Long id){
        FoodProductEntity entity = service.getById(id);
        FoodProductDto dto = FoodProductConvert.convertToDto(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);
    }

    @GetMapping("/get/page")
    public ApiResponse<Object> get(@RequestBody FilterForm filter){

        Page<FoodProductEntity> entity = service.getFilterPage(filter);
        List<FoodProductDto> dtoList = FoodProductConvert.convertToDto(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dtoList)
                .message(ResponseMessage.OK);
    }

    @PatchMapping("/update")
    public ApiResponse<Object> productUpdate(@RequestBody FoodProductUpdateDto dto){

        FoodProductEntity entity = FoodProductConvert.convertToEntity(dto);
        boolean isUpdate = service.update(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isUpdate)
                .message(ResponseMessage.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse<Object> delete(@PathVariable("id") Long id){

        service.delete(id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(Boolean.TRUE)
                .message(ResponseMessage.OK);
    }

}
