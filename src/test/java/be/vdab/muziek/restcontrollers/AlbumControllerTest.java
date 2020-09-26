package be.vdab.muziek.restcontrollers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
// !!
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// INTEGRATION TEST >>                                               << ( NO UNIT TEST )
// inject MOCKMVC OBJECT                                                ( NO domain objects needed )
// && use  perform( GET...)   .andExpect( status() | jsonPath()  )      ( NO assertThat( obj.meth() ).is...(testObj) )


@SpringBootTest
@AutoConfigureMockMvc
@Sql("/insertAlbums.sql")
public class AlbumControllerTest extends AbstractTransactionalJUnit4SpringContextTests {

    private final MockMvc mockMvc;

    public AlbumControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    public long idVanTestAlbum() {
        return super.jdbcTemplate.queryForObject(
                "select id from albums where naam ='test'",
                Long.class
        );
    }

    @Test
    void onbestaandeIdWerptException() throws Exception {
        mockMvc.perform(
                get("/albums/{id}", -1)
        ).andExpect(
                status().isNotFound()
        );
    }

    @Test
    void bestaandeIdGeeftCorrecteResponse() throws Exception {
        mockMvc.perform(
                get("/albums/{id}", idVanTestAlbum() )
        ).andExpect(
                status().isOk()
        );
    }
}
