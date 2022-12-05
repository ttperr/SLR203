package mqtt;

import org.eclipse.paho.client.mqttv3.*;

public class SubscribingMqttClient {
    public static void main(String[] args) {

        String topic = "labs/paho-example-topic";
        int qos = 0;
        String brokerURI = "tcp://localhost:1883";
        String clientId = "myClientID_Sub";
        //MemoryPersistence persistence = new MemoryPersistence();


        try (
                ////instantiate a synchronous MQTT Client to connect to the targeted Mqtt Broker
                MqttClient mqttClient = new MqttClient(brokerURI, clientId)) {


            ////specify the Mqtt Client's connection options
            MqttConnectOptions connectOptions = new MqttConnectOptions();
            //clean session
            connectOptions.setCleanSession(true);
            //customise other connection options here...
            mqttClient.setCallback(new MqttCallback() {

                public void connectionLost(Throwable cause) {
                    System.out.println("connectionLost: " + cause.getMessage());
                }

                public void messageArrived(String topic, MqttMessage message) {
                    System.out.println("\tTopic: " + topic);
                    System.out.println("\tQos: " + message.getQos());
                    System.out.println("\tMessage content: " + new String(message.getPayload()));

                }

                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("deliveryComplete---------" + token.isComplete());
                }

            });

            ////connect the mqtt client to the broker
            System.out.println("Mqtt Client: Connecting to Mqtt Broker running at: " + brokerURI);
            mqttClient.connect(connectOptions);
            System.out.println("Mqtt Client: sucessfully Connected.");

            ////subscribe to a topic
            System.out.println("Mqtt Client: Subscribing to topic: " + topic);
            mqttClient.subscribe(topic, qos);
            System.out.println("Mqtt Client: successfully subscribed to the topic.");

            while (true) {
                System.out.println("Mqtt Client: Waiting for messages...");
                Thread.sleep(3000);
            }

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
