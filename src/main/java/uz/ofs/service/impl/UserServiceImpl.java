package uz.ofs.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.MapUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.ofs.dto.dtoUtil.FilterForm;
import uz.ofs.entity.UserEntity;
import uz.ofs.repository.UserRepository;
import uz.ofs.service.UserService;
import uz.ofs.validation.CommonValidation;

import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    private final CommonValidation commonValidation;

    @Override
//    @Cacheable(value = "userCache")
    public UserEntity getById(Long id) {
        System.out.println("user servicedan kelyabdi");
        return commonValidation.validateUserId(id);
    }

    @Override
    public boolean update(UserEntity updateEntity) {

        UserEntity updateEntityDB = commonValidation.validateUserUpdate(updateEntity);

        repository.save(updateEntityDB);
        return true;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commonValidation.validateUserId(id);
        repository.userDelete(id);
    }

    @Override
    public Page<UserEntity> getFilterPage(FilterForm filter) {
        Sort sort = orderSortField("id");

        Pageable pageable = pageable(sort, filter);
        Map<String, Object> filterMap = filter.getFilter();

        String firstname = null;
        String username = null;

        if(Objects.nonNull(filterMap)){
            if (filterMap.containsKey("firstname")) {
                firstname = MapUtils.getString(filterMap, "firstname");
            }
            if(filterMap.containsKey("username")){
                username=MapUtils.getString(filterMap, "username");
            }
        }


        return repository.getPageUser(username, firstname, pageable);
    }


    public Sort orderSortField(String field) {
        return Sort.by(Sort.Order.by(field));
    }

    public Pageable pageable(Sort sort, FilterForm filterForm) {
        return PageRequest.of(filterForm.getStart() / filterForm.getLength(), filterForm.getLength(), sort);
    }
}
