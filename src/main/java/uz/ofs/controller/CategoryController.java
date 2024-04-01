package uz.ofs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import uz.ofs.constants.ResponseCode;
import uz.ofs.constants.ResponseMessage;
import uz.ofs.controller.convert.CategoryConvert;
import uz.ofs.dto.CategoryDto;
import uz.ofs.dto.dtoUtil.ApiResponse;
import uz.ofs.dto.request.CategoryCreatedRequestDto;
import uz.ofs.dto.request.CategoryUpdate;
import uz.ofs.entity.CategoryEntity;
import uz.ofs.service.CategoryService;
import org.springframework.validation.annotation.Validated;


@RestController
@EnableMethodSecurity
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
@Tag(name = "Category Controller", description = "This controller manages the categories.")
public class CategoryController {

    private final CategoryService service;

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Add Category", description = "This method adds a new category. If no parentId is provided, the added category will be considered as a parent category.")
    @PostMapping("/add")
    public ApiResponse<Object> addCategory(@RequestBody @Validated CategoryCreatedRequestDto categoryDto) {

        CategoryEntity category = CategoryConvert.convertToEntity(categoryDto);
        boolean categorySave = service.add(category);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(categorySave)
                .message(ResponseMessage.OK);

    }

    @Operation(summary = "Get Category Tree", description = "This method retrieves the category along with its descendants in a tree structure based on the provided ID.")
    @GetMapping("/get/child/{id}")
    public ApiResponse<Object> getCategoryChildId(@PathVariable Long id) {

        CategoryEntity category = service.getById(id);
        CategoryDto dto = CategoryConvert.fromOneLevelChild(category);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);

    }

    @Operation(summary = "Get Category Tree", description = "This method retrieves the category along with its descendants in a tree structure based on the provided ID.")
    @GetMapping("/get/no-child/{id}")
    public ApiResponse<Object> getCategoryId(@PathVariable Long id) {

        CategoryEntity category = service.getById(id);
        CategoryDto dto = CategoryConvert.fromNoChild(category);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(dto)
                .message(ResponseMessage.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "This method Patch", description = "This method is intended to update the category. Updates Category to the value you provide")
    @PatchMapping("/update")
    public ApiResponse<Object> getUpdate(@RequestBody @Validated CategoryUpdate categoryDto) {

        CategoryEntity entity = CategoryConvert.convertToEntity(categoryDto);
        boolean isUpdate = service.update(entity);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(isUpdate)
                .message(ResponseMessage.OK);

    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "This method Delete", description = "This method is category DELETE")
    @DeleteMapping("/deleted/{id}")
    public ApiResponse<Object> getDelete(@PathVariable Long id) {

        service.delete(id);

        return ApiResponse.build()
                .code(ResponseCode.OK)
                .body(ResponseMessage.DELETE_SUCCESS_MESSAGE)
                .message(ResponseMessage.OK);

    }


}
