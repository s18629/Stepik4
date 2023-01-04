


import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

//change that shit ffs!!!

public class MockitoTest {
    FriendsCollection friendsMock = mock(FriendsCollection.class);
    FriendshipsMongo friendshipsMongoMock = mock(FriendshipsMongo.class);
    List<String> list = new ArrayList<>();
    Person firstPerson = new Person("Michael");
    Person secondPerson = new Person("Mike");
    Person thirdPerson = new Person("Frank");

    @Test
    public void MockInstanceOfTest() {
        Assert.assertTrue(friendsMock instanceof FriendsCollection);
    }


    @Test
    public void mockingTest(){
        when(friendshipsMongoMock.areFriends(firstPerson.getName(), secondPerson.getName())).thenReturn(true);
        assertThat(friendshipsMongoMock.areFriends(firstPerson.getName(), secondPerson.getName())).isEqualTo(true);
    }


    @Test
    public void savePersonTest(){
        Person person = new Person("Mike");
        friendsMock.save(thirdPerson);
        when(friendsMock.findByName("Mike")).thenReturn(person);
        assertThat(friendsMock.findByName("Mike")).isEqualTo(person);
    }

    @Test
    public void addFriends(){
        friendshipsMongoMock.makeFriends(firstPerson.getName(), thirdPerson.getName());
        when(friendshipsMongoMock.areFriends("Michael", "Mike")).thenReturn(true);
        assertThat(friendshipsMongoMock.areFriends("Michael", "Mike")).isEqualTo(true);
    }

    @Test
    public void getFriendsTest(){
        friendsMock.save(firstPerson);
        friendsMock.save(secondPerson);
        friendshipsMongoMock.makeFriends(firstPerson.getName(), secondPerson.getName());
        List<String> friends = new ArrayList<>();
        friends.add("Mike");
        when(friendshipsMongoMock.getFriendsList("Michael")).thenReturn(friends);
        assertTrue(friendshipsMongoMock.getFriendsList("Michael").contains("Mike"));
    }


}