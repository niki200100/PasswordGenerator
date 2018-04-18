public class Control {

	private Gui gui;//multitier architecture
	private Data data;//multitier architecture
	
	public Control(){
		
		gui= new Gui(this);//multitier architecture
		data= new Data();//multitier architecture
		
		gui.createGui();
	}
	
	public String getPassword(int length, boolean letters, boolean uppercases, int uCCount, boolean lowercases, int lCCount, boolean numbers, int ncount, boolean etw, boolean symbols, int symbolCount){
		
		return data.generatePassword(length, letters, uppercases, uCCount, lowercases, lCCount, numbers, ncount, etw, symbols, symbolCount);
	}
}