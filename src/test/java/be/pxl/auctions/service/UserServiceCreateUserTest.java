package be.pxl.auctions.service;

import be.pxl.auctions.dao.UserDao;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceCreateUserTest {

    @InjectMocks
    UserService userService;


    @Mock
    UserDao userDao;




	// TODO add unit tests for all possible scenario's of the createUser method
}
