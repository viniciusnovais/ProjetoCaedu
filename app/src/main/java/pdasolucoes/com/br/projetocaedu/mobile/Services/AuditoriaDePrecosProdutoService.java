package pdasolucoes.com.br.projetocaedu.mobile.Services;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.w3c.dom.Document;

import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import pdasolucoes.com.br.projetocaedu.mobile.Model.Filial;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Produto;

/**
 * Created by PDA on 28/09/2017.
 */

public class AuditoriaDePrecosProdutoService {

    private static String URL = "http://172.16.6.59:8081/papafila/wsproduto.asmx";

    private static String SOAP_ACTION = "http://tempuri.org/ConsultaProdutoSortimento";

    private static String NAMESPACE = "http://tempuri.org/";

    public static List<Produto> getProdutoWS(String codigoFilial, String codigoBarra, String usuario) {

        SoapObject request = new SoapObject(NAMESPACE, "ConsultaProdutoSortimento");
        SoapObject response;

        List<Produto> lista = new ArrayList<>();

        try {

            PropertyInfo codFilialInfo = new PropertyInfo();
            codFilialInfo.setName("_filial");
            codFilialInfo.setValue(codigoFilial);
            codFilialInfo.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(codFilialInfo);

            PropertyInfo codBarraInfo = new PropertyInfo();
            codBarraInfo.setName("_codigoBarra");
            codBarraInfo.setValue(codigoBarra);
            codBarraInfo.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(codBarraInfo);

            PropertyInfo codUsuarioInfo = new PropertyInfo();
            codUsuarioInfo.setName("_usuario");
            codUsuarioInfo.setValue(usuario);
            codUsuarioInfo.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(codUsuarioInfo);

            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            response = (SoapObject) soapEnvelope.getResponse();




            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document document1 = docBuilder.newDocument();

            Document document2 = docBuilder.parse( response.toString() );

            Log.w("produto",response.toString());

            SoapObject item;

//            for (int i = 0; i < response.getPropertyCount(); i++) {
//                item = (SoapObject) response.getProperty(i);
//                for (int j = 0; j < item.getPropertyCount(); j++) {
//                    Filial f = new Filial();
//
//                    f.setCodigo(Integer.parseInt(item.getProperty("Codigo").toString()));
//                    f.setEmail(item.getProperty("Email").toString());
//                    f.setNome(item.getProperty("Nome").toString());
//
//                    lista.add(f);
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
