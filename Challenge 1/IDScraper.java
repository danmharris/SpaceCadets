import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class IDScraper {
	static BufferedReader br;
	
	public static void main(String[] args) throws IOException {
		IDScraper scraper = new IDScraper();
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("ECS Detail Scraper");
		System.out.println("------------------");
		
		String goAgain = "";
		do {
			System.out.println();
			scraper.scrape(scraper.inputID());
			
			do { //Input validation
				System.out.print("Would you like to perform another search? [Y/N]: ");
				goAgain = br.readLine();
			} while (!goAgain.toLowerCase().matches("^(y|n)$"));
			
		} while (goAgain.toLowerCase().equals("y"));
		br.close();
	}
	
	public String inputID(){
		System.out.print("Enter User ID: ");
		String userID ="";
		try {
			userID = br.readLine();
		} catch (IOException ioe){
			System.out.println("Error");
		}
		return userID;
	}

	public void scrape(String userID) throws IOException{
		String URLString = "http://www.ecs.soton.ac.uk/people/" + userID + "/";
		URL userPage = new URL(URLString);
		BufferedReader pageReader = new BufferedReader(new InputStreamReader(userPage.openStream())); //Buffer that scrapes the HTML page
		
		int currentLine;
		String line = "";
		
		for (currentLine = 1; currentLine <= 8; currentLine++){ //Goes to line with title tag to check whether it is directory
			line = pageReader.readLine();
		}
		
		if (line.contains("People")){
			System.out.println("Page does not exist, have they made their page public?");
		} else {		
			for (int i = 9; i <= 96; i++){ //Goes down to line 96 where required information is
				line = pageReader.readLine();
			}
			pageReader.close();
			
			int startPosition; 
			
			//Extracts name property
			startPosition = line.indexOf("property=\"name\"");
			String userName = line.substring(startPosition).split("[><]")[1]; //Retrieves string between tags after name property
	
			//Extracts Telephone number property
			startPosition = line.indexOf("property='telephone'");
			String telephone = line.substring(startPosition).split("[><]")[1]; //Retrieves string between tags after telephone property
			telephone = telephone.replaceAll("\\+44", "0"); //Replaces +44 with 0
			
			//Extracts URL property
			startPosition = line.indexOf("property=\"url\"");
			String website = line.substring(startPosition).split("href=\"|\">")[1]; //Retrieves string in href property
			
			//Presents information to the user
			System.out.println("Name: "+userName);
			System.out.println("Telephone: "+telephone);
			System.out.println("Website: "+website);
			System.out.println("ECS Website: " + URLString);
		}
	}
}
