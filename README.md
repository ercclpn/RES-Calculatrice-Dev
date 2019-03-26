# PROTOCOL Specification
Author : Tran Eric	
Date : 18.03.2019

1. What transport protocol do we use?	
   We use the TCP protocol.

2. How does the client find the server (addresses and ports)?	
   The client find the server using the following syntax : address port	
   Exemple : 172.100.0.10 3636

3. Who speaks first?
   The client.

4. What is the sequence of messages exchanged by the client and the server?
   *	Instruction to calculate from the client.
   *	Computing and answer by the server to the client.

   Exemple:
   1.	Client : 5 + 5
   2.	Server : Answer=10
   
5. What happens when a message is received from the other party?
   The server store the message and the ip from the client in a queue. 

6. What is the syntax of the messages? How we generate and parse them?
   Client : The syntax is "A" and "operator" and "B".	
   Exemple : 5 + 5

   Server : The syntax of the server is "Answer=X"	
   Exemple : Answer=10

   All the informations are send as a string.

7. Who closes the connection and when?
   The server close the connection when it send the answer. The connection is not keep.
