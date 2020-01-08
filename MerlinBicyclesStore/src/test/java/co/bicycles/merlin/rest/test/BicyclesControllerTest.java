package co.bicycles.merlin.rest.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import co.bicycles.merlin.rest.model.Bicycle;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BicyclesControllerTest {

  @LocalServerPort
  private int port;

  private URL base;

  @Autowired
  private TestRestTemplate template;

  @Before
  public void setUp() throws Exception {
    this.base = new URL("http://localhost:" + port + "/bicycles");
  }

  @Test
  public void createOneBicycle() throws Exception {
	  Bicycle bike = new Bicycle("", "name", "color", "brand", "rim", "frame", 1.0, "nickName");
	  HttpEntity<Bicycle> request = new HttpEntity<>(bike);
	  ResponseEntity<Bicycle> response = template.postForEntity(base.toString(), request, Bicycle.class);
	  assertEquals(response.getStatusCodeValue(), HttpStatus.CREATED.value());
  }
  
  @Test
  public void getOneBicycle() throws Exception {
	  Bicycle bike = new Bicycle("", "name", "color", "brand", "rim", "frame", 1.0, "nickName");
	  HttpEntity<Bicycle> request = new HttpEntity<>(bike);
	  ResponseEntity<Bicycle> response = template.postForEntity(base.toString(), request, Bicycle.class);
	  System.out.println(response.getStatusCodeValue());
	  System.out.println(response.getHeaders().getLocation());
	  ResponseEntity<Bicycle> responseGet = template.getForEntity(response.getHeaders().getLocation(),Bicycle.class);
	  assertEquals(responseGet.getStatusCodeValue(), HttpStatus.OK.value());
  }
}
