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
            slib.getJson(transcript);

			}
	}
 
