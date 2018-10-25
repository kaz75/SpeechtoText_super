package speechtotext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;

public class SpeechtoText_main {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			SpeechtoText_lib slib = new SpeechtoText_lib(new File("audio/track06.mp3"));
            SpeechRecognitionResults transcript = slib.getTranscript();
            System.out.println(transcript);

			
					   String s = String.valueOf(transcript);
					   ObjectMapper mapper = new ObjectMapper();
					   
					   try {
						JsonNode node = mapper.readTree(s);
						for(int i = 0; i < node.get("results").size(); i++) {
						//String text = node.get("results").toString();
						//String text = node.get("results").get(0).toString();
						//System.out.println(text);
						String text = node.get("results").get(i).get("alternatives").get(0).get("transcript").toString();
						System.out.println("transcript : "+ text);
						double confidence = node.get("results").get(i).get("alternatives").get(0).get("confidence").asDouble();
						System.out.println("confidence : "+ confidence);
						
						}
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}
		private static void writeToFile(InputStream in, File file) {
			   try {
			     OutputStream out = new FileOutputStream(file);
			     byte[] buf = new byte[1024];
			     int len;
			     while ((len = in.read(buf)) > 0) {
			       out.write(buf, 0, len);
			     }
			     out.close();
			     in.close();
			   } catch (Exception e) {
			     e.printStackTrace();
			   }
			 }

	}
