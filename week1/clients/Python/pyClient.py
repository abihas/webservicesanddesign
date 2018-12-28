#!/usr/bin/env python3

import sys
from suds.client import Client

wsdl = "http://127.0.0.1:8888/Student?wsdl"

if (len(sys.argv)==1): person="Jos Bos"
else: person = sys.argv[1]

_client = Client(wsdl)
print(_client.service.getStudent(person))
