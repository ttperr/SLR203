package mqtt;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MQTTClient {
    public static void main(String[] args) throws Exception {
        // Connect to the MQTT broker
        Socket socket = new Socket("localhost", 1883);

        // Get the input and output streams for the socket
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        // Define the byte array for the CONNECT message
        byte[] connectPacket = {
            0x10, // The first byte of the MQTT message (CONNECT Command Type and Control Flags)
            0x0a, // The length of the remaining packet
            0x00, 0x04, // The length of the Protocol Name (4 bytes)
            0x4d, 0x51, 0x54, 0x54, // The Protocol Name ("MQTT")
            0x04, // Protocol Level (4)
            0x00, // Connect Flags
            0x00, 0x3c, // Keep Alive (60 seconds)
            0x00, 0x0a, // The length of the client ID
            0x74, 0x65, 0x73, 0x74, 0x69, 0x6e, 0x67, 0x73, 0x74, 0x72 // The client ID ("testingstr")
        };

        // Send the CONNECT message to the broker
        out.write(connectPacket);

        // Read the response from the broker (CONNACK)
        byte[] connackPacket = new byte[4];
        in.read(connackPacket);

        // Display the received CONNACK message, byte by byte
        for (byte b : connackPacket) {
            System.out.printf("0x%02X%n", b);
        }

        // Close the socket
        socket.close();
    }
}