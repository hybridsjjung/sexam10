package hybrid.webapp;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
public class HostsOutputTest {
	
	static Log log = LogFactory.getLog(HostsOutputTest.class);

	String hosts = "C:\\Windows\\System32\\drivers\\etc\\hosts";
	
	@Test
	public void test1_hosts() throws IOException {
		
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.info("test1_hosts() ");
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		FileReader reader = new FileReader(hosts);
		
		int ch = reader.read(); // 한 글자씩 읽는다
		while(ch > 0) {
			System.out.print((char)ch);
			ch = reader.read();
		}
	}
	
	@Test
	public void test2_hosts() throws IOException {
		
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.info("test2_hosts() ");
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		FileInputStream in = new FileInputStream(hosts);
		InputStreamReader reader = new InputStreamReader(in, "euc-kr"); // 한글 encoding 처리

		int ch = reader.read();
		while(ch > 0) {
			System.out.print((char)ch);
			ch = reader.read();
		}
		
	}
	
	@Test
	public void test3_hosts() throws IOException {
		
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@");
		log.info("test3_hosts() ");
		log.info("@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		FileSystemResource resource = new FileSystemResource(hosts);
		InputStream in = resource.getInputStream();
		
		Scanner scan = new Scanner(in, "euc-kr");
		
		while(scan.hasNextLine()) {
			String line = scan.nextLine();
			line = line.trim(); // 양 사이드의 공백 지운다
			
			if(line.startsWith("#") || line.isEmpty())
				continue;
				
			//log.info(line);
				
			StringTokenizer tokens = new StringTokenizer(line);
			String parseLine = "";
			
			while(tokens.hasMoreElements()) {
				String token = tokens.nextToken();
				parseLine += token + "###";
			}	
			
			log.info(parseLine);	
		}
		
		
	}

}
