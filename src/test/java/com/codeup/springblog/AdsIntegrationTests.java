package com.codeup.springblog;

import com.codeup.springblog.models.Ad;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.AdRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpSession;

import java.util.Date;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringblogApplication.class)
@AutoConfigureMockMvc
public class AdsIntegrationTests {

    private HttpSession httpSession;

    // constructor dependency injection not allowed in tests

    @Autowired
    private MockMvc mvc;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private AdRepository adsDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Before
    /**
     * Create use an existing test user or create a new test user and login to the app, storing the active session in the instance field.
     */
    public void setup() throws Exception {
        User testUser = userDao.findByUsername("testUser");

        if (testUser == null) {
            User newUser = new User();
            newUser.setUsername("testUser");;
            newUser.setPassword(passwordEncoder.encode("pass"));
            newUser.setEmail("testUser@codeup.com");
            userDao.save(newUser);
        }

        this.httpSession = mvc.perform(
                post("/login").with(csrf())
                        .param("username", "testUser")
                        .param("password", "pass")
        )
                .andExpect(status().is(HttpStatus.FOUND.value()))
                .andExpect(redirectedUrl("/ads"))
                .andReturn()
                .getRequest()
                .getSession();
    }

    @Test
    /**
     * Check if the the mvc context loaded properly.
     */
    public void contextLoads() {
        assertNotNull(mvc);
    }

    @Test
    /**
     * Check if the test user is successfully logged in to the test context.
     */
    public void testSessionIsActive() {
        assertNotNull(httpSession);
    }

    @Test
    /**
     * Create a new ad and verify that a successful redirection occurs.
     * NOTE: this approach requires a new ad be created each time the tests are run.
     */
    public void testAdCreation() throws Exception {
        this.mvc.perform(
                post("/ads/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "test create ad" + new Date().toString())
                        .param("description", "this is the latest test ad")
        )
                .andExpect(status().is3xxRedirection());
    }

    @Test
    /**
     * Check on an existing ad to see if the show page displays the information from the database.
     */
    public void testAdShow() throws Exception {
        Ad existingAd = adsDao.findAll().get(0);
        this.mvc.perform(
                get("/ads/" + existingAd.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(existingAd.getTitle())))
                .andExpect(content().string(containsString(existingAd.getDescription())));
    }

    @Test
    /**
     * Check if the information for a given ad in the DB is viewable.
     */
    public void testAdView() throws Exception {
        Ad randomAd = adsDao.findAll().get(0);
        this.mvc.perform(
                get("/ads")
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("this is the latest test ad")))
                .andExpect(content().string(containsString(randomAd.getTitle()))
                );
    }

    @Test
    /**
     * Check if the ad, once edited is viewable with the new information on it's show page.
     */
    public void testAdEdit() throws Exception {
        Ad adToEdit = adsDao.findAll().get(0);
        String editTitle = "title " + new Date().toString();

        this.mvc.perform(
                post("/ads/" + adToEdit.getId() + "/edit").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", editTitle)
                        .param("description", "edited ad description")
        )
                .andExpect(status().is3xxRedirection());

        this.mvc.perform(
                get("/ads/" + adToEdit.getId())
        )
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(editTitle)))
                .andExpect(content().string(containsString("edited ad description")));
    }

    @Test
    /**
     * Create an ad to then delete.
     */
    public void testAdDelete() throws Exception {
        this.mvc.perform(
                post("/ads/create").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("title", "title to delete")
                        .param("description", "description to delete")
        )
                .andExpect(status().is3xxRedirection());

        Ad adToDelete = adsDao.findByTitle("title to delete");

        this.mvc.perform(
                post("/ads/" + adToDelete.getId() + "/delete").with(csrf())
                        .session((MockHttpSession) httpSession)
                        .param("id", String.valueOf(adToDelete.getId()))
        )
                .andExpect(status().is3xxRedirection());
    }


}
