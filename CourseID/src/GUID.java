

import java.util.UUID;


public class GUID {
	public static String getGUID(){
		// ���� GUID ����
	      UUID uuid = UUID.randomUUID();
	      // �õ����������ID
	      String a = uuid.toString();
	      // ת��Ϊ��д
	      a = a.toUpperCase();
	      // �滻 -
	       a = a.replaceAll("-", "");
	     return a;
	}
}
