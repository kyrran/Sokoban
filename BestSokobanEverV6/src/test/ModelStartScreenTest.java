package test;

import static org.junit.Assert.*;

import org.junit.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sokodan.MVCStartScreen.ModelStartScreen;
/**This class is used as testing for modelstartscreen class
 * 
 * @author Kangle Yuan
 *
 */
public class ModelStartScreenTest {

	@Test
	public void testModelStartScreen() {
		String wallcolor = "pink";
		String username = "heyNo.1";
		ObservableList<String>m_list = FXCollections.observableArrayList("yellow", "white");
		
		ModelStartScreen object1 = new ModelStartScreen(wallcolor, username, m_list);
		
		assertEquals(wallcolor, ModelStartScreen.getM_wallcolor());
	    assertEquals(username, ModelStartScreen.getM_username());
	    assertEquals(m_list, object1.getM_list());
	}
	
	@Test
	public void testModelStartScreenForLongString() {
		String wallcolor = "pinkkkkkkkkkkkkkkkkkkkkkkk";
		String username = "heyNo.12222222222tttttttttttt6666666";
		ObservableList<String>m_list = FXCollections.observableArrayList("yellow", "white",
											"yellow", "white","yellow","yellow", "white", 
											"white","yellow", "white","yellow", "white",
											"yellow", "white","yellow", "white","yellow", 
											"white","yellow", "white","yellow", "white");
		
		ModelStartScreen object1 = new ModelStartScreen(wallcolor, username, m_list);
		
		assertEquals(wallcolor, ModelStartScreen.getM_wallcolor());
	    assertEquals(username, ModelStartScreen.getM_username());
	    assertEquals(m_list, object1.getM_list());
	}

}
