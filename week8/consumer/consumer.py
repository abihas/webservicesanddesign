import logging
import sys
import time
import xml.etree.ElementTree as ET

import stomp
from stomp import ConnectionListener

import User


def create_person_dict(xml):
    rv = {}
    f = ET.fromstring(xml)
    rv['name'] = f.find('name').text
    rv['password'] = f.find('code').text
    return rv

if len(sys.argv) > 1:
    queuename = sys.argv[1] 
else:
    queuename = "queue/demo"

logging.basicConfig( level=logging.ERROR)

class Consumer(stomp.ConnectionListener):
    def on_error(self, headers, message):
        print ("ERROR")
        print ('received an error %s' % message)

    def on_message(self, headers, message):
        #VUL DEZE METHODE AAN
        userCred = str(message)
        person = create_person_dict(userCred)

        newUser = User.User(username = person["name"], password = person["password"])
        User.activeSession.add(newUser)
        User.activeSession.commit()


#VUL HIER DE INFRASTRUCTUUR AAN
conn = stomp.Connection([('localhost', 61613)])
conn.set_listener('', Consumer())  #Hier wordt een Consumer-object geïnjecteerd in de set-
conn.start()
conn.connect()

conn.subscribe(destination=queuename, id=1, ack='auto') 
while 1:
    time.sleep(2)
