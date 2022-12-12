package mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.time.LocalDateTime;

public class PublishingMqttClient {//synchronous client

    public static void main(String[] args) {

        String topic = "labs/paho-example-topic";
        String messageContent = "Message from my Lab's Paho Mqtt Client at " + LocalDateTime.now();
        int qos = args[0].equals("0") ? 0 : args[0].equals("1") ? 1 : 2;
        boolean cleanSession = args[1].equals("true");
        boolean retained = args[2].equals("true");
        int nbMessages = Integer.parseInt(args[3]);
        String brokerURI = "tcp://localhost:1883";
        String clientId = "myClientID_Pub";
        //MemoryPersistence persistence = new MemoryPersistence();


        try (
                ////instantiate a synchronous MQTT Client to connect to the targeted Mqtt Broker
                MqttClient mqttClient = new MqttClient(brokerURI, clientId)) {



            ////specify the Mqtt Client's connection options
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            //clean session
            connectOptions.setCleanSession(cleanSession);
            //customise other connection options here...
            connectOptions.setWill(topic, "I'm going offline".getBytes(), qos, retained);

            ////connect the mqtt client to the broker
            System.out.println("Mqtt Client: Connecting to Mqtt Broker running at: " + brokerURI);
            mqttClient.connect(connectOptions);
            System.out.println("Mqtt Client: sucessfully Connected.");

            for(int i = 0; i < nbMessages; i++) {
                ////publish a message
                System.out.println("Mqtt Client: Publishing message n°" + i + " : " + messageContent);
                MqttMessage message = new MqttMessage(messageContent.getBytes());//instantiate the message including its content (payload)
                message.setQos(qos);//set the message's QoS
                message.setRetained(retained);//set the message's retained flag
                mqttClient.publish(topic, message);//publish the message to a given topic
                System.out.println("Mqtt Client: successfully published the message n°"+ i +".");
                Thread.sleep(1000);
            }

            ////disconnect the Mqtt Client
            mqttClient.disconnect();
            System.out.println("Mqtt Client: Disconnected.");


        } catch (MqttException e) {
            System.out.println("Mqtt Exception reason: " + e.getReasonCode());
            System.out.println("Mqtt Exception message: " + e.getMessage());
            System.out.println("Mqtt Exception location: " + e.getLocalizedMessage());
            System.out.println("Mqtt Exception cause: " + e.getCause());
            System.out.println("Mqtt Exception reason: " + e);
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
