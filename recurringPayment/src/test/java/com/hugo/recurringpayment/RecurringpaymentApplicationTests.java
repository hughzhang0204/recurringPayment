package com.hugo.recurringpayment;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RecurringpaymentApplicationTests {

	@Test
	public void contextLoads() {
	}


	@Test
	public void doesNotExists_404IsReceived()
			throws ClientProtocolException, IOException {

		// Given
		HttpUriRequest request = new HttpGet( "https://localhost:8080/subscript" );

		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

		// Then
		assertEquals(
				httpResponse.getStatusLine().getStatusCode(),
				HttpStatus.SC_NOT_FOUND);

	}


}
