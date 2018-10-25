package speechtotext;

import java.io.File;
import java.io.FileNotFoundException;

import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechRecognitionResults;

public class SpeechtoText_lib {
	
	SpeechToText service;
    RecognizeOptions options = null;
    
    public SpeechtoText_lib(File audio) {
    	service = new SpeechToText();
	    service.setUsernameAndPassword("1618037", "1618037");
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
}
