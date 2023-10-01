package api.testcases;

import api.endpoints.UserEndPoints;
import api.payload.UserPayload;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserTestDD {

    @Test(priority = 1,dataProvider = "AllData",dataProviderClass = DataProviders.class)
    public void testCreateUser(String userId, String UserName, String fname, String lname, String email, String pwd, String phone)
    {
        UserPayload userPayload = new UserPayload();

        userPayload.setId(Integer.parseInt(userId));
        userPayload.setUsername(UserName);
        userPayload.setFirstName(fname);
        userPayload.setLastName(lname);
        userPayload.setEmail(email);
        userPayload.setPassword(pwd);
        userPayload.setPhone(phone);

        Response response = UserEndPoints.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }
    @Test(priority = 2,dataProvider = "UserNamesData",dataProviderClass = DataProviders.class)
    public void testGetUser(String username)
    {
        System.out.println("Get User Data");
        Response response=UserEndPoints.getUser(username);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 3,dataProvider = "UserNamesData",dataProviderClass = DataProviders.class)
    public void testDeleteUser(String username)
    {
        Response response=UserEndPoints.deleteUser(username);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

}
