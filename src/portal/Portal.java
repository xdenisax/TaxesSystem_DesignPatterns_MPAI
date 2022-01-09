package portal;

import java.util.Scanner;

import database.Database;
import database.DatabaseInterface;
import database.DatabaseProxy;
import entities.Entity;
import entities.EntityFactory;
import entities.EntityInterface;
import taxes.DividendTax;
import taxes.GamblingTax;
import taxes.IncomeTax;
import taxes.ProfitTax;
import taxes.VAT;
import taxes.WageTax;


public class Portal {
	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		DatabaseInterface protectedDatabase = new DatabaseProxy();
		Database fullAccessDatabase = Database.getInstance();
		Boolean isProcessTerminated = false;
		
		while(!isProcessTerminated) {
			System.out.println("Selectati o optiune: \n 1 - Adaugare persoana fizica/juridica \n 2 - Sterge persoana fizica/juridica");

			String userOperation = userInput.nextLine();
			while (!userOperation.equals("1") && !userOperation.equals("2")) {
				System.out.println(" Valoarea introdusa nu corespunde niciunei variante de raspuns. \n Selectati o optiune: \n 1 - Adaugare persoana fizica/juridica \n 2 - Sterge persoana fizica/juridica");
				userOperation = userInput.nextLine();
			}
			if( userOperation.equals("1")) {
				// Factory 
				Entity entity = buildEnitityFromInput(userInput);
				System.out.println(entity.getName() + " " + entity.getAddress() + " " + entity.getIdentifier());
				
				// Decorator
				boolean hasMoreIncomeStreams = true;
				while (hasMoreIncomeStreams) {
					EntityInterface taxableEntity = decorateEntity(getTaxTypeFromInput(userInput), getTaxAmountFromInput(userInput), entity);
					taxableEntity.calculateTaxes();
					
					System.out.println("Doriti adaugarea unui alt venit? \n 1 - Da \n 2 - Nu");
					String userAnswer = userInput.nextLine();
					if(userAnswer.contentEquals("2")) {
						hasMoreIncomeStreams = false;
					} else {
						while (!userAnswer.contentEquals("1")) {
							System.out.println(" Valoarea introdusa nu corespunde niciunei variante de raspuns. \n Doriti adaugarea unui alt venit? Alegeti 1 sau 2. \n 1 - Da \n 2 - Nu");
							userAnswer = userInput.nextLine();
						}
					}
				} 
				
				protectedDatabase.addEntity(entity);
				System.out.println();
				
			} else if( userOperation.equals("2")){
				System.out.println("Aceasta operatiune necesita drepturi de administrator. Introduceti parola: ");
				final String password = "admin";
				int trialsLeft = 3;

				String userPassword = userInput.nextLine();
				while(trialsLeft > 0 && !userPassword.equals(password)) {
					trialsLeft--;
					System.out.println("Parola introdusa nu este corecta. Mai aveti "+ trialsLeft + " incercari");
					userPassword = userInput.nextLine();
				}
				
				if(userPassword.equals(password) && trialsLeft > 0) {
					int deletedIndex;
					if(fullAccessDatabase.getEntities().size() > 0) {
						System.out.println("Alegeti persoana care va fi stearsa");
						for(int i =0 ;i <fullAccessDatabase.getEntities().size(); i++) {
							System.out.println( i + " - " + fullAccessDatabase.getEntities().get(i).presentEntity());
						}
						deletedIndex = Integer.parseInt(userInput.nextLine());	
						while(deletedIndex < 0  && deletedIndex > fullAccessDatabase.getEntities().size()) { 
							System.out.println("Indexul nu corespunde niciunei persoane de mai sus. Reincercati.");
							userInput.nextLine();
						}
						fullAccessDatabase.removeEntity(deletedIndex);
					} else {
						System.out.println("Nu exista nicio inregistrare.");
					}
				}
			}
			
			System.out.println("Doriti sa mai realizati si alte operatiuni? \n 1- Da \n 2- Nu ");
			String userTerminationChoice = userInput.nextLine();
			if( userTerminationChoice.equals("2")) {
				isProcessTerminated = true;
			}
		}
	
		
		userInput.close();
	}

	//Decorator
	public static EntityInterface decorateEntity(String taxType, double taxAmount, Entity entity) {
		EntityInterface taxableEntity = null;
		
		switch(taxType) {
		  case "s":
			  taxableEntity = new WageTax(entity, taxAmount);
		    break;
		  case "d":
			  taxableEntity = new DividendTax(entity, taxAmount);
			break;
		  case "p":
			  taxableEntity = new ProfitTax(entity, taxAmount);
			break;
		  case "j":
			  taxableEntity = new GamblingTax(entity, taxAmount);
			break;
		  case "v":
			  taxableEntity = new IncomeTax(entity, taxAmount);
			break;
		  case "t":
			  taxableEntity = new VAT(entity, taxAmount);
			break;
		  default:
			  System.out.println("Tipul nu este suportat.");
		}
		return taxableEntity;
	}

	//Decorator
	public static String getTaxTypeFromInput(Scanner userInput) {
		System.out.println("Alegeti tipul de venit: \n s - Salariu \n d - Dividende \n p - Profit \n j - Jocuri de noroc \n v - Alte venituri decat salariale  \n t - TVA");
		
		String incomeType="";
		if(userInput.hasNextLine()) {
			incomeType = userInput.nextLine();
			while(!incomeType.equals("s") && 
					!incomeType.equals("d") && 
					!incomeType.equals("p") && 
					!incomeType.equals("j") && 
					!incomeType.equals("v") && 
					!incomeType.equals("t")) {
				System.out.println("Alegeti tipul de venit: \n s - Salariu \n d - Dividende \n p - Profit \n j - Jocuri de noroc \n v - Alte venituri decat salariale  \n t - TVA");
				incomeType = userInput.nextLine();
			}		
		}
		return incomeType;
	}

	//Decorator
	public static double getTaxAmountFromInput(Scanner userInput) {
		System.out.println("Introduceti valoarea venitului");
		return Integer.parseInt(userInput.nextLine());	
	}
	
	// Factory implementation
	public static Entity buildEnitityFromInput(Scanner userInput) {
		EntityFactory entityFactory = new EntityFactory();
		
		System.out.println("Alegeti tipul de persoana. \n f - Persoana Fizica \n j - Persoana Juridica");

		String entityType = "";
		
		entityType = userInput.nextLine();
		while(!entityType.equals("j") && !entityType.equals("J") && !entityType.equals("F") &&!entityType.equals("f")) {
			System.out.println("Alegeti tipul de persoana. \n f - Persoana Fizica \n j - Persoana Juridica");
			entityType = userInput.nextLine();
		}

		System.out.println("Introduceti numele.");
		String name = userInput.nextLine();
		while(name.length() < 3 ) {
			System.out.println("Numele este prea scurt. Reincercati.");
			name = userInput.nextLine();
		}
		
		System.out.println("Introduceti identificatorul.");
		String identifier = userInput.nextLine();
		while(identifier.length() < 8 ) {
			System.out.println("Identificatorul este prea scurt. Reincercati.");
			identifier = userInput.nextLine();
		}

		System.out.println("Introduceti adresa.");
		String address = userInput.nextLine();
		while(address.length() < 3 ) {
			System.out.println("Adresa este prea scurt. Reincercati.");
			address = userInput.nextLine();
		}
		
		return entityFactory.createEntity(entityType, name, identifier, address);
	}
}
