package uz.ofs.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    private final PasswordEncoder passwordEncoder;

    public void validateID(Long id){
        if(Objects.isNull(id) || id<0){
            throw new NotFoundException(id + "-id does not meet the requirement!");
        }
    }
    public UserEntity validateUser(String username){
        return userRepository.getByUsername(username).orElseThrow(()->new UsernameNotFoundException(username + " username not found!"));
    }

    public void validateUserByUsername(String username) {
        if(Objects.nonNull(userRepository.getUserByUsername(username))){
            throw new UserDataException("User registered with this username!");
        }
    }

    public CategoryEntity validateCategory(Long id){
        validateID(id);
        return categoryRepository.findByCategoryId(id).orElseThrow(() -> new NotFoundException(id + "-id not found!"));
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

       validateFoodProductQuantity(product.getQuantity());

       validateFoodProductStoragePeriod(product.getStoragePeriod());
    }

    private void validateFoodProductQuantity(Double quantity){
        if(quantity==0){
            throw new FoodProductException("The quantity of the product you want to put in the warehouse will not be 0");
        }
    }

    private void validateFoodProductStoragePeriod(LocalDateTime storagePeriod){
        if(LocalDateTime.now().isAfter(storagePeriod)){
            throw new FoodProductException("Your product has expired. Food Storage cannot be saved.");
        }
    }

    public FoodProductEntity validateFoodProduct(Long id){
        validateID(id);
        return foodProductRepository.getFoodById(id).orElseThrow(()-> new NotFoundException(id + "-id not found!"));
    }

    public void validateFoodProductId(Long id){
        validateID(id);
        if (!foodProductRepository.existsById(id)){
            throw new NotFoundException(id+ "-id not found!");
        }
    }


    public FoodProductEntity validateFoodProductUpdate(FoodProductEntity updateEntity) {

        FoodProductEntity entityDB = validateFoodProduct(updateEntity.getId());
        validateCategoryId(updateEntity.getCategoryId());

        String name = updateEntity.getName();
        if(Objects.nonNull(name)){
            entityDB.setName(name);
        }

        Double quantity = updateEntity.getQuantity();
        if(Objects.nonNull(quantity)){
            validateFoodProductQuantity(quantity);
            entityDB.setQuantity(quantity);
        }

        LocalDateTime storagePeriod = updateEntity.getStoragePeriod();
        if(Objects.nonNull(storagePeriod)){
            validateFoodProductStoragePeriod(storagePeriod);
            entityDB.setStoragePeriod(storagePeriod);
        }

        return entityDB;
    }

    public UserEntity validateUserUpdate(UserEntity updateEntity) {
        UserEntity userDB = validateUserId(updateEntity.getId());

        String username = updateEntity.getUsername();
        if(Objects.nonNull(username)){
            if(!Objects.equals(userDB.getUsername(), username)){
                if(validateUser(username)!=null){
                    throw new UserDataException(username+"user with such username exists!");
                }else{
                    userDB.setUsername(username);
                }
            }
        }

        String firstname = updateEntity.getFirstname();
        if(Objects.nonNull(firstname)){
            userDB.setFirstname(firstname);
        }

        String lastname = updateEntity.getLastname();
        if(Objects.nonNull(lastname)){
            userDB.setLastname(lastname);
        }

        String password = updateEntity.getPassword();
        if(Objects.nonNull(password)){
            userDB.setPassword(passwordEncoder.encode(password));
        }

        return userDB;
    }

    public UserEntity validateUserId(Long id) {
        validateID(id);
        return userRepository.getByUserId(id).orElseThrow(()-> new UsernameNotFoundException(id+"-id not found!"));
    }
}
