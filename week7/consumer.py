import time
import sys
import logging
import stomp
from stomp import ConnectionListener
import xml.etree.ElementTree as ET

def create_person_dict(xml):
    rv = {}
    f = ET.fromstring(xml)
    rv['name'] = f.find('name').text
    rv['password'] = f.find('code').text
    return rv

# queuename = sys.argv[1] 
queuename = "queue/demo"
logging.basicConfig( level=logging.DEBUG)

class Consumer(stomp.ConnectionListener):
    def on_error(self, headers, message):
        print ("ERROR")
        print ('received an error %s' % message)

    def on_message(self, headers, message):
        #VUL DEZE METHODE AAN
        userCred = str(message)
        person = create_person_dict(userCred)
        print(person)


#VUL HIER DE INFRASTRUCTUUR AAN
conn = stomp.Connection([('localhost', 61613)])
conn.set_listener('', Consumer())  #Hier wordt een Consumer-object geïnjecteerd in de set-
conn.start()
conn.connect()
conn.subscribe(destination=queuename, id=1, ack='auto') 

while 1:
    time.sleep(2)

