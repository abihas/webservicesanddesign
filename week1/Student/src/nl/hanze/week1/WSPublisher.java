package nl.hanze.week1;

import javax.xml.ws.Endpoint;

public class WSPublisher {
	public static void main(String[] args) {
		// test at http://127.0.0.1:8888/Student?wsdl
		Endpoint.publish("http://127.0.0.1:8888/Student", new Room());
	}
}
