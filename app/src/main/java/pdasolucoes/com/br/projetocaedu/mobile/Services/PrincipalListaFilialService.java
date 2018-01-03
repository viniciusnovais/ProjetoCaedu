package pdasolucoes.com.br.projetocaedu.mobile.Services;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

import pdasolucoes.com.br.projetocaedu.mobile.Model.Filial;

/**
 * Created by PDA on 28/09/2017.
 */

public class PrincipalListaFilialService {

    private static String URL = "http://172.16.6.59:8081/papafila/wsfilial.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/ListaDeFiliais";

    private static String NAMESPACE = "http://tempuri.org/";

    public static List<Filial> listaFilialWS(String codigo) {

        SoapObject request = new SoapObject(NAMESPACE, "ListaDeFiliais");
        SoapObject response;

        List<Filial> lista = new ArrayList<>();

        try {

            PropertyInfo codigoInfo = new PropertyInfo();
            codigoInfo.setName("_codigo");
            codigoInfo.setValue(codigo);
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
                Filial f = new Filial();

                f.setCodigo(Integer.parseInt(item.getProperty("Codigo").toString()));
                f.setEmail(item.getProperty("Email").toString());
                f.setNome(item.getProperty("Nome").toString());

                lista.add(f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
