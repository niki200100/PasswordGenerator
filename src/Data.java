public class Data {

	private String[] lowerCases={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
	private String[] upperCases={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	private String[] etwLeft= {"1","2","3","4","5","q","w","e","r","t","a","s","d","f","g","y","x","c","v","b"};
	private String[] etwRight= {"6","7","8","9","0","z","u","i","o","p","h","j","k","l","n","m"};
	private String[] symbols= {"!","§","$","%","&","/","(",")","=","?","+","-","*","'","#"};
	private String[] sEtwLeft= {"!","§","%"};
	private String[] sEtwRight= {"&","/","(",")","=","?","+","-","*","'","#"};
	
	public Data(){
		
	}
	
	public String generatePassword(int length, boolean letters, boolean uppercases, int uCCount, boolean lowercases, int lCCount, boolean numbers, int ncount, boolean etw, boolean symbols, int symbolCount){
		
		String password= "";
		String last= "right";
		int upc= uCCount, lcc= lCCount, nc= ncount, sC= symbolCount;
		
		int a=-1;
		if(lowercases==true&& uppercases== false&& numbers== false&& etw== false){
			a=0;
		}else
			if(uppercases== true&& lowercases==false&& numbers== false&& etw== false){
				a=1;
			}else
				if(uppercases== true&& lowercases==true&& numbers==false){
					a=2;
				}else
					if(numbers== true&& letters== false&& etw==false){
						a=3;
					}else
						if(numbers== true&& uppercases== true&& lowercases== false&& etw==false){
							a=4;
						}else
							if(numbers== true&& lowercases== true&& uppercases== false&& etw==false){
								a=5;
							}else
								if(numbers== true&& lowercases== true&& uppercases== true&& etw==false){
									a=6;
								}else
									if(etw==true&& numbers==true&& letters==false){
										a=7;
									}else
										if(etw==true&& lowercases==true&& uppercases==false&& numbers==false){
											a=8;
										}else
											if(etw==true&& lowercases==true&& numbers==true&& uppercases==false){
												a=9;
											}
		
		for(int i=0;i<length;i++){
			
			switch(a){
			case -1: System.out.println("ERROR - NO CASE");break;
			case 0: password= password+letters(false, true, false, null);break;
			case 1: password= password+letters(true, false, false, null);break;
			case 2: password= password+letters(true, true, false, null);break;
			case 3: password= password+numbers(false, null);break;
			case 4: if(upc>0&& nc>0){switch(randomNumber(0, 1)){case 0:password= password+numbers(false, null);nc--;break; case 1:password= password+letters(true, false, false, null);upc--;break;}}else if(nc>0){password= password+numbers(false, null);nc--;}else if(upc>0){password= password+letters(true, false, false, null);upc--;};break;
			case 5: if(lcc>0&& nc>0){switch(randomNumber(0, 1)){case 0:password= password+numbers(false, null);nc--;break; case 1:password= password+letters(false, true, false, null);lcc--;break;}}else if(nc>0){password= password+numbers(false, null);nc--;}else if(lcc>0){password= password+letters(false, true, false, null);lcc--;};break;
			case 6: if(lcc>0&& upc>0&& nc>0){switch(randomNumber(0, 2)){case 0:password= password+numbers(false, null);nc--;break; case 1:password= password+letters(false, true, false, null);lcc--;break; case 2:password= password+letters(true, false, false, null);upc--;break;}}else if(nc>0){password= password+numbers(false, null);nc--;}else if(lcc>0){password= password+letters(false, true, false, null);lcc--;}else if(upc>0){password= password+letters(true, false, false, null);upc--;}break;
			case 7: password= password+numbers(true, last); if(last.equals("right"))last="left";else if(last.equals("left"))last="right";break;
			case 8: password= password+letters(false, false, true, last); if(last.equals("right"))last="left";else if(last.equals("left"))last="right";break;
			case 9: if(lcc>0&& nc>0){switch(randomNumber(0, 2)){case 0:password= password+numbers(true, last); if(last.equals("right"))last="left";else if(last.equals("left"))last="right";nc--;break; case 1:password= password+letters(false, true, true, last); if(last.equals("right"))last="left";else if(last.equals("left"))last="right";lcc--;break;case 2:password= password+symbols(true, last); if(last.equals("right"))last="left";else if(last.equals("left"))last="right";sC--;break;};break;}else if(lcc>0){password= password+letters(false, true, true, last); if(last.equals("right"))last="left";else if(last.equals("left"))last="right";lcc--;}else if(nc>0){password= password+numbers(true, last); if(last.equals("right"))last="left";else if(last.equals("left"))last="right";nc--;}else if(sC>0){password= password+symbols(true, last); if(last.equals("right"))last="left";else if(last.equals("left"))last="right";sC--;}
			}
		}
		
		return password;
	}
	
	private String symbols(boolean etw, String last) {
		
		if(etw== true){
			if(last.equals("right")){
				return getSETWLeft(randomNumber(0, 2));
			}else
				if(last.equals("left")){
					return getSETWRight(randomNumber(0, 10));
				}
		}else
			if(etw== false){
				return getSymbols(randomNumber(0, 14));
			}

		return "A";
	}

	private String letters(boolean uppercases, boolean lowercases, boolean etw, String last){
		
		String letter= "";
		
		if(etw== true){
			if(last.equals("right")){
				letter= getETWLeft(randomNumber(5, 19));
			}else
				if(last.equals("left")){
					letter= getETWRight(randomNumber(5, 15));
				}
		}else
			if(etw== false){
				if(uppercases== true&& lowercases== true){
					int a= randomNumber(0, 3);
					
					switch(a){
					case 0: letter= getLowerCase(randomNumber(0, 25));break;
					case 1: letter= getUpperCase(randomNumber(0, 25));break;
					case 2: letter= getETWLeft(randomNumber(0, 19));break;
					case 3: letter= getETWRight(randomNumber(0, 15));break;
					}
				}else
					if(uppercases== true){
						letter= getUpperCase(randomNumber(0, 25));
					}else
						if(lowercases== true){
							letter= getLowerCase(randomNumber(0, 25));
						}
			}
		
		return letter;
	}
	
	private String numbers(boolean etw, String last){
		
		if(etw== true){
			if(last.equals("right")){
				return getETWLeft(randomNumber(0, 4));
			}else
				if(last.equals("left")){
					return getETWRight(randomNumber(0, 4));
				}
		}else
			if(etw== false){
				return ""+randomNumber(0, 9);
			}

		return "A";
	}
	
	private int randomNumber(int min, int max){
		
		return (int)(Math.random()*((max-min)+1)+min);
	}
	
	private String getLowerCase(int i){
		
		return lowerCases[i];
	}
	
	private String getUpperCase(int i){
		
		return upperCases[i];
	}
	
	private String getETWLeft(int i){
		
		return etwLeft[i];
	}
	
	private String getETWRight(int i){
		
		return etwRight[i];
	}

	private String getSymbols(int i){
		
		return symbols[i];
	}
	
	private String getSETWLeft(int i){
		
		return sEtwLeft[i];
	}
	
	private String getSETWRight(int i){
		
		return sEtwRight[i];
	}
}
