package hazak.main;

import java.util.HashMap;

public class MyClosure {
	public HashMap<String, Object> record;
	//recordは関数宣言時，args_idがnullとしてconstructされる
	
	public MyClosure() {
		record = new HashMap<String,Object>();
	}
}
