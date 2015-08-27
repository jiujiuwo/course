


public class TestID {

	public static void main(String[] args) {
		
		
		
		String id=GUID.getGUID();
		String idmd5=EncoderHandler.encodeByMD5("12345");
		for(int i=0;i<1000;i++)
		{
		System.out.println(GUID.getGUID());
		}
		
	}

}
