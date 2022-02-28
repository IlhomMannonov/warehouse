package uz.pdp.finaltask.servise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.finaltask.entity.User;
import uz.pdp.finaltask.exeption.PageSizeExeption;
import uz.pdp.finaltask.payload.ApiResponce;
import uz.pdp.finaltask.payload.UserDto;
import uz.pdp.finaltask.repo.UserRepo;
import uz.pdp.finaltask.utils.CommandUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public ApiResponce saveOrEdit(UserDto userDto) {
        User user = new User();
        if (userDto.getId() != null) {
            final Optional<User> optionalUser = userRepo.findById(userDto.getId());
            if (optionalUser.isEmpty())
                return new ApiResponce(false, "User Toplimadi");

            user = generateUserFromDto(userDto);

        } else {

            user = generateUserFromDto(userDto);
            userRepo.save(user);
        }
        return new ApiResponce(true, userDto.getId() != null ? "Edited" : "Saved");

    }

    public ApiResponce getAll(int page, int size, boolean all) throws PageSizeExeption {
        List<UserDto> userDtos = new ArrayList<>();
        if (all) {
            userDtos = userRepo.findAll().stream().map(this::generateUserDtoFromUser).collect(Collectors.toList());
        } else {
            userDtos = userRepo.findAll(CommandUtils.ascDescPageable(page, size, false))
                    .stream()
                    .map(this::generateUserDtoFromUser)
                    .collect(Collectors.toList());
        }
        return new ApiResponce(true, "ok", userDtos);
    }


    public User generateUserFromDto(UserDto userDto) {
        User user = new User();

        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setActive(userDto.isActive());
        user.setCode(UUID.randomUUID().toString());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto generateUserDtoFromUser(User user) {
        UserDto userDto = new UserDto();

        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setPhoneNumber(user.getPhoneNumber());
        userDto.setActive(user.isActive());
        userDto.setId(user.getId());
        return userDto;
    }

    public ApiResponce delete(Long id) {

        try {
            userRepo.deleteById(id);
            return new ApiResponce(true, "deleted");
        } catch (Exception e) {
            return new ApiResponce(true, "Not deleted");
        }
    }
}

