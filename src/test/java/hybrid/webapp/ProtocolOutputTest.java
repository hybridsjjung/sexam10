package hybrid.webapp;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.core.io.FileSystemResource;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProtocolOutputTest {

	static Log log = LogFactory.getLog(HostsOutputTest.class);
	
	String protocol = "C:\\Windows\\System32\\drivers\\etc\\protocol";
	
	@Test
	public void test1_protocol() throws IOException {
		
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.info("test1_protocol() ");
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		FileInputStream in = new FileInputStream(protocol);
		InputStreamReader reader = new InputStreamReader(in, "euc-kr"); // 한글 encoding 처리

		int ch = reader.read();
		while(ch > 0) {
			System.out.print((char)ch);
			ch = reader.read();
		}
		
	}
	
	@Test
	public void test2_protocol() throws IOException {

		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.info("test2_protocol()");
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		FileSystemResource resource = new FileSystemResource(protocol);
		InputStream in = resource.getInputStream();
		
		Scanner scan = new Scanner(in, "euc-kr");
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			line = line.trim(); // 양 사이드의 공백 지운다
			
			if(line.startsWith("#") || line.isEmpty())
				continue;
				
			
			StringTokenizer tokens = new StringTokenizer(line);
			String parseLine = "";
			
			while(tokens.hasMoreElements()) {
				String token = tokens.nextToken();
				
				if(token.startsWith("#")) {
					break;
				} else {
					parseLine += token + "###";
				}

			}	
			
			log.info(parseLine);	

		}
		
	}

}
