package pdasolucoes.com.br.projetocaedu.mobile.Services;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import pdasolucoes.com.br.projetocaedu.mobile.Model.Filial;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Sistemas;

/**
 * Created by PDA on 03/10/2017.
 */

public class PrincipalListaSistemasService {

    private static String URL = "http://172.16.6.59:8081/papafila/wsautenticacao.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/ListaDeSistemas ";

    private static String NAMESPACE = "http://tempuri.org/";

    public static List<Sistemas> listaSistemasWS(String perfil) {

        SoapObject request = new SoapObject(NAMESPACE, "ListaDeSistemas ");
        SoapObject response;

        List<Sistemas> lista = new ArrayList<>();

        try {

            PropertyInfo codigoInfo = new PropertyInfo();
            codigoInfo.setName("_perfil");
            codigoInfo.setValue(perfil);
            codigoInfo.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(codigoInfo);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            response = (SoapObject) soapEnvelope.getResponse();

            SoapObject item;

            for (int i = 0; i < response.getPropertyCount(); i++) {
                item = (SoapObject) response.getProperty(i);
                Sistemas s = new Sistemas();

                s.setCodigo(Integer.parseInt(item.getProperty("Codigo").toString()));
                s.setNome(item.getProperty("Nome").toString());
                s.setSigla(item.getProperty("Sigla").toString());

                lista.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
