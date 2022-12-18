# Q&A

## Q1: Once connected, can you find the 'Connection Options'?
  
* *CleanSession - True or false ? What does this mean ? (Cf. lecture notes or online documentation)*

Clean session is on true. This allows to declare the session as persistent or not. If this is on true then all clients info is stored until the clients requests a cleanSession.

* *KeepAliveInterval - what is the value and its measuring unit ? What does it mean ?*

This is the longest period (in seconds) with no communication between Client & Broker.

* *ConnectionTimeout - what is the value and its measuring unit ? What does it mean ?*

This is the longest period (in seconds) while trying to connect to the MQTT server.

* *Is your connection secure? if so, how ?*

The connection is not secured. To handle that i need to do an user/password connection.

## *Q2: Can you figure-out how to change your connection's configuration values ? E.g. change the KeepAliveInterval to 70s.*

Yes, Manage connections -> detailed -> KeepAliveInterval

## *Q3: can you check that the message was sent correctly? (e.g. by checking the message's content, or by checking the message's properties)*

Yes, in the window below.

## *Q4: how can you tell whether the subscription was established correctly ?*

Also in the window below.

## *Clean Session and QoS Configurations*

### *Test 4.1: Clean Session = True, QoS = 0*

The clean session is on true, so the client will not store any information about the connection. The QoS is 0, so the message is sent and received only once. And the subscribing client will not receive it.

### *Test 4.2: Clean Session = False, QoS = 0*

The broker hasn't stored any information about the connection, so the client has not received the message.

### *Test 4.3: Clean Session = False, QoS = 1*

The broker has stored the information about the connection, so the client has received the message.

### *Test 4.4: Clean Session = True, QoS = 1*

The broker has not stored the information about the connection, so the client has not received the message.

### *Test 4.5: Clean Session = False for the subscriber and true for the publisher, QoS = 1 for both clients*

The broker has stored the information about the connection, so the client has received the message.

### *Test 4.6: Same as above, except QoS = 0 for the publishers*

QoS = 0 for the publisher and QoS = 1 for the subscriber so the client has not received the message.

### *Test 4.7: Same as above, except QoS = 2 for both clients*

In this case, the QoS level of the publisher and subscriber does not affect the outcome, since the message will not be stored by the broker due to the publisher's clean_session flag being set to true.

### *Clean Session = True, QoS = 0 (for both clients) and retain = True*

I only received the message once, so the message was not stored by the broker.

## *MQTT Binary Stream Client*

### *How many bytes did the CONNACK message contain? What were their values? What is your interpretation of the byte values in the received CONNACK message? Do they make sense?*
````
0x20  // The first byte of the MQTT message (CONNACK Command Type and Control Flags)
0x02  // The length of the remaining packet -> Connection Refused
0x00  
0x82  
````
