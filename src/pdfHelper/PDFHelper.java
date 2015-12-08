package pdfHelper;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.*;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;


public class PDFHelper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			if (args[0] == null || args[1] == null){
				System.err.println("Source file or desition path not entered");
			}
			
			String sourceDir =  args[0];
			String destinationDir = args[1];
			
			String fileType = "jpg";
			
			File sourceFile = new File(sourceDir);
			File destinationFile = new File(destinationDir);
			if(!destinationFile.exists()){
				destinationFile.mkdir();
				System.out.println("Folder created: " + destinationFile.getAbsolutePath());
			}
			if(sourceFile.exists()){
				System.out.println("Images copied to folder: " + destinationFile.getName());
				PDDocument document = PDDocument.load(sourceDir);
				@SuppressWarnings("unchecked")
				List<PDPage> list = document.getDocumentCatalog().getAllPages();
				
				String fileName = sourceFile.getName().replace(".pdf", "");
				
				BufferedImage image = (list.get(0)).convertToImage();
				File outputfile = new File(destinationDir + fileName + "." + fileType);
				System.out.println("Img created: " + outputfile.getName());
				ImageIO.write(image, fileType, outputfile);
				
				document.close();
				System.out.println("Converted ima saved at: " + destinationFile.getAbsolutePath());
				
			}
			else{
				System.err.println(sourceFile.getName() + " does not exist");
	
			}	
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
