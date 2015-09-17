package headfirst.decorator.io;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.StringBufferInputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LowerCaseInputStreamTest {

	String word;
	String output;
	StringBufferInputStream fis;
	LowerCaseInputStream lcis;
	ByteArrayOutputStream baos;
	int c;
	@Before
	public void setUp() throws Exception {
		output = "";
		word = "Hello World";
		fis = new StringBufferInputStream(word);
		lcis = new LowerCaseInputStream(fis);
		baos = new ByteArrayOutputStream();
	}

	@After
	public void tearDown() throws Exception {
		fis = null;
		lcis = null;
		baos = null;
	}

	@Test
	public void testRead() {
		try {
			while((c = lcis.read()) >=0){
				baos.write(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(baos.toString());
		assertEquals(baos.toString(), "hello world");
	}

	@Test
	public void testReadByteArrayIntInt() {
		byte[] words = new byte[word.length()];
		try {
			c = lcis.read(words, 2, word.length()-2);
			if(c != -1){
				baos.write(words);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String outputString = baos.toString();
		assertEquals(outputString.substring(2), "hello wor");
	}

}
