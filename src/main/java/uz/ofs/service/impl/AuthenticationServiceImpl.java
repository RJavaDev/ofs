package uz.ofs.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ofs.constants.Role;
import uz.ofs.util.SecurityUtils;
import uz.ofs.config.token.JwtService;
import uz.ofs.constants.EntityStatus;
import uz.ofs.controller.convert.TokenResponseConvert;
import uz.ofs.dto.request.LoginRequestDto;
import uz.ofs.dto.response.TokenResponseDto;
import uz.ofs.entity.UserEntity;
import uz.ofs.exception.AuthenticationException;
import uz.ofs.exception.UserDataException;
import uz.ofs.exception.UserUnauthorizedAction;
import uz.ofs.repository.UserRepository;
import uz.ofs.service.AuthenticationService;
import uz.ofs.validation.CommonValidation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final CommonValidation commonValidation;


    @Override
    public TokenResponseDto register(UserEntity request) {

        UserEntity userEntity = saveUser(request);
        String jwtToken = jwtService.generateToken(userEntity);

        return TokenResponseConvert.from(jwtToken, userEntity);

    }

    @Override
    public TokenResponseDto authenticate(LoginRequestDto request) {

        UserEntity userDB = verifyUser(request.getUsername(), request.getPassword());
        String jwt = jwtService.generateToken(userDB);

        return TokenResponseConvert.from(jwt, userDB);
    }

    private UserEntity saveUser(UserEntity user) throws UserDataException {

        commonValidation.validateUserByUsername(user.getUsername());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userForCreate(user);

        user.setStatus(EntityStatus.PASSIVE);

        return repository.save(user);

    }

    private void userForCreate(UserEntity user) {

        UserEntity userEntity = SecurityUtils.getUser();

        if (userEntity != null) {
            if (userRoleAdminVerify(userEntity.getRoleList())) {
                user.forCreate(userEntity.getId());
            } else {
                throw new UserUnauthorizedAction(userEntity.getId() + " User Unauthorized action!!!");
            }
        } else
            user.forCreate();
    }

    private boolean userRoleAdminVerify(List<Role> roleEnumList) {
        for (Role e : roleEnumList) {
            if (e == Role.ADMIN)
                return true;
        }
        return false;
    }

    private UserEntity verifyUser(String username, String password) {

        UserEntity user = commonValidation.validateUser(username);

        verifyPassword(password, user.getPassword());

        return user;
    }

    private void verifyPassword(String passwordDto, String userPassword) {
        if (!passwordEncoder.matches(passwordDto, userPassword)) {
            throw new AuthenticationException("Incorrect password");
        }
    }

}
