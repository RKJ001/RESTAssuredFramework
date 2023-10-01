package api.testcases;

import api.endpoints.UserEndPoints2;
import api.payload.UserPayload;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class UserTests2 {

    Faker faker;
    UserPayload userPayload;
    public static Logger logger;
    @BeforeClass
    public void generateTestData()
    {
        faker=new Faker();
        userPayload=new UserPayload();

        userPayload.setId(faker.idNumber().hashCode());
        userPayload.setUsername(faker.name().username());
        userPayload.setFirstName(faker.name().firstName());
        userPayload.setLastName(faker.name().lastName());
        userPayload.setEmail(faker.internet().safeEmailAddress());
        userPayload.setPassword(faker.internet().password(5,10));
        userPayload.setPhone(faker.phoneNumber().cellPhone());

        logger= LogManager.getLogger("PetstoreAPIframework");
    }
    @Test(priority = 1)
    public void testCreateUser()
    {
        Response response=UserEndPoints2.createUser(userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("Create user executed");
    }
    @Test(priority = 2)
    public void testGetUser()
    {
        System.out.println("Get User Data");
        Response response=UserEndPoints2.getUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("Get user executed");
    }
    @Test(priority = 3)
    public void testUpdateUser()
    {
        userPayload.setFirstName(faker.name().firstName());
        Response response=UserEndPoints2.updateUser(userPayload,this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        //Read updates user to verify
        System.out.println("User After Update");
        Response responsePostUpdate=UserEndPoints2.getUser(this.userPayload.getUsername());
        responsePostUpdate.then().log().all();

        logger.info("Update user executed");
    }
    @Test(priority = 4)
    public void testDeleteUser()
    {
        Response response=UserEndPoints2.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        logger.info("Delete user executed");
    }
}
