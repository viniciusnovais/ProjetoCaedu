package pdasolucoes.com.br.projetocaedu.mobile.Services;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by PDA on 28/09/2017.
 */

public class PrincipalLoginService {

    private static String URL = "http://172.16.6.59:8081/papafila/wsautenticacao.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/Login";

    private static String NAMESPACE = "http://tempuri.org/";

    public static SoapObject autenticacoWS(String login, String senha) {

        SoapObject request = new SoapObject(NAMESPACE, "Login");
        SoapObject response = new SoapObject();

        try {

            PropertyInfo loginInfo = new PropertyInfo();
            loginInfo.setName("_login");
            loginInfo.setValue(login);
            loginInfo.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(loginInfo);

            PropertyInfo senhaInfo = new PropertyInfo();
            senhaInfo.setName("_senha");
            senhaInfo.setValue(senha);
            senhaInfo.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(senhaInfo);


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            response = (SoapObject) soapEnvelope.getResponse();

            Log.w("res", response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}