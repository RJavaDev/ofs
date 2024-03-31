package uz.ofs.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.ofs.dto.dtoUtil.FilterForm;
import uz.ofs.entity.UserEntity;
import uz.ofs.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Override
    public UserEntity getById(Long id) {
        return null;
    }

    @Override
    public boolean update(UserEntity updateEntity) {
        return false;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<UserEntity> getFilterPage(FilterForm filterForm) {
        return null;
    }
}
