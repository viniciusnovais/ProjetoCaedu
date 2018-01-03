package pdasolucoes.com.br.projetocaedu.mobile.Services;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import pdasolucoes.com.br.projetocaedu.mobile.Model.Filial;
import pdasolucoes.com.br.projetocaedu.mobile.Model.Produto;

/**
 * Created by PDA on 11/10/2017.
 */

public class AuditoriaPrecosMelhoresPioresService {

    private static String URL = "http://servicebus.grupopalma.com.br:57772/csp/sistemas/Sistemas.MelhoresPiores.MelhoresPioresSOAPService.cls";
    private static String NAMESAPCE = "http://www.grupopalma.com.br";
    private static String SOAP_ACTION = "http://www.grupopalma.com.br/ConsultarMelhoresPiores";

    public static String[] ConsultaMelhoresPiores(String loja, String produto) {


        SoapObject request = new SoapObject(NAMESAPCE, "ConsultarMelhoresPiores");
        String[] resposta = new String[3];
        SoapObject response;

        try {


            PropertyInfo pLoja = new PropertyInfo();
            pLoja.setName("pLoja");
            pLoja.setValue(loja);
            pLoja.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(pLoja);

            PropertyInfo pProduto = new PropertyInfo();
            pProduto.setName("pProduto");
            pProduto.setValue(produto);
            pProduto.setType(PropertyInfo.STRING_CLASS);

            request.addProperty(pProduto);


            SoapSerializationEnvelope soapEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            soapEnvelope.dotNet = true;
            soapEnvelope.setOutputSoapObject(request);

            HttpTransportSE transport = new HttpTransportSE(URL);

            transport.call(SOAP_ACTION, soapEnvelope);
            response = (SoapObject) soapEnvelope.getResponse();

            if (!response.getPropertyAsString("Status").equals("NOK")) {
                resposta[0] = response.getPropertyAsString("statusLoja");
                resposta[1] = response.getPropertyAsString("statusLoja");
                resposta[2] = response.getPropertyAsString("statusEmpresa");
            } else {
                resposta[0] = "-";
                resposta[1] = "-";
                resposta[2] = "-";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return resposta;
    }
}
