package no.hvl.dat110.messaging;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = null;
		byte[] data;
		
		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer

		segment = new byte[SEGMENTSIZE];
		data = message.getData();

		byte dataLength = (byte)data.length;

        // Første byte av segmenten er lengden av dataen
        segment[0] = dataLength;

		for(int i = 0; i<dataLength; i++){
			segment[i+1] = data[i];
		}

		//Man kan også gjøre slik. src, srcpostition, dest, destStartPos, length.
		//System.arraycopy(data, 0, segment, 1, dataLength);
       
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		//message innholder byte[data].
		Message message = null;

		int length = segment[0]; // == lengden er lagret i pos0, se encapsulate.
		byte[] data = new byte[length]; //== datatabellen som skal legges til i message med korrekt lengde.
		
		// TODO - START
		// decapsulate segment and put received payload data into a message
		
		//kopierer over elementene fra segment[] ,f.o.m index 1 til slutt, til data[].
		System.arraycopy(segment, 1, data, 0, length);

		message = new Message(data);
		
		return message;
		
	}
	
}
