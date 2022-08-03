package tests.api;

import api.UsersApi;
import api.models.GetUserRequest;
import io.qameta.allure.Epic;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

@Epic("Проверка получения записей пользователей.")
public class GetUserRequestTest {
    private final int USER_ID = 2;
    private final String EMAIL = "janet.weaver@reqres.in";
    private final String FIRST_NAME = "Janet";
    private final String LAST_NAME = "Weaver";
    private final GetUserRequest TEST_USER = new GetUserRequest(USER_ID, EMAIL, FIRST_NAME, LAST_NAME);

    @Test
    @DisplayName("Успешное получение данных пользователя.")
    public void checkGetUser() {
        GetUserRequest getUserRequest = UsersApi.getUserSuccess(TEST_USER.getId());
        // TODO Add validate JsonSchema
        assertEquals(getUserRequest.getId(), TEST_USER.getId());
        assertEquals(getUserRequest.getEmail(), TEST_USER.getEmail());
        assertEquals(getUserRequest.getFirstName(), TEST_USER.getFirstName());
        assertEquals(getUserRequest.getLastName(), TEST_USER.getLastName());
    }

    @Test
    @DisplayName("Получение данных пользователя с несуществующим ID.")
    public void checkGetUserWithWrongId() {
        int userId = 23;
        UsersApi.getUserFail(userId);
    }

    @Test
    @DisplayName("Успешное получение данных всех пользователей.")
    public void checkGetUsers() {
        List<GetUserRequest> userList = UsersApi.getUsersSuccess();
        assertTrue(userList.contains(TEST_USER));
    }

    @Test
    @DisplayName("Успешное получение данных всех пользователей с параметрами.")
    public void checkGetUsersWithParam() {
        int page = 1;
        int per_page = 12;
        List<GetUserRequest> userList = UsersApi.getUsersSuccessWithParam(page, per_page);
        assertTrue(userList.contains(TEST_USER));
        assertEquals(userList.size(), per_page);
    }
}