package speechtotext;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

public class SpeechtoText_lib {
	
	SpeechToText service;
    RecognizeOptions options = null;
    
    public SpeechtoText_lib(File audio) {
    	service = new SpeechToText();
	    service.setUsernameAndPassword("99aca234-f422-4112-9a60-93f26a3dd22a", "4h44R1WPnKyr");
	    try {
			   options = new RecognizeOptions.Builder()
				.model("ja-JP_BroadbandModel")
			    .audio(audio)
		        .contentType(RecognizeOptions.ContentType.AUDIO_MP3)
		        .build();
		   } catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    
    public SpeechRecognitionResults getTranscript() {
        SpeechRecognitionResults transcript = service.recognize(options).execute();
        return transcript;
    }
    
    public void getJson(SpeechRecognitionResults transcript) {
	String s = String.valueOf(transcript);
	   ObjectMapper mapper = new ObjectMapper();
	   MySQL mysql = new MySQL();
	   
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
		mysql.updateImage(text, confidence);
		
		}
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }
}
