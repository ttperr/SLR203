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

## *Q2: Q2: Can you figure-out how to change your connection's configuration values ? E.g. change the KeepAliveInterval to 70s.*

Yes, Manage connections -> detailed -> KeepAliveInterval

## *Q3: can you check that the message was sent correctly? (e.g. by checking the message's content, or by checking the message's properties)*

Yes, in the window below.

## *Q4: how can you tell whether the subscription was established correctly ?*

Also in the window below.
