package uz.ofs.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.ofs.constants.ResponseCode;
import uz.ofs.constants.ResponseMessage;
import uz.ofs.controller.convert.FoodProductConvert;
import uz.ofs.dto.FoodProductDto;
import uz.ofs.dto.dtoUtil.ApiResponse;
import uz.ofs.dto.request.FoodProductCreateDto;
import uz.ofs.dto.request.FoodProductUpdateDto;
import uz.ofs.entity.FoodProductEntity;
import uz.ofs.service.FoodProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/food-product")
public class FoodProductController {

    private final FoodProductService service;

    @PostMapping("/add")
    public ApiResponse<Object> add(FoodProductCreateDto dto){
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

    @GetMapping("/get/all")
    public ApiResponse<Object> getAll(){
        List<FoodProductEntity> entity = service.getAll();
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

}
