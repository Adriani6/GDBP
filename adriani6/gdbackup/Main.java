package adriani6.gdbackup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;

public class Main extends JavaPlugin implements CommandExecutor{
	
	String response;
	 private static String CLIENT_ID = "YOUR_CLIENT_ID";
	  private static String CLIENT_SECRET = "YOUR_CLIENT_SECRET";

	  private static String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	  
	  GoogleAuthorizationCodeFlow flow;
	  
	    HttpTransport httpTransport = new NetHttpTransport();
	    JsonFactory jsonFactory = new JacksonFactory();
	
	public void onEnable(){
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("basic")) {
			if(args[0].equalsIgnoreCase("start")){


				   
				    flow = new GoogleAuthorizationCodeFlow.Builder(
				        httpTransport, jsonFactory, CLIENT_ID, CLIENT_SECRET, Arrays.asList(DriveScopes.DRIVE))
				        .setAccessType("online")
				        .setApprovalPrompt("auto").build();
				    
				    String url = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI).build();
				    
				    FileConfiguration config = null;
					java.io.File file_config = new java.io.File("plugins"+java.io.File.separator+"GoogleDrive"+java.io.File.separator+"url.yml");
				    config = YamlConfiguration.loadConfiguration(file_config);
				    config.set("URL", url.toString());
				    try {
						config.save(file_config);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				    sender.sendMessage("Link has been generated, please open url.yml and paste the generated link using /gd start upload <code>");
			}else if(args[0].equalsIgnoreCase("upload")){
				
				String code = args[1];
			    GoogleTokenResponse response = null;
				try {
					response = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
			    
			    //Create a new authorized API client
			    Drive service = new Drive.Builder(httpTransport, jsonFactory, credential).build();

			    //Insert a file  
			    File body = new File();
			    body.setTitle("My document");
			    body.setDescription("A test document");
			    body.setMimeType("text/plain");
			    
			    java.io.File fileContent = new java.io.File("document.txt");
			    FileContent mediaContent = new FileContent("text/plain", fileContent);

			    File file;
				try {
					file = service.files().insert(body, mediaContent).execute();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    sender.sendMessage("File uploaded successfully ");
				
			}
			
			
			return true;
		} //If this has happened the function will return true. 
		
	// If this hasn't happened the value of false will be returned.
		return false; 

	}

}
