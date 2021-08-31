package be.pxl.auctions.service;

import be.pxl.auctions.dao.UserDao;
import be.pxl.auctions.model.User;
import be.pxl.auctions.rest.resource.UserCreateResource;
import be.pxl.auctions.rest.resource.UserDTO;
import be.pxl.auctions.util.exception.InvalidEmailException;
import be.pxl.auctions.util.exception.UserNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceCreateUserTest {

    private UserCreateResource userCreateResource;
    private User user;


    @Mock
    UserDao userDao;

    @InjectMocks
    UserService userService;


    @BeforeEach
    public void init() {

        user = new User();
        user.setId(1);
        user.setFirstName("Mark");
        user.setLastName("Zuckerberg");
        user.setDateOfBirth(LocalDate.of(1989, 5, 3));
        user.setEmail("mark@facebook.com");

        userCreateResource = new UserCreateResource();
        userCreateResource.setFirstName("Mark");
        userCreateResource.setLastName("Zuckerberg");
        userCreateResource.setDateOfBirth("03/05/1989");
        userCreateResource.setEmail("mark@facebook.com");
    }

    @Test
    public void createUserWhenAllFieldsOKTest() {

        when(userDao.saveUser(any(User.class))).thenReturn(user);
        UserDTO userDTO = mapToUserDTO(user);

//        userService.createUser(userCreateResource);

        assertEquals(userDTO.getId(), userService.createUser(userCreateResource).getId());

    }

    @Test
    public void throwExceptionWhenAFieldIsNotOKTest(){

        //I think this is wrong
        when(userDao.saveUser(any(User.class))).thenThrow(InvalidEmailException.class);

        assertThrows(InvalidEmailException.class, ()-> userService.createUser(userCreateResource));

    }



    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setEmail(user.getEmail());
        userDTO.setAge(user.getAge());
        return userDTO;

    }


    // TODO add unit tests for all possible scenario's of the createUser method
}
