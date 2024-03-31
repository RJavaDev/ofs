package uz.ofs.service;

import org.springframework.data.domain.Page;
import uz.ofs.dto.dtoUtil.FilterForm;
import uz.ofs.entity.UserEntity;

public interface UserService extends BaseService<UserEntity>{

    Page<UserEntity> getFilterPage(FilterForm filterForm);
}
