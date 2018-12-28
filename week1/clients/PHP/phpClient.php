<?php
  global $argc, $argv;
  // var_dump($argc);
  // var_dump($argv);

  $person = ($argc==1) ? "Jos Bos" : $argv[1]; 
  $client = new SoapClient('http://127.0.0.1:8888/Student?wsdl', array('trace'=>1));

  print "FUNCTIONS OF THE SERVICE:\n"; 
  var_dump ($client->__getFunctions());

  print "\nSOAP CALL:\n";
  $client->__soapCall('getStudent', array(array('arg0'=>$person)));
  var_dump ($client->__getLastRequest());

  print "\nSOAP RESPONSE:\n";
  var_dump ($client->__getLastResponse());

  print "\n\n";
?>

