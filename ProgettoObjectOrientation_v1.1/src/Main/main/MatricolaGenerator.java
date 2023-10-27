package main;
import DAOs.StudenteDAOimpl;
import DAOs.StudenteDAOinterf;

public class MatricolaGenerator {
	String massimo;
	StudenteDAOinterf studenteDAO;
	
	public MatricolaGenerator(Controller controller) {
		studenteDAO = new StudenteDAOimpl(controller);
	}
	
	private void getMassimo() {
		massimo = studenteDAO.massimoMatricola();
	}
	
	public String nuovaMatricola() {
		getMassimo();
		String[] parts = massimo.split("A");
		String part2 = parts[1];
		int number = 0;
		try{
            number = Integer.parseInt(part2);
            number++;
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
		massimo = "NA" + number;
		return massimo;
	}
	
	
}
