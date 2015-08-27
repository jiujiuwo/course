

import java.security.MessageDigest;

public class EncoderHandler {
	private static final String ALGORITHM_MD5 = "MD5";
	private static final String ALGORITHM_SHA_1 = "SHA-1";
//	private static final String ALGORITHM_SHA_256 = "SHA-256";
//	private static final String ALGORITHM_SHA_384 = "SHA-384";
//	private static final String ALGORITHM_SHA_512 = "SHA-512";
	

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	
	// ������ʽΪ���ָ��ַ���
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        // System.out.println("iRet="+iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 16;
        int iD2 = iRet % 16;
       
        
        return String.valueOf(HEX_DIGITS[iD1])+ String.valueOf(HEX_DIGITS[iD2]);
    }

    // ������ʽֻΪ����
    /*
    private static String byteToNum(byte bByte) {
        int iRet = bByte;
        System.out.println("iRet1=" + iRet);
        if (iRet < 0) {
            iRet += 256;
        }
        return String.valueOf(iRet);
    }
    */

    // ת���ֽ�����Ϊ16�����ִ�
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

	/**
	 * ɢ����
	 *
	 * @param algorithm
	 * @param str
	 * @return String
	 */
	public static String encodeBySHA(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM_SHA_1);
			messageDigest.update(str.getBytes());
			return byteToString(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * encode By MD5
	 *
	 * @param str
	 * @return String
	 */
	public static String encodeByMD5(String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM_MD5);
			messageDigest.update(str.getBytes());
			return byteToString(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
