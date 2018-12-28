using System;

namespace SoapClient
{
    using System;
    using System.IO;
    using System.Net;
    using System.Xml;

    namespace UsingSOAPRequest
    {
        public class Program
        {
            static void Main(string[] args)
            {
                //creating object of program class to access methods  
                Program obj = new Program();
                Console.WriteLine("Wie wil je begroeten?");
                String naam = Convert.ToString(Console.ReadLine());
                obj.InvokeService(naam);
            }
            public void InvokeService(String naam)
            {
                //Calling CreateSOAPWebRequest method  
                HttpWebRequest request = CreateSOAPWebRequest();

                XmlDocument SOAPReqBody = new XmlDocument();

                SOAPReqBody.LoadXml(@"<soapenv:Envelope xmlns:soapenv=""http://schemas.xmlsoap.org/soap/envelope/"" xmlns:week=""http://week1.hanze.nl/"">
                                          <soapenv:Header/>
                                          <soapenv:Body>
                                              <week:getStudent>
                                                  <!--Optional:-->
                                                      <arg0>" + naam + @"</arg0>
                                              </week:getStudent>
                                          </soapenv:Body>
                                      </soapenv:Envelope>");

                Console.WriteLine("\n");
                Console.WriteLine(SOAPReqBody.InnerXml);
                Console.WriteLine("\n");

                using (Stream stream = request.GetRequestStream())
                {
                    SOAPReqBody.Save(stream);
                }
                //Geting response from request  
                using (WebResponse Serviceres = request.GetResponse())
                {
                    using (StreamReader rd = new StreamReader(Serviceres.GetResponseStream()))
                    {
                        //reading stream  
                        var ServiceResult = rd.ReadToEnd();
                        //writting stream result on console  
                        Console.WriteLine(ServiceResult);
                        Console.ReadLine();
                    }
                }
            }

            public HttpWebRequest CreateSOAPWebRequest()
            {
                //Making Web Request  
                HttpWebRequest Req = (HttpWebRequest)WebRequest.Create(@"http://127.0.0.1:8888/Student");
                //SOAPAction  
                Req.Headers.Add(@"SOAPAction:""http://tempuri.org/Addition""");
                //Content_type  
                Req.ContentType = "text/xml;charset=\"utf-8\"";
                Req.Accept = "text/xml";
                //HTTP method  
                Req.Method = "POST";
                //return HttpWebRequest  
                return Req;
            }
        }
    }
}
