package uz.ofs.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;
import uz.ofs.constants.CategoryType;
import uz.ofs.constants.EntityStatus;
import uz.ofs.entity.CategoryEntity;
import uz.ofs.entity.FoodProductEntity;
import uz.ofs.entity.UserEntity;
import uz.ofs.exception.FoodProductException;
import uz.ofs.exception.UserDataException;
import uz.ofs.exception.UsernameNotFoundException;
import uz.ofs.repository.CategoryRepository;
import uz.ofs.repository.FoodProductRepository;
import uz.ofs.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Component
@RequiredArgsConstructor
public class CommonValidation {

    private final UserRepository userRepository;

    private final CategoryRepository categoryRepository;

    private final FoodProductRepository foodProductRepository;

    public void validateID(Long id){
        if(Objects.isNull(id) || id<0){
            throw new NotFoundException(id + "-id does not meet the requirement!");
        }
    }
    public UserEntity validateUser(String username){
        return userRepository.getByUsername(username).orElseThrow(()->new UsernameNotFoundException(username + " username not found!"));
    }

    public void validateUserByUsername(String username) {
        if(Objects.nonNull(validateUser(username))){
            throw new UserDataException("User registered with this username!");
        }
    }

    public CategoryEntity validateCategory(Long id){
        return categoryRepository.findByCategoryId(id).orElseThrow(()-> new NotFoundException(id+ "-id not found!"));
    }

    public void validateCategoryId(Long id){

        if(Objects.nonNull(id) && id>0){
            if (!categoryRepository.existsById(id)){
                throw new NotFoundException(id+ "-id not found!");
            }
        }
    }

    public void validateCategoryParent(CategoryEntity entity){

        Long parentId = entity.getParentId();

        if(Objects.nonNull(parentId) && parentId>0){
            CategoryEntity parentEntityDB = validateCategory(parentId);
            entity.setType(parentEntityDB.getType());
        }
    }

    public void validateCategoryName(String name) {

        CategoryEntity categoryDB = categoryRepository.getCategoryName(name);

        if(Objects.nonNull(categoryDB)){
            if(categoryDB.getStatus()== EntityStatus.DELETED){
                categoryRepository.delete(categoryDB);
            }else{
                throw new RuntimeException("There is a category named " + name);
            }
        }
    }

    public CategoryEntity validateCategoryUpdate(CategoryEntity entity) {
        CategoryEntity entityDB = validateCategory(entity.getId());

        String name = entity.getName();
        Long parentId = entity.getParentId();

        if(Objects.nonNull(name)){
            if(!Objects.equals(name, entityDB.getName())){
                validateCategoryName(name);
                entityDB.setName(name);
            }
        }

        if(Objects.nonNull(parentId)){
            validateCategoryId(parentId);
            entityDB.setParentId(parentId);
            validateCategoryParent(entityDB);
        }

        return entityDB;
    }

    public void validateFoodProduct(FoodProductEntity product) {

        Double quantity = product.getQuantity();
        if(quantity==0){
            throw new FoodProductException("The quantity of the product you want to put in the warehouse will not be 0");
        }

        LocalDateTime storagePeriod = product.getStoragePeriod();
        if(LocalDateTime.now().isAfter(storagePeriod)){
            throw new FoodProductException("Your product has expired. Food Storage cannot be saved.");
        }

    }

    public FoodProductEntity validateFoodProduct(Long id){
        validateID(id);
        return foodProductRepository.getFoodById(id).orElseThrow(()-> new NotFoundException(id + "-id not found!"));
    }


}
