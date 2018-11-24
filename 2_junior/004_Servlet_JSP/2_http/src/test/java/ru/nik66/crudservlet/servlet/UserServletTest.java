package ru.nik66.crudservlet.servlet;

import org.junit.Before;
import org.junit.Test;
import ru.nik66.crudservlet.Validate;
import ru.nik66.crudservlet.ValidateService;
import ru.nik66.crudservlet.model.Role;
import ru.nik66.crudservlet.model.User;
import ru.nik66.crudservlet.store.MemoryStore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

//@RunWith(PowerMockRunner.class)
//@PrepareForTest(DbStore.class)
public class UserServletTest {

    private final Validate validate = ValidateService.getInstance();

    @Before
    public void init() {
        this.validate.clear();
    }

    @Test
    public void whenAddUser() throws Exception {
        Map<String, String[]> params = new HashMap<>();
        params.put("action", new String[]{"add"});
        params.put("name", new String[]{"qqq"});
        params.put("login", new String[]{"qqq"});
        params.put("password", new String[]{"qqq"});
        params.put("role", new String[]{"USER"});
        params.put("email", new String[]{"qwe@qwe.qw"});
        UserServlet servlet = new UserServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameterMap()).thenReturn(params);
        servlet.doPost(req, resp);
        String actual = this.validate.findAll().get(0).getName();
        String expected = "qqq";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenUpdateUser() throws Exception {
        User oldUser = new User("aaa", "aaa", "aaa", Role.USER, "aaa@a.a", LocalDateTime.now());
        MemoryStore.getInstance().add(oldUser);
        Map<String, String[]> params = new HashMap<>();
        params.put("id", new String[]{String.valueOf(oldUser.getId())});
        params.put("action", new String[]{"update"});
        params.put("name", new String[]{"qqq"});
        params.put("login", new String[]{"aaa"});
        params.put("password", new String[]{"aaa"});
        params.put("role", new String[]{"USER"});
        params.put("email", new String[]{"aaa@a.a"});
        UserServlet servlet = new UserServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameterMap()).thenReturn(params);
        servlet.doPost(req, resp);
        String actual = this.validate.findAll().get(0).getName();
        String expected = "qqq";
        assertThat(actual, is(expected));
    }

    @Test
    public void whenDeleteUser() throws Exception {
        User user = new User("aaa", "aaa", "aaa", Role.USER, "aaa@a.a", LocalDateTime.now());
        MemoryStore.getInstance().add(user);
        Map<String, String[]> params = new HashMap<>();
        params.put("id", new String[]{String.valueOf(user.getId())});
        params.put("action", new String[]{"delete"});
        UserServlet servlet = new UserServlet();
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameterMap()).thenReturn(params);
        servlet.doPost(req, resp);
        List<User> actual = this.validate.findAll();
        List<User> expected = Collections.emptyList();
        assertThat(actual, is(expected));
    }

//    // PowerMock in console error:
//    // java.lang.LinkageError: loader constraint violation: when resolving method "java.lang.management.ManagementFactory.getPlatformMBeanServer()Ljavax/management/MBeanServer;" the class loader (instance of org/powermock/core/classloader/MockClassLoader) of the current class, org/apache/commons/dbcp2/PoolableConnection, and the class loader (instance of <bootloader>) for the method's defining class, java/lang/management/ManagementFactory, have different Class objects for the type javax/management/MBeanServer used in the signature
//    @Test
//    public void whenAddUser() throws Exception {
//        Map<String, String[]> params = new HashMap<>();
//        params.put("action", new String[]{"add"});
//        params.put("name", new String[]{"qqq"});
//        params.put("login", new String[]{"qqq"});
//        params.put("password", new String[]{"qqq"});
//        params.put("role", new String[]{"USER"});
//        params.put("email", new String[]{"qwe@qwe.qw"});
//        UserServlet servlet = new UserServlet();
//
//        Store dbStore = DbStore.getInstance();
//        Store memoryStore = MemoryStore.getInstance();
//        mockStatic(DbStore.class);
//        when(dbStore).thenReturn(memoryStore);
//
//        HttpServletRequest req = mock(HttpServletRequest.class);
//        HttpServletResponse resp = mock(HttpServletResponse.class);
//        when(req.getParameterMap()).thenReturn(params);
//        servlet.doPost(req, resp);
//
//        for (User user : memoryStore.findAll()) {
//            System.out.println(user);
//        }
//    }

}