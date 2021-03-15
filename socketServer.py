import socket 
import os
import struct
FORMAT = "utf-8"      

def sub_server(indirizzo, backlog=1): # blacklog quante richieste può accettare  
    try:
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind(indirizzo) # collegamento dell socket server al'ip della macchina che lo ospita 
        s.listen(backlog) # mi metto in ascolto di richieste specificando il backlog, cioè quante ne posso gestire 
        print("server initializated, listening...")
    except socket.error as error:
        print(f"server crash {error}")
        print("try to rerun the server")
        sub_server(indirizzo, backlog=1)
        
    count = 0
    path = os.path.dirname(os.path.abspath(__file__)) + '\imagesReceived'
    print(path)
    # path = 'D:\GroupProject\AndroidStudioProjects\GP-main\imagesReceived'
    while True:
        conn, indirizzo_client = s.accept() # accetto la richiesta di un client, 
                                            # funzione che ritorna la connessione (il socket del client) e l'inidrizzo del client 
        
        string = ''
        buf = bytes(string, 'utf-8')
        while len(buf)<4:
            buf += conn.recv(4 - len(buf))
        size = struct.unpack('!i', buf)
        print("receiving %s bytes" % size)
        
        filename = path + '\image'+str(count)+'.png'
        #filename = path + '\image.png'
        with open(filename, 'wb') as file:
            while True:
                data = conn.recv(1024)
                if not data:
                    break
                file.write(data)
        print("Image received!")
        count = count+1
        file.close()  
        
    conn.close() 

if __name__ == "__main__":
    sub_server(("192.168.1.91",8888)) #  "" = prende l'IP della macchina su cui sta girando però la porta deve essere specificata  